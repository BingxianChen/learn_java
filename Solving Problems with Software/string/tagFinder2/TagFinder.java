/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    
    public String loopindex(String dnaLower, String dna,String stopKey ,int start){
        int stop = dnaLower.indexOf(stopKey, start+3);
         while(true){
            if ((stop - start) % 3 == 0) {
                return dna.substring(start, stop+3);
            }
            stop = dna.indexOf(stopKey, stop + 3);
            if(stop == -1){
                break;
            }
       
        }
        return "not";
          
    }
    
    
    
    public String findProtein(String dna) {
        String dnaLower = dna.toLowerCase();
        int start = dnaLower.indexOf("atg");
        if (start == -1) {
            return "";
        }
        String protein = loopindex(dnaLower,dna,"tag",start);
        if(!protein.equals("not")){
            System.out.println("tag");
            return protein;
        }
        protein = loopindex(dnaLower,dna,"tga",start);
        if(!protein.equals("not")){
            System.out.println("tga");
            return protein;
        }
        protein = loopindex(dnaLower,dna,"taa",start);
         if(!protein.equals("not")){
            System.out.println("taa");
            return protein;
        }
        
        return "";
 
    }
    
    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
}
