package datalayer.user.managerdao;

import bussinesslayer.entity.user.Manager;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ManagerDao implements IManagerDao {
    @Override
    public Manager getById(int id) throws Exception {
        Manager manager = null;
        try {
            String sql = "SELECT * FROM Manager WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                manager = new Manager();
                manager.setId(resultSet.getInt("id"));
                manager.setName(resultSet.getString("name"));
                manager.setAge(resultSet.getInt("age"));
                manager.setEmail(resultSet.getString("email"));
                manager.setPassword(resultSet.getString("password"));
                manager.setPhoneNumber(resultSet.getString("phone_number"));
                manager.setAddress(resultSet.getString("address"));
                manager.setRole(resultSet.getString("role"));
                manager.setGender(resultSet.getBoolean("gender"));
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
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Manager manager = new Manager();
                manager.setId(resultSet.getInt("id"));
                manager.setName(resultSet.getString("name"));
                manager.setAge(resultSet.getInt("age"));
                manager.setEmail(resultSet.getString("email"));
                manager.setPassword(resultSet.getString("password"));
                manager.setPhoneNumber(resultSet.getString("phone_number"));
                manager.setAddress(resultSet.getString("address"));
                manager.setRole(resultSet.getString("role"));
                manager.setGender(resultSet.getBoolean("gender"));
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
            String sql = "INSERT INTO Manager (name, age, email, password, phone_number, address, role, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhoneNumber());
            statement.setString(6, space.getAddress());
            statement.setString(7, space.getRole());
            statement.setBoolean(8, space.isGender());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manager space) throws Exception {
        try {
            String sql = "UPDATE Manager SET name = ?, age = ?, email = ?, password = ?, phone_number = ?, address = ?, role = ?, gender = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhoneNumber());
            statement.setString(6, space.getAddress());
            statement.setString(7, space.getRole());
            statement.setBoolean(8, space.isGender());
            statement.setInt(9, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Manager WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
