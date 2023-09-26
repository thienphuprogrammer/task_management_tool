package datalayer.spacedao.substaskdao;

import bussinesslayer.entity.space.Subtask;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubtaskDao implements ISubtaskDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public SubtaskDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Subtask getById(int id) throws Exception {
        Subtask subTask = null;
        try {
            String sql = "SELECT * FROM SubTask WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subTask = new Subtask();
                subTask.setId(resultSet.getInt("id"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setName(resultSet.getString("name"));
                subTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                subTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> subTask.setStatus(0);
                    case "In Progress" -> subTask.setStatus(1);
                    case "Completed" -> subTask.setStatus(2);
                    case "On Hold" -> subTask.setStatus(3);
                    case "Cancelled" -> subTask.setStatus(4);
                }
                subTask.setMemberId(resultSet.getInt("member_id"));
                subTask.setTaskId(resultSet.getInt("task_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subTask;
    }

    @Override
    public List<Subtask> getAll() throws Exception {
        List<Subtask> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubTask";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subtask subTask = new Subtask();
                subTask.setId(resultSet.getInt("id"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setName(resultSet.getString("name"));
                subTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                subTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> subTask.setStatus(0);
                    case "In Progress" -> subTask.setStatus(1);
                    case "Completed" -> subTask.setStatus(2);
                    case "On Hold" -> subTask.setStatus(3);
                    case "Cancelled" -> subTask.setStatus(4);
                }
                subTask.setMemberId(resultSet.getInt("member_id"));
                subTask.setTaskId(resultSet.getInt("task_id"));
                list.add(subTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Subtask space) throws Exception {
        try {
            String sql = "INSERT INTO SubTask (description, name, start_date, end_date, status, member_id, task_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getName());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            String status = "";
            switch (space.getStatus()) {
                case 0 -> status = "Open";
                case 1 -> status = "In Progress";
                case 2 -> status = "Completed";
                case 3 -> status = "On Hold";
                case 4 -> status = "Cancelled";
            }
            statement.setString(5, status);
            statement.setInt(6, space.getMemberId());
            statement.setInt(7, space.getTaskId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Subtask space) throws Exception {
        try {
            String sql = "UPDATE SubTask SET description = ?, name = ?, start_date = ?, end_date = ?, status = ?, member_id = ?, task_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getName());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            String status = "";
            switch (space.getStatus()) {
                case 0 -> status = "Open";
                case 1 -> status = "In Progress";
                case 2 -> status = "Completed";
                case 3 -> status = "On Hold";
                case 4 -> status = "Cancelled";
            }
            statement.setString(5, status);
            statement.setInt(6, space.getMemberId());
            statement.setInt(7, space.getTaskId());
            statement.setInt(8, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM SubTask WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Subtask> getAllSubtaskProject(int taskId) {
        List<Subtask> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubTask WHERE task_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subtask subTask = new Subtask();
                subTask.setId(resultSet.getInt("id"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setName(resultSet.getString("name"));
                subTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                subTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> subTask.setStatus(0);
                    case "In Progress" -> subTask.setStatus(1);
                    case "Completed" -> subTask.setStatus(2);
                    case "On Hold" -> subTask.setStatus(3);
                    case "Cancelled" -> subTask.setStatus(4);
                }
                subTask.setMemberId(resultSet.getInt("member_id"));
                subTask.setTaskId(resultSet.getInt("task_id"));
                list.add(subTask);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Subtask> getAllMySubtask(int memberId, int taskId) {
        List<Subtask> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubTask WHERE member_id = ? AND task_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);
            statement.setInt(2, taskId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subtask subTask = new Subtask();
                subTask.setId(resultSet.getInt("id"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setName(resultSet.getString("name"));
                subTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                subTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> subTask.setStatus(0);
                    case "In Progress" -> subTask.setStatus(1);
                    case "Completed" -> subTask.setStatus(2);
                    case "On Hold" -> subTask.setStatus(3);
                    case "Cancelled" -> subTask.setStatus(4);
                }
                subTask.setMemberId(resultSet.getInt("member_id"));
                subTask.setTaskId(resultSet.getInt("task_id"));
                list.add(subTask);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Subtask> getAllSubtask(int taskId) {
        List<Subtask> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubTask WHERE task_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subtask subTask = new Subtask();
                subTask.setId(resultSet.getInt("id"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setName(resultSet.getString("name"));
                subTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                subTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> subTask.setStatus(0);
                    case "In Progress" -> subTask.setStatus(1);
                    case "Completed" -> subTask.setStatus(2);
                    case "On Hold" -> subTask.setStatus(3);
                    case "Cancelled" -> subTask.setStatus(4);
                }
                subTask.setMemberId(resultSet.getInt("member_id"));
                subTask.setTaskId(resultSet.getInt("task_id"));
                list.add(subTask);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Subtask> getAllMySubtaskProject(int taskId, int memberId) {
        List<Subtask> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubTask WHERE task_id = ? AND member_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.setInt(2, memberId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subtask subTask = new Subtask();
                subTask.setId(resultSet.getInt("id"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setName(resultSet.getString("name"));
                subTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                subTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> subTask.setStatus(0);
                    case "In Progress" -> subTask.setStatus(1);
                    case "Completed" -> subTask.setStatus(2);
                    case "On Hold" -> subTask.setStatus(3);
                    case "Cancelled" -> subTask.setStatus(4);
                }
                subTask.setMemberId(resultSet.getInt("member_id"));
                subTask.setTaskId(resultSet.getInt("task_id"));
                list.add(subTask);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void submitSubtask(int subtaskId) {
        try {
            String sql = "UPDATE SubTask SET status = \"Completed\" WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, subtaskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
