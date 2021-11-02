package demo;

import demo.demo_entities.WebPage;
import persistence.PersistenceManager;
import persistence.mysql.PersistenceManagerMariaDB;
import persistence.mysql.PersistenceManagerMySQL;

public class Runner {

    public static void main(String[] args) {
        PersistenceManager sql = new PersistenceManagerMariaDB("localhost","test","test","selenium_test");
        sql.connect();
        sql.saveObject(new WebPage("Google","www.google.com"));
        WebPage p = (WebPage) sql.loadObject("13",WebPage.class);
        System.out.println(p);
    }
}
