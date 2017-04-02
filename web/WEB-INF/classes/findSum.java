import org.json.JSONObject;

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
import java.util.Map;
import java.util.Set;

public class findSum extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //call the RESTful thing here to fetch the information
        //redirect to results.jsp
        String summonerName = request.getParameter("summonerName");
        String region =  request.getParameter("region");
        String stat = request.getParameter("stat");

        request.getSession().setAttribute("summonerName", summonerName);

        APIConnection findSum= new APIConnection();
       // findSum.findSummoner(summonerName,region);

        System.out.println("break 4");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = new DBConnection().getconnection();

            System.out.println("break 5 ");

                        if (summonerName.equals(null)){
                            response.sendRedirect("/findSum");
                            System.out.println("1");
                        } else if (summonerName.equals("")){
                            response.sendRedirect("/findSum");
                            System.out.println("1");
                        } else if (summonerName.equals("null")){
                            response.sendRedirect("/findSum");
                            System.out.println("1");
                        }else if ((!summonerName.equals(null)) && (!summonerName.equals("")) && (!summonerName.equals("null")) && !region.equals(null) && stat.equals("runes")){
                            APIConnection api = new APIConnection();
                            Map<String, Object> map = api.findSummoner(summonerName, region);
                            ArrayList<Map> runes = api.summonerRunes((int)map.get("id"), region);
                            Set runeSet;
                            //System.out.println(runes.get(0).keySet().);
                          /*  for(int i = 0; i < runes.size(); i++){
                                runeSet = runes.get(i).keySet();
                                for(Object e : runeSet){
                                    System.out.println("rune" + i + "runes " + e + "slot " + runes.get(i).get(e));
                                }
                               // System.out.println("runes "+ i + "slot "+ runes.get(i));
                            }*/
                            request.setAttribute("map", runes);
                            request.setAttribute("name", summonerName);
                            System.out.println("here");
                            request.getRequestDispatcher("runes.jsp").forward(request,response);
                            response.sendRedirect(request.getContextPath() + "/runes.jsp");
                            System.out.println("after");
                        }else if ((!summonerName.equals(null)) && (!summonerName.equals("")) && (!summonerName.equals("null")) && !region.equals(null) && stat.equals("masteries")){
                            APIConnection api = new APIConnection();
                            Map<String, Object> map = api.findSummoner(summonerName, region);
                            ArrayList<Map> masteries = api.summonerMasteries((int)map.get("id"), region);
                            Set runeSet;
                            request.setAttribute("map", masteries);
                            request.setAttribute("name", summonerName);
                            request.getRequestDispatcher("masteries.jsp").forward(request,response);
                            response.sendRedirect(request.getContextPath() + "/masteries.jsp");
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
