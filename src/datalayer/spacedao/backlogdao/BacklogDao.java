package datalayer.spacedao.backlogdao;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.user.Member;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BacklogDao implements IBacklogDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public BacklogDao() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Backlog getById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM Backlog WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Backlog backlog = new Backlog();
                backlog.setId(resultSet.getInt("id"));
                return backlog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Backlog> getAll() throws Exception {
        List<Backlog> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Backlog";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Backlog backlog = new Backlog();
                backlog.setId(resultSet.getInt("id"));
                backlog.setProjectId(resultSet.getInt("project_id"));
                list.add(backlog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Backlog space) throws Exception {
        try {
            String sql = "INSERT INTO Backlog (description, title, file_url) VALUES (?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Backlog space) throws Exception {
        try {
            String sql = "UPDATE Backlog SET description = ?, title = ?, file_url = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(4, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM backlog WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Backlog getBacklogByProjectId(int projectId) throws Exception {
        Backlog backlog = null;
        try {
            String sql = "SELECT * FROM backlog WHERE project_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                backlog = new Backlog();
                backlog.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return backlog;
    }

    @Override
    public List<Task> getTasksInBacklog(int backlogId) {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Backlog as bl " +
                    "JOIN Task as t on t.backlog_id = bl.id " +
                    "WHERE bl.id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, backlogId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setStartDate(resultSet.getDate("start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("end_date").toLocalDate());
                task.setMemberId(resultSet.getInt("member_id"));
                task.setSprintId(resultSet.getInt("sprint_id"));
                task.setStatus(resultSet.getInt("status"));
                list.add(task);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
