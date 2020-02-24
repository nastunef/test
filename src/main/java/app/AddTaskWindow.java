package app;

import database.Const;
import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.Plan;
import patterns.Task;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class AddTaskWindow extends ChildWindow {

    public void show() throws SQLException {
        Stage newWindow = new Stage();
        AnchorPane secondaryLayout = new AnchorPane();
        Scene secondScene = new Scene(secondaryLayout, 350, 250);

        Map<String,String> projectId = FillingOutThePlan.getAllProjects();
        ObservableList<String> langs =  getListNameProjects(projectId);

        ChoiceBox<String> langsChoiceBox = new ChoiceBox<>(langs);
        langsChoiceBox.setValue(Const.TASK_NOT_PROJECT);

        final DatePicker datePicker = new DatePicker();

        TextField textTask = new TextField();
        textTask.setPrefSize(200,30);

        Label labeInputName = new Label();
        labeInputName.setPrefSize(130,20);
        labeInputName.setText("Введите задачу");

        Label labelDate =  new Label();
        labelDate.setPrefSize(150,20);
        labelDate.setText("Дата исполнения");

        Label labelProject =  new Label();
        labelProject.setPrefSize(110,20);
        labelProject.setText("Проект");

        Button buttonAdd = new Button();
        buttonAdd.setText(CREATE_BUTTON);
        buttonAdd.setOnAction(event -> {
            String textTaskString = textTask.getText();
            if(!isEmptyString(textTaskString,labeInputName)) {
                try {
                    clickOnCreateTask( langsChoiceBox.getValue(), textTaskString, datePicker.getValue(), projectId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                newWindow.close();
            }
        });

        AnchorPane.setTopAnchor(labelProject, 20.0);
        AnchorPane.setLeftAnchor(labelProject, 40.0);
        AnchorPane.setRightAnchor(labelProject, 50.0);

        AnchorPane.setTopAnchor(langsChoiceBox, 60.0);
        AnchorPane.setLeftAnchor(langsChoiceBox, 40.0);
        AnchorPane.setRightAnchor(langsChoiceBox, 50.0);

        AnchorPane.setTopAnchor(labeInputName, 90.0);
        AnchorPane.setLeftAnchor(labeInputName, 40.0);
        AnchorPane.setRightAnchor(labeInputName, 50.0);

        AnchorPane.setTopAnchor(textTask, 110.0);
        AnchorPane.setLeftAnchor(textTask, 40.0);
        AnchorPane.setRightAnchor(textTask, 50.0);

        AnchorPane.setTopAnchor(labelDate, 140.0);
        AnchorPane.setLeftAnchor(labelDate, 40.0);
        AnchorPane.setRightAnchor(labelDate, 50.0);

        AnchorPane.setTopAnchor(datePicker, 160.0);
        AnchorPane.setLeftAnchor(datePicker, 40.0);
        AnchorPane.setRightAnchor(datePicker, 50.0);

        AnchorPane.setTopAnchor(buttonAdd, 200.0);
        AnchorPane.setLeftAnchor(buttonAdd, 50.0);
        AnchorPane.setRightAnchor(buttonAdd, 70.0);

        secondaryLayout.getChildren().add(textTask);
        secondaryLayout.getChildren().add(labeInputName);
        secondaryLayout.getChildren().add(labelDate);
        secondaryLayout.getChildren().add(labelProject);
        secondaryLayout.getChildren().add(buttonAdd);
        secondaryLayout.getChildren().add(langsChoiceBox);
        secondaryLayout.getChildren().add(datePicker);

        newWindow.setTitle("Добавить задачу");
        newWindow.setScene(secondScene);

        newWindow.setX(500);
        newWindow.setY(200);
        newWindow.show();
    }

    public ObservableList<String> getListNameProjects(Map<String, String> projectId) {
        ObservableList<String> langs = FXCollections.observableArrayList(Const.TASK_NOT_PROJECT);


        for(int i = 0; i<projectId.size();i++){
            String valueForFirstKey = (String) projectId.keySet().toArray()[i];
            langs.add(valueForFirstKey);
        }
        return langs;
    }

    private static void clickOnCreateTask(String nameProject, String textTask, LocalDate dateTask, Map<String, String> projectId) throws SQLException {

        Plan plan = Plan.getInstance();
        Task task;

        if(nameProject.equals(Const.TASK_NOT_PROJECT)) {
            task = new Task(textTask,"0",dateTask.toString(),Const.TASK_STATUS_NOT_EXECUTABLE);
        }
        else{
            task = new Task(textTask, projectId.get(nameProject),dateTask.toString(),Const.TASK_STATUS_NOT_EXECUTABLE);
        }
        addInDataBase(plan,task);
    }
    public static boolean isEmptyString(String str, Label label){
        if(str.equals(STRING_EMPTY)){
            if(label != null) {
                label.setText(ERROR_EMPTY_STRING);
            }
            return true;
        } else {
            return false;
        }
    }
    private static void addInDataBase(Plan plan, Task task) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.addTask(plan,task);
    }
}
