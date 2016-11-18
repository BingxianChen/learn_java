
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> maps;
    public WordsInFiles(){
        maps = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String s : fr.words()){
            if (maps.containsKey(s)){
                if (maps.get(s).indexOf(f.getName()) == -1){
                    ArrayList<String> fileList = maps.get(s);
                    fileList.add(f.getName());
                    maps.put(s,fileList);
                }
            }else{
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                maps.put(s,fileList);
            }
        }
    }
    
    public void buildWordFileMap(){
        maps.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String s : maps.keySet()){
            if (max < maps.get(s).size()){
                max = maps.get(s).size();
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for (String s : maps.keySet()){
            if (number == maps.get(s).size()){
                wordList.add(s);
            }
        }
        return wordList;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> fileList = maps.get(word);
        for (String s : fileList){
            System.out.println(s);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("maxNum is: " + maxNum);
        System.out.println("max words is");
        ArrayList<String> files = wordsInNumFiles(5);
        //System.out.println("total words occur in five files: " + files.size());
        System.out.println("total words occur in four files: " + wordsInNumFiles(4).size());
        printFilesIn("tree");
        //printFilesIn("red");
        
        
        //System.out.println("**************************************");
        //StringBuffer sb = new StringBuffer();
        //for (String s : maps.keySet()){
        //    sb.delete(0,sb.length());
        //    ArrayList<String> fileList = maps.get(s);
        //    sb.append(s+":  ");
        //    for(String f : fileList){
        //        sb.append(f + " ");
        //    }
        //    System.out.println(sb.toString());
        //}
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
}





























