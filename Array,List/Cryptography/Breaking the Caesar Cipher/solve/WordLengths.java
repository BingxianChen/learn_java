
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length() - 1);
            int count = word.length();
            if (!Character.isLetter(firstChar)){
                count --;
            }
            if (!Character.isLetter(lastChar)){
                count --;
            }
            if (count >=1){
                counts[count] ++;
            }
        }
    }
    
    public int indexOfMax(int[] counts){
        int max = counts[1];
        for (int i = 1; i < counts.length; i++){
            if ( counts[i] > max){
                max = counts[i];
            }
        }
        return max;
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource,counts);
        for (int i = 1; i < counts.length; i ++){
            if (counts[i] == 0){
                continue;
            }
            System.out.println(counts[i] + " words of length: " + i);
        
        }
        System.out.println("most common word length is: " + indexOfMax(counts));
    }

}