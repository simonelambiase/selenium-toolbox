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
    boolean loadObject ( int id,  Class objectType );
    boolean loadObject ( String key, Class objectType );
    void executeQuery ( String query );
}
