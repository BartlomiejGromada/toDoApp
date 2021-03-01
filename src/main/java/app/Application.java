package app;

import database.SQLiteJDBC;
import model.Task;
import repo.TaskRepository;
import service.TaskService;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    private Scanner sc;
    private TaskService taskService;

    public Application() {
        this.taskService = new TaskService(new TaskRepository());
        this.sc = new Scanner(System.in);
    }

    public void start() throws SQLException {
        while (true) {

            System.out.println("\n----ALL TASKS----");
            taskService.getAllTasks();
            System.out.println("\n1. INSERT");
            System.out.println("2. UPDATE");
            System.out.println("3. MARK AS DONE");
            System.out.println("4. DELETE");
            System.out.println("5. DELETE ALL DONE");
            System.out.println("6. EXIT");
            System.out.println("\nEnter the operation number:");
            int operation = sc.nextInt();
            sc.nextLine();

            switch (operation) {
                case 1:
                    insertOperation();
                    break;
                case 2:
                    updateOperation();
                    break;
                case 3:
                    markAsDoneOperation();
                    break;
                case 4:
                    deleteOperation();
                    break;
                case 5:
                    taskService.deleteAllDoneTasks();
                    break;
                case 6:
                    SQLiteJDBC.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Select the correct number...");
            }
        }
    }

    private void insertOperation() {
        boolean flag = true;

        while (flag) {
            System.out.println("Enter task's name: ");
            String name = sc.nextLine();

            System.out.println("Enter task's priority (1-10): ");
            int priority = sc.nextInt();
            sc.nextLine();

            if (name.equals("") || priority < 1 || priority > 10)
                System.out.println("Enter valid values");
            else {
                flag = false;
                taskService.insertTask(new Task(name, priority, 0));
            }
        }
    }

    private void updateOperation() {
        boolean flag = true;

        while (flag) {
            System.out.println("Enter of the id task you want to change: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter task's name: ");
            String name = sc.nextLine();

            System.out.println("Enter task's priority (1-10): ");
            int priority = sc.nextInt();
            sc.nextLine();

            System.out.println("Task is done? 0 - false | 1 - true");
            int isDone = sc.nextInt();
            sc.nextLine();

            if (id < 1 || name.equals("") || (priority < 1 || priority > 10) || (isDone != 0 & isDone != 1))
                System.out.println("Enter valid values");
            else {
                flag = false;
                taskService.updateTask(id, new Task(name, priority, isDone));
            }
        }
    }

    private void markAsDoneOperation() {
        boolean flag = true;

        while (flag) {
            System.out.println("Enter of the id task you want to mark as done: ");
            int id = sc.nextInt();
            sc.nextLine();

            if (id < 1)
                System.out.println("Enter valid values");
            else {
                flag = false;
                taskService.markTaskAsDone(id);
            }
        }
    }

    private void deleteOperation() {
        boolean flag = true;

        while (flag) {
            System.out.println("Enter of the id task you want to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            if (id < 1)
                System.out.println("Enter valid values");
            else {
                flag = false;
                taskService.deleteTask(id);
            }
        }
    }

}
