
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        int count = 0;
        StringBuffer newMessage = new StringBuffer();
        for (int i = start; i < message.length(); i++){
            if(count % 2 == 0){
                newMessage.append(message.charAt(i));
            }
            count ++;
        }
        return newMessage.toString();
    }
    
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
        String firstString = halfOfString(input,0);
        String lastString  = halfOfString(input,1);
        int key1 = 0;
        int key2 = 0;
        for (int i = 0; i < 26; i++){
            CaesarCipher cc = new CaesarCipher(i);
            String s = cc.decrypt(firstString);
            if (getKey(s) == 4){
                key1 = i;
                break;
            }
        }
        System.out.println("key1: " + key1);
        
         for (int i = 0; i < 26; i++){
            CaesarCipher cc = new CaesarCipher(i);
            String s = cc.decrypt(lastString);
            if (getKey(s) == 4){
                key2 = i;
                break;
            }
        }
        System.out.println("key2: " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String decrypted = cc.decrypt(input);
        System.out.println("decrypt the message: " + decrypted);
    
    }
    
    
    
    
    
    
    
    
    public void simpleTests(){
        FileResource file = new FileResource();
        String input = file.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encrypt(input);
        System.out.println("encrypt: " + encrypted);
        String decrypted = cc.decrypt(input);
        System.out.println("decrypt: " + decrypted);
        breakCaesarCipher(input);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
