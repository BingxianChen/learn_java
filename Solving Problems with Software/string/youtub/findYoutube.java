
/**
 * Write a description of findYoutube here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
public class findYoutube {

    public void runHello(){
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
            for ( String item : file.words() ) {
                String itemLower = item.toLowerCase();
                int pos = itemLower.indexOf("youtube.com");
                if ( pos != -1 ) {
                        int beg = item.lastIndexOf("\"", pos);
                        int end = item.indexOf("\"", pos + 1);
                        System.out.println(item.substring(beg + 1, end));
                    }
                }
            }
    
    
    public static void main(String[] arg ){
        findYoutube hw = new findYoutube();
        hw.runHello();
    }
}
