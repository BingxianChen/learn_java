
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class MarkovWord implements IMarkovModel{
    
    private String[] myText;
    private int myOrder;
    private Random myRandom; 
    public MarkovWord() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, String target, int start){
       for (int i = start; i < words.length; i ++){
           if (words[i].equals(target)){
               return i;
           }
       }
       return -1;
    }
    
    public void testIndexOf(){
        //String[] arr = {"this","is","just","a","test"};
        String[] arr = "this is just a test yes this is a simple test".split("\\s+");
        System.out.println(indexOf(arr,"this",3) + " “this” starting at 0,");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = 0;
        while (true){
            index = indexOf(myText,key,index);
            if (index == -1 ||index==myText.length - 1){
                break;
            }
            index ++;
            follows.add(myText[index]);
        }
        return follows;
    }
    
    public void getRandomText(String key){
        //String[] arr = {"this","is","just","a","test"};
        System.out.println(key + " " + getFollows(key));
    }
}
