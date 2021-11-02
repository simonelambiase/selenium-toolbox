package persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import persistence.PersistenceManager;

import java.io.File;

public class PersistenceManagerJSON implements PersistenceManager {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File storageDirectory;

    public PersistenceManagerJSON( String directoryPath ) {
        storageDirectory = new File(directoryPath);
    }


    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public Object getConnection() {
        return gson;
    }

    @Override
    public void saveObject(Object o) {
    }

    @Override
    public Object loadObject(Object id, Class returnType ) {
        return null;
    }


    @Override
    public Object executeQuery(String query) {
        return null;
    }
}
