package datalayer.spacedao.taskdao;

import bussinesslayer.entity.space.Task;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDao implements ITaskDao<Task> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet =   null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Task getById(int id) throws Exception {
        Task task = null;
        try {
            String sql = "SELECT * FROM Task WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setDescription(resultSet.getString("description"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getDate("start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("end_date").toLocalDate());
                task.setMemberId(resultSet.getInt("member_id"));
                task.setSprintId(resultSet.getInt("sprint_id"));
                task.setStatus(resultSet.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> getAll() throws Exception {
        List<Task> list = null;
        try {
            String sql = "SELECT * FROM Task";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setDescription(resultSet.getString("description"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getDate("start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("end_date").toLocalDate());
                task.setMemberId(resultSet.getInt("member_id"));
                task.setSprintId(resultSet.getInt("sprint_id"));
                task.setStatus(resultSet.getInt("status"));
                list.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Task space) throws Exception {
        try {
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, sprint_id, status) VALUES (?,?,?,?,?,?,?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getMemberId());
            statement.setInt(6, space.getSprintId());
            statement.setInt(7, space.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Task space) throws Exception {
        try {
            String sql = "UPDATE Task SET name = ?, description = ?, start_date = ?, end_date = ?, member_id = ?, sprint_id = ?, status = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getMemberId());
            statement.setInt(6, space.getSprintId());
            statement.setInt(7, space.getStatus());
            statement.setInt(8, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Task WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Task space) throws Exception {
        try {
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, sprint_id, status) VALUES (?,?,?,?,?,?,?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getMemberId());
            statement.setInt(6, space.getSprintId());
            statement.setInt(7, space.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}