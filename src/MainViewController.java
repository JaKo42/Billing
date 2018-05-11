import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainViewController extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception {


    Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
    primaryStage.setTitle("Artikeldatenbank");
    primaryStage.setScene(new Scene(root, 700, 500));

    primaryStage.show();
  }


}
