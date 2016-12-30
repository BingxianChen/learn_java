
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
	   ArrayList<String> foll = new ArrayList<String>();
	   int len = key.length();
	   for (int i = 0; i < myText.length(); i++){
	       try{
	           if(key.equals(myText.substring(i,i + len))){
	               foll.add(myText.substring(i+len,i + len + 1));
	           }
	       }catch(Exception e){
	           break;
	       } 
	   }
	   return foll;
	}
}
