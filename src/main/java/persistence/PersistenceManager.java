package persistence;


/**
 *
 * @Author: Simone Lambiase
 *
 */
public interface PersistenceManager {

    boolean connect();
    boolean close();
    boolean isAlive();
    Object getConnection();
    void saveObject ( Object o );
    Object loadObject (Object id, Class returnType  );
    Object loadObject ( Class returnType  );
    Object executeQuery ( String query );
}
