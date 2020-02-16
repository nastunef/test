package patterns;

import database.Const;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Vector;


public class PlanTest {

    private static Plan plan;
    private static Project project;
    private static Task task;

    @BeforeClass
    public static void createOperations() {
        String idUser = "10";
        plan = Plan.getInstance();
        plan.setIdUser(idUser);
        String nameTask = "newNameTask";
        String nameProject = "newNameProject";
        project = new Project("id",nameProject,plan.getIdUser());
        task = new Task(nameTask,"","", Const.TASK_STATUS_NOT_EXECUTABLE);
    }
    @Test
   public void addProject() {
        plan.addProject(project);
        Vector<Project>  projects = plan.getUsersProject();
        Assert.assertFalse(projects.isEmpty());
    }
}