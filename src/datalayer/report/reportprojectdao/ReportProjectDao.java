package datalayer.report.reportprojectdao;

import bussinesslayer.entity.report.ReportProject;
import datalayer.IDao;
import datalayer.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportProjectDao implements IReportProjectDao {
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
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportProject reportProject = new ReportProject();
                reportProject.setId(resultSet.getInt("id"));
                reportProject.setDescription(resultSet.getString("Description"));
                reportProject.setDate(resultSet.getDate("date").toLocalDate());
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
                reportProject.setDate(resultSet.getDate("date").toLocalDate());
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
            String sqlStatement = "INSERT INTO Report_Project(id, date, description, project_id, time) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, space.getId());
            statement.setDate(2, Date.valueOf(space.getDate()));
            statement.setTime(5, Time.valueOf(space.getTime()));
            statement.setString(3, space.getDescription());
            statement.setInt(4, space.getProject_id());
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
            statement.setDate(1, Date.valueOf(space.getDate()));
            statement.setTime(2, Time.valueOf(space.getTime()));
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

    @Override
    public List<ReportProject> getReportsByProjectId(int projectId) {
        List<ReportProject> list = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM Report_Project WHERE project_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, projectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReportProject reportProject = new ReportProject();
                reportProject.setId(resultSet.getInt("id"));
                reportProject.setDescription(resultSet.getString("Description"));
                reportProject.setDate(resultSet.getDate("date").toLocalDate());
                reportProject.setProject_id(resultSet.getInt("project_id"));
                list.add(reportProject);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return  list;
    }
}
