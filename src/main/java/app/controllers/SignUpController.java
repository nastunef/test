package app.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import patterns.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginFild;

    @FXML
    private PasswordField passwordFild;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            try {
                signUpNewUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void signUpNewUser() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = loginFild.getText();
        String password = passwordFild.getText();

        User user = new User(firstName,lastName,password,userName);
        dbHandler.signUpUser(user);

    }
}
