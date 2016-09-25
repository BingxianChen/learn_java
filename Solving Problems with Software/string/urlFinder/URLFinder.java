/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;
public class URLFinder {
    public StorageResource findURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("http")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }

    public void testURLWithStorage() {
        StorageResource s1 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        int httpsNum = 0;
        int comNum = 0;
        int endcomNum = 0;
        int pointNum = 0;
        for (String link : s1.data()) {
            if(link.startsWith("https")){
                httpsNum ++;
              }
            if(link.indexOf(".com") != -1){
                comNum ++;
            }
            if(link.endsWith(".com") || link.endsWith(".com/")){
                endcomNum ++;
            }
            int index = 0;
            while(true){
                index = link.indexOf(".",index);
                if(index == -1){
                    break;  
                }
                pointNum ++;
                index ++;
            }
            
            System.out.println(link);
        }
        System.out.println("size = " + s1.size());
        System.out.println("https nums = " + httpsNum);
        System.out.println("have .com nums = " + comNum);
        System.out.println("end with .com or .com/ nums = " + endcomNum);
        System.out.println(". nums = " + pointNum);
    }
}
