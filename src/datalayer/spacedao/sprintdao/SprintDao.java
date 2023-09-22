package datalayer.spacedao.sprintdao;

import bussinesslayer.entity.space.Sprint;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SprintDao implements ISprintDao<Sprint> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public SprintDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Sprint getById(int id) throws Exception {
        Sprint sprint = null;
        try {
            String sql = "SELECT * FROM Sprint WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sprint = new Sprint();
                sprint.setId(resultSet.getInt("id"));
                sprint.setDescription(resultSet.getString("description"));
                sprint.setName(resultSet.getString("name"));
                sprint.setStartDate(resultSet.getDate("start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("project_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sprint;
    }

    @Override
    public List<Sprint> getAll() throws Exception {
        List<Sprint> list = null;
        try {
            String sql = "SELECT * FROM Sprint";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sprint sprint = new Sprint();
                sprint.setId(resultSet.getInt("id"));
                sprint.setDescription(resultSet.getString("description"));
                sprint.setName(resultSet.getString("name"));
                sprint.setStartDate(resultSet.getDate("start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("project_id"));
                list.add(sprint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Sprint space) throws Exception {
        try {
            String sql = "INSERT INTO Sprint (description, name, start_date, end_date, project_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getName());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getProjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Sprint space) throws Exception {
        try {
            String sql = "UPDATE Sprint SET description = ?, name = ?, start_date = ?, end_date = ?, project_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getName());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getProjectId());
            statement.setInt(6, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Sprint WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Sprint space) throws Exception {
        try {
            String sql = "INSERT INTO Sprint (description, name, start_date, end_date, project_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getName());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getProjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
