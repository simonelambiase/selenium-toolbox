package demo;

import demo.demo_entities.WebPage;
import persistence.PersistenceManager;
import persistence.mysql.PersistenceManagerMariaDB;
import persistence.mysql.PersistenceManagerMySQL;

public class Runner {

    public static void main(String[] args) {
        PersistenceManager sql = new PersistenceManagerMariaDB("simonelambiase.it","test","simone23.","selenium_test");
        sql.connect();
        sql.saveObject(new WebPage("Google","www.google.com"));
    }
}
