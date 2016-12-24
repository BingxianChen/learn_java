
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> fil;
    
    public MatchAllFilter(){
        fil = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        fil.add(f);;
    }
    
    public String getName(){
        StringBuilder sb = new StringBuilder();
        for (Filter f : fil){
           sb.append(f.getName() + " ");
        }
        return sb.toString();
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        for (Filter f : fil){
           if (!f.satisfies(qe)){
               return false;
           }
        }
        return true;
    }
    
}
