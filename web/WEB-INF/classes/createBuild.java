import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;



public class createBuild extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String champ = (String) request.getParameter("champ");
        String post_count;
        String post_name = (String) request.getParameter("post_name");
        String ss1 = (String) request.getParameter("ss1");
        String ss2 = (String) request.getParameter("ss2");
        String marks = (String) request.getParameter("marks");
        String seals = (String) request.getParameter("seals");
        String glyphs = (String) request.getParameter("glyphs");
        String quints = (String) request.getParameter("quints");
        String i1 = (String) request.getParameter("i1");
        String i2 = (String) request.getParameter("i2");
        String i3 = (String) request.getParameter("i3");
        String i4 = (String) request.getParameter("i4");
        String i5 = (String) request.getParameter("i5");
        String i6 = (String) request.getParameter("i6");
        String ability_sequence = (String) request.getParameter("ability_sequence");
        String masteries = (String) request.getParameter("masteries");



        try {
            String postCountQuery = "select count(*) from posts";
            String getChampIDQuery = "select champ_id from ChampionList where champ_name='" + champ + "'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement1 = conn.createStatement();
            ResultSet postCount = statement1.executeQuery(postCountQuery);
            while(postCount.next()) {
                post_count = postCount.getString("count(*)");
                String post_num = Integer.toString(Integer.parseInt(post_count) + 1);

                Statement statement2 = conn.createStatement();
                ResultSet id = statement2.executeQuery(getChampIDQuery);
                while(id.next()) {
                    String champID = id.getString("champ_id");

                    PreparedStatement statement3 = null;
                    PreparedStatement statement4 = null;...

                    statement3 = conn.prepareStatement("INSERT INTO posts VALUES (?,?,?)");
                    statement3.setString(1, post_num);
                    statement3.setString(2, champID);
                    statement3.setString(3, post_name);
                    statement3.executeUpdate();

                    statement4 = conn.prepareStatement("INSERT INTO Builds VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");



                }


                response.sendRedirect("/champs.jsp");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }




    }
}