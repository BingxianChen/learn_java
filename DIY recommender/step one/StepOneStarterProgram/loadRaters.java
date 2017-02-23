
/**
 * Write a description of loadRaters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class loadRaters {
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ra = new ArrayList<Rater>();
        FileResource file = new FileResource("data/" + filename);
        CSVParser parser = file.getCSVParser();
        for(CSVRecord record : parser){
            String myID = record.get("rater_id");
            String item = record.get("movie_id");
            Double rating = Double.valueOf(record.get("rating"));
            int id = Integer.valueOf(myID);
                // 若评级人的id 为新的id说明是新的评级人,则需要新建一个评级人 
            if (ra.size() < id ){
                Rater rater = new Rater(myID);
                rater.addRating(item, rating);
                ra.add(rater);
            }else{
                // 对于重复的评级人,需要做取出,添加,重新插入    
                Rater rater = ra.get(id - 1);
                rater.addRating(item, rating);
                ra.set(id-1,rater);
            }

        }
        return ra;
    }
    
    public void testLoadRaters(){
        //ArrayList<Rater> ra = loadRaters("ratings_short.csv");
        ArrayList<Rater> ra = loadRaters("ratings.csv");
        int max_rat = 1; //评分者最多的评分数量
        for (Rater rater : ra){
            System.out.println("评分人的id: " + rater.getID() + " , 评分的电影数量: " + rater.numRatings());
             if (rater.numRatings() > max_rat){
                max_rat = rater.numRatings();
            }
        }
        System.out.println("评分的总人数: " + ra.size());
        
        System.out.println("******************");
        //获取给定评级者的评级记录
        int rater_id = 193;
        System.out.println("评分人 " + (rater_id) + " 的评分电影数量为: " + ra.get(rater_id - 1).numRatings());
        
        //先找到评分者最多的评分数量,再将其打印
        System.out.println("评分电影最多的人如下:");
        for (Rater rater : ra){
            if (rater.numRatings() == max_rat){
                System.out.println("评分人的id: " + rater.getID() + " , 评分的电影数量: " + rater.numRatings());
            }
        }
        System.out.println("******************");
        
        // 找出某部电影的评分数量
        String movie_id = "1798709";
        int rat_num = 0;
        for (Rater rater : ra){
            if (rater.getItemsRated().contains(movie_id)){
                rat_num ++;
            }
        }
        System.out.println("电影id " + movie_id + " 被评分次数为: " + rat_num);
        System.out.println("******************");
        
        // 所有参与评分的电影数量
        HashSet<String> hs = new HashSet<String>();
        for (Rater rater : ra){
           ArrayList<String> items = rater.getItemsRated();
           for (String s : items){
               hs.add(s);
           }
        }
        System.out.println("所有参与评分的电影数量: " + hs.size());
    }
    
}
