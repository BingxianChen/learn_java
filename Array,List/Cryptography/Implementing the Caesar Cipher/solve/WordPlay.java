
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
            if (phraseBuffer.charAt(i) == ch){
                if (i%2 == 0){
                    phraseBuffer.setCharAt(i,'*');
                }else{
                    phraseBuffer.setCharAt(i,'+');
                }
            }
        }
        return phraseBuffer.toString();
    }
    
    public void testReplaceVowels(){
        String a = replaceVowels("Hello World",'*');
        System.out.println(a);
    }
    
    public void testEmphasize(){
        String a = emphasize("Mary Bella Abracadabra",'a');
        System.out.println(a);
    }
    
    
    
    
}