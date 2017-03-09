import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class buildPage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String post_id = (String) request.getParameter("postID");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement = conn.createStatement();
            String query = "select* from Builds where post_id=" + post_id;
            String query2 = "select name from posts where post_id=" +post_id;
            ResultSet build = statement.executeQuery(query);

            while(build.next()){
                String build_id = build.getString("build_id");
                String postID = build.getString("post_id");
                String likes = build.getString("likes");
                String dislikes = build.getString("dislikes");
                String ss1 = build.getString("ss1");
                String ss2 = build.getString("ss2");
                String marks = build.getString("marks");
                String seals = build.getString("seals");
                String glyphs = build.getString("glyphs");
                String quints = build.getString("quints");
                String i1 = build.getString("i1");
                String i2 = build.getString("i2");
                String i3 = build.getString("i3");
                String i4 = build.getString("i4");
                String i5 = build.getString("i5");
                String i6 = build.getString("i6");
                String ability_sequence = build.getString("ability_sequence");
                String masteries = build.getString("masteries");

                ResultSet post_name = statement.executeQuery(query2);
                while (post_name.next()){

                    String name = post_name.getString("name");
                    System.out.println(name + " " +build_id + " " + postID + " " + likes + " " + dislikes + " " + ss1 + " " + ss2+ " "+marks + " "+seals + " "+ glyphs+ " "+ quints+ " "+ i1+ " "+ i2+ " "+ i3+ " "+ i4+ " "+i5 + " "+i6 + " "+ ability_sequence + " "+ masteries);

                    response.sendRedirect("/buildPage.jsp");

                }
            }



        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }


    }
}
