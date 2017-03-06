import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    private static final String user = "panda";
    private static final String password = "baobao";
    private static final String url = "jdbc:mysql://pi.cs.oswego.edu:3306/cayuga";
    //private static final String url = "jdbc:mysql://localhost:3306/project";


    public Connection getconnection() throws SQLException{

        return DriverManager.getConnection(url, user, password);
    }

}
