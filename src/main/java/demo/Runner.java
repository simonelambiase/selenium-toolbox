package demo;

import demo.entities.WebPage;
import persistence.PersistenceManager;
import persistence.mysql.PersistenceManagerSQLite;

public class Runner {

    public static void main(String[] args) {
        PersistenceManager sql = new PersistenceManagerSQLite("resources/test");
        sql.connect();
        sql.saveObject(new WebPage("Amazon","www.amazon.it"));
        System.out.println(sql.executeQuery("DROP WebPage"));
    }
}

