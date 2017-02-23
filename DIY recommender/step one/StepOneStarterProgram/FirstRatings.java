
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> mo = new ArrayList<Movie>();
        FileResource file = new FileResource("data/" + filename);
        CSVParser parser = file.getCSVParser();
        for(CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            String poster = record.get("poster");
            int minutes = Integer.valueOf(record.get("minutes"));
            Movie m = new Movie(id,title,year,genre,director,country,poster,minutes);
            mo.add(m);
        }
        return mo;
    }
    
    public void testLoadMovies(){
        //ArrayList<Movie> mo = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> mo = loadMovies("ratedmoviesfull.csv");
        int genre = 0;
        int minu = 0;
        
        //数据结构map key是导演姓名,value是导演出现的次数
        HashMap<String, Integer> dire_num = new HashMap<String, Integer>();
        for (Movie m : mo){
            if (m.getGenres().indexOf("Comedy") != -1){
                genre ++;
            }
            if (m.getMinutes() > 150){
                minu ++;
            }
            //System.out.println(m.toString());
            String[] directors = m.getDirector().split(",");
            
            for(String s : directors){
                if (dire_num.containsKey(s)){
                    dire_num.put(s, dire_num.get(s) + 1);
                }else{
                    dire_num.put(s, 1);
                }
            }
            
        }
        
        // 先找到value值最大的值,也就是导演过的最大电影数
        int max_dir = 1;
        for (Integer v : dire_num.values()){
            if (max_dir < v){
                max_dir = v;
            }
        }
        
        // 输出导演过最多电影的导演名及相关的电影数量
        System.out.println("导演过最多电影的数量是: " + max_dir);
        System.out.println("导演是:");
        for (String key : dire_num.keySet()){
            if (dire_num.get(key) == max_dir){
                System.out.println(key);
            }
        }
        System.out.println("***************************");
        
        System.out.println("size: " + mo.size());
        System.out.println("genre: " + genre);
        System.out.println("minu: " + minu);
        
    }
}


