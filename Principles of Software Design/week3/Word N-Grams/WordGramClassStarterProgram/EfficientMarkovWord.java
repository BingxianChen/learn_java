
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class EfficientMarkovWord implements IMarkovModel{
    
    private HashMap<WordGram,ArrayList<String>> map;
    private String[] myText;
    private int myOrder;
    private Random myRandom; 
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram,ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }
    
    private int indexOf(String[] words, WordGram target, int start){
       for (int i = start; i < words.length - target.length(); i ++){
           if ((new WordGram(myText,i,myOrder).equals(target))){
               return i;
           }
       }
       return -1;
    }
    
    /*
    public void testIndexOf(){
        //String[] arr = {"this","is","just","a","test"};
        String[] arr = "this is just a test yes this is a simple test".split("\\s+");
        System.out.println(indexOf(arr,"this",3) + " “this” starting at 0,");
    }
    */
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram wg = new WordGram(myText,index,myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }
    
    public void buildMap(){
        //HashMap<WordGram,ArrayList<String>> map = new HashMap<WordGram,ArrayList<String>>();
        for (int i = 0 ; i < myText.length - myOrder + 1; i ++){
            WordGram kGram = new WordGram(myText,i,myOrder);
            if (!map.containsKey(kGram)){
                ArrayList<String> arr = new ArrayList<String>();
                int index = 0;
                while (true){
                    index = indexOf(myText,kGram,index);
                    if (index == -1 ||index==myText.length - myOrder){
                        break;
                    }
                    index = index + myOrder;
                    arr.add(myText[index]);
                }
                map.put(kGram,arr);
            }
        }
    }
    
    public void printHashMapInfo(){
        int max = 0;
        for (WordGram wg : map.keySet()){
            System.out.println(wg.toString() + map.get(wg));
            if (max < map.get(wg).size()){
                max = map.get(wg).size();
            }
        }
        System.out.println("the number of keys in the HashMap:" + map.size());
        System.out.println("maximum number of elements following a key is:" + max);
        for (WordGram wg : map.keySet()){
            if (max == map.get(wg).size()){
                System.out.println(wg.toString());
            }
        }
    }
}
