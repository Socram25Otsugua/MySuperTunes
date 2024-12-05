package dk.easv.mytunes.DAL;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;


public class DBConnection {
    private final SQLServerDataSource ds;

    public DBConnection() {

        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTunes7");
        ds.setUser("CSe2024b_e_19");
        ds.setPassword("CSe2024bE19!24");
        ds.setServerName("EASV-DB4");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }

   /* public static void main(String[] args) {
        try {
            DBConnection dbConnection = new DBConnection(); // Create an instance
            try (Connection conn = dbConnection.getConnection()) { // Use the instance to get a connection
                System.out.println("Connection successful!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    */
}
