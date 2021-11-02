package persistence.json;

import com.google.gson.Gson;
import persistence.PersistenceManager;

public class PersistenceManagerJSON implements PersistenceManager {

    private Gson gson;

    public PersistenceManagerJSON() {

    }

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
    public boolean loadObject(int id, Class objectType) {
        return false;
    }

    @Override
    public boolean loadObject(String key, Class objectType) {
        return false;
    }

    @Override
    public void executeQuery(String query) {

    }
}
