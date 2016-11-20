
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.text.*;
//61.15.121.171 - - [15/Mar/2015:08:00:33 -0400] "GET /indexdfa.gif HTTP/1.1" 200 6161
public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource file = new FileResource(filename);
         for (String s : file.lines()){
            //String[] logArray = s.split(" ");
            //String ip = logArray[0];
            //int status = Integer.valueOf(logArray[8]);
            //int bytes = Integer.valueOf(logArray[9]);
            //String req = s.split("\"")[1];
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",Locale.ENGLISH);
            //try {
            //    Date date = sdf.parse(logArray[3].substring(1));
            //    LogEntry le = new LogEntry(ip, date, req, status, bytes);
            //    records.add(le);
            //    
            //} catch (ParseException e){
            //    System.out.println("Unparseable using " + logArray[3].substring(1));
            //}
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
