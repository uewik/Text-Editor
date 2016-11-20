package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
	DictionaryLL()
	{
		dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String lowerCaseWord = word.toLowerCase();
    	if (!dict.contains(lowerCaseWord))
    	{
    		dict.add(lowerCaseWord);
        	return true;
    	}
    	return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	String lowerCaseS = s.toLowerCase();
    	return dict.contains(lowerCaseS);
    }
}
