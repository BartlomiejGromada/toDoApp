package repo;

import database.SQLiteJDBC;
import model.Task;

import java.sql.*;

public class TaskRepository implements Repository {

    private Connection connection;

    public TaskRepository() {
        this.connection = SQLiteJDBC.getInstance();
        createTable();
    }

    public void createTable() {

        try {
            Statement statement = connection.createStatement();
            connection.setAutoCommit(true);

            String sql = "CREATE TABLE IF NOT EXISTS task" +
                    "(idTask        INTEGER PRIMARY KEY     AUTOINCREMENT," +
                    "name           TEXT                    NOT NULL," +
                    "priority       INT                     NOT NULL," +
                    "isDone         INT                     NOT NULL);";

            statement.execute(sql);
            statement.close();

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }

    }

    @Override
    public ResultSet getAll() {

        ResultSet result = null;

        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM task;";

            result = statement.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }

        return result;
    }

    @Override
    public int insert(Object object) {

        int count = 0;

        try {
            String sql = "INSERT INTO task(idTask, name, priority, isDone)" +
                    "VALUES  (NULL, ?, ?, 0);";

            PreparedStatement statement = connection.prepareStatement(sql);
            Task task = (Task) object;
            statement.setString(1, task.getName());
            statement.setInt(2, task.getPriority());

            count = statement.executeUpdate();

            statement.close();


        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }

        return count;

    }

    @Override
    public int update(int id, Object object) {

        int count = 0;
        Task task = (Task) object;

        try {
            String sql = "UPDATE task " +
                    "SET name = ?, priority = ?, isDone = ?" +
                    "WHERE idTask = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setInt(2, task.getPriority());
            statement.setInt(3, task.getIsDone());
            statement.setInt(4, id);

            count = statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }

        return count;
    }

    public int markAsDone(int id) {

        int count = 0;

        try {
            String sql = "UPDATE task SET isDone = 1 WHERE idTask=?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            count = statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            System.out.println(getClass().getName() + ": " + e.getMessage());
        }

        return count;
    }

    @Override
    public int delete(int id) {

        int count = 0;

        try {
            String sql = "DELETE FROM task WHERE idTask=?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            count = statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            System.out.println(getClass().getName() + ": " + e.getMessage());
        }

        return count;
    }

    public int deleteAllDone() {
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM task WHERE isDone = 1";
            count = statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println(getClass().getName() + " : " + e.getMessage());
        }

        return count;
    }
}
