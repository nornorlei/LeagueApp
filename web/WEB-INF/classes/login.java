import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        APIConnection api = new APIConnection();
       // api.fillMasteries();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            String query = "select password from Users where username ='" + username + "'";
            Statement statement = conn.createStatement();
            ResultSet pw = statement.executeQuery(query);
            boolean empty = true;
            while(pw.next()) {
                empty = false;
                String pass = pw.getString("password");
                 if (password.equals(pass)){
                     HttpSession session = request.getSession();
                     session.setAttribute("username", username);
                    response.sendRedirect("/Profile");
                }
                else{
                    response.sendRedirect("/login");
                }
            }
            if(empty == true){
                response.sendRedirect("/login");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
