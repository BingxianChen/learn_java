
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int largestIndex = 0;
        double maxMagnitude = 0.0;
        for (int i = 0; i < data.size(); i ++){
            if (data.get(i).getMagnitude() > maxMagnitude){
                maxMagnitude = data.get(i).getMagnitude();
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>();
        quakeDataCopy.addAll(quakeData);
        while(howMany > 0 && quakeDataCopy.size() > 0){
            
            int index = indexOfLargest(quakeDataCopy);
            
            ret.add(quakeDataCopy.get(index));
            quakeDataCopy.remove(index);
            howMany--;
        }
        return ret;
    }
    
    
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        for (QuakeEntry qe: list){
            System.out.println(qe.toString());
        }
        System.out.println("read data for "+list.size());
        System.out.println("*********************************************");
        int largestIndex = indexOfLargest(list);
        System.out.println("index location of the largest magnitude is : "+largestIndex);
        System.out.println(list.get(largestIndex).toString());
        System.out.println("*********************************************");
        
        ArrayList<QuakeEntry> ret = getLargest(list,5);
        for (QuakeEntry qe: ret){
            System.out.println(qe.toString());
        }
        System.out.println("read data for "+ret.size());
    }

}
