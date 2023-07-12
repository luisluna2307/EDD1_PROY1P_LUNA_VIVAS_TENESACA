package controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String filePathUsers = "src/main/resources/archivos/";
    public static String fileImagesMouths = "src/main/resources/images/mouth/";
    public static String fileImagesEyebrows = "src/main/resources/images/eyebrows/";
    public static String fileImagesAccessories = "src/main/resources/images/accessories/";
    public static String fileImagesFaces = "src/main/resources/images/faces/";
    public static String fileImagesEyes = "src/main/resources/images/eyes/";
    public static String pathEyes = "/informacionPredefinida/pathEyes.txt";
    public static String pathMouths = "/informacionPredefinida/pathMouths.txt";
    public static String pathFaces = "/informacionPredefinida/pathFaces.txt";
    public static String pathEyebrows = "/informacionPredefinida/pathEyebrows.txt";
    public static String pathAccessories = "/informacionPredefinida/pathAccessories.txt";
    public static String pathInterfaz = "/interfaz/";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
