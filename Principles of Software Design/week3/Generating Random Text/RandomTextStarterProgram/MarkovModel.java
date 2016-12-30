
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;
public class MarkovModel {
    private String myText;
	private Random myRandom;
	private int n;
	public MarkovModel(int N) {
		myRandom = new Random();
		n = N;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
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
	
	public ArrayList<String> getFollows(String key){
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
