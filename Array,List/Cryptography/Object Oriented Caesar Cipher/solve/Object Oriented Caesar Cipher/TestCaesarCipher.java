
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class TestCaesarCipher {
   public int[] countLetters(String s){
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet = alphabetUpper.toLowerCase();
        int[] counts = new int[26];
        for (int i = 0;i < s.length(); i++){
            char k = Character.toLowerCase(s.charAt(i));
            int index = alphabet.indexOf(k);
            if (index != -1){
                counts[index] ++;
            }
        }
        return counts;
    }
    
   public int maxIndex(int[] counts){
        int max = counts[0];
        int index = 0;
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > max){
                max = counts[i];
                index = i;
            }
        }
        return index;
   }
   
   public int getKey(String s){
        int[] counts = countLetters(s);
        int key = maxIndex(counts);
        return key;   
    }
   
   public void breakCaesarCipher(String input){
       int key = 0;
       for (int i = 0; i < 26; i++){
           CaesarCipher cc = new CaesarCipher(i);
           String s = cc.decrypt(input);
           if (getKey(s) == 4){
               key = i;
               break;
           }
       }
       System.out.println("key: " + key);
       CaesarCipher cc = new CaesarCipher(key);
       String s = cc.decrypt(input);
       System.out.println("decrypt the message: " + s);
   }
   
   public void simpleTests(){
       FileResource file = new FileResource();
       String s = file.asString();
       CaesarCipher cc = new CaesarCipher(15);
       String encrypted = cc.encrypt(s);
       System.out.println("encrypt: " + encrypted);
       String decrypted = cc.decrypt(s);
       System.out.println("decrypt: " + decrypted);
       breakCaesarCipher(s);
       
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
