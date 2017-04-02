import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;

public class APIConnection{

    private String api = "RGAPI-52269a2a-0944-4223-bbb2-39dd1d381a12";

    public Map findSummoner(String summoner, String region){
        String s = "https://" + region.toLowerCase() + ".api.riotgames.com/api/lol/" + region.toUpperCase() +"/v1.4/summoner/by-name/"+ summoner + "?api_key=" + api;
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        Map<String, Object> map = null;

        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            map = new HashMap<>();
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            System.out.println(display);
            JSONObject json = null;
            try {
                json = new JSONObject(display);
                JSONObject data = json.getJSONObject(summoner);
                System.out.println(data.getInt("summonerLevel"));
                map.put("id", data.getInt("id"));
                map.put("name", data.getString("name"));
                map.put("image", data.getInt("profileIconId"));
                map.put("sumLevel", data.getInt("summonerLevel"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    public ArrayList summonerRunes(int id, String region){
       // Map<String, Object> map = findSummoner(summoner, region);
        //int id = (int) map.get("id");
        String s = "https://na.api.riotgames.com/api/lol/"+region+"/v1.4/summoner/"+id+"/runes?api_key="+api;
        ArrayList runes = null;
        ArrayList runesets = new ArrayList<>();
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = new DBConnection().getconnection();
                JSONObject json = new JSONObject(display);
                json = json.getJSONObject(Integer.toString(id));
                JSONArray data = json.getJSONArray("pages");
                if(data.length() > 0){
                    for(int i = 0; i < data.length(); i++){
                        //runes = new HashMap<>();
                        JSONObject slots = data.getJSONObject(i);
                        JSONArray array = slots.getJSONArray("slots");
                        String name = slots.getString("name");
                        runes = new ArrayList();
                        runes.add("name");
                        runes.add(name);
                        runesets.add(runes);
                        for(int j = 0; j < array.length(); j++){
                           // System.out.println(" index " + j + " " + array.g);
                            JSONObject obj = (JSONObject) array.get(j);
                            System.out.println(obj);
                            int runeSlot = obj.getInt("runeSlotId");
                            int runeID = obj.getInt("runeId");

                            PreparedStatement statement = null;
                            statement = conn.prepareStatement("select * from Runes where id = ?");
                            statement.setInt(1,runeID);
                            ResultSet result = statement.executeQuery();
                            String runename = "";
                            String description = "";
                            runes = new ArrayList();
                            if(result.next()){
                                runename = result.getString("name");
                                description = result.getString("description");
                                runes.add(runename);
                                runes.add(description);
                                runesets.add(runes);
                            }
                           // runes.put(obj.getInt("runeSlotId"), obj.getInt("runeId"));
                        }
                    }
                }
                } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return runesets;
    }

    public ArrayList summonerMasteries(int id, String region){
        String s = "https://na.api.riotgames.com/api/lol/"+region+"/v1.4/summoner/"+id+"/masteries?api_key="+api;
        Map<Integer, Integer> mastery = null;
        ArrayList<Map> masteries = new ArrayList<>();
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = new DBConnection().getconnection();
                JSONObject json = new JSONObject(display);
                json = json.getJSONObject(Integer.toString(id));
                JSONArray data = json.getJSONArray("pages");
                if(data.length() > 0){
                    for(int i = 0; i < data.length(); i++){
                        mastery = new HashMap<>();
                        JSONObject slots = data.getJSONObject(i);
                        JSONArray array = slots.getJSONArray("masteries");
                        String name = slots.getString("name");
                        masteries.add(new HashMap(){{put("name", name);}});
                        for(int j = 0; j < array.length(); j++){
                            // System.out.println(" index " + j + " " + array.g);
                            JSONObject obj = (JSONObject) array.get(j);
                            //System.out.println(obj);
                            mastery.put(j, obj.getInt("id"));
                            //masteries.add()
                        }
                        masteries.add(mastery);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < masteries.size(); i++){
            System.out.println(masteries.get(i));
        }

        return masteries;
    }

    public static void main(String [ ] args) {
        APIConnection conn = new APIConnection();
        ArrayList<String> s = new ArrayList<>();
        s = conn.summonerChampMasteries(23509933,"na");
        System.out.println(s);
    }

    public ArrayList summonerChampMasteries(int id, String region){
        String s = "https://" + region + ".api.riotgames.com/championmastery/location/" + region + "/player/" + id + "/champions?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        String ss = "https://" + region + ".api.riotgames.com/championmastery/location/" + region + "1/player/" + id + "/champions?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        Map<Integer, Integer> mastery = null;
        ArrayList<Map> masteries = new ArrayList<>();
        HttpURLConnection connection = null;
        if(region.equalsIgnoreCase("kr") || region.equalsIgnoreCase("ru")){
            connection = getConnection(s);
        }else{
            connection = getConnection(ss);
        }
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            display = "{ \"" + id + "\":{" + "\"summonerId\": " + id + "," +"\"pages\": [{ \"masteries\": ".concat(display).concat("}]}}");
            System.out.println(display);
            try{
                JSONObject json = new JSONObject(display);
                json = json.getJSONObject(Integer.toString(id));
                JSONArray data = json.getJSONArray("pages");
                System.out.println("data " + data);
                if(data.length() > 0){
                    for(int i = 0; i < data.length(); i++){
                        mastery = new HashMap<>();
                        JSONObject slots = data.getJSONObject(i);
                        JSONArray array = slots.getJSONArray("masteries");
                        String name = slots.getString("name");
                        masteries.add(new HashMap(){{put("name", name);}});
                        for(int j = 0; j < array.length(); j++){
                            // System.out.println(" index " + j + " " + array.g);
                            JSONObject obj = (JSONObject) array.get(j);
                            //System.out.println(obj);
                            mastery.put(j, obj.getInt("id"));
                            //masteries.add()
                        }
                        masteries.add(mastery);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < masteries.size(); i++){
            System.out.println(masteries.get(i));
        }

        return masteries;
    }


    public void fillRunes(){
        String s = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/rune?runeListData=image&api_key=RGAPI-52269a2a-0944-4223-bbb2-39dd1d381a12";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = new DBConnection().getconnection();
                System.out.println("display " + display);
                JSONObject json = new JSONObject(display);
                JSONObject data = json.getJSONObject("data");
                Iterator keys = data.keys();
                while(keys.hasNext()){
                    String rune = (String)keys.next();
                    System.out.println(rune);

                    JSONObject runes = data.getJSONObject(rune);
                    String description = runes.getString("description");
                    String name = runes.getString("name");
                    JSONObject imageData = runes.getJSONObject("image");
                    String image = imageData.getString("full");
                    int id = runes.getInt("id");

                    PreparedStatement statement = null;
                    statement = conn.prepareStatement("select * from Runes where name = ?");
                    statement.setString(1,name);
                    ResultSet result = statement.executeQuery();

                    if(!result.next()){
                        PreparedStatement statementChamp = null;
                        statementChamp = conn.prepareStatement("insert into Runes values (?,?,?,?)");
                        statementChamp.setInt(1, id);
                        statementChamp.setString(2, name);
                        statementChamp.setString(3, description);
                        statementChamp.setString(4, image);
                        statementChamp.execute();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillMasteries(){
        String s = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/mastery?masteryListData=image&api_key="+api;
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = new DBConnection().getconnection();
             //   System.out.println("display " + display);
                JSONObject json = new JSONObject(display);
                JSONObject data = json.getJSONObject("data");
                Iterator keys = data.keys();
                while(keys.hasNext()){
                    String mastery = (String)keys.next();
                    System.out.println(mastery);

                    JSONObject masteries = data.getJSONObject(mastery);
                    int id = masteries.getInt("id");
                    String name = masteries.getString("name");
                    JSONObject imageData = masteries.getJSONObject("image");
                    String image = imageData.getString("full");
                    JSONArray description = masteries.getJSONArray("description");
                    ArrayList descript = new ArrayList();
                    for(int i = 0; i < description.length(); i++){
                        descript.add(description.getString(i));
                       // System.out.println(descript.get(i));
                    }
                    PreparedStatement statement = null;
                    statement = conn.prepareStatement("select * from Masteries where id = ?");
                    statement.setInt(1,id);
                    ResultSet result = statement.executeQuery();

                   /* if(!result.next()){
                        PreparedStatement statementM = null;
                        statementM = conn.prepareStatement("insert into Masteries values (?,?,?)");
                        statementM.setInt(1, id);
                        statementM.setString(2, name);
                        statementM.setString(3, image);
                        statementM.execute();

                        PreparedStatement statementD = null;
                        statementD = conn.prepareStatement("insert into descriptionsM values (?,?,?)");
                        for(int i = 0; i < de)
                        statementD.setInt(1, id);
                        statementD.setString(2, name);
                        statementD.setString(3, image);
                        statementD.execute();
                    }*/

                   // System.out.println("Descritiion " + description);
                /*    PreparedStatement statement = null;
                    statement = conn.prepareStatement("select * from Runes where name = ?");
                    statement.setString(1,name);
                    ResultSet result = statement.executeQuery();

                    if(!result.next()){
                        PreparedStatement statementChamp = null;
                        statementChamp = conn.prepareStatement("insert into Runes values (?,?,?,?)");
                        statementChamp.setInt(1, id);
                        statementChamp.setString(2, name);
                        statementChamp.setString(3, description);
                        statementChamp.setString(4, image);
                        statementChamp.execute();
                    }*/
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillChamps(){
        String s = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/champion?champData=image&api_key="+api;
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = new DBConnection().getconnection();

                JSONObject json = new JSONObject(display);
                JSONObject data = json.getJSONObject("data");
                Iterator keys = data.keys();
                while(keys.hasNext()){
                    String champ = (String)keys.next();
                  //  System.out.println(champ);

                    JSONObject champion = data.getJSONObject(champ);
                    String name = champion.getString("name");
                    String title = champion.getString("title");
                    JSONObject imageData = champion.getJSONObject("image");
                    String image = imageData.getString("full");
                    int id = champion.getInt("id");

                    PreparedStatement statement = null;
                    statement = conn.prepareStatement("select * from Champions where name = ?");
                    statement.setString(1,name);
                    ResultSet result = statement.executeQuery();

                    if(!result.next()){
                        PreparedStatement statementChamp = null;
                        statementChamp = conn.prepareStatement("insert into Champions values (?,?,?,?)");
                        statementChamp.setInt(1, id);
                        statementChamp.setString(2, name);
                        statementChamp.setString(3, title);
                        statementChamp.setString(4, image);
                        statementChamp.execute();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public HttpURLConnection getConnection(String string){
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(string);
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        }

        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
            } catch (IOException e) {
                System.out.println("Failed to get connection");
            }
        }

        return connection;
    }

    private Scanner getConnectionScanner(HttpURLConnection connection) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(connection.getInputStream());
        } catch (IOException e) {
            System.out.println("Couldn't get input stream");
        }

        return scanner;
    }
}

