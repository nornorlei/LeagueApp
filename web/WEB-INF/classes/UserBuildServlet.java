import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import com.google.gson.JsonObject;
import java.sql.Connection;
import com.google.gson.JsonArray;

public class UserBuildServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        Writer writer = response.getWriter();
        String result;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement = conn.createStatement();
            String query = "select * from posts where post_id=" + id;

            ResultSet post_name = statement.executeQuery(query);
            if(post_name.next()) {

                int postid = post_name.getInt("post_id");
                int champid = post_name.getInt("champ_id");

                String queryname = "select * from ChampionList where champ_id=" + post_name.getInt("champ_id");
                ResultSet champ = statement.executeQuery(queryname);
                String champion = "";
                if(champ.next()) {
                    champion = champ.getString("champ_name");
                }
                object.addProperty("PostID", postid);
                object.addProperty("champid", champid);
                object.addProperty("Champion name", champion);

                String results = object.toString();
                response.setContentType("application/json");

                System.out.println("works");
                writer.write(results);
                writer.flush();
            }else{

                String query1 = "select post_id from posts";
                ResultSet post_name1 = statement.executeQuery(query1);

                while(post_name1.next()) {
                    array.add(post_name1.getInt("post_id"));
                }
                String results = array.toString();
                response.setContentType("application/json");

                writer.write(results);
                writer.flush();
                System.out.println("here");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}