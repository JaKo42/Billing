
import java.sql.*;


public class DBConnection {

    public void estConnection() throws SQLException{



        Connection conn = DriverManager.getConnection("jdbc:sqlite:Artikel.db");
        Statement stat = conn.createStatement();
        stat.execute("create table if NOT exists article(name, price);");
        PreparedStatement insArticle = conn.prepareStatement("insert into article values (?, ?);");

            insArticle.setString(1, "Käse");
            insArticle.setDouble(2,2.99 );
            insArticle.addBatch();

            insArticle.setString(1, "Zucchini");
            insArticle.setDouble(2,0.89 );
            insArticle.addBatch();

            insArticle.setString(1, "Tomate KG");
            insArticle.setDouble(2,0.99 );
            insArticle.addBatch();

            insArticle.setString(1, "Salami");
            insArticle.setDouble(2,3.00 );
            insArticle.addBatch();

            insArticle.setString(1, "Energy Drink");
            insArticle.setDouble(2,0.69 );
            insArticle.addBatch();

            insArticle.setString(1, "Karotten");
            insArticle.setDouble(2,1.99 );
            insArticle.addBatch();

//            TODO: Auf dublikate prüfen
            ResultSet dublicateProof = stat.executeQuery("select name from article");

            if ()
                    {
            conn.setAutoCommit(false);
            insArticle.executeBatch();
            conn.setAutoCommit(true);
        }

        ResultSet selectArticle = stat.executeQuery("select * from article;");
            while (selectArticle.next()){
                System.out.println("Artikel= " + selectArticle.getString("name"));
                System.out.println("Preis= " + selectArticle.getDouble("price")+" €");
            }

            stat.execute("drop table article;");
            selectArticle.close();
            conn.close();

    }

}
