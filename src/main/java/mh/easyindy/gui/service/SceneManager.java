package mh.easyindy.gui.service;


import mh.easyindy.MainApp;
import mh.easyindy.indy.Indy;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {

    public static void changeScene(ActionEvent event, String fxml, String title, Indy indy) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource(fxml));
            Scene scene = new Scene(root);
            stage.setUserData(indy);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    public static Stage getStage() {
        return null;
    }

    public static void newStage(String fxml, String title) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainApp.class.getResource(fxml));
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }

    }

}
