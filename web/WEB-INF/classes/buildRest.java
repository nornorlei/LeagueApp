import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import com.google.gson.JsonObject;
import java.sql.Connection;
import com.google.gson.JsonArray;

public class buildRest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        Writer writer = resp.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement = conn.createStatement();
            String query = "select * from Builds where build_id=" + id;

            ResultSet post_name = statement.executeQuery(query);
            if(post_name.next()) {
                String ss1 = post_name.getString("ss1");
                String ss2 = post_name.getString("ss1");
                String marks = post_name.getString("marks");
                String sequence = post_name.getString("ability_sequence");

                object.addProperty("Summoner Spell 1", ss1);
                object.addProperty("Summoner Spell 2", ss2);
                object.addProperty("Marks", marks);
                object.addProperty("Sequence", sequence);

                String results = object.toString();
                resp.setContentType("application/json");

                System.out.println("works");
                writer.write(results);
                writer.flush();
            }else{

                String query1 = "select build_id from Builds";
                ResultSet post_name1 = statement.executeQuery(query1);

                while(post_name1.next()) {
                    array.add(post_name1.getInt("build_id"));
                }
                String results = array.toString();
                resp.setContentType("application/json");

                writer.write(results);
                writer.flush();
                System.out.println("here");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
