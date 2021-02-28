package database;

import java.sql.*;

public class ToDoTable {

    private Connection connection;

    public ToDoTable() {
        connection = SQLiteJDBC.getInstance();
        createTable();
    }

    public void createTable() {

        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(true);

            String sql = "CREATE TABLE IF NOT EXISTS toDo" +
                    "(idToDo        INTEGER PRIMARY KEY     AUTOINCREMENT," +
                    "name           TEXT                    NOT NULL," +
                    "priority       INT                     NOT NULL," +
                    "isDone         INT                     NOT NULL);";

            statement.execute(sql);
            statement.close();

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }

    }

    public void getAll() {

        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM toDo;";

            ResultSet result = statement.executeQuery(sql);

            String text = null;

            while (result.next()) {
                text =
                        result.getInt(1) + ". " +
                                result.getString(2) + " | " +
                                result.getInt(3) + " | ";
                text += (result.getInt(4) == 0) ? "not done" : "done";

                System.out.println(text);
            }

            if (text == null)
                System.out.println("No tasks to be performed");

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }
    }

    public void insert(String name, int priority) {

        try {
            String sql = "INSERT INTO toDo(idToDo, name, priority, isDone)" +
                    "VALUES  (NULL, ?, ?, 0);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, priority);

            if (statement.executeUpdate() > 0)
                System.out.println("Task \"" + name + "\" has been added");
            else
                System.out.println("Task: \"" + name + "\" hasn't been added");


            statement.close();

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }
    }

    public void delete(int id) {

        try {
            String sql = "DELETE FROM toDO WHERE idToDo=?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            if (statement.executeUpdate() > 0)
                System.out.println("Task with id = " + id + " has been deleted");
            else
                System.out.println("Task with id = " + id + " hasn't been deleted");

            statement.close();

        } catch (SQLException e) {
            System.out.println(getClass().getName() + ": " + e.getMessage());
        }
    }

    public void update(int id, String name, int priority, int isDone) {

        try {
            String sql = "UPDATE toDo " +
                    "SET name = ?, priority = ?, isDone = ?" +
                    "WHERE idtoDo = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, priority);
            statement.setInt(3, isDone);
            statement.setInt(4, id);

            if (statement.executeUpdate() > 0)
                System.out.println("Task with id = " + id + " has been updated");
            else
                System.out.println("Task with id = " + id + " hasn't been updated");

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }
    }

}
