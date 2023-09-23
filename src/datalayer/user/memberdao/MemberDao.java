package datalayer.user.memberdao;

import bussinesslayer.entity.user.Member;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao implements IMemberDao<Member> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet =   null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }
    @Override
    public Member getById(int id) throws Exception {
        Member member = new Member();
        try {
            String sql = "SELECT * FROM Member WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setAge(resultSet.getInt("age"));
                member.setEmail(resultSet.getString("email"));
                member.setPassword(resultSet.getString("password"));
                member.setPhone_number(resultSet.getString("phone_number"));
                member.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> getAll() throws Exception {
        List<Member> list = null;
        try {
            String sql = "SELECT * FROM Member";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setAge(resultSet.getInt("age"));
                member.setEmail(resultSet.getString("email"));
                member.setPassword(resultSet.getString("password"));
                member.setPhone_number(resultSet.getString("phone_number"));
                member.setAddress(resultSet.getString("address"));
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
            String sql = "INSERT INTO Member (name, age, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
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
    public void update(Member space) throws Exception {
        try {
            String sql = "UPDATE Member SET name = ?, age = ?, email = ?, password = ?, phone_number = ?, address = ? WHERE id = ?";
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
            String sql = "DELETE FROM Member WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Member space) throws Exception {
        try {
            String sql = "INSERT INTO Member (name, age, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
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
