package datalayer.documentdao;

import bussinesslayer.entity.document.Document;
import datalayer.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao implements IDocumentDao {
    @Override
    public Document getById(int id) throws Exception {
        Document doc = new Document();
        try {
            String sql = "SELECT * FROM Document WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doc.setId(resultSet.getInt("id"));
                doc.setTitle(resultSet.getString("title"));
                doc.setDescription(resultSet.getString("description"));
                doc.setContent(resultSet.getString("content"));
                doc.setProjectId(resultSet.getInt("project_id"));
            }
        } catch (SQLException e) {
            throw new Exception();
        }
        return doc;
    }

    @Override
    public List<Document> getAll() throws Exception {
        List<Document> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Document";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Document doc = new Document();
                doc.setId(resultSet.getInt("id"));
                doc.setTitle(resultSet.getString("title"));
                doc.setDescription(resultSet.getString("description"));
                doc.setContent(resultSet.getString("content"));
                doc.setProjectId(resultSet.getInt("project_id"));
                list.add(doc);
            }
        } catch (SQLException e) {
            throw new Exception();
        }
        return list;
    }

    @Override
    public void addNew(Document space) throws Exception {
        try {
            String sql = "INSERT INTO Document (title, description, content, project_id) VALUES (?, ?, ?, ?)";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getTitle());
            statement.setString(2, space.getDescription());
            statement.setString(3, space.getContent());
            statement.setInt(4, space.getProjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    @Override
    public void update(Document space) throws Exception {
        try {
            String sql = "UPDATE Document SET title = ?, description = ?, content = ?, project_id = ? WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, space.getTitle());
            statement.setString(2, space.getDescription());
            statement.setString(3, space.getContent());
            statement.setInt(4, space.getProjectId());
            statement.setInt(5, space.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            String sql = "DELETE FROM Document WHERE id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    @Override
    public List<Document> getAllDocumentsByProjectId(int projectId) throws Exception {
        List<Document> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Document WHERE project_id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Document doc = new Document();
                doc.setId(resultSet.getInt("id"));
                doc.setTitle(resultSet.getString("title"));
                doc.setDescription(resultSet.getString("description"));
                doc.setContent(resultSet.getString("content"));
                doc.setProjectId(resultSet.getInt("project_id"));
                list.add(doc);
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return list;
    }

    @Override
    public Document getDocument(int projectId, int documentId) throws Exception {
        Document doc = new Document();
        try {
            String sql = "SELECT * FROM Document WHERE project_id = ? AND id = ?";
            Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.setInt(2, documentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doc.setId(resultSet.getInt("id"));
                doc.setTitle(resultSet.getString("title"));
                doc.setDescription(resultSet.getString("description"));
                doc.setContent(resultSet.getString("content"));
                doc.setProjectId(resultSet.getInt("project_id"));
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return doc;
    }
}
