
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
     
     public HashMap<String,Integer> countVisitsPerIp(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip,1);
            }else{
                counts.put(ip,counts.get(ip) + 1);
            }
        }
        return counts;
     }
     
     public int mostNumberVisitsByIp(HashMap<String,Integer> counts){
        int max = 0;
        for (int num : counts.values()){
            if (max < num){
                max = num;
            }
        }
        return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        int max = mostNumberVisitsByIp(counts);
        ArrayList<String> ips = new ArrayList<String>();
        for(String s : counts.keySet()){
            if (counts.get(s) == max){
                ips.add(s);
            }
        }
        return ips;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> countsMap = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records){
            String[] dateArray = le.getAccessTime().toString().split(" ");
            String date = dateArray[1] + " " + dateArray[2];
            if (!countsMap.containsKey(date)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(le.getIpAddress());
                countsMap.put(date,list);
            }else{
                ArrayList<String> list = countsMap.get(date);
                list.add(le.getIpAddress());
                countsMap.put(date,list);
            }
         }
         return countsMap;
     }
     
     public ArrayList<String> dayWithMostIpVisits(HashMap<String,ArrayList<String>> countsMap){
        int max = 0;
        ArrayList<String> dateList = new ArrayList<String>(); 
        for (ArrayList<String> list : countsMap.values()){
            if (list.size() > max){
                max = list.size();
            }
        }
        
        for (String s : countsMap.keySet()){
            if (countsMap.get(s).size() == max){
                dateList.add(s);
            }
        }
        return dateList;
        
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> countsMap, String someDay){
        ArrayList<String> ipsListDay = new ArrayList<String>(); 
        for (String s : countsMap.get(someDay)){
            if (ipsListDay.indexOf(s) == -1){
                ipsListDay.add(s);
            }
        }
        return ipsListDay;
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
