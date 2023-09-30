package datalayer.user.managerdao;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                manager.setGender(resultSet.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() throws Exception {
        List<Manager> list = new ArrayList<>();
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
                manager.setGender(resultSet.getString("gender"));
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
            String sql = "INSERT INTO Manager (name, age, email, password, phone_number, address, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setInt(2, space.getAge());
            statement.setString(3, space.getEmail());
            statement.setString(4, space.getPassword());
            statement.setString(5, space.getPhoneNumber());
            statement.setString(6, space.getAddress());
            statement.setString(7, space.isGender());
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
            statement.setString(7, space.isGender());
            statement.setInt(8, space.getId());
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

    @Override
    public List<Member> viewAllMember(int managerId) {
        List<Member> list = new ArrayList<>();
        try {
            String sql = "SELECT distinct m.id, m.name, m.age, m.email, m.gender, m.phone_number, m.role, m.address, m.password FROM Manager as ma " +
                    "INNER JOIN Project as p ON p.manager_id = ma.id " +
                    "INNER JOIN Member_Project as mp ON mp.project_id = p.id " +
                    "INNER JOIN Member as m ON m.id = mp.member_id "+
                    "WHERE ma.id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, managerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("m.id"));
                member.setName(resultSet.getString("m.name"));
                member.setAge(resultSet.getInt("m.age"));
                member.setGender(resultSet.getString("m.gender"));
                member.setEmail(resultSet.getString("m.email"));
                member.setPassword(resultSet.getString("m.password"));
                member.setPhoneNumber(resultSet.getString("m.phone_number"));
                member.setAddress(resultSet.getString("m.address"));
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Manager loginManager(String email, String password) {
        Manager manager = null;
        try {
            String sql = "SELECT * FROM Manager WHERE email = ? AND password = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manager = new Manager();
                manager.setId(resultSet.getInt("id"));
                manager.setName(resultSet.getString("name"));
                manager.setAge(resultSet.getInt("age"));
                manager.setEmail(resultSet.getString("email"));
                manager.setPassword(resultSet.getString("password"));
                manager.setPhoneNumber(resultSet.getString("phone_number"));
                manager.setAddress(resultSet.getString("address"));
                manager.setGender(resultSet.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    @Override
    public List<Admin> getByEmail(String email) {
        List<Admin> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Admin WHERE email = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
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
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void changePassword(int id, String password) {
        try {
            String sql = "UPDATE Manager SET password = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeEmail(int id, String email) {
        try {
            String sql = "UPDATE Manager SET email = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
