package datalayer;

import java.sql.Connection;

public interface IDaoFactory {
    public Connection getConnection();
}
