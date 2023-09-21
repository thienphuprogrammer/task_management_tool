package datalayer;

import java.sql.Connection;

public class DaoFactory implements IDaoFactory {
    private Connection connection;
    private IFileManager fileManager;

    public DaoFactory(Connection connection, IFileManager fileManager) {
        this.connection = connection;
        this.fileManager = fileManager;
    }

    @Override
    public IConnection getConnection() {
        return new MySqlConnection();
    }
}
