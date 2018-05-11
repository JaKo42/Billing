import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main extends Application {

  private static DBConnection conn = new DBConnection();
  private static Scanner insert = new Scanner(System.in);


  public static void main(String[] args) {
    launch(args);


    System.out.println("Belegleser Version 0.1");
    System.out.println("start...");

//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    try {
      conn.initDatabase();
      conn.printArticle();
      ablauf();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  private static void ablauf() throws SQLException {

    System.out.println("Möchten Sie einen neuen Artikel eingeben?");
    String answer = insert.next();
    switch (answer) {
      case "ja":
      case "Ja":
        System.out.println("Geben Sie Name und Preis ein: ");
        conn.createArticle(insert.next(), insert.nextDouble());
        break;
      case "nein":
      case "Nein":
        break;
    }
    System.out.println("Möchten Sie einen Artikel löschen?");
    answer = insert.next();
    if (answer.equals("ja")) {
      System.out.println("Geben Sie den Name des zu löschenden Artikels ein : ");
      String articleName = insert.next();
      System.out.println("Sind Sie sicher das Sie " + articleName + " löschen wollen?");
      answer = insert.next();
      if (answer.equals("ja")) {
        conn.removeArticle(articleName);
      }
    }

  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    GridPane grid = new GridPane();
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setPadding(new Insets(10, 10, 10, 10));

    Scene mainScene = new Scene(grid, 400, 500);

    primaryStage.setTitle("Artikelstamm");
    primaryStage.setScene(mainScene);

    Label greeting = new Label("Artikelstamm");
    grid.add(greeting, 1, 1, 1, 1);


    primaryStage.show();
  }


}
