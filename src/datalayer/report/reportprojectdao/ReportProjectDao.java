package datalayer.report.reportprojectdao;

import bussinesslayer.entity.report.ReportProject;
import datalayer.IDao;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportProjectDao implements IDao<ReportProject> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }
    @Override
    public ReportProject getById(int id) throws Exception {
        try {
            String sqlStatement = "SELECT * FROM Report_Project WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, ID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportProject reportProject = new ReportProject();
                reportProject.setId(resultSet.getInt("id"));
                reportProject.setDescription(resultSet.getString("Description"));
                reportProject.setDate(resultSet.getDate("date"));
                reportProject.setProject_id(resultSet.getInt("project_id"));
                return reportProject;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportProject> getAll() throws Exception {
        List<ReportProject> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Project";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportProject reportProject = new ReportProject();
                reportProject.setId(resultSet.getInt("id"));
                reportProject.setDescription(resultSet.getString("Description"));
                reportProject.setDate(resultSet.getDate("date"));
                reportProject.setProject_id(resultSet.getInt("project_id"));
                list.add(reportProject);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return  list;
    }

    @Override
    public void addNew(ReportProject space) throws Exception {
        try {
            String sqlStatement = "INSERT INTO Report_Backlog(id, date, time, Descripton, project_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, space.getId());
            statement.setDate(2, space.getDate());
            statement.setTime(3, space.getTime());
            statement.setString(4, space.getDescription());
            statement.setInt(5, space.getProject_id());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(ReportProject space) throws Exception {
        try {
            String sqlStatement = "UPDATE Report_Project SET date = ?, time = ?, description = ?, project_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setDate(1, space.getDate());
            statement.setTime(2, space.getTime());
            statement.setString(3, space.getDescription());
            statement.setInt(4, space.getProject_id());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sqlStatement = "DELETE FROM Report_Project WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
