package persistence.mysql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import persistence.PersistenceManager;
import utils.LoggerUtils;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PersistenceManagerSQLite implements PersistenceManager {

    private Logger log = LoggerUtils.createLogger(this.getClass());
    private Properties hibernateProperties;
    private Configuration hibernateConfiguration = new Configuration();
    private Session session;
    private boolean alive = false;

    public PersistenceManagerSQLite( String fileName ) {
        this.hibernateProperties = new Properties();
        File sqlLiteFile = new File(fileName.contains(".sqlite") ? fileName : fileName + ".sqlite");
        if ( !sqlLiteFile.exists() ) {
            try {
                sqlLiteFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String connectionString = fileName.contains(".sqlite") ? "jdbc:sqlite:" + fileName : "jdbc:sqlite:" + fileName + ".sqlite";
        this.hibernateProperties.setProperty("hibernate.connection.url",connectionString);
        this.hibernateProperties.setProperty("dialect","org.sqlite.hibernate.dialect.SQLiteDialect");
        this.hibernateProperties.setProperty("hibernate.connection.driver_class","org.sqlite.JDBC");
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
    public Object executeQuery(String query) {
        session.beginTransaction();
        Query hQuery = session.createQuery(query);
        return hQuery.getResultList();
    }
}
