import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuffer sb = new StringBuffer();
        //StringBuffer mesb = new StringBuffer(message);
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength ; i ++){
            String s = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(s);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String s : fr.lines()){
            s.toLowerCase();
            hs.add(s);
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] arr = message.split("\\W");
        int count = 0;
        for (int i = 0; i< arr.length; i ++){
            
            if(dictionary.contains(arr[i].toLowerCase())){
                count ++;
            }
        }
        return count;
    
    }
    
    public String[] breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String[] s = new String[2];
        char cha = mostCommonCharIn(dictionary);
        int[] mostkeys = new int[100];
        for (int i = 1; i<= 100; i++){
            
            int[] keys = tryKeyLength(encrypted, i, cha);
            VigenereCipher vc = new VigenereCipher(keys);
            String dc = vc.decrypt(encrypted);
            int count = countWords(dc,dictionary);
            //System.out.println("length:" + i + "count: " + count);
            if (max < count) {
                max = count;
                //System.out.println("max: " + "length:" + i + "count: " + max);
                s[0] = dc;
                s[1] = String.valueOf(max);
                mostkeys = keys;
            }
        }
        System.out.print("keys: ");
        for (int i = 0; i < mostkeys.length; i ++) {
            System.out.print(mostkeys[i] + " ");
        }
        System.out.println();
        System.out.println("most common character: " + cha);
        return s;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String s : dictionary){
            for (int k = 0; k < s.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(s.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }
        }
        int max = 0;
        int index = 0;
        for (int i = 0; i < 26; i++){
            if(max < counts[i]){
                max = counts[i];
                index = i;
            }   
        }
        return alph.charAt(index);
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String,HashSet<String>> languages){
        String lang = "";
        String de = "";
        int maxcount = 0;
        for (String s : languages.keySet()) {
            String[] deandnum = breakForLanguage(encrypted, languages.get(s));
            String message = deandnum[0];
            int num = Integer.valueOf(deandnum[1]);
            if (maxcount < num){
                maxcount = num;
                lang = s;
                de = message;
            }
            //System.out.println("decrypted: " + message);
            System.out.println("languages: " + s);
            System.out.println("counts: " + num);
            System.out.println();
        }
        System.out.println(" ************************* ");
        System.out.println("decrypted: " + de);
        System.out.println("languages: " + lang);
        System.out.println("counts: " + maxcount);
    }
    
    public void breakVigenere () {
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            HashSet<String> dict = readDictionary(fr);
            languages.put(f.getName(),dict);
            System.out.println(f.getName() + "download finish!");
        }
        System.out.println();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        breakForAllLanguages(encrypted, languages);
    }
    
    
    
    
    
    
    
    
    
    
}
