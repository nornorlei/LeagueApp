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
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            Statement statement = conn.createStatement();
            ResultSet postCount = statement.executeQuery(postCountQuery);
            while(postCount.next()) {
                post_count = postCount.getString("count(*)");





                response.sendRedirect("/champs.jsp");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }




    }
}