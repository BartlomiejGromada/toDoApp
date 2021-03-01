package service;

import model.Task;
import repo.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = new TaskRepository();
    }

    public void getAllTasks() throws SQLException {
        ResultSet resultSet = taskRepository.getAll();

        String text = "";

        while (resultSet.next()) {
            text =
                    resultSet.getInt(1) + ". " +
                            resultSet.getString(2) + " | " +
                            resultSet.getInt(3) + " | ";
            text += (resultSet.getInt(4) == 0) ? "not done" : "done";

            System.out.println(text);
        }
    }

    public void insertTask(Task task) {
        int count = taskRepository.insert(task);

        if (count > 0)
            System.out.println("Task \"" + task.getName() + "\" has been added");
        else
            System.out.println("Task: \"" + task.getName() + "\" hasn't been added");
    }

    public void updateTask(int id, Task task) {
        int count = taskRepository.update(id, task);

        if (count > 0)
            System.out.println("Task with id = " + id + " has been updated");
        else
            System.out.println("Task with id = " + id + " hasn't been updated");
    }

    public void markTaskAsDone(int id) {
        int count = taskRepository.markAsDone(id);

        if (count > 0)
            System.out.println("Task with id = " + id + " has been marked as done");
        else
            System.out.println("Task with id = " + id + " hasn't been marked as done");
    }

    public void deleteTask(int id) {
        int count = taskRepository.delete(id);

        if (count > 0)
            System.out.println("Task with id = " + id + " has been deleted");
        else
            System.out.println("Task with id = " + id + " hasn't been deleted");
    }

    public void deleteAllDoneTasks() {
        int count = taskRepository.deleteAllDone();

        if (count > 0)
            System.out.println("All done tasks has been deleted");
        else
            System.out.println("No done tasks");
    }

}
