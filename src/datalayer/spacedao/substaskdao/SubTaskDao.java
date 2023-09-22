package datalayer.spacedao.substaskdao;

import bussinesslayer.entity.space.SubTask;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubTaskDao implements ISubTaskDao<SubTask> {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public SubTaskDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        return conn;
    }

    @Override
    public SubTask getById(int id) throws Exception {
        SubTask = null;
        try {
            String sql = "SELECT * FROM SubTask WHERE id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                SubTask = new SubTask();
                SubTask.setId(resultSet.getInt("id"));
                SubTask.setDescription(resultSet.getString("description"));
                SubTask.setName(resultSet.getString("name"));
                SubTask.setStartDate(resultSet.getDate("start_date").toLocalDate());
                SubTask.setEndDate(resultSet.getDate("end_date").toLocalDate());
                SubTask.setProjectId(resultSet.getInt("project_id"));
            }
        }
    }

    @Override
    public List<SubTask> getAll() throws Exception {
        return null;
    }

    @Override
    public void addNew(SubTask space) throws Exception {

    }

    @Override
    public void update(SubTask space) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public void save(SubTask space) throws Exception {

    }
}
