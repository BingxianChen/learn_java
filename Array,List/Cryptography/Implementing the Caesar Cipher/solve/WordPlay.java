
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
    
    public boolean isVowel(char ch){
        String vowel = "aeiouAEIOU";
        for(int i = 0; i < vowel.length() ; i++){
            if (vowel.charAt(i) == ch){
                return true;
            } 
        }
        return false;
    
    }
    
    public String replaceVowels(String phrase,char ch){
        StringBuffer phraseBuffer = new StringBuffer(phrase);
        for (int i = 0;i < phraseBuffer.length();i++){
            if(isVowel(phraseBuffer.charAt(i))){
                phraseBuffer.setCharAt(i,ch);
            }
        }
        return phraseBuffer.toString();
    }
    
    public String emphasize(String phrase,char ch){
        StringBuffer phraseBuffer = new StringBuffer(phrase);
        for (int i = 0;i < phraseBuffer.length();i++){
            
        
        }
    
    }
    
    
    
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
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}