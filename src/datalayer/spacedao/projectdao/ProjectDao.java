package datalayer.spacedao.projectdao;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements IProjectDao {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ProjectDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public Project getById(int id) throws Exception {
        Project project = null;
        try {
            String sql = "SELECT * FROM Project WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setDescription(resultSet.getString("description"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("manager_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public List<Project> getAll() throws Exception {
        List<Project> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Project";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setDescription(resultSet.getString("description"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("manager_id"));
                list.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addNew(Project space) throws Exception {
        try {
            String sql = "INSERT INTO Project (name, description, start_date, end_date, manager_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getManagerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project space) throws Exception {
        try {
            String sql = "UPDATE Project SET name = ?, description = ?, start_date = ?, end_date = ?, manager_id = ? WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, space.getName());
            statement.setString(2, space.getDescription());
            statement.setDate(3, java.sql.Date.valueOf(space.getStartDate()));
            statement.setDate(4, java.sql.Date.valueOf(space.getEndDate()));
            statement.setInt(5, space.getManagerId());
            statement.setInt(6, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Project WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMemberToProject(int projectId, int memberId) {
        try {
            String sql = "INSERT INTO Member_Project (project_id, member_id) VALUES (?, ?)";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.setInt(2, memberId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMemberFromProject(int projectId, int memberId) {
        try {
            String sql = "DELETE FROM Member_Project WHERE project_id = ? AND member_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.setInt(2, memberId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> getAllMemberProject(int projectId, int managerId) {
        List<Member> list = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Member_Project as mp " +
                    "JOIN Project as p on p.id = mp.project_id " +
                    "JOIN Member as m on m.id = mp.member_id " +
                    "WHERE p.id = ? AND p.manager_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.setInt(2, managerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("m.id"));
                member.setName(resultSet.getString("m.name"));
                member.setEmail(resultSet.getString("m.email"));
                member.setPhoneNumber(resultSet.getString("m.phone_number"));
                member.setAge(resultSet.getInt("m.age"));
                member.setAddress(resultSet.getString("m.address"));
                member.setGender(resultSet.getString("m.gender"));
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Project> getAllProject(int userId) {
        List<Project> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Project WHERE manager_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setDescription(resultSet.getString("description"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("manager_id"));
                list.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Project getProjectByMemberId(int projectId, int memberId) {
        Project project = null;
        try {
            String sql = "SELECT distinct * FROM Project as pr " +
                    "JOIN Member_Project as mp ON pr.id = mp.project_id " +
                    "JOIN Member as mb ON mp.member_id = mb.id " +
                    "WHERE pr.id = ? AND mb.id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.setInt(2, memberId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("pr.id"));
                project.setDescription(resultSet.getString("pr.description"));
                project.setName(resultSet.getString("pr.name"));
                project.setStartDate(resultSet.getDate("pr.start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("pr.end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("pr.manager_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public List<Project> getAllProjectMember(int memberId) {
        List<Project> list = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Project as pr " +
                    "JOIN Member_Project as mp ON pr.id = mp.project_id " +
                    "JOIN Member as mb ON mp.member_id = mb.id " +
                    "WHERE mb.id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("pr.id"));
                project.setDescription(resultSet.getString("pr.description"));
                project.setName(resultSet.getString("pr.name"));
                project.setStartDate(resultSet.getDate("pr.start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("pr.end_date").toLocalDate());
                project.setManagerId(resultSet.getInt("pr.manager_id"));
                list.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Member searchMemberInProject(int projectId, int memberId) throws Exception {
        Member member = null;
        try {
            String sql = "SELECT distinct m.id, m.name, m.email, m.phone_number, m.age, m.address, m.gender FROM Member_Project as mp " +
                    "JOIN Project as p on p.id = mp.project_id " +
                    "JOIN Member as m on m.id = mp.member_id " +
                    "WHERE p.id = ? AND m.id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.setInt(2, memberId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getInt("m.id"));
                member.setName(resultSet.getString("m.name"));
                member.setEmail(resultSet.getString("m.email"));
                member.setPhoneNumber(resultSet.getString("m.phone_number"));
                member.setAge(resultSet.getInt("m.age"));
                member.setAddress(resultSet.getString("m.address"));
                member.setGender(resultSet.getString("m.gender"));
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return member;
    }
}
