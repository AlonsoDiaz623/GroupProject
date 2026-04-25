/**
 * Date: 4/25/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * Purpose: Launches the JavaFX application and loads the main FXML
 */
package application;

//Import JavaFX classes
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //Mian class should extend application
    @Override
    public void start(Stage stage) throws Exception {
        //Creates an FXML loader and points it to the Simon.fxml file
        //FXML layout gets wrapped inside a Scene
        //Lastly sets title,scene, and show
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Simon.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Simon Memory Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //Starts everything
        launch(args);
    }
}