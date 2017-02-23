
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        //SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("电影的数量:" + sr.getMovieSize());
        System.out.println("参评人数:" + sr.getRaterSize());
        
        ArrayList<Rating> ra = sr.getAverageRatings(3);
        Collections.sort(ra);
        for (Rating rat: ra){
            System.out.println(rat.getValue() + " " + sr.getTitle(rat.getItem()));
        }
    }

    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        
        // 获取电影名对应的电影id
        String title = "The Godfather";
        String id = sr.getID(title);
        if (id.equals("NO SUCH TITLE")){
            System.out.println("没有这个电影!");
        }else{
            double rating = sr.getAverageByID(id,1);
            System.out.println(title + "对应电影的平均评分为:" + rating);
        }
        
    }
}
