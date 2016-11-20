package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBSTMatchCase implements Dictionary 
{
   private TreeSet<String> dict;
	
	public DictionaryBSTMatchCase()
	{
		dict = new TreeSet<String>();
	}
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String lowerCaseWord = word.toLowerCase();
    	return dict.add(lowerCaseWord);
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	if (s.length() == 0) return false;
    	if ((s.equals(s.toLowerCase()) || s.equals(s.toUpperCase())) && dict.contains(s.toLowerCase()))		//all letters are lowercase or all letters are uppercase
    		return true;
    	if (s.length() > 1 && Character.isUpperCase(s.charAt(0)) && s.substring(1, s.length()).equals(s.substring(1, s.length()).toLowerCase()) && dict.contains(s.toLowerCase()))		// first letter is uppercase, other letters are lowercases.
    		return true;
    	return false;
    }

}
