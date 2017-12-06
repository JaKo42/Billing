import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class Main {




    public static void main(String[] args){


        Scanner insert = new Scanner(System.in);

        System.out.println("Belegleser Version 0.1");
        System.out.println("start...");

//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        DBConnection conn = new DBConnection();
        try {
            conn.initDatabase();
            conn.printArticle();
            System.out.println("Möchten Sie einen neuen Artikel eingeben?");
            String answer = insert.next();
            switch(answer) {
                case "ja":
                    System.out.println("Geben Sie Name und Preis ein: ");
                    conn.insertArticle(insert.next(), insert.nextDouble());
                    break;
                case "nein":
                    break;
            }
            System.out.println("Möchten Sie einen Artikel löschen?");
            answer = insert.next();
            if (answer.equals("ja")) {
                System.out.println("Geben Sie Name und Preis ein: ");
                conn.removeArticle(insert.next());
                conn.printArticle();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
