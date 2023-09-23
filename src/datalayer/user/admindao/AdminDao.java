package datalayer.user.admindao;

import bussinesslayer.entity.user.Admin;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao implements IAdminDao<Admin> {
    // --------------------- Properties ------------------------
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet =   null;

    // --------------------- Constructor ------------------------
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    // --------------------- Getter and Setter ------------------------

    @Override
    public Admin getById(int id) throws Exception {
        Admin admin = new Admin();
        try {
            String sql = "SELECT * FROM Admin WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setAge(resultSet.getInt("age"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setPhone_number(resultSet.getString("phone_number"));
                admin.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Admin> getAll() throws Exception {
        List<Admin> list = null;
        try {
            String sql = "SELECT * FROM Admin";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setAge(resultSet.getInt("age"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setPhone_number(resultSet.getString("phone_number"));
                admin.setAddress(resultSet.getString("address"));
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Admin space) throws Exception {
        try {
            String sql = "INSERT INTO Admin (name, age, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
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
    public void update(Admin space) throws Exception {
        try {
            String sql = "UPDATE Admin SET name = ?, age = ?, email = ?, password = ?, phone_number = ?, address = ? WHERE id = ?";
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
            String sql = "DELETE FROM Admin WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Admin space) throws Exception {
        try {
            String sql = "INSERT INTO Admin (name, age, email, password, phone_number, address) VALUES (?,?,?,?,?,?)";
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
