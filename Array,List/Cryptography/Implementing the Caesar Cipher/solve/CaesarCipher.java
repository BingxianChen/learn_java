
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if (Character.isUpperCase(currChar) ){
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }else{
                int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){ 
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if (Character.isUpperCase(currChar) ){
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = 'a';
                    if (i%2 == 0) {
                         newChar = shiftedAlphabet1.charAt(idx);
                    }else{
                         newChar = shiftedAlphabet2.charAt(idx);
                    }
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                    
                }
            }else{
                int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = 'a';
                    if (i%2 == 0) {
                         newChar = shiftedAlphabet1.charAt(idx);
                    }else{
                         newChar = shiftedAlphabet2.charAt(idx);
                    }
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    
    
    }
    
    
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is:" + key + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
    
    public void testencryptTwoKeys() {
        int key1 = 8;
        int key2 = 21;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("key is:" + key1 +" and "+ key2 + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
}
