
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
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
         }
     }
     
     public int countUniquelPs(){
         
         ArrayList<String> countIp = new ArrayList<String>();
         
         for (LogEntry le : records){
            if(!countIp.contains(le.getIpAddress())){
                countIp.add(le.getIpAddress());
            }
            
         }
         return countIp.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniIpDay = new ArrayList<String>();
        for (LogEntry le : records){
            String[] dateArray = le.getAccessTime().toString().split(" ");
            String date = dateArray[1] + " " + dateArray[2];
            if(date.equals(someday) && !uniIpDay.contains(le.getIpAddress())){
                uniIpDay.add(le.getIpAddress());
            }
        }
        return uniIpDay;
     }
     
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniIp = new ArrayList<String>();
        for (LogEntry le : records){
            int status = le.getStatusCode();
            if (status >= low && status <= high && !uniIp.contains(le.getIpAddress())){
                uniIp.add(le.getIpAddress());
            }
        
        }
        return uniIp.size();
        
     }
     
     
     
     public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            if(le.getStatusCode() > num){
                System.out.println(le);
            }
        } 
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
