
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void update(String person){ 
        int index = names.indexOf(person);
        if (index == -1){
            names.add(person);
            freqs.add(1);
        }
        else {
            int freq = freqs.get(index);
            freqs.set(index,freq+1);
        }
    }
    
    public void findAllCharacters(){
        FileResource resource = new FileResource();
        for (String line : resource.lines()){
            int index = line.indexOf(".");
            if (index != -1){
                String person = line.substring(0,index);
                update(person);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max = freqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < freqs.size(); k++){
            if (freqs.get(k) > max){
                max = freqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        findAllCharacters();
        //for (int index = 0; index < names.size(); index ++) {
        //    System.out.println(names.get(index) + " " + freqs.get(index));
        //}
        
        int maxIndex = findIndexOfMax();
        System.out.println("**********************************");
        System.out.println("main character and freqs: " + names.get(maxIndex) + " " + freqs.get(maxIndex));
        
        System.out.println("**********************************");
        charactersWithNumParts(10, 15);
    
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int k=0; k < freqs.size(); k++){
            if ((freqs.get(k) >= num1) && (freqs.get(k) <= num2)){
               System.out.println(names.get(k) + " " + freqs.get(k));
            }
        }
    
    
    }
    
}













