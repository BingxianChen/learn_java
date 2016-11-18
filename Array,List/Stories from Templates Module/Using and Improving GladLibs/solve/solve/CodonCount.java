
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> codons;
    public CodonCount(){
        codons = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        codons.clear();
        String threeCodon;
        for (int i = start; i < dna.length() - 2; i += 3){
            threeCodon = dna.substring(i,i+3);
            if (codons.containsKey(threeCodon)){
                codons.put(threeCodon,codons.get(threeCodon) + 1);
            }else{
                codons.put(threeCodon,1);
            }
        }  
    }
    
    public String getMostCommonCodon(){
        StringBuffer commonCodon = new StringBuffer();
        int maxCount = 0;
        for(Integer v : codons.values()){
            if (maxCount < v){
                maxCount = v;
            }
        
        }        
        for(String s : codons.keySet()){
            if (codons.get(s) == maxCount){
                commonCodon.append(s + " ");
            }
        }    
        return commonCodon.toString();
    }
    
    public void printCodonCounts(int start, int end){
        for (String s : codons.keySet()){
            if ((codons.get(s) >= start) && (codons.get(s) <= end)){
                System.out.println(s + "  " + codons.get(s));           
            }          
        }    
    }
    
    public void tester(){
        FileResource file = new FileResource();
        String dna = file.asString().toUpperCase().trim();
        for (int i = 0 ; i <= 2; i++){
            buildCodonMap(i,dna);
            int uniqueCodons = codons.size();
            String commonCodons = getMostCommonCodon();
            System.out.println("Reading frame starting with " + i + " results in " + uniqueCodons + " unique codons");
            System.out.println("    and most common codon is " + commonCodons + "with count " + codons.get(commonCodons.substring(0,3)));
            printCodonCounts(5,8);
            System.out.println();
        }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
