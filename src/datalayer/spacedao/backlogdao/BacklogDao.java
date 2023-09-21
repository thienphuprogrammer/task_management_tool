package datalayer.spacedao.backlogdao;

import java.util.List;

public class BacklogDao<Backlog> implements IBacklogDao<Backlog> {
    public BacklogDao() {
    }

    @Override
    public Backlog getById(int id) throws Exception {
//        Backlog backlog = null;
//        String sql = "SELECT * FROM Backlog WHERE id = ?";
//
//        try (Connection connection = DaoFactory.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
////                    backlog = new Backlog();
////                    backlog.setId(resultSet.getInt("id"));
////                    backlog.setName(resultSet.getString("name"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return backlog;
        return null;
    }

    @Override
    public List<Backlog> getAll() throws Exception {
        return null;
    }

    @Override
    public void addNew(Backlog space) throws Exception {

    }

    @Override
    public void update(Backlog space) throws Exception {

    }

    @Override
    public void delete(Backlog space) throws Exception {

    }

    @Override
    public void save(Backlog space) throws Exception {

    }
}
