package datalayer.report.reportbacklogdao;

import bussinesslayer.entity.report.ReportBacklog;
import datalayer.IDao;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportBacklogDao implements IDao<ReportBacklog> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ReportBacklogDao() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }
    @Override
    public ReportBacklog getById(int id) throws Exception {
        try {
            String sqlStatement = "SELECT * FROM Report_Backlog WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportBacklog reportBacklog = new ReportBacklog();
                reportBacklog.setId(resultSet.getInt("id"));
                reportBacklog.setTime(resultSet.getTime("time"));
                reportBacklog.setDate(resultSet.getDate("date"));
                reportBacklog.setDescription(resultSet.getString("Description"));
                reportBacklog.setBacklog_id(resultSet.getInt("backlog_id"));
                reportBacklog.setTask_id(resultSet.getInt("task_id"));
                return reportBacklog;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<ReportBacklog> getAll() throws Exception {
        List<ReportBacklog> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Backlog";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                ReportBacklog reportBacklog = new ReportBacklog();
                reportBacklog.setId(resultSet.getInt("id"));
                reportBacklog.setTime(resultSet.getTime("time"));
                reportBacklog.setDate(resultSet.getDate("date"));
                reportBacklog.setDescription(resultSet.getString("Description"));
                reportBacklog.setBacklog_id(resultSet.getInt("backlog_id"));
                reportBacklog.setTask_id(resultSet.getInt("task_id"));
                list.add(reportBacklog);
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return list;
    }

    @Override
    public void addNew(ReportBacklog space) throws Exception {
        try {
            String sqlStatement = "UPDATE Report_Backlog SET date = ?, Description = ?, backlog_id = ?, task_id = ? WHERE id =?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setDate(1, space.getDate());
            statement.setString(2, space.getDescription());
            statement.setInt(3, space.getBacklog_id());
            statement.setInt(4, space.getTask_id());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void update(ReportBacklog space) throws Exception {
        try {
            String sqlStatement = "UPDATE Report_Backlog SET date = ?, Description = ?, backlog_id = ?, task_id = ? WHERE id =?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setDate(1, space.getDate());
            statement.setString(2, space.getDescription());
            statement.setInt(3, space.getBacklog_id());
            statement.setInt(4, space.getTask_id());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sqlStatement = "DELETE FROM Report_Backlog WHERE id = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
