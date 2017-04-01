import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class found extends HttpServlet {
    public void found(String string) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = new DBConnection().getconnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String get = "insert into api values('"+string+"')"; ;
        Statement statement1 = conn.createStatement();
        ResultSet results = statement1.executeQuery(get);

        while(results.next()){
            String api = results.getString("api");
            String p = "<p>" + api +"<p>";
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("found.jsp").forward(req, resp);
    }
}
