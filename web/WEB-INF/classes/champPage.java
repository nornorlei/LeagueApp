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



public class champPage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String champ = (String) request.getParameter("champ");
        ArrayList<String> postList = new <String> ArrayList();
        postList.add(0,"");
        PrintWriter out = response.getWriter();



        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            String query1 = "select champ_id from ChampionList where champ_name='" + champ + "'";
            int count = 1;
            Statement statement = conn.createStatement();
            ResultSet champ_id = statement.executeQuery(query1);
            while(champ_id.next()) {
                String id = champ_id.getString("champ_id");
                //request.getSession().setAttribute("champ_id",id);
                System.out.println(id);


                String query2 = "select* from posts where champ_id=" + id;
                ResultSet posts = statement.executeQuery(query2);

                while(posts.next()){
                    String postName = posts.getString("name");
                    String postID = posts.getString("post_id");
                    String champID = posts.getString("champ_id");


                    postList.add(count, "<p>" + postName + "<input type=\"submit\" value=\"" + postID +"\" name=\"postID\" />" + "</p>");
                    count++;
                    System.out.println(postID + " " + champID + " " + postName);
                }
                String formattedList = postList.toString().replace(",","").replace("[","").replace("]","");
                request.getSession().setAttribute("postList",formattedList);
                System.out.println(postList);
                response.sendRedirect("/champPage.jsp");
            }
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }




    }
}