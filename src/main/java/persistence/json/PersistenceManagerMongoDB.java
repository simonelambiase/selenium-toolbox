package persistence.json;

import persistence.PersistenceManager;

public class PersistenceManagerMongoDB implements PersistenceManager {


    /*
    ToDo
     */

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public Object getConnection() {
        return null;
    }

    @Override
    public void saveObject(Object o) {

    }

    @Override
    public Object loadObject(Object id, Class returnType) {
        return null;
    }

    @Override
    public Object loadObject(Class returnType) {
        return null;
    }

    @Override
    public Object executeQuery(String query) {
        return null;
    }
}
