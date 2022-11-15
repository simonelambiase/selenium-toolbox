package persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import persistence.PersistenceManager;
import utils.LoggerUtils;

import java.io.*;

public class PersistenceManagerJSON implements PersistenceManager {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File storageDirectory;
    private File jsonFile;
    private Logger log = LoggerUtils.createLogger(this.getClass());


    public PersistenceManagerJSON(String directoryPath, String fileName) {
        if (!fileName.contains(".json")) {
            fileName = fileName + ".json";
        }
        if (directoryPath.charAt(directoryPath.length() - 1) == '/' || directoryPath.charAt(directoryPath.length() - 1) == '\\') {
            directoryPath = directoryPath.substring(directoryPath.length() - 2);
        }
        storageDirectory = new File(directoryPath);
        jsonFile = new File(directoryPath + File.separator + fileName);
    }


    @Override
    public boolean connect() {
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean close() {
        log.error("This method is not currently supported for this class " + this.getClass().getName());
        return true;
    }

    @Override
    public boolean isAlive() {
        return jsonFile.exists();
    }

    @Override
    public Object getConnection() {
        return gson;
    }

    @Override
    public void saveObject(Object o) {
        if ( !isAlive() ) {
            connect();
        }
        try (FileWriter wr = new FileWriter(jsonFile)) {
            gson.toJson(o, wr);
            wr.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object loadObject(Object id, Class returnType) {
        try {
            return gson.fromJson(new FileReader(jsonFile), returnType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object loadObject(Class returnType) {
        try {
            return gson.fromJson(new FileReader(jsonFile), returnType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Object executeQuery(String query) {
        log.error("This method is not currently supported for this class " + this.getClass().getName());
        return null;
    }
}
