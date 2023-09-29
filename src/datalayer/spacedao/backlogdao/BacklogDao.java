package datalayer.spacedao.backlogdao;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.entity.space.Task;
import datalayer.MySqlConnection;

import java.sql.*;
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
                backlog.setProjectId(resultSet.getInt("project_id"));
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
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> task.setStatus(0);
                    case "In Progress" -> task.setStatus(1);
                    case "Completed" -> task.setStatus(2);
                    case "On Hold" -> task.setStatus(3);
                    case "Cancelled" -> task.setStatus(4);
                }
                list.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addTaskInBacklogToSprint(int backlogId, int taskId, int sprintId) throws Exception {
        try {
            Task task = getTaskInBacklogByTaskId(taskId);
            task.setBacklogId(backlogId);
            task.setSprintId(sprintId);
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, backlog_id, sprint_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getStartDate()));
            statement.setDate(4, Date.valueOf(task.getEndDate()));
            statement.setInt(5, task.getMemberId());
            statement.setInt(6, backlogId);
            statement.setInt(7, sprintId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTaskInBacklog(Task task) throws Exception {
        try {
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, backlog_id) VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getStartDate()));
            statement.setDate(4, Date.valueOf(task.getEndDate()));
            statement.setInt(5, task.getMemberId());
            statement.setInt(6, task.getBacklogId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getTaskInBacklogByTaskId(int taskId) throws Exception {
        Task task = new Task();
        try {
            String sql = "SELECT * FROM Task WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setStartDate(resultSet.getDate("start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("end_date").toLocalDate());
                task.setMemberId(resultSet.getInt("member_id"));
                task.setSprintId(resultSet.getInt("sprint_id"));
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> task.setStatus(0);
                    case "In Progress" -> task.setStatus(1);
                    case "Completed" -> task.setStatus(2);
                    case "On Hold" -> task.setStatus(3);
                    case "Cancelled" -> task.setStatus(4);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void updateTaskInBacklog(Task task) throws Exception {
        try {
            String sql = "UPDATE Task SET name = ?, description = ?, start_date = ?, end_date = ?, member_id = ?, backlog_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getStartDate()));
            statement.setDate(4, Date.valueOf(task.getEndDate()));
            statement.setInt(5, task.getMemberId());
            statement.setInt(6, task.getBacklogId());
            statement.setInt(7, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTaskInBacklog(int taskId) throws Exception {
        try {
            String sql = "DELETE FROM Task WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasksInBacklog(int backlogId) throws Exception {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Task WHERE backlog_id = ?";
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
                String status = resultSet.getString("status");
                switch (status) {
                    case "Open" -> task.setStatus(0);
                    case "In Progress" -> task.setStatus(1);
                    case "Completed" -> task.setStatus(2);
                    case "On Hold" -> task.setStatus(3);
                    case "Cancelled" -> task.setStatus(4);
                }
                list.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Sprint getSprintBySprintId(int sprintId) throws Exception {
        Sprint sprint = new Sprint();
        try {
            String sql = "SELECT * FROM Sprint WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sprintId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sprint.setId(resultSet.getInt("id"));
                sprint.setName(resultSet.getString("name"));
                sprint.setDescription(resultSet.getString("description"));
                sprint.setStartDate(resultSet.getDate("start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("project_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sprint;
    }
}
