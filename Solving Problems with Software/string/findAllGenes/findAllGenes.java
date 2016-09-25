
/**
 * Write a description of findAllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
public class findAllGenes {
    public int findStopIndex(String dna, int index){
       int startCodon = dna.indexOf("atg",index);
       if(startCodon == -1 ){
           return -1;
        }
        
       int min = dna.length();
        
       int stopCodon = dna.indexOf("tag",startCodon + 3);
     
       if ( (stopCodon != -1) &&((stopCodon - startCodon)%3 == 0) ){
           if(min > stopCodon){
              min = stopCodon;
            }
       }
       
       stopCodon = dna.indexOf("tga",startCodon + 3);
      
       if ( (stopCodon != -1) &&((stopCodon - startCodon)%3 == 0) ){
           if(min > stopCodon){
              min = stopCodon;
            }
       }
       
       stopCodon = dna.indexOf("taa",startCodon + 3);
      
       if ( (stopCodon != -1) &&((stopCodon - startCodon)%3 == 0)){
           if(min > stopCodon){
              min = stopCodon;
            }
       }
       
       if(min != dna.length() ){
           return min;
        }else{
           return -1;
        }

   
   }
   
   public void printAll(String DNA){
       String dna = DNA.toLowerCase();
       int index = 0;
       int stopCodon = 0;
       //int jump = 0;
       while(true){
           stopCodon = findStopIndex(dna,index);
           if(stopCodon == -1){
               
               index = dna.indexOf("atg", index + 3);
               if(index != -1){
                   continue;

                }
               break;
            
            
            }
   
           String gene = DNA.substring(index, stopCodon + 3);
           System.out.println(gene);
           
           index = dna.indexOf("atg", stopCodon);
           if(index == -1){
            break;
            }
        }
    }
    
    public StorageResource storeAll(String DNA){
        
       StorageResource store = new StorageResource(); 
       String dna = DNA.toLowerCase();
       int index = dna.indexOf("atg", 0);;
       int stopCodon = 0;
       int start = 0;
       int ctgnum = 0;
       while (true) {
            start = dna.indexOf("ctg", start);
            if (start == -1) {
                break;
            }
            ctgnum ++;
            start = start + 2;
       }
       System.out.println("*******************************************");
       System.out.println(" number of ctg appear times:   " + ctgnum);
       System.out.println("*******************************************");
        
       //int jump = 0;
       while(true){
           stopCodon = findStopIndex(dna,index);
           if(stopCodon == -1){
               
               index = dna.indexOf("atg", index + 3);
               if(index != -1){
                   continue;

                }
               break;
            }
   
           String gene = DNA.substring(index, stopCodon + 3);
           
           //if(gene.length() != 6 ){
           //    System.out.println(gene);
           //    store.add(gene);
           //}
           store.add(gene);
           index = dna.indexOf("atg", stopCodon);
           if(index == -1){
            break;
            } 
        }
       return store;
    }
    
    public void testStorageFinder(){
        FileResource fr = new FileResource("/Users/chen/Desktop/dna/GRch38dnapart.fa");
        String DNA = fr.asString();
        
        
        
        StorageResource store= storeAll(DNA);
        printGenes(store);
    
    }
    
    public float cgRatio(String DNA){
        String dna = DNA.toLowerCase();
        int size = dna.length();
        float cgs = 0l;
        int index = 0;
        for(int i = 0; i < size; i++){
            if(String.valueOf(dna.charAt(i)).equals("c") || String.valueOf(dna.charAt(i)).equals("g")){
                cgs++;
            }
            
        }
        return (float)(cgs/size);
    }
    
    public void printGenes(StorageResource sr){
        
        System.out.println("*******************************************");
        System.out.println(" number of strings :   " + sr.size());
        System.out.println("*******************************************");
        int num = 0;
        int numcg = 0;
        for (String item : sr.data()) {
           if(item.length() > 60){
               System.out.println("longer than 60 characters:   " + item);
               num ++;
                
            }
        }  
        System.out.println("*******************************************");
        System.out.println("number of Strings that are longer than 60 characters:   " + num);
        System.out.println("*******************************************");
        for (String item : sr.data()) {
           if(cgRatio(item) > 0.35){
               System.out.println("C-G-ratio is higher than 0.35:   " + item);
               numcg ++;
                
           }
        }
        System.out.println("*******************************************");
        System.out.println(" number of strings whose C-G-ratio is higher than 0.35:   " + numcg);
        System.out.println("*******************************************");
    
        int max = 0;
        for (String item : sr.data()) {
           if(max < item.length()){
               System.out.println("maxer :   " + item);
               max = item.length();
                
           }
        }
        System.out.println("*******************************************");
        System.out.println(" length of max str:   " + max);
        System.out.println("*******************************************");
    
    
    
    }
    
    
    
    
}
   
  




















