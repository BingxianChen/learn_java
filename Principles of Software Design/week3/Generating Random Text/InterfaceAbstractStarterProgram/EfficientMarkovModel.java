
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int n;
    private HashMap<String,ArrayList<String>> map;
    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        n = N;
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index,index+n);
        sb.append(key);
        for(int k=0; k < numChars - n; k++){
            ArrayList<String> foll = getFollows(key);
            if (foll.size()==0){
              break;
            }
            index = myRandom.nextInt(foll.size());
            String next = foll.get(index);
            sb.append(foll.get(index));
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public void buildMap(){
       for (int i = 0; i <= myText.length() - n; i ++ ){
           String key = myText.substring(i, i+n);
           if (map.containsKey(key)){
               continue;
           }else{
               ArrayList<String> foll = new ArrayList<String>();
               for (int j=i; j < myText.length() - n; j ++){
                   
                   if (key.equals(myText.substring(j, j+n))){
                       foll.add(myText.substring(j+n, j+n+1));
                   }
               }
               map.put(key,foll);
           }
        
       } 
    }
    
    public ArrayList<String> getFollows(String key){
	   return map.get(key);
	}
    
    public String toString(){
       return "MarkovModel of order " + n;
    }
    
    public void printHashMapInfo(){
        int max = 0;
        String maxKey = "";
        for(String key:map.keySet()){
            System.out.println(key + " : " + map.get(key));
            if (max < map.get(key).size()){
                max = map.get(key).size();
                maxKey = key;
            }
        }
        System.out.println("**********************");
        System.out.println("number of keys is : " + map.size());
        System.out.println("size of the largest value in the HashMap : " + max);
        System.out.println("keys that have the maximum size value : ");
        
        for(String key:map.keySet()){
            if (max == map.get(key).size()){
                System.out.println(key);
            }
        }
    }
    

}

