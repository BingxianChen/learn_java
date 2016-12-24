
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where; 
    private String pharse;
    
    public PhraseFilter(String whe, String phars){
        where = whe;
        pharse = phars;
    }
    
    public String getName(){
        return "PhraseFilter";
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        if (where.equals("start")){
            if (qe.getInfo().startsWith(pharse)){
                return true;
            }
        }
        if (where.equals("end")){
            if (qe.getInfo().endsWith(pharse)){
                return true;
            }
        }
        if (where.equals("any")){
            if (qe.getInfo().indexOf(pharse) != -1){
                return true;
            }
        }
        return false;
    }
}