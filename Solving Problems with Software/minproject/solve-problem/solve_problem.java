
/**
 * Write a description of solve_problem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class solve_problem {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        List<String> numberOfGirlsNames = new ArrayList<String>();
        List<String> numberOfBoysNames = new ArrayList<String>();
        int numberOfTotalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
               totalBoys += numBorn;
                int k = 0;
                for(String li : numberOfBoysNames){
                    if (li.equals(rec.get(0))){
                        //System.out.println(li);
                        k ++;
                    }
                }
                if (k == 0){
                    numberOfBoysNames.add(rec.get(0));
                }
               
            }
            
             if (rec.get(1).equals("F")) {
                totalGirls += numBorn;
                int k = 0;
                for(String li : numberOfGirlsNames){
                    if (li.equals(rec.get(0))){
                        //System.out.println(li);
                        k ++;
                    }
                }
                if (k == 0){
                    numberOfGirlsNames.add(rec.get(0));
                }
               
            }
        }
        //numberOfTotalNames = numberOfGirlsNames.size() + numberOfBoysNames.size();
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("number of girls names = " + numberOfGirlsNames.size());
        System.out.println("number of boys names = " + numberOfBoysNames.size());  
        System.out.println("number of totals names = " + numberOfTotalNames);
    }
    
    public int getRank(int year,String name,String gender){
       FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year +".csv");
       int rank = 0;
       for (CSVRecord rec : fr.getCSVParser(false)) {
           if (rec.get(1).equals(gender)){
               rank ++;
               if (rec.get(0).equals(name)) {
                   return rank;
               }
           }
           
       }
       return -1;   
    }
    
    public String getName(int year,int rank,String gender){
       FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year +".csv");
       int count = 0;
       for (CSVRecord rec : fr.getCSVParser(false)) {
           if (rec.get(1).equals(gender)){
               count ++;
               if (count == rank) {
                   return rec.get(0);
               }
           }
           
       }
       return "NO NAME";   
    
    
    }
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        String choiceGender = "he";
        if (gender.equals("F")){
            choiceGender = "she";
        }
        System.out.println( name + " born in " + year + " would be " + newName 
                                + " if " + choiceGender +" was born in " + newYear +".");
    
    
    }
    
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;
        int rankYear = -1;
        int year = -1;
        int rank = -1;
        int higthestRank = 999999;
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            fileName = f.getName();
            year = Integer.parseInt(fileName.substring(3,7));
            rank = getRank(year,name,gender);
            System.out.println(rank);
            //if (higthestRank == -1) {
            //    higthestRank = rank +1;
            //}
            if (rank < higthestRank && rank != -1){
                higthestRank = rank;
                rankYear = year;
            }
        }
        if (higthestRank == -1){
            return higthestRank;
        }else{
            return rankYear;
        }
      
    }
    
    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;
        int year = -1;
        double rank = -1;
        double averageRank = 0.0;
        int count = 0;
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            fileName = f.getName();
            year = Integer.parseInt(fileName.substring(3,7));
            rank = getRank(year,name,gender);
            averageRank += rank;
            count ++;
        }
        return averageRank/count;
    
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
       FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year +".csv");
       int total = 0;
       for (CSVRecord rec : fr.getCSVParser(false)) {
           if (rec.get(1).equals(gender)){
               
               if (rec.get(0).equals(name)) {
                  break;
               }
               else{
                  total += Integer.parseInt(rec.get(2));
               }
           }
           
       }
       return total;   
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1995.csv");
        //FileResource fr = new FileResource("data/yob1990.csv");
        totalBirths(fr);
    }
    
    public void testGetRank () {
        //FileResource fr = new FileResource();
        //System.out.println(getRank(1960,"Emily","F"));
        System.out.println(getRank(1971,"Frank","M"));
    }
    
     public void testGetName () {
        //FileResource fr = new FileResource();
        System.out.println(getName(1980,350,"F"));
        System.out.println(getName(1982,450,"M"));
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Susan",1972,2014,"F");
        whatIsNameInYear("Owen",1974,2014,"M");
    }
    
    public void testYearOfHighestRank() {
        //System.out.println(yearOfHighestRank("Genevieve","F"));
        System.out.println(yearOfHighestRank("Mich","M"));
    }
    
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Susan","F"));
        System.out.println(getAverageRank("Robert","M"));
    }
    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}