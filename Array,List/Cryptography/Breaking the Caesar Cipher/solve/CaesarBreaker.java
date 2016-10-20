
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CaesarBreaker {
    public String decrypt(String encrypted,int key){
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        int key = 12;
        FileResource resource = new FileResource();
        String message = resource.asString();
        String encrypted = cc.encrypt(message,key);
        System.out.println("encrypted: " + encrypted);
        String decrypted = decrypt(encrypted,key);
        System.out.println("decrypted: " + decrypted);
    }
    
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
    
    public String decryptTwoKeys(String encrypted){
        String firstString = halfOfString(encrypted,0);
        String lastString  = halfOfString(encrypted,1);
        int key1 = 0;
        int key2 = 0;
        for (int i = 0; i < 26; i++){
            String s = decrypt(firstString, i);
            if (getKey(s) == 4){
                key1 = i;
                break;
            }
        }
        System.out.println("key1: " + key1);
        
         for (int i = 0; i < 26; i++){
            String s = decrypt(lastString, i);
            if (getKey(s) == 4){
                key2 = i;
                break;
            }
        }
        System.out.println("key2: " + key2);
        
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted,26 - key1,26 - key2);
        System.out.println(decrypted);
        return decrypted;
    }
    
    public void testdecryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted,26 - 2,26 - 20);
        System.out.println(decrypted);
    }
    
    public void testdecryptTwoKeysFromFile(){
         FileResource resource = new FileResource();
         String s = decryptTwoKeys(resource.asString());
         System.out.println(s);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}