import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// this is basically just a drop down menu page with all the champions

public class champPage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String champ = (String) request.getParameter("champ");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            String query1 = "select champ_id from ChampionList where champ_name='" + champ + "'";

            Statement statement = conn.createStatement();
            ResultSet champ_id = statement.executeQuery(query1);
            while(champ_id.next()) {
                String id = champ_id.getString("champ_id");
                System.out.println(id);

                String query2 = "select* from posts where champ_id=" + id;
                ResultSet posts = statement.executeQuery(query2);

                while(posts.next()){
                    String postName = posts.getString("name");
                    String postID = posts.getString("post_id");
                    String champID = posts.getString("champ_id");

                    System.out.println(postID + " " + champID + " " + postName);
                }

            }
            response.sendRedirect("/champPage.jsp");
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }




    }
}