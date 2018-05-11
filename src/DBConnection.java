
import java.sql.*;

public class DBConnection {

    public void initDatabase() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");
        Statement stat = conn.createStatement();
        System.out.println("Initialize Database...");
        stat.execute("create table if NOT exists article(name, price);");
        PreparedStatement insArticle = conn.prepareStatement("insert into article values (?, ?);");
        ResultSet firstInitCheck = stat.executeQuery("select name from article");

        Article kaese = new Article("Käse", 2.99);
        Article zucchini = new Article("Zucchini", 0.89);
        Article tomate = new Article("Tomate KG", 0.99);
        Article salami = new Article("Salami", 3.00);
        Article energy = new Article("Energy Drink", 0.69);
        Article karotten = new Article("Karotten", 1.99);

        if (!firstInitCheck.next()) {
            insArticle.setString(1, kaese.getName());
            insArticle.setDouble(2, kaese.getPrice());
            insArticle.addBatch();

            insArticle.setString(1, zucchini.getName());
            insArticle.setDouble(2, zucchini.getPrice());
            insArticle.addBatch();

            insArticle.setString(1, tomate.getName());
            insArticle.setDouble(2, tomate.getPrice());
            insArticle.addBatch();

            insArticle.setString(1, salami.getName());
            insArticle.setDouble(2, salami.getPrice());
            insArticle.addBatch();

            insArticle.setString(1, energy.getName());
            insArticle.setDouble(2, energy.getPrice());
            insArticle.addBatch();

            insArticle.setString(1, karotten.getName());
            insArticle.setDouble(2, karotten.getPrice());
            insArticle.addBatch();


            conn.setAutoCommit(false);
            insArticle.executeBatch();
            conn.setAutoCommit(true);
        } else System.out.println("Database already set!");
        conn.close();
    }

    public void printArticle() throws SQLException{

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");
        Statement stat = conn.createStatement();

        ResultSet selectArticle = stat.executeQuery("select * from article;");
            while (selectArticle.next()){
                System.out.println("Artikel= " + selectArticle.getString("name"));
                System.out.println("Preis= " + selectArticle.getDouble("price")+" €");

            }


            selectArticle.close();
            conn.close();

    }

    public void createArticle(String name, double price){

        Article article = new Article(name, price);
        try {
            insertArticle(article);
        } catch (SQLException e) {
            System.out.println("SQL Exception beim einfügen in die Datenbank");
            e.printStackTrace();
        }

    }


    public void insertArticle(Article article) throws SQLException{

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");

        PreparedStatement insArt = conn.prepareStatement("insert into article values (?, ?);");

        insArt.setString(1,article.getName());
        insArt.setDouble(2,article.getPrice());
        insArt.execute();
        System.out.println("Artikel wurde angelegt!");

        conn.close();

    }

    public void removeArticle (String name) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");

        PreparedStatement remArt = conn.prepareStatement("DELETE FROM article WHERE name= ?;");
        remArt.setString(1, name);
        remArt.execute();
        System.out.println("Artikel wurde gelöscht!");




        conn.close();

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
