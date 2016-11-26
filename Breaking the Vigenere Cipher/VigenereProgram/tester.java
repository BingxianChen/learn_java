import edu.duke.*;

/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    public void test1(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(s,4,'e');
        for (int i = 0; i < 4 ; i++){
            System.out.println(keys[i]);
        }

    }
}
