
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> arr = new ArrayList<String>();
        arr = markov.getFollows("t");
        System.out.println(arr);
        System.out.println(arr.size());
        arr = markov.getFollows("e");
        System.out.println(arr);
        System.out.println(arr.size());
        arr = markov.getFollows("es");
        System.out.println(arr);
        System.out.println(arr.size());
    
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//MarkovZero markov = new MarkovZero();
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		ArrayList<String> arr = new ArrayList<String>();
		arr = markov.getFollows("o");
		System.out.println("o: " + arr.size());
		System.out.println("th: " + markov.getFollows("th").size());
    }
}
