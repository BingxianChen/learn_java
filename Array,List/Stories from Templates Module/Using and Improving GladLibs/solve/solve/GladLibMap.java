
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> catego;
    private ArrayList<String> stringList;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        catego = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective","noun","color","country","name","animal","timeframe",
                                     "verb","fruit"};
        for (String s : categories){
            ArrayList<String> wordList = readIt(source + "/" + s + ".txt");
            myMap.put(s,wordList);
        }                             
        stringList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)){
            if (catego.indexOf(label) == -1){
                catego.add(label); 
            }
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int k = 0;
        while(true){
            
            if (stringList.indexOf(sub) == -1){
                stringList.add(sub);
                break;
            }
            sub = getSubstitute(w.substring(first+1,last));
            k++;
            if (k > 200){
                return "stop_stop";
            }
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        int num = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
            num ++;
        }
        System.out.println();
        System.out.println("*************************");
        System.out.println("total number is: " + num);
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int count = 0;
        for (ArrayList<String> wordList : myMap.values()){
            count += wordList.size();
        }
        return count;
    }
    
    private int totaWordsConsidered(){
        int count = 0;
        for (String s : catego){
            count += myMap.get(s).size();
        }
        return count;
    }
    
    public void makeStory(){
        stringList.clear();
        System.out.println("\n");
        //String story = fromTemplate("data/madtemplate2.txt");
        //printOut(story, 60);
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("total number of words in all txt is: " + totalWordsInMap());
        System.out.println("total number of words in considered is: " + totaWordsConsidered());
    }
    
}

























