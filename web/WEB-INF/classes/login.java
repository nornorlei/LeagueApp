import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");



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
                System.out.println(pass);

                 if (password.equals(pass)){
                     HttpSession session = request.getSession();
                     session.setAttribute("username", username);
                    response.sendRedirect("Profile.jsp");
                }
                else{
                    response.sendRedirect("login.jsp");
                }
            }
            if(empty == true){
                response.sendRedirect("login.jsp");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }
}
