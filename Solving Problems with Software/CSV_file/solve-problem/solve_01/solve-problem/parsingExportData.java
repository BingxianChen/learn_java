
/**
 * Write a description of parsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class parsingExportData {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String country){
       for (CSVRecord record : parser){
           if (record.get("Country").contains(country)){
               String info = record.get("Country") +":" + record.get("Exports") + ":" + record.get("Value (dollars)");
               return info;  
           }  
       }
       return "NOT FOUND";
       
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,String exportItem2){
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }  
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int num = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                num ++;
            
            }
        }
        return num;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            if (compare(record.get("Value (dollars)"),amount)){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
            
            
        }
    }
    
    public boolean compare(String cvsValue,String amount){
        if (cvsValue.length() > amount.length()){
            return true;
        }else if(cvsValue.length() == amount.length() ){
            if(cvsValue.compareTo(amount) >  0){
                return true;
            }else{
                return false;
            }
        
        }else{
            return false;
        }
    }
    
    

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.print(countryInfo(parser,"Nauru"));
        //listExportersTwoProducts(parser,"gold","diamonds");
        //System.out.println(numberOfExporters(parser,"sugar"));
        //bigExporters(parser,"rea");
        bigExporters(parser,"$999,999,999,999");
    }

}
