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
        findSum.makeRequest(summonerName,region);

        ArrayList<String> masteries = new ArrayList();
        ArrayList<String> runes = new ArrayList();

        System.out.println("break 4");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();
            String tempCountQuery = "select count(*) from temp_variables";
            Statement statement0 = conn.createStatement();

            Statement statement1 = conn.createStatement();
            ResultSet tempCount = statement0.executeQuery(tempCountQuery);

            System.out.println("break 5 ");

            while (tempCount.next()) {
                int temp_Count = Integer.parseInt(tempCount.getString("count(*)"));
                if (temp_Count > 0) {
                    String query = "select * from temp_variables limit 1";
                    Statement statement = conn.createStatement();
                    ResultSet summName = statement.executeQuery(query);

                    System.out.println("break 5");

                    while (summName.next()) {
                        String sumName = summName.getString("name");
                        String id = summName.getString("id");
                        System.out.println("break 6");


                        if (sumName.equals(null)){
                            response.sendRedirect("/findSum.jsp");
                            findSum.clearTempDB();
                            System.out.println("1");
                        } else if (sumName.equals("")){
                            response.sendRedirect("/findSum.jsp");
                            findSum.clearTempDB();
                            System.out.println("1");
                        } else if (sumName.equals("null")){
                            response.sendRedirect("/findSum.jsp");
                            findSum.clearTempDB();
                            System.out.println("1");
                        } else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("matchhistory")){
                            response.sendRedirect("/matchhistory.jsp");
                            findSum.clearTempDB();
                            System.out.println("1");
                        } else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("rankedstats")){
                            response.sendRedirect("/rankedStats.jsp");
                            findSum.clearTempDB();
                            System.out.println("1");
                        }else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("runesmasteries")){

                            findSum.makeMasteriesRequest(id,region);
                            findSum.makeRunesRequest(id,region);
                            String tempMasteries = "select * from temp_runes_masteries";
                            ResultSet mastery = statement0.executeQuery(tempMasteries);

                            while (mastery.next()) {

                                String mast = mastery.getString("masteries");
                                String r = mastery.getString("runes");
                                runes.add(0,r);
                                masteries.add(0,mast);

                            }


                            String formattedList2 = runes.toString().replace(",","").replace("[","").replace("]","");
                            request.getSession().setAttribute("runeList",formattedList2);

                            String formattedList1 = masteries.toString().replace(",","").replace("[","").replace("]","");
                            request.getSession().setAttribute("masteryList",formattedList1);


                            findSum.clearTempDB();
                            findSum.clearTempMasteries();
                            response.sendRedirect("/runesMasteries.jsp");

                        }else if ((!sumName.equals(null)) && (!sumName.equals("")) && (!sumName.equals("null")) && stat.equals("championmasteries")){
                            response.sendRedirect("/championMasteries.jsp");
                            findSum.clearTempDB();
                            System.out.println("1");
                        }


                    }
                }else{

                    System.out.println("break 7 ");
                    response.sendRedirect("/findSum.jsp");
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }

    }
}
