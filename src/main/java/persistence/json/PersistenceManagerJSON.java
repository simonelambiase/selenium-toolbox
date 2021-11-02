package persistence.json;

import com.google.gson.Gson;
import persistence.PersistenceManager;

public class PersistenceManagerJSON implements PersistenceManager {

    private Gson gson;

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
    public Object loadObject(Object id, Class obj) {
        return null;
    }


    @Override
    public void executeQuery(String query) {

    }
}
