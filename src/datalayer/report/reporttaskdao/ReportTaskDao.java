package datalayer.report.reporttaskdao;

import bussinesslayer.entity.report.ReportTask;
import datalayer.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportTaskDao implements IReportTaskDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public ReportTask getById(int id) throws Exception {
        ReportTask reportTask = null;
        try {
            String sqlStatement = "SELECT * FROM Report_Task WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            reportTask = new ReportTask();
            reportTask.setId(resultSet.getInt("id"));
            reportTask.setDate(resultSet.getDate("date").toLocalDate());
            reportTask.setTime(resultSet.getTime("time").toLocalTime());
            reportTask.setDescription(resultSet.getString("Description"));
            reportTask.setTaskId(resultSet.getInt("task_id"));
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return reportTask;
    }

    @Override
    public List<ReportTask> getAll() throws Exception {
        List<ReportTask> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Task";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportTask reportTask = new ReportTask();
                reportTask.setId(resultSet.getInt("id"));
                reportTask.setDate(resultSet.getDate("date").toLocalDate());
                reportTask.setTime(resultSet.getTime("time").toLocalTime());
                reportTask.setDescription(resultSet.getString("Description"));
                reportTask.setTaskId(resultSet.getInt("task_id"));
                list.add(reportTask);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public void addNew(ReportTask space) throws Exception {
        try {
            String sqlStatement = "INSERT INTO Report_Task(id, date, description, Task_id) VALUES (?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, space.getId());
            statement.setDate(2, Date.valueOf(space.getDate()));
            statement.setString(3, space.getDescription());
            statement.setInt(4, space.getTaskId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void update(ReportTask space) throws Exception {
        try {
            String sqlStatement = "UPDATE Report_Task SET date = ?, description = ?, task_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setDate(1, Date.valueOf(space.getDate()));
            statement.setString(2, space.getDescription());
            statement.setInt(3, space.getTaskId());
            statement.setInt(4, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sqlStatement = "DELETE FROM Report_Task WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<ReportTask> getTaskProgress(int sprintId) throws Exception {
        List<ReportTask> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Task WHERE task_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, sprintId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportTask reportTask = new ReportTask();
                reportTask.setId(resultSet.getInt("id"));
                reportTask.setDate(resultSet.getDate("date").toLocalDate());
                reportTask.setDescription(resultSet.getString("Description"));
                reportTask.setTaskId(resultSet.getInt("task_id"));
                list.add(reportTask);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public List<ReportTask> getReportsByTaskId(int taskId) throws Exception {
        List<ReportTask> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Task WHERE task_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, taskId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportTask reportTask = new ReportTask();
                reportTask.setId(resultSet.getInt("id"));
                reportTask.setDate(resultSet.getDate("date").toLocalDate());
                reportTask.setDescription(resultSet.getString("Description"));
                reportTask.setTaskId(resultSet.getInt("task_id"));
                list.add(reportTask);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public List<ReportTask> getReportsBySprintId(int sprintId) throws Exception {
        List<ReportTask> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT distinct rt.id, rt.date, rt.description, rt.task_id FROM Report_Task as rt " +
                    " INNER JOIN Task as t ON rt.task_id = t.id " +
                    " INNER JOIN Sprint as s ON t.sprint_id = s.id" +
                    " WHERE s.id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, sprintId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportTask reportTask = new ReportTask();
                reportTask.setId(resultSet.getInt("rt.id"));
                reportTask.setDate(resultSet.getDate("rt.date").toLocalDate());
                reportTask.setDescription(resultSet.getString("rt.Description"));
                reportTask.setTaskId(resultSet.getInt("rt.task_id"));
                list.add(reportTask);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }
}
