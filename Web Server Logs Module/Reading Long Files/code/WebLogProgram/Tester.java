
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer lay = new LogAnalyzer();
        lay.readFile("short-test_log");
        lay.printAll();
    }
    
    public void textUniqueIp(){
        LogAnalyzer lay = new LogAnalyzer();
        lay.readFile("weblog2_log");
        lay.printAllHigherThanNum(400);
        System.out.println("Unique ip number is: " + lay.countUniquelPs());
        
        
        //lay.readFile("weblog-short_log");
        ArrayList<String> uniIpDay = lay.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(uniIpDay.size());
        
        //lay.readFile("short-test_log");
        int uniIp = lay.countUniqueIPsInRange(200,299);
        System.out.println(uniIp);
    
    }
    
    public void textHashMap(){
        LogAnalyzer lay = new LogAnalyzer();
        lay.readFile("weblog2_log");
        HashMap<String,Integer> counts = lay.countVisitsPerIp();
        System.out.println(lay.mostNumberVisitsByIp(counts));
        ArrayList<String> ips = lay.iPsMostVisits(counts);
        System.out.println(ips);
        HashMap<String,ArrayList<String>> countsMap = lay.iPsForDays();
        System.out.println(countsMap);
        ArrayList<String> dateList = lay.dayWithMostIpVisits(countsMap);
        System.out.println(dateList);
        ArrayList<String> ipsListDay = lay.iPsWithMostVisitsOnDay(countsMap,"Sep 30");
        System.out.println(ipsListDay);
    }
    
}
