package datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection implements IConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/task_management_tool";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@Thienphu2004";

    private static MySqlConnection mySqlConnection = null;

    private MySqlConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
    public static MySqlConnection getInstance() {
        if (mySqlConnection == null) {
            mySqlConnection = new MySqlConnection();
        }
        return mySqlConnection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
