import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class findSum extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //call the RESTful thing here to fetch the information
        //redirect to results.jsp
        String summonerName = request.getParameter("summonerName");
        String region =  request.getParameter("region");
        String stat = request.getParameter("stat");
//sdfsdfsdfsdgfdfsgdsfgds
        request.getSession().setAttribute("summonerName", summonerName);

        APIConnection findSum= new APIConnection();
        findSum.findSummoner(summonerName,region);

        System.out.println("break 4");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            String tempCountQuery = "select count(*) from temp_variables";
            Statement statement0 = conn.createStatement();
            ResultSet tempCount = statement0.executeQuery(tempCountQuery);

            System.out.println("break 5 ");

            while (tempCount.next()) {
                int temp_Count = Integer.parseInt(tempCount.getString("count(*)"));
                if (temp_Count > 0) {
                    String query = "select temp from temp_variables limit 1";
                    Statement statement = conn.createStatement();
                    ResultSet summName = statement.executeQuery(query);

                    System.out.println("break 5");

                    while (summName.next()) {
                        String sumName = summName.getString("temp");

                        System.out.println("break 6");


                        if (sumName.equals(null)){
                            response.sendRedirect("/findSum");
                            System.out.println("1");
                        } else if (sumName.equals("")){
                            response.sendRedirect("/findSum");
                            System.out.println("1");
                        } else if (sumName.equals("null")){
                            response.sendRedirect("/findSum");
                            System.out.println("1");
                        } else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("matchhistory")){
                            response.sendRedirect("/matchhistory");
                            System.out.println("1");
                        } else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("rankedstats")){
                            response.sendRedirect("/rankedStats");
                            System.out.println("1");
                        }else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("runesmasteries")){
                            response.sendRedirect("/runesMasteries");
                            System.out.println("1");
                        }else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("championmasteries")){
                            response.sendRedirect("/championMasteries");
                            System.out.println("1");
                        }


                    }
                }else{

                    System.out.println("break 7 ");
                    response.sendRedirect("/findSum");
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("findSum.jsp").forward(req, resp);
    }
}
