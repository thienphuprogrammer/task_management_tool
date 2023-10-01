package datalayer.user.admindao;

import bussinesslayer.entity.user.Admin;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements IAdminDao{
    @Override
    public Admin getById(int id) throws Exception {
        Admin admin = null;
        try {
            String sql = "select * from admin where id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setAge(resultSet.getInt("age"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setPhoneNumber(resultSet.getString("phone_number"));
                admin.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return admin;
    }

    @Override
    public List<Admin> getAll() throws Exception {
        List<Admin> list = new ArrayList<>();
        try {
            String sql = "select * from admin";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setAge(resultSet.getInt("age"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setPhoneNumber(resultSet.getString("phone_number"));
                admin.setAddress(resultSet.getString("address"));
                list.add(admin);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return list;
    }

    @Override
    public void addNew(Admin space) throws Exception {
        try {
            String sql = "INSERT INTO admin (name, age, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhoneNumber());
            statement.setString(6, space.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void update(Admin space) throws Exception {
        try {
            String sql = "UPDATE admin SET name = ?, age = ?, email = ?, password = ?, phone_number = ?, address = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhoneNumber());
            statement.setString(6, space.getAddress());
            statement.setInt(7, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM admin WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public Admin login(String email, String password) throws Exception {
        Admin admin = null;
        try {
            String sql = "select * from admin where email = ? and password = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setAge(resultSet.getInt("age"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setPhoneNumber(resultSet.getString("phone_number"));
                admin.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return admin;
    }
}
