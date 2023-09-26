package datalayer.user.memberdao;

import bussinesslayer.entity.user.Member;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao implements IMemberDao {
    @Override
    public Member getById(int id) throws Exception {
        Member member = new Member();
        try {
            String sql = "SELECT * FROM Member WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setEmail(resultSet.getString("email"));
                member.setPhoneNumber(resultSet.getString("phone_number"));
                member.setAge(resultSet.getInt("age"));
                member.setAddress(resultSet.getString("address"));
                member.setGender(resultSet.getString("gender"));
                member.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> getAll() throws Exception {
        List<Member> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Member";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setEmail(resultSet.getString("email"));
                member.setPhoneNumber(resultSet.getString("phone_number"));
                member.setAge(resultSet.getInt("age"));
                member.setAddress(resultSet.getString("address"));
                member.setGender(resultSet.getString("gender"));
                member.setRole(resultSet.getString("role"));
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Member space) throws Exception {
        try {
            String sql = "INSERT INTO Member (name, email, phone_number, age, address, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getEmail());
            statement.setString(3, space.getPhoneNumber());
            statement.setInt(4, space.getAge());
            statement.setString(5, space.getAddress());
            statement.setString(6, space.isGender());
            statement.setString(7, space.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Member space) throws Exception {
        try {
            String sql = "UPDATE Member SET name = ?, email = ?, phone_number = ?, age = ?, address = ?, gender = ?, role = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getEmail());
            statement.setString(3, space.getPhoneNumber());
            statement.setInt(4, space.getAge());
            statement.setString(5, space.getAddress());
            statement.setString(6, space.isGender());
            statement.setString(7, space.getRole());
            statement.setInt(8, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Member WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> getByEmail(String email) {
        List<Member> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Member WHERE email = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setEmail(resultSet.getString("email"));
                member.setPhoneNumber(resultSet.getString("phone_number"));
                member.setAge(resultSet.getInt("age"));
                member.setAddress(resultSet.getString("address"));
                member.setGender(resultSet.getString("gender"));
                member.setRole(resultSet.getString("role"));
                list.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Member loginMember(String email, String password) {
        Member member = null;
        try {
            String sql = "SELECT * FROM Member WHERE email = ? AND password = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setEmail(resultSet.getString("email"));
                member.setPhoneNumber(resultSet.getString("phone_number"));
                member.setAge(resultSet.getInt("age"));
                member.setAddress(resultSet.getString("address"));
                member.setGender(resultSet.getString("gender"));
                member.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public void changeEmail(int id, String email) {
        try {
            String sql = "UPDATE Member SET email = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(int id, String password) {
        try {
            String sql = "UPDATE Member SET password = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
