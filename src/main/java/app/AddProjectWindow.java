package app;

import database.DatabaseHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.Plan;
import patterns.Project;

import java.sql.SQLException;

public class AddProjectWindow extends ChildWindow {

    public void show(){

        Stage newWindow = new Stage();

        AnchorPane secondaryLayout = new AnchorPane();
        Scene secondScene = new Scene(secondaryLayout, 300, 150);
        TextField nameProject = new TextField();

        Label labelInputName = new Label();
        labelInputName.setPrefSize(130,20);
        labelInputName.setText(TITLE_INPUT_NAME_PROJECT);

        Button buttonAdd = new Button();
        buttonAdd.setText(CREATE_BUTTON);

        buttonAdd.setOnAction(event -> {
            String nameNewProject = nameProject.getText();
            if(nameNewProject.equals(STRING_EMPTY)){
                labelInputName.setText(ERROR_EMPTY_NAME_PROJECT);
            }
            else {
                try {
                    addNewProject(nameNewProject);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                newWindow.close();
            }
        });

        AnchorPane.setTopAnchor(labelInputName, 20.0);
        AnchorPane.setLeftAnchor(labelInputName, 40.0);
        AnchorPane.setRightAnchor(labelInputName, 50.0);

        AnchorPane.setTopAnchor(nameProject, 50.0);
        AnchorPane.setLeftAnchor(nameProject, 40.0);
        AnchorPane.setRightAnchor(nameProject, 50.0);

        AnchorPane.setTopAnchor(buttonAdd, 90.0);
        AnchorPane.setLeftAnchor(buttonAdd, 50.0);
        AnchorPane.setRightAnchor(buttonAdd, 70.0);

        secondaryLayout.getChildren().add(nameProject);
        secondaryLayout.getChildren().add(labelInputName);
        secondaryLayout.getChildren().add(buttonAdd);

        newWindow.setTitle(TITLE_ADD_PROJECT);
        newWindow.setScene(secondScene);

        newWindow.setX(500);
        newWindow.setY(200);
        newWindow.show();
    }

    public void addNewProject(String nameNewProject) throws SQLException {
        Plan plan = Plan.getInstance();
        addInDataBase(plan,nameNewProject);
        Project newProject = new Project("id", nameNewProject,plan.getIdUser());
        plan.addProject(newProject);
    }

    public void addInDataBase(Plan plan, String nameNewProject) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.addProject(plan,nameNewProject);
    }


}
