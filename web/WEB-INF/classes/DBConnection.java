import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    private static final String user = "panda";
    private static final String password = "baobao";
    private static final String url = "jdbc:myql://pi.cs.oswego.edu:3306/cayuga";
    public Connection connection;

    public Connection getconnection() throws SQLException{
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public void close() throws SQLException{
        if(connection != null){
                connection.close();
        }
    }

}
