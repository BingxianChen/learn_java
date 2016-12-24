
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] lastTitle1 = qe1.getInfo().split(" ");
        String[] lastTitle2 = qe2.getInfo().split(" ");
        String lastWord1 = lastTitle1[lastTitle1.length - 1];
        String lastWord2 = lastTitle2[lastTitle2.length - 1];
        if (lastWord1.compareTo(lastWord2) != 0){
            return lastWord1.compareTo(lastWord2);
        }else{
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        
    }
}