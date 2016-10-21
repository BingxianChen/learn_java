
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
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
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted =  cc.encrypt(input);
        return decrypted;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
