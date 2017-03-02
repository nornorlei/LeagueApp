import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class Signup extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        try {
            Connection conn = new DBConnection().getconnection();
            PreparedStatement statement = null;

            statement = conn.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, (String) session.getAttribute("username"));
            statement.executeQuery();
        }catch (SQLException e){
            System.out.println(e);
            resp.sendRedirect("/login");
        }

    }
}
