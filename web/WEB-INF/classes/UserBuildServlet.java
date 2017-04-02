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
import org.json.JSONException;
import org.json.JSONObject;

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
            String query = "select * from Posts where postID=" + id;

            ResultSet post_name = statement.executeQuery(query);
            if(post_name.next()) {

                int postid = post_name.getInt("postID");

                String queryname = "select * from Builds where postID=" + postid;
                ResultSet champ = statement.executeQuery(queryname);
                String champion = "";
                if(champ.next()) {
                     champion = champ.getString("champID");
                }
                object.addProperty("PostID", postid);
                object.addProperty("Champion", champion);

                String results = object.toString();
                response.setContentType("application/json");

                System.out.println("works");
                writer.write(results);
                writer.flush();
            }else{

                String query1 = "select postID from Posts";
                ResultSet post_name1 = statement.executeQuery(query1);

                while(post_name1.next()) {
                    array.add(post_name1.getInt("postID"));
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