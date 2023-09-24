package datalayer.report.reportsprintdao;

import bussinesslayer.entity.report.ReportSprint;
import datalayer.IDao;
import datalayer.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportSprintDao implements IDao<ReportSprint> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }
    @Override
    public ReportSprint getById(int id) throws Exception {
        try {
            String sqlStatement = "SELECT * FROM Report_Sprint WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportSprint reportSprint = new ReportSprint();
                reportSprint.setId(resultSet.getInt("id"));
                reportSprint.setDate(resultSet.getDate("date").toLocalDate());
                reportSprint.setTime(resultSet.getTime("time").toLocalTime());
                reportSprint.setDescription(resultSet.getString("Description"));
                reportSprint.setSprint_id(resultSet.getInt("sprint_id"));
                return reportSprint;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportSprint> getAll() throws Exception {
        List<ReportSprint> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Sprint";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportSprint reportSprint = new ReportSprint();
                reportSprint.setId(resultSet.getInt("id"));
                reportSprint.setDate(resultSet.getDate("date").toLocalDate());
                reportSprint.setTime(resultSet.getTime("time").toLocalTime());
                reportSprint.setDescription(resultSet.getString("Description"));
                reportSprint.setSprint_id(reportSprint.getSprint_id());
                list.add(reportSprint);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(ReportSprint space) throws Exception {
        try {
            String sqlStatement = "INSERT INTO Report_Sprint(id, date, time, description, sprint_id) SET (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, space.getId());
            statement.setDate(2, Date.valueOf(space.getDate()));
            statement.setTime(3, Time.valueOf(space.getTime()));
            statement.setString(4, space.getDescription());
            statement.setInt(5, space.getSprint_id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(ReportSprint space) throws Exception {
        try {
            String sqlStatement = "UPDATE Report_Sprint SET date = ?, time = ?, description = ?, sprint_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setDate(1, Date.valueOf(space.getDate()));
            statement.setTime(2, Time.valueOf(space.getTime()));
            statement.setString(3, space.getDescription());
            statement.setInt(4, space.getSprint_id());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sqlStatement = "DELETE FROM Report_Sprint WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
