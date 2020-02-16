package patterns;

import java.util.Vector;

public class Plan {

    private static Plan plan = null;

    public Vector<Project> getUsersProject() {
        return usersProject;
    }

    private Vector<Project> usersProject = null;

    public Vector<Task> getTodayTask() {
        return todayTask;
    }

    private Vector<Task> todayTask = null;

    public Vector<Task> getTomorrowTask() {
        return tomorrowTask;
    }

    private Vector<Task> tomorrowTask = null;
    private String idUser;
    private String nameUser;

    private Plan() {}

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public static Plan getInstance() {
        if (plan == null) {
            plan = new Plan();
        }
        return plan;
    }

    public void addProject(Project newProject) {
        if(usersProject == null){
            usersProject = new Vector<>();
        }
        usersProject.add(newProject);
    }

    public void addTodayTask(Task task) {
        if(todayTask == null){
            todayTask = new Vector<>();
        }
        todayTask.add(task);
    }

    public void addTomorrowTask(Task task) {
        if(tomorrowTask == null){
            tomorrowTask = new Vector<>();
        }
        tomorrowTask.add(task);
    }
}