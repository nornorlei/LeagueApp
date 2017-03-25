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
        buildList.add(0,"");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement = conn.createStatement();
            String query = "select* from Builds where post_id=" + post_id;
            String query2 = "select name from posts where post_id=" +post_id;

            ResultSet post_name = statement.executeQuery(query2);

            while (post_name.next()){
                String name = post_name.getString("name");
                buildList.add(1,"<h1><font size=\"18\">" + name + "</font></h1>");

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

                    buildList.add(2,"<p> Likes: " + likes + "</p>");
                    buildList.add(3,"<p> Dislikes: " + dislikes + "</p>");
                    buildList.add(4,"<p> Summoner Skill 1: " + ss1 + "</p>");
                    buildList.add(5,"<p> Summoner Skill 2: " + ss2 + "</p>");
                    buildList.add(6,"<p> Marks: " + marks + "</p>");
                    buildList.add(7,"<p> Seals: " + seals + "</p>");
                    buildList.add(8,"<p> Glyphs: " + glyphs + "</p>");
                    buildList.add(9,"<p> Quints: " + quints + "</p>");
                    buildList.add(10,"<p> Item 1: " + i1 + "</p>");
                    buildList.add(11,"<p> Item 2: " + i2 + "</p>");
                    buildList.add(12,"<p> Item 3: " + i3 + "</p>");
                    buildList.add(13,"<p> Item 4: " + i4 + "</p>");
                    buildList.add(14,"<p> Item 5: " + i5 + "</p>");
                    buildList.add(15,"<p> Item 6: " + i6 + "</p>");
                    buildList.add(16,"<p> Ability Sequence: " + ability_sequence + "</p>");
                    buildList.add(17,"<p> Masteries: " + masteries + "</p>");

                    System.out.println(name + " " +build_id + " " + postID + " " + likes + " " + dislikes + " " + ss1 + " " + ss2+ " "+marks + " "+seals + " "+ glyphs+ " "+ quints+ " "+ i1+ " "+ i2+ " "+ i3+ " "+ i4+ " "+i5 + " "+i6 + " "+ ability_sequence + " "+ masteries);



                }
                String formattedList = buildList.toString().replace(",","").replace("[","").replace("]","");
                request.getSession().setAttribute("buildList",formattedList);
                System.out.println(buildList);
                response.sendRedirect("/buildPage.jsp");
            }



        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }


    }
}
