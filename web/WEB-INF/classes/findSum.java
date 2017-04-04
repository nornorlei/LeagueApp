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


import static java.lang.Math.PI;
import static java.lang.Math.toIntExact;

public class findSum extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //call the RESTful thing here to fetch the information
        //redirect to results.jsp
        String summonerName = request.getParameter("summonerName");
        String region =  request.getParameter("region");
        String stat = request.getParameter("stat");

        request.getSession().setAttribute("summonerName", summonerName);


       // findSum.findSummoner(summonerName,region);

        String val = "ds";
        try{
            APIConnection findSum= new APIConnection();
            String s = findSum.getSummonerName(summonerName,region);
             if (s.equalsIgnoreCase(summonerName)){
                if (stat.equals("runes")){
                    Map<String, Object> map = findSum.findSummoner(summonerName, region);
                    ArrayList<Map> runes = findSum.summonerRunes((int)map.get("id"), region);
                    request.setAttribute("map", runes);
                    request.setAttribute("name", summonerName);
                    request.getRequestDispatcher("runes.jsp").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/runes.jsp");
                }else if ( stat.equals("masteries")){
                    Map<String, Object> map = findSum.findSummoner(summonerName, region);
                    ArrayList<Map> masteries = findSum.summonerMasteries((int)map.get("id"), region);
                    request.setAttribute("map", masteries);
                    request.setAttribute("name", summonerName);
                    request.getRequestDispatcher("masteries.jsp").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/masteries.jsp");
                }else if ( stat.equals("championMasteries")){
                    int b = toIntExact(findSum.getSummonerID(summonerName,region));
                    ArrayList<String> championMasteries = findSum.summonerChampMasteries(b,region);
                    String formattedString = championMasteries.toString().replace("[","").replace(",","").replace("]","");
                    request.setAttribute("championMasteries",formattedString);
                    request.setAttribute("name", summonerName);
                    request.getRequestDispatcher("championMasteries.jsp").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/championMasteries.jsp");
                }else if (stat.equals("rankedStats")){
                    APIConnection api = new APIConnection();
                    int b = toIntExact(api.getSummonerID(summonerName,region));
                    ArrayList<String> rankedStats = api.summonerRankedStats(b,region);
                    String formattedString = rankedStats.toString().replace("[","").replace(",","").replace("]","");
                    request.setAttribute("rankedStats",formattedString);
                    request.setAttribute("name", summonerName);
                    request.getRequestDispatcher("rankedStats.jsp").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/rankedStats.jsp");
                }else if (stat.equals("matchHistory")){
                    APIConnection api = new APIConnection();
                    int b = toIntExact(api.getSummonerID(summonerName,region));
                    ArrayList<String> rankedStats = api.summonerMatchHistory(b,region);
                    String formattedString = rankedStats.toString().replace("[","").replace(",","").replace("]","");
                    request.setAttribute("matchHistory",formattedString);
                    request.setAttribute("name", summonerName);
                    request.getRequestDispatcher("matchhistory.jsp").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/matchhistory.jsp");
                }
                else{
                    response.sendRedirect("/findSum.jsp");
                }
               }
            throw new IOException();

        }catch(IOException f){
            System.out.println(f);
        }catch(NullPointerException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("findSum.jsp").forward(req, resp);
    }
}
