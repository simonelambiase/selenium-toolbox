package demo;

import persistence.PersistenceManager;
import persistence.mysql.PersistenceManagerMariaDB;
import persistence.mysql.PersistenceManagerSQLite;

import javax.persistence.*;

public class Runner {

    public static void main(String[] args) {
        PersistenceManager sql = new PersistenceManagerSQLite("resources/test");
        sql.connect();
        sql.saveObject(new WebPage("Amazon","www.amazon.it"));
        System.out.println(sql.executeQuery("FROM demo.WebPage"));
    }
}

@Entity
@Table ( name = "web_page")
class WebPage {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int id;

    @Column ( name = "page_name")
    private String pageName;

    @Column ( name = "page_url")
    private String pageUrl;

    public WebPage() {

    }

    public WebPage(int id, String pageName, String pageUrl) {
        this.id = id;
        this.pageName = pageName;
        this.pageUrl = pageUrl;
    }

    public WebPage(String pageName, String pageUrl) {
        this.pageName = pageName;
        this.pageUrl = pageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "id=" + id +
                ", pageName='" + pageName + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                '}';
    }
}
