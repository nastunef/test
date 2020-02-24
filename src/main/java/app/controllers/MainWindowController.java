package app.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.AddProjectWindow;
import app.AddTaskWindow;
import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;



public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink addTaskHyperlink;

    @FXML
    private Hyperlink addProjectHyperlink;

    @FXML
    private Hyperlink myProjectHyperlink;

    @FXML
    private Hyperlink todayHyperlink;

    @FXML
    private Hyperlink tomorrowHyperlink;

    @FXML
    private Hyperlink dailyPlannerHyperlink;


    @FXML
    private Label planeTitle;

    @FXML
    private Pane printInformationPane;

    @FXML
    private Hyperlink addCurriculum;

    @FXML
    void initialize() throws SQLException {
        addProjectHyperlink.setOnAction(event ->  addProject() );
        addTaskHyperlink.setOnAction(event -> {
            try {
                addTask();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        todayHyperlink.setOnAction(event -> {
        });
        tomorrowHyperlink.setOnAction(event -> {
        });
        dailyPlannerHyperlink.setOnAction(event -> {
        });
        myProjectHyperlink.setOnAction(event -> {
        });
        addCurriculum.setOnAction(event -> {
        });

        initPlan();

    }

    private void addTask() throws SQLException {
        AddTaskWindow atw = new AddTaskWindow();
        atw.show();
    }

    private void addProject() {
        AddProjectWindow apw = new AddProjectWindow();
        apw.show();
    }

    private void initPlan() throws SQLException {
        DatabaseHandler dbHandler  = new DatabaseHandler();
        dbHandler.getPlan();
    }
}