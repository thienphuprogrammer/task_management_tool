package datalayer.spacedao.sprintdao;

import bussinesslayer.entity.space.Sprint;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SprintDao implements ISprintDao {
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

    @Override
    public List<Sprint> getAll() throws Exception {
        List<Sprint> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Sprint";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sprint sprint = new Sprint();
                sprint.setId(resultSet.getInt("id"));
                sprint.setName(resultSet.getString("name"));
                sprint.setStartDate(resultSet.getDate("start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("project_id"));
                list.add(sprint);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public void addNew(Sprint space) throws Exception {
        try {
            String sql = "INSERT INTO Sprint (name, start_date, end_date, project_id) VALUES (?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setDate(2, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(3, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(4, space.getProjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void update(Sprint space) throws Exception {
        try {
            String sql = "UPDATE Sprint SET name = ?, start_date = ?, end_date = ?, project_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, space.getName());
            statement.setDate(2, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(3, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(4, space.getProjectId());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
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
            throw new Exception(e);
        }
    }

    @Override
    public List<Sprint> getAllSprintsOfProject(int projectId) throws Exception {
        List<Sprint> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Sprint WHERE project_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sprint sprint = new Sprint();
                sprint.setId(resultSet.getInt("id"));
                sprint.setName(resultSet.getString("name"));
                sprint.setStartDate(resultSet.getDate("start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("project_id"));
                list.add(sprint);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public List<Sprint> getAllSprintsInProjectOfMember(int projectId, int memberId) throws Exception {
        List<Sprint> list = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Sprint as sp " +
                    "JOIN Project as pr ON sp.project_id = pr.id " +
                    "JOIN Member_Project as mp ON pr.id = mp.project_id " +
                    "JOIN Member as me ON me.id = mp.member_id " +
                    "WHERE me.id = ? AND sp.project_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);
            statement.setInt(2, projectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sprint sprint = new Sprint();
                sprint.setId(resultSet.getInt("sp.id"));
                sprint.setName(resultSet.getString("sp.name"));
                sprint.setStartDate(resultSet.getDate("sp.start_date").toLocalDate());
                sprint.setEndDate(resultSet.getDate("sp.end_date").toLocalDate());
                sprint.setProjectId(resultSet.getInt("sp.project_id"));
                list.add(sprint);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }
}
