
import java.sql.*;

public class DBConnection {

    public void initDatabase() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");
        Statement stat = conn.createStatement();
        System.out.println("Initialize Database...");
        stat.execute("create table if NOT exists article(name, price);");
        PreparedStatement insArticle = conn.prepareStatement("insert into article values (?, ?);");
        ResultSet firstInitCheck = stat.executeQuery("select name from article");


        if (!firstInitCheck.next()) {
            insArticle.setString(1, "Käse");
            insArticle.setDouble(2, 2.99);
            insArticle.addBatch();

            insArticle.setString(1, "Zucchini");
            insArticle.setDouble(2, 0.89);
            insArticle.addBatch();

            insArticle.setString(1, "Tomate KG");
            insArticle.setDouble(2, 0.99);
            insArticle.addBatch();

            insArticle.setString(1, "Salami");
            insArticle.setDouble(2, 3.00);
            insArticle.addBatch();

            insArticle.setString(1, "Energy Drink");
            insArticle.setDouble(2, 0.69);
            insArticle.addBatch();

            insArticle.setString(1, "Karotten");
            insArticle.setDouble(2, 1.99);
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
//            TODO: mehrere auf einmal hinzufügen Varargs? Arrays?...
    public void insertArticle(String name, double price) throws SQLException{

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");
        Statement stat = conn.createStatement();

        PreparedStatement insArt = conn.prepareStatement("insert into article values (?, ?);");

        insArt.setString(1,name);
        insArt.setDouble(2,price);
        insArt.execute();
        System.out.println("Artikel wurde angelegt!");

        conn.close();

    }

    public void removeArticle (String name) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");
        Statement stat = conn.createStatement();

        PreparedStatement remArt = conn.prepareStatement("DELETE FROM article WHERE name= ?;");
        remArt.setString(1, name);
        remArt.execute();




        conn.close();

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
