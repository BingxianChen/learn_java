/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings(String moviefile, String ratingsfile) {
        // default constructor
        //this("ratedmoviesfull.csv", "ratings.csv");
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        loadRaters lr = new loadRaters();
        myRaters = lr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        // 记录电影的投票次数
        int time = 0;
        // 记录电影的评分总数
        double sum = 0.0;
        for (Rater ra : myRaters){
            // 投票者有参评该电影
            double rating = ra.getRating(id);
            if (rating != -1){
                time ++;
                sum += rating;
            }
        }
        
        if (time < minimalRaters){
            return 0.0;
        }else{
            return (double)sum/time;
        }
        
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ra = new ArrayList<Rating>();
        for (Movie mo : myMovies){
            String id = mo.getID();
            double value = getAverageByID(id, minimalRaters);
            if (value == 0.0){
                continue;
            }
            Rating rating = new Rating(id, value);
            ra.add(rating);
        }
        return ra;
    }
    
    public String getTitle(String id){
        for (Movie mo : myMovies){
            if (mo.getID().equals(id)){
                return mo.getTitle();
            }
        }
        return "不存在这个ID的电影!";
    }
    
    public String getID(String title){
        for (Movie mo : myMovies){
            if (mo.getTitle().equals(title)){
                return mo.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}













































