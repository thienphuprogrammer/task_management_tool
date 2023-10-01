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
            throw new Exception(e);
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
            throw new Exception(e);
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
            throw new Exception(e);
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
            throw new Exception(e);
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
            throw new Exception(e);
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
            throw new Exception(e);
        }
        return backlog;
    }

    @Override
    public List<Task> getTasksInBacklog(int backlogId) throws Exception {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Backlog as bl " +
                    "JOIN Task as t on t.backlog_id = bl.id " +
                    "WHERE bl.id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, backlogId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("t.id"));
                task.setName(resultSet.getString("t.name"));
                task.setDescription(resultSet.getString("t.description"));
                task.setStartDate(resultSet.getDate("t.start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("t.end_date").toLocalDate());
                task.setMemberId(resultSet.getInt("t.member_id"));
                task.setSprintId(resultSet.getInt("t.sprint_id"));
                String status = resultSet.getString("t.status");
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
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public void addTaskInBacklogToSprint(int backlogId, int taskId, int sprintId) throws Exception {
        try {
            Task task = getTaskInBacklogByTaskId(taskId);
            task.setBacklogId(backlogId);
            task.setSprintId(sprintId);
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, backlog_id, sprint_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getStartDate()));
            statement.setDate(4, Date.valueOf(task.getEndDate()));
            statement.setInt(5, task.getMemberId());
            statement.setInt(6, backlogId);
            statement.setInt(7, sprintId);
            int status = task.getStatus();
            switch (status) {
                case 0 -> statement.setString(8, "Open");
                case 1 -> statement.setString(8, "In Progress");
                case 2 -> statement.setString(8, "Completed");
                case 3 -> statement.setString(8, "On Hold");
                case 4 -> statement.setString(8, "Cancelled");
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void createTaskInBacklog(Task task) throws Exception {
        try {
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, sprint_id, backlog_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getStartDate()));
            statement.setDate(4, Date.valueOf(task.getEndDate()));
            statement.setNull(5, Types.INTEGER); // Set the 5th parameter to NULL
            statement.setNull(6, Types.INTEGER); // Set the 6th parameter to NULL
            statement.setInt(7, task.getBacklogId());
            int status = task.getStatus();
            switch (status) {
                case 0 -> statement.setString(8, "Open");
                case 1 -> statement.setString(8, "In Progress");
                case 2 -> statement.setString(8, "Completed");
                case 3 -> statement.setString(8, "On Hold");
                case 4 -> statement.setString(8, "Cancelled");
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
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
                int memberId = resultSet.getInt("member_id");
                if (resultSet.wasNull()) {
                    task.setMemberId(-1);
                }
                else {
                    task.setMemberId(memberId);
                }
                int sprintId = resultSet.getInt("sprint_id");
                if (resultSet.wasNull()) {
                    task.setSprintId(-1);
                }
                else {
                    task.setSprintId(sprintId);
                }
                task.setBacklogId(resultSet.getInt("backlog_id"));
                String status = resultSet.getString("status");
                if(resultSet.wasNull()) {
                    task.setStatus(0);
                } else {
                    switch (status) {
                        case "Open" -> task.setStatus(0);
                        case "In Progress" -> task.setStatus(1);
                        case "Completed" -> task.setStatus(2);
                        case "On Hold" -> task.setStatus(3);
                        case "Cancelled" -> task.setStatus(4);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return task;
    }

    @Override
    public void updateTaskInBacklog(Task task) throws Exception {
        try {
            String sql = "UPDATE Task SET name = ?, description = ?, start_date = ?, end_date = ?, member_id = ?, backlog_id = ?, status = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getStartDate()));
            statement.setDate(4, Date.valueOf(task.getEndDate()));
            if (task.getMemberId() == -1) {
                statement.setNull(5, Types.INTEGER); // Set the 5th parameter to NULL
            }
            else {
                statement.setInt(5, task.getMemberId());
            }
            if (task.getSprintId() == -1) {
                statement.setNull(6, Types.INTEGER); // Set the 6th parameter to NULL
            }
            else {
                statement.setInt(6, task.getSprintId());
            }

            int status = task.getStatus();
            switch (status) {
                case 0 -> statement.setString(7, "Open");
                case 1 -> statement.setString(7, "In Progress");
                case 2 -> statement.setString(7, "Completed");
                case 3 -> statement.setString(7, "On Hold");
                case 4 -> statement.setString(7, "Cancelled");
            }

            statement.setInt(8, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
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
            throw new Exception(e);
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
                int memberId = resultSet.getInt("member_id");
                if (resultSet.wasNull()) {
                    task.setMemberId(-1);
                } else {
                    task.setMemberId(memberId);
                }
                int sprintId = resultSet.getInt("sprint_id");
                if (resultSet.wasNull()) {
                    task.setSprintId(-1);
                } else {
                    task.setSprintId(sprintId);
                }
                task.setBacklogId(resultSet.getInt("backlog_id"));
                String status = resultSet.getString("status");
                if (resultSet.wasNull()) {
                    task.setStatus(-1);
                } else {
                    switch (status) {
                        case "Open" -> task.setStatus(0);
                        case "In Progress" -> task.setStatus(1);
                        case "Completed" -> task.setStatus(2);
                        case "On Hold" -> task.setStatus(3);
                        case "Cancelled" -> task.setStatus(4);
                        default -> task.setStatus(-1);
                    }
                }
                list.add(task);
            }
        } catch (SQLException e) {
            throw new Exception(e);
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
                sprint.setStartDate(resultSet.getDate("start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("project_id"));
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return sprint;
    }
}
