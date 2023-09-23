package datalayer.user.managerdao;

import bussinesslayer.entity.user.Manager;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ManagerDao implements IManagerDao<Manager> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet =   null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Manager getById(int id) throws Exception {
        Manager manager = new Manager();
        try {
            String sql = "SELECT * FROM Manager WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                manager.setId(resultSet.getInt("id"));
                manager.setName(resultSet.getString("name"));
                manager.setAge(resultSet.getInt("age"));
                manager.setEmail(resultSet.getString("email"));
                manager.setPassword(resultSet.getString("password"));
                manager.setPhone_number(resultSet.getString("phone_number"));
                manager.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() throws Exception {
        List<Manager> list = null;
        try {
            String sql = "SELECT * FROM Manager";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Manager manager = new Manager();
                manager.setId(resultSet.getInt("id"));
                manager.setName(resultSet.getString("name"));
                manager.setAge(resultSet.getInt("age"));
                manager.setEmail(resultSet.getString("email"));
                manager.setPassword(resultSet.getString("password"));
                manager.setPhone_number(resultSet.getString("phone_number"));
                manager.setAddress(resultSet.getString("address"));
                list.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Manager space) throws Exception {
        try {
            String sql = "INSERT INTO Manager (name, age, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhone_number());
            statement.setString(6, space.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manager space) throws Exception {
        try {
            String sql = "UPDATE Manager SET name = ?, age = ?, email = ?, password = ?, phone_number = ?, address = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhone_number());
            statement.setString(6, space.getAddress());
            statement.setInt(7, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Manager WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Manager space) throws Exception {
        try {
            String sql = "INSERT INTO Manager (name, age, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhone_number());
            statement.setString(6, space.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
