package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import patterns.Plan;
import patterns.User;

public class AuthSigInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginFild;

    @FXML
    private PasswordField passwordFild;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String loginText = loginFild.getText().trim();
            String loginPassword = passwordFild.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")){
                try {
                    loginUser(loginText,loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        loginSignUpButton.setOnAction(event -> openNewScene("/fxml/signUp.fxml"));


    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result =  dbHandler.getUser(user);
        int counter = 0;

        while(true){
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if(counter == 1){

            Plan plan =  Plan.getInstance();
            plan.setNameUser(loginText);
            result.beforeFirst();
            while(result.next()){
                plan.setIdUser(result.getString(1));

            }
            openNewScene("/fxml/mainWindow.fxml");
        }
    }

    public void openNewScene(String window){
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root= loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
