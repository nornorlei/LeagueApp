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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);


        try {
            //register your mysql jdbc driver with the java application.
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = new DBConnection().getconnection();
            PreparedStatement statement = null;
            statement = conn.prepareStatement("INSERT INTO accounts VALUES (?,?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, (String) session.getAttribute("username"));
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/login");
    }
}