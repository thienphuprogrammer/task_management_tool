package datalayer.spacedao.projectdao;

import bussinesslayer.entity.space.Project;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectDao implements IProjectDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ProjectDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Project getById(int id) throws Exception {
        Project project = null;
        try {
            String sql = "SELECT * FROM Project WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setDescription(resultSet.getString("description"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("manager_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public List<Project> getAll() throws Exception {
        List<Project> list = null;
        try {
            String sql = "SELECT * FROM Project";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setDescription(resultSet.getString("description"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("manager_id"));
                list.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Project space) throws Exception {
        try {
            String sql = "INSERT INTO Project (name, description, start_date, end_date, manager_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getManagerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project space) throws Exception {
        try {
            String sql = "UPDATE Project SET name = ?, description = ?, start_date = ?, end_date = ?, manager_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getManagerId());
            statement.setInt(6, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Project WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
