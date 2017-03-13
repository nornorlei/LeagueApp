import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class findSum extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //call the RESTful thing here to fetch the information
        //redirect to results.jsp
        String summonerName = request.getParameter("summonerName");
        String region =  request.getParameter("region");
        String stat = request.getParameter("stat");

        APIConnection findSum= new APIConnection();
        if((findSum.makeRequest(summonerName,region)==true) && (stat.equals("matchhistory")) ){
            request.getSession().setAttribute("summonerName",summonerName);
        } else if((findSum.makeRequest(summonerName,region)==true) && (stat.equals("rankedstats")) ){
            response.sendRedirect("/rankedStats.jsp");
            request.getSession().setAttribute("summonerName",summonerName);
        } else if((findSum.makeRequest(summonerName,region)==true) && (stat.equals("runesmasteries")) ){
            response.sendRedirect("/runesMasteries.jsp");
            request.getSession().setAttribute("summonerName",summonerName);
        } else if((findSum.makeRequest(summonerName,region)==true) && (stat.equals("championmasteries")) ){
            response.sendRedirect("/championMasteries.jsp");
            request.getSession().setAttribute("summonerName",summonerName);
        }else{
            response.sendRedirect("/findSum.jsp");
        }




    }
}
