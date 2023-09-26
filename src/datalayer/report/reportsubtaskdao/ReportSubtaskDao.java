package datalayer.report.reportsubtaskdao;

import bussinesslayer.entity.report.ReportSubtask;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportSubtaskDao implements IReportSubtaskDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    public ReportSubtaskDao() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }
    @Override
    public ReportSubtask getById(int id) throws Exception {
        ReportSubtask reportSubtask = null;
        try {
            String sqlStatement = "SELECT * FROM Report_Subtask WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reportSubtask = new ReportSubtask();
                reportSubtask.setId(resultSet.getInt("id"));
                reportSubtask.setDate(resultSet.getDate("date").toLocalDate());
                reportSubtask.setTime(resultSet.getTime("time").toLocalTime());
                reportSubtask.setDescription(resultSet.getString("Description"));
                reportSubtask.setSubtaskId(resultSet.getInt("task_id"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return reportSubtask;
    }

    @Override
    public List<ReportSubtask> getAll() throws Exception {
        List<ReportSubtask> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Subtask";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                ReportSubtask reportSubtask = new ReportSubtask();
                reportSubtask.setId(resultSet.getInt("id"));
                reportSubtask.setDate(resultSet.getDate("date").toLocalDate());
                reportSubtask.setTime(resultSet.getTime("time").toLocalTime());
                reportSubtask.setDescription(resultSet.getString("Description"));
                reportSubtask.setSubtaskId(resultSet.getInt("subtask_id"));
                list.add(reportSubtask);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(ReportSubtask space) throws Exception {
        try {
            String sqlStatement = "INSERT INTO Report_Subtask(id, date, time, Description, subtask_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, space.getId());
            statement.setDate(2, java.sql.Date.valueOf(space.getDate()));
            statement.setTime(3, java.sql.Time.valueOf(space.getTime()));
            statement.setString(4, space.getDescription());
            statement.setInt(5, space.getSubtaskId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(ReportSubtask space) throws Exception {
        try {
            String sqlStatement = "UPDATE Report_Subtask SET date = ?, time = ?, Description = ?, subtask_id = ? WHERE id =?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setDate(1, java.sql.Date.valueOf(space.getDate()));
            statement.setTime(2, java.sql.Time.valueOf(space.getTime()));
            statement.setString(3, space.getDescription());
            statement.setInt(4, space.getSubtaskId());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sqlStatement = "DELETE FROM Report_Subtask WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
