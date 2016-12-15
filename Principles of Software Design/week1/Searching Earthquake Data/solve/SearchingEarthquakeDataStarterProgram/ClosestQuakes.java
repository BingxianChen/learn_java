
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>();
        quakeDataCopy.addAll(quakeData);
        while(howMany > 0 && quakeDataCopy.size() > 0){
            double min = quakeDataCopy.get(0).getLocation().distanceTo(current); 
            int index = 0;
            for (QuakeEntry qe : quakeDataCopy){
                if (qe.getLocation().distanceTo(current) < min){
                    min = qe.getLocation().distanceTo(current);
                    index = quakeDataCopy.indexOf(qe);
                }
            }
            ret.add(quakeDataCopy.get(index));
            quakeDataCopy.remove(index);
            howMany--;
        }

        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
