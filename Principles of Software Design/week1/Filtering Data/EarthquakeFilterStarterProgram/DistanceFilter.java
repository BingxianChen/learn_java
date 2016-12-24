
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location myLocation; 
    private double maximum;
    
    public DistanceFilter(Location locat,double max){
        myLocation = locat;
        maximum = max;
    }
    
    public String getName(){
        return "DistanceFilter";
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(myLocation) < maximum; 
    }
}
