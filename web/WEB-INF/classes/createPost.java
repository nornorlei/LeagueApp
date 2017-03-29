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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.Date;


public class createPost extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String champ = (String) request.getParameter("champ");
        String post_count;
        String buildName = (String) request.getParameter("post_name");
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
        System.out.println(champ +" " + buildName + " " +ss1+ " " +ss2+ " " +marks+ " " +seals+ " " +glyphs+ " " +quints+ " " +i1+ " " +i2+ " " +i3+ " " +i4+ " " +i5+ " " +i6+ " " +ability_sequence+ " " +masteries);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            String postCountQuery = "select count(*) from Posts";
            String getChampIDQuery = "select champID from Champions where name='" + champ + "'";
            Statement statement1 = conn.createStatement();
            ResultSet postCount = statement1.executeQuery(postCountQuery);
            while (postCount.next()) {
                post_count = postCount.getString("count(*)");
                String post_num = Integer.toString(Integer.parseInt(post_count) + 1);
                System.out.println(post_num);
                Statement statement2 = conn.createStatement();
                ResultSet id = statement2.executeQuery(getChampIDQuery);
                while (id.next()) {
                    String champID = id.getString("champID");

                    PreparedStatement statement3 = null;
                    PreparedStatement statement4 = null;
                    System.out.println(post_num + " " + champID + " " + buildName);
                    statement3 = conn.prepareStatement("INSERT INTO Posts VALUES (?,?)");
                    statement3.setString(1, post_num);
                    statement3.setString(2, post_num);
                    statement3.executeUpdate();

                    System.out.println(post_num + " " +ss1+ " " +ss2+ " " +marks+ " " +seals+ " " +glyphs+ " " +quints+ " " +i1+ " " +i2+ " " +i3+ " " +i4+ " " +i5+ " " +i6+ " " +ability_sequence+ " " +masteries);
                    statement4 = conn.prepareStatement("INSERT INTO Builds VALUES (?,?,?,?,?,?,?,?,?)");
                    statement4.setString(1,post_num);
                    statement4.setString(2,post_num);
                    statement4.setString(3,buildName);
                    statement4.setString(4, (String)request.getSession().getAttribute("username"));
                    statement4.setString(5, dateFormat.format(date));
                    statement4.setInt(6,0);
                    statement4.setInt(7,0);
                    statement4.setString(8,ability_sequence);
                    statement4.setInt(9,Integer.parseInt(champID));
                    statement4.executeUpdate();
                }
                response.sendRedirect("/champs");
            }

        }catch(SQLException e){
            System.out.println(e);
        } catch(ClassNotFoundException e){
            System.out.println(e);
        }


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("createPost.jsp").forward(req, resp);
    }

}