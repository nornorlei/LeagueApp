import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    private static final String user = "root";
    private static final String password = "123";
    //private static final String url = "jdbc:mysql://pi.cs.oswego.edu:3306/cayuga";
    private static final String url = "jdbc:mysql://localhost:3306/users";


    public Connection getconnection() throws SQLException{

        return DriverManager.getConnection(url, user, password);
    }

}
