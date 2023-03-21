package mh.easyindy.gui.controller;

import mh.easyindy.indy.model.IndyCredentials;
import mh.easyindy.indy.Indy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mh.easyindy.gui.service.SceneManager;
import java.io.IOException;

public class LoginController {


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordFIeld;

    @FXML
    private Label infoLabel;

    @FXML
    protected void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordFIeld.getText();
        Indy indy = Indy.getInstance();
        indy.setCredentials(new IndyCredentials(username, password));
        try {
            indy.login();
        } catch(Exception e) {
            System.out.println("Couldn´t connect to Indy");
        }

        if (indy.loggedIn()) {
            SceneManager.changeScene(event, "booking.fxml", "IndyBot", indy);
        } else {
            infoLabel.setText("Wrong credentials!");
        }
    }

}