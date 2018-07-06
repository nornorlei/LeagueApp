import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
        Map<Integer, String> mastery = null;
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
                            int abc = obj.getInt("id");
                            mastery.put(j, getMasteryName(abc));
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



    public ArrayList summonerChampMasteries(int id, String region){
        String s = "https://" + region + ".api.riotgames.com/championmastery/location/" + region + "/player/" + id + "/champions?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        String ss = "https://" + region + ".api.riotgames.com/championmastery/location/" + region + "1/player/" + id + "/champions?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";

        ArrayList<String> masteries = new ArrayList<>();
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
            try{
                JSONArray json = new JSONArray(display);

                int k;
                for (int m = 0; m <json.length();m++){
                    for (int i = 0;i <json.length()-1 ; i++){
                        k = i + 1;
                        JSONObject obj1 = (JSONObject) json.get(i);
                        JSONObject obj2 = (JSONObject) json.get(k);
                        if (obj1.getInt("championPoints") < obj2.getInt("championPoints")){
                            JSONObject temp = (JSONObject) json.get(i);

                            json.put(i,obj2);
                            json.put(k,obj1);

                        }
                    }
                }
                for (int r = 0; r <json.length(); r++){
                    JSONObject obj = (JSONObject) json.get(r);
                    String abc = getChampName(obj.getInt("championId"));
                    masteries.add(r,"<p>Champion: " + abc + " " + "Champion Level: " + obj.getInt("championLevel") + " " + "Champion Points: " + obj.getInt("championPoints")+ "</p>");
                    //System.out.println(obj.getInt("championId"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                        for(int i = 0; i < descript.size(); i++) {
                            statementD.setInt(1, id);
                            statementD.setString(2, name);
                            statementD.setString(3, image);
                            statementD.execute();
                        }
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



    public String getSummonerName (String name, String region){
        String s =  "https://" + region + ".api.riotgames.com/api/lol/" + region + "/v1.4/summoner/by-name/" + name + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        String ss = "";
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null) {
            String display = "";
            while (scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try {
                JSONObject json = new JSONObject(display);
                JSONObject j = json.getJSONObject(name);
                ss = j.getString("name");
                System.out.println(ss);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  ss;
    }
    public Long getSummonerID (String name, String region){
        String s =  "https://" + region + ".api.riotgames.com/api/lol/" + region + "/v1.4/summoner/by-name/" + name + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        Long ss = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null) {
            String display = "";
            while (scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try {
                JSONObject json = new JSONObject(display);
                JSONObject j = json.getJSONObject(name);
                ss = j.getLong("id");
                System.out.println(ss);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  ss;
    }

    public String getTier (int id, String region){
        String s =  "https://" + region + ".api.riotgames.com/api/lol/" + region + "/v2.5/league/by-summoner/" + id + "/entry?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        String tierInfo = "";
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
                JSONObject json = new JSONObject(display);
                String r = Integer.toString(id);
                JSONArray array = json.getJSONArray(r);

                for (int i = 0; i < array.length() ; i++){
                    JSONObject obj1 = array.getJSONObject(i);
                    String tier = obj1.getString("tier");
                    String queue = obj1.getString("queue");
                    JSONArray a = obj1.getJSONArray("entries");
                    JSONObject b = a.getJSONObject(0);
                    String division = b.getString("division");
                    int wins = b.getInt("wins");
                    int losses = b.getInt("losses");
                    int lp = b.getInt("leaguePoints");

                    tierInfo = ("|" + tier + " " + division + " " + queue + " Wins: " +wins + " Losses: " + losses+ " LP: " + lp + "|").concat(tierInfo);
                }


                tierInfo = "<h2>" + tierInfo + "</h2>";


            } catch (JSONException e) {
                e.printStackTrace();
            }

    }
        return tierInfo;
    }
    public ArrayList summonerRankedStats(int id, String region){
        String s = "https://" + region + ".api.riotgames.com/api/lol/" + region + "/v1.3/stats/by-summoner/" + id + "/ranked?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";

        ArrayList<String> rankedStats = new ArrayList<>();
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
                JSONObject json = new JSONObject(display);
                JSONArray data = json.getJSONArray("champions");
                rankedStats.add(0,getTier(id,region));
                if(data.length() > 0){
                    for(int i = 0; i < data.length(); i++){
                        JSONObject slots = data.getJSONObject(i);
                        JSONObject stats = slots.getJSONObject("stats");
                        int champid, win, loss;
                        double games;
                        String winPercent, kills, deaths, assists, creeps;
                        champid = slots.getInt("id");
                        win = stats.getInt("totalSessionsWon");
                        loss = stats.getInt("totalSessionsLost");
                        String game = Integer.toString(stats.getInt("totalSessionsPlayed")).concat(".0");
                        games = Double.parseDouble(game);
                        double wp,k,d,a,c;
                        wp=(win*100)/games;
                        k=stats.getInt("totalChampionKills")/games;
                        d=stats.getInt("totalDeathsPerSession")/games;
                        a=stats.getInt("totalAssists")/games;
                        c=stats.getInt("totalMinionKills")/games;
                        winPercent = String.format("%.4g%n",wp);
                        kills = String.format("%.3g%n",k);
                        deaths = String.format("%.3g%n",d);
                        assists = String.format("%.3g%n",a);
                        creeps = String.format("%.4g%n",c);
                        String abc = getChampName(champid);

                        rankedStats.add(i+1,"<p>Champion: " + abc + "</p><table><tr><td>Wins</td><td>" + win + "</td></tr><tr><td>Losses</td><td>" + loss + "</td></tr><tr><td>Total Games Played</td><td>" + games + "</td></tr><tr><td>Win Rate</td><td>" + winPercent + "</td></tr><tr><td>K/D/A</td><td>" + kills + "/" + deaths + "/" + assists + "</td></tr><tr><td>CS</td><td>" +creeps+"</td></tr></table>");
                        //System.out.println("ID: " + champid + " Wins: " + win + " Losses: " + loss + " Total Games Played: " + games + " Win Rate: " + winPercent + " K/D/A " + kills + "/" + deaths + "/" + assists + " CS: " +creeps);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < rankedStats.size(); i++){
            System.out.println(rankedStats.get(i));
        }

        return rankedStats;
    }

    public ArrayList summonerMatchHistory(int id, String region){
        String s = "https://" + region + ".api.riotgames.com/api/lol/" + region + "/v1.3/game/by-summoner/" + id + "/recent?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";

        ArrayList<String> matchHistory = new ArrayList<>();
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
                JSONObject json = new JSONObject(display);
                JSONArray data = json.getJSONArray("games");
                if(data.length() > 0){
                    for(int i = 0; i < data.length(); i++){
                        boolean win;
                        int champID;
                        String subGameType;
                        int k,d,a;
                        int totalDmg;
                        int cs;
                        int gold;
                        int item0 = 0;
                        int item1 = 0;
                        int item2 = 0;
                        int item3 = 0;
                        int item4 = 0;
                        int item5 = 0;
                        int item6 = 0;

                        int spell1,spell2;
                        String spell11 = "";
                        String spell22="";

                        JSONObject slots = data.getJSONObject(i);

                        champID = slots.getInt("championId");
                        subGameType = slots.getString("subType");
                        spell1 = slots.getInt("spell1");
                        spell2 = slots.getInt("spell2");

                        spell11 = getSpellName(spell1);
                        spell22 = getSpellName(spell2);

                        JSONObject stats = slots.getJSONObject("stats");
                        k = stats.getInt("championsKilled");
                        d = stats.getInt("numDeaths");
                        a = stats.getInt("assists");
                        totalDmg = stats.getInt("totalDamageDealtToChampions");
                        cs = stats.getInt("minionsKilled");
                        gold = stats.getInt("goldEarned");
                        String item00 = "Empty";
                        String item11 = "Empty";
                        String item22 = "Empty";
                        String item33 = "Empty";
                        String item44 = "Empty";
                        String item55 = "Empty";
                        String item66 = "Empty";

                        try {
                            item0 = stats.getInt("item0");
                        }catch (JSONException e){
                        }try{
                            item1 = stats.getInt("item1");
                        }catch (JSONException e){
                        }try{
                            item2 = stats.getInt("item2");
                        }catch (JSONException e){
                        }try{
                            item3 = stats.getInt("item3");
                        }catch (JSONException e){
                        }try{
                            item4 = stats.getInt("item4");
                        }catch (JSONException e){
                        }try{
                            item5 = stats.getInt("item5");
                        }catch (JSONException e){
                        }try{
                            item6 = stats.getInt("item6");
                        }catch (JSONException e){
                        }
                        if(item0 != 0 ) {
                            item00 = getItemName(item0);
                        }if(item1 !=0){
                            item11 = getItemName(item1);
                        } if(item2 !=0){
                            item22 = getItemName(item2);
                        } if(item3 !=0){
                            item33 = getItemName(item3);
                        } if(item4 !=0){
                            item44 = getItemName(item4);
                        } if(item5 !=0){
                            item55 = getItemName(item5);
                        } if(item6 !=0){
                            item66 = getItemName(item6);
                        }
                        win = stats.getBoolean("win");
                        String abc = getChampName(champID);
                        matchHistory.add(i,"<p>" + (i+1)  + "</p><table><tr><td>Game Type</td><td>" + subGameType + "</td></tr><tr><td>Win</td><td>" + win + "</td></tr><tr><td>Champion</td><td>" + abc  + "</td></tr><tr><td>K/D/A</td><td>" + k + "/" + d + "/" + a + "</td></tr><tr><td>Damage dealt to champs</td><td>" + totalDmg + "</td></tr><tr><td>CS</td><td>" + cs + "</td></tr><tr><td>Gold</td><td>" + gold + "</td></tr><tr><td>item0</td><td>" + item00 + "</td></tr><tr><td>item1</td><td>" + item11 + "</td></tr><tr><td>item2</td><td>" + item22 + "</td></tr><tr><td>item3</td><td>" + item33 + "</td></tr><tr><td>item4</td><td>" + item44 + "</td></tr><tr><td>item5</td><td>" + item55 + "</td></tr><tr><td>item6</td><td>" + item66 + "</td></tr><tr><td>Summoner Skill 1</td><td>" + spell11 + "</td></tr><tr><td>Summoner Skill 2</td><td>" + spell22 + "</td></tr></table>");


                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return matchHistory;
    }
    public String getSpellName (int id){
        String s =  "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/summoner-spell/" + id + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        String spellName="";
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null) {
            String display = "";
            while (scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try {
                JSONObject json = new JSONObject(display);
                spellName = json.getString("name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  spellName;
    }

    public String getMasteryName (int id){
        String s =  "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/mastery/"+ id + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        String masteryName="";
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null) {
            String display = "";
            while (scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try {
                JSONObject json = new JSONObject(display);
                masteryName = json.getString("name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  masteryName;
    }
    public String getItemName (int id){
        String s = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/item/" + id + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        String itemName="";
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null) {
            String display = "";
            while (scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try {
                JSONObject json = new JSONObject(display);
                itemName = json.getString("name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  itemName;
    }

    public String getChampName (int id){
        String s = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/champion/"+ id + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        String champName="";
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null) {
            String display = "";
            while (scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try {
                JSONObject json = new JSONObject(display);
                champName = json.getString("name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  champName;
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
                //uncomment below for league app
                //connection.setRequestMethod("GET");
                //connection.setRequestProperty("Accept", "application/json");
                connection.addRequestProperty("User-Agent","Mozilla/4.0");
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
            System.out.println(e);
            System.out.println("Couldn't get input stream");
        }

        return scanner;
    }

    //email with subject and text params
    public void emailReport (String subject, String text){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "nlei@oswego.edu";//
        final String password = "devgrut6";
        try {
            String emailSubject = "";
            String emailText = "";


            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("nlei@oswego.edu"));

            msg.addRecipients(Message.RecipientType.CC,
                    InternetAddress.parse("tripodoreo@gmail.com,nlei@oswego.edu"));

            //email subject
            msg.setSubject(subject);
            //email text
            msg.setText(text);

            msg.setSentDate(new Date());
            Transport.send(msg);

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }



    public void JSONRigMonitor() throws InterruptedException {
        while(1==1){
            Date date = new Date();
            System.out.println(date.toString());
            String s = "https://ethermine.org/api/miner_new/c053c7f0b2513462cdb5ddf60bd8cdfc58e3a8af";
            HttpURLConnection connection = getConnection(s);
            Scanner scanner = null;
            if(connection != null)
                scanner = getConnectionScanner(connection);
            if(scanner != null) {
                String display = "";
                while (scanner.hasNextLine()) {
                    display = display.concat(scanner.nextLine());
                }
                //System.out.println(display);
                try {

                    //Getting hashrate
                    JSONObject jsonobj =  new JSONObject(display);

                    JSONObject obj = jsonobj.getJSONObject("workers");
                    JSONObject worker1 = obj.getJSONObject("Rig1");
                    JSONObject worker2 = obj.getJSONObject("Rig2Reddrags");
                    JSONObject worker3 = obj.getJSONObject("Rig4Nitros");

                    String worker1reportedhash = worker1.getString("reportedHashRate");
                    String worker2reportedhash = worker2.getString("reportedHashRate");
                    String worker3reportedhash = worker3.getString("reportedHashRate");

                    System.out.println("Worker 1 reported Hash: " + worker1reportedhash);
                    System.out.println("Worker 2 reported Hash: " + worker2reportedhash);
                    System.out.println("Worker 3 reported Hash: " + worker3reportedhash);

                    worker1reportedhash = worker1reportedhash.substring(0, worker1reportedhash.length() - 5);
                    worker2reportedhash = worker2reportedhash.substring(0, worker2reportedhash.length() - 5);
                    worker3reportedhash = worker3reportedhash.substring(0, worker3reportedhash.length() - 5);

                    float worker1hash = Float.parseFloat(worker1reportedhash);
                    float worker2hash = Float.parseFloat(worker2reportedhash);
                    float worker3hash = Float.parseFloat(worker3reportedhash);

                    boolean worker1failed = worker1hash < 85.0;
                    boolean worker2failed = worker2hash < 85.0;
                    boolean worker3failed = worker3hash < 85.0;

                    if ((worker1failed || worker2failed || worker3failed) == true) {


                            String emailSubject = "";
                            String emailText = "";

                            if (worker1failed == true) {
                                emailSubject = emailSubject.concat("Rig1, ");
                                emailText = emailText.concat("Rig1 has a reported hashrate of " + worker1hash + ". ");
                            }
                            if (worker2failed == true) {
                                emailSubject = emailSubject.concat("Rig2Reddrags, ");
                                emailText = emailText.concat("Rig2Reddrags has a reported hashrate of " + worker2hash + ". ");
                            }
                            if (worker3failed == true) {
                                emailSubject = emailSubject.concat("Rig4Nitros, ");
                                emailText = emailText.concat("Rig4Nitros has a reported hashrate of " + worker3hash + ". ");
                            }
                            if ((worker1failed || worker2failed || worker3failed) == true) {
                                emailSubject.trim();
                                emailSubject = emailSubject.substring(0, emailSubject.length()-2);
                                emailSubject = emailSubject.concat(" has failed.");
                            }

                            emailReport(emailSubject,emailText);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Some shit went wrong somewhere");
                    JSONRigMonitor();
                }

            }
            TimeUnit.MINUTES.sleep(30);
        }

    }
        public static void main(String [ ] args) throws InterruptedException {
        APIConnection conn = new APIConnection();
        //ArrayList <Map>  s = conn.summonerMatchHistory(29489927,"na");
           // String  s = conn.getItemName(3153);

//        System.out.println(s);
//            System.out.println(" mod "   + (1%7) +  (2%7));
            conn.JSONRigMonitor();

    }
}

//sdadsaadssadas