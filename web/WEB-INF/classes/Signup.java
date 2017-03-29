import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class Signup extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            PreparedStatement statement = null;
            statement = conn.prepareStatement("Select * FROM Users WHERE username = ? OR email = ?");
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet result = statement.executeQuery();

            if(result.first()) {
                resp.sendRedirect("/signup");
            }
            else{
                session.setAttribute("username", username);
                statement = conn.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)");
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, (String) session.getAttribute("username"));
                statement.executeUpdate();

                resp.sendRedirect("/login");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }
}
