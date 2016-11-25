import java.util.*;
import edu.duke.*;

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

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String s = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(s,5,'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String dc = vc.decrypt(s);
        System.out.println(dc);
    }
    
}
