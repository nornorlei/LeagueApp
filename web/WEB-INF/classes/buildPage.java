import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class buildPage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String post_id = (String) request.getParameter("postID");
        ArrayList <String> buildList = new <String> ArrayList();
        buildList.add("");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement = conn.createStatement();
            String query = "select* from Builds where postID=" + post_id;

                ResultSet build = statement.executeQuery(query);
                while(build.next()){
                    buildList.add("<h1><font size=\"18\">" + build.getString("name") + "</font></h1>");
                    String build_id = build.getString("buildID");
                    String postID = build.getString("postID");
                    String likes = build.getString("likes");
                    String dislikes = build.getString("dislikes");
                    String ability_sequence = build.getString("abilities");

                    buildList.add("<p> Likes: " + likes + "</p>");
                    buildList.add("<p> Dislikes: " + dislikes + "</p>");
                    buildList.add("<p> Ability Sequence: " + ability_sequence + "</p>");

                    System.out.println( " " +build_id + " " + postID + " " + likes + " " + dislikes + " Abilities " + ability_sequence );

                }
                String formattedList = buildList.toString().replace(",","").replace("[","").replace("]","");
                request.getSession().setAttribute("buildList",formattedList);
                System.out.println(buildList);
                response.sendRedirect("/buildPage");

        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("buildPage.jsp").forward(req, resp);
    }
}
