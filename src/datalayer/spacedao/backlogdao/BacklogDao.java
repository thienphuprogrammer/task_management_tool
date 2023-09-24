package datalayer.spacedao.backlogdao;

import datalayer.IConnection;
import bussinesslayer.entity.space.Backlog;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BacklogDao implements IBacklogDao<Backlog> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public BacklogDao() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Backlog getById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM Backlog WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Backlog backlog = new Backlog();
                backlog.setId(resultSet.getInt("id"));
                backlog.setDescription(resultSet.getString("description"));
                backlog.setTitle(resultSet.getString("title"));
                backlog.setFileURL(resultSet.getString("file_url"));
                return backlog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Backlog> getAll() throws Exception {
        List<Backlog> list = null;
        try {
            String sql = "SELECT * FROM Backlog";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Backlog backlog = new Backlog();
                backlog.setId(resultSet.getInt("id"));
                backlog.setDescription(resultSet.getString("description"));
                backlog.setTitle(resultSet.getString("title"));
                backlog.setFileURL(resultSet.getString("file_url"));
                list.add(backlog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Backlog space) throws Exception {
        try {
            String sql = "INSERT INTO Backlog (description, title, file_url) VALUES (?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getTitle());
            statement.setString(3, space.getFileURL());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Backlog space) throws Exception {
        try {
            String sql = "UPDATE Backlog SET description = ?, title = ?, file_url = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getDescription());
            statement.setString(2, space.getTitle());
            statement.setString(3, space.getFileURL());
            statement.setInt(4, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM backlog WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
