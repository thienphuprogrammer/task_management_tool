package datalayer.spacedao.taskdao;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.submission.SubmissionTask;
import datalayer.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements ITaskDao {
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
    public List<Task> getAll() throws Exception {
        List<Task> list = new ArrayList<>();
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
    public void addNew(Task space) throws Exception {
        try {
            String sql = "INSERT INTO Task (name, description, start_date, end_date, member_id, sprint_id, status) VALUES (?,?,?,?,?,?,?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            int memberId = space.getMemberId();
            if (memberId == -1) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, space.getMemberId());
            }
            statement.setInt(6, space.getSprintId());
            statement.setString(7, "Open");
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
            String status = "";
            switch (space.getStatus()) {
                case 0 -> status = "Open";
                case 1 -> status = "In Progress";
                case 2 -> status = "Completed";
                case 3 -> status = "On Hold";
                case 4 -> status = "Cancelled";
            }
            statement.setString(7, status);
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
    public List<Task> getAllTasksOfMember(int sprintId, int memberId) {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Task WHERE sprint_id = ? AND member_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sprintId);
            statement.setInt(2, memberId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setDescription(resultSet.getString("description"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getDate("start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("end_date").toLocalDate());
                int memberIdForTask = resultSet.getInt("member_id");
                if (resultSet.wasNull()) {
                    task.setMemberId(-1);
                }
                else {
                    task.setMemberId(memberIdForTask);
                }
                int sprintIdForTask = resultSet.getInt("sprint_id");
                if (resultSet.wasNull()) {
                    task.setSprintId(-1);
                }
                else {
                    task.setSprintId(sprintIdForTask);
                }
                task.setBacklogId(resultSet.getInt("backlog_id"));
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
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void submitTask(SubmissionTask submissionTask, int taskId) {
        try {
            String sql = "UPDATE Task SET status = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Completed");
            statement.setInt(2, taskId);
            statement.executeUpdate();

            String sql2 = "INSERT INTO Submission_Task (date, time, content, task_id) VALUES (?,?,?,?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql2);
            statement.setDate(1, java.sql.Date.valueOf(submissionTask.getDate()));
            statement.setTime(2, java.sql.Time.valueOf(submissionTask.getTime()));
            statement.setString(3, submissionTask.getContent());
            statement.setInt(4, taskId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getAllTaskBacklog(int backlogId) {
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
                task.setDescription(resultSet.getString("description"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getDate("start_date").toLocalDate());
                task.setEndDate(resultSet.getDate("end_date").toLocalDate());
                int memberIdForTask = resultSet.getInt("member_id");
                if (resultSet.wasNull()) {
                    task.setMemberId(-1);
                }
                else {
                    task.setMemberId(memberIdForTask);
                }
                int sprintIdForTask = resultSet.getInt("sprint_id");
                if (resultSet.wasNull()) {
                    task.setSprintId(-1);
                }
                else {
                    task.setSprintId(sprintIdForTask);
                }
                task.setBacklogId(resultSet.getInt("backlog_id"));

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
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Task> getTasksMember(int sprintId, int memberId) {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Task WHERE sprint_id = ? AND member_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sprintId);
            statement.setInt(2, memberId);
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
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Task> getTaskProgress(int sprintId) {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Task WHERE sprint_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sprintId);
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
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Task> getAllTasks(int sprintId) {
        List<Task> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Task WHERE sprint_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sprintId);
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
                String status = resultSet.getString("status");
                if(resultSet.wasNull()) {
                    continue;
                } else {
                    switch (status) {
                        case "Open" -> task.setStatus(0);
                        case "In Progress" -> task.setStatus(1);
                        case "Completed" -> task.setStatus(2);
                        case "On Hold" -> task.setStatus(3);
                        case "Cancelled" -> task.setStatus(4);
                    }
                }
                list.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<SubmissionTask> getSubmissionTaskByTaskId(int taskId) throws Exception {
        List<SubmissionTask> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Submission_Task WHERE task_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SubmissionTask submissionTask = new SubmissionTask();
                submissionTask.setDate(resultSet.getDate("date").toLocalDate());
                submissionTask.setTime(resultSet.getTime("time").toLocalTime());
                submissionTask.setContent(resultSet.getString("content"));
                list.add(submissionTask);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }
}
