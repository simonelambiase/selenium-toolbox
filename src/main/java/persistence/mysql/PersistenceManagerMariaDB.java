package persistence.mysql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import persistence.PersistenceManager;
import utils.LoggerUtils;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.util.Properties;

public class PersistenceManagerMariaDB implements PersistenceManager {

    private Logger log = LoggerUtils.createLogger(this.getClass());
    private String hostName;
    private String userName;
    private String password;
    private String databaseName;
    private int port;
    private Properties hibernateProperties;
    private Configuration hibernateConfiguration = new Configuration();
    private Session session;
    private boolean alive = false;

    public PersistenceManagerMariaDB(String hostName, String userName, String password, String databaseName) {
        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        this.hibernateProperties = new Properties();
        String connectionString = "jdbc:mysql://$host:$port/$database".replace("$host",hostName).replace("$port","3306").replace("$database",databaseName);
        this.hibernateProperties.setProperty("hibernate.connection.url",connectionString);
        this.hibernateProperties.setProperty("hibernate.connection.username",userName);
        this.hibernateProperties.setProperty("hibernate.connection.password",password);
        this.hibernateProperties.setProperty("dialect","org.hibernate.dialect.MariaDBDialect");
        this.hibernateProperties.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
        this.hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        this.hibernateProperties.setProperty("show_sql","true");
    }

    public PersistenceManagerMariaDB(Logger log, String hostName, String userName, String password, String databaseName, int port) {
        this.log = log;
        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        this.port = port;
        this.hibernateProperties = new Properties();
        String connectionString = "jdbc:mysql://$host:$port/$database".replace("$host",hostName).replace("$port",String.valueOf(port)).replace("$database",databaseName);
        this.hibernateProperties.setProperty("hibernate.connection.url",connectionString);
        this.hibernateProperties.setProperty("hibernate.connection.username",userName);
        this.hibernateProperties.setProperty("hibernate.connection.password",password);
        this.hibernateProperties.setProperty("dialect","org.hibernate.dialect.MariaDBDialect");
        this.hibernateProperties.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
        this.hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        this.hibernateProperties.setProperty("show_sql","true");
    }

    @Override
    public boolean connect() {
        hibernateConfiguration.addProperties(hibernateProperties);
        SessionFactory factory = hibernateConfiguration.buildSessionFactory();
        session = factory.openSession();
        log.info("Opening connection to database...");
        return session.isOpen();
    }

    @Override
    public boolean close() {
        session.close();
        alive = session.isOpen();
        return alive;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Object getConnection() {
        return session;
    }

    @Override
    public void saveObject( Object o) {

        hibernateConfiguration.addAnnotatedClass(o.getClass());
        connect();

        if ( !o.getClass().isAnnotationPresent(Entity.class) ) {
            log.error("You must insert the hibernate annotations to your class for use the persistence manager." + "\nReference link: https://www.tutorialspoint.com/hibernate/hibernate_annotations.htm");
        } else {
            session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
        }
    }

    @Override
    public Object loadObject(Object id, Class returnType ) {
        session.beginTransaction();
        Query query = session.createQuery("FROM " + returnType.getName() + " O WHERE O.id = " + id,returnType);
        return query.getSingleResult();
    }

    @Override
    public Object loadObject(Class returnType) {
        log.error("This method is not currently supported for this class " + this.getClass().getName());
        return null;
    }

    @Override
    public Object executeQuery(String query) {
        session.beginTransaction();
        Query hQuery = session.createQuery(query);
        return hQuery.getSingleResult();
    }

}
