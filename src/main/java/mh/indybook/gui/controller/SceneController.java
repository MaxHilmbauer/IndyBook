package mh.indybook.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void switchScene(ActionEvent event, String fxmlFile, String sceneTitle) throws IOException {
        Parent root;
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(sceneTitle);
            stage.getIcons().removeAll(stage.getIcons());
            stage.setScene(new Scene(root, 1440, 860));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
