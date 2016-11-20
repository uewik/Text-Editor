package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	public boolean isRightCase(String s)
	{
    	if (s.length() == 0) return false;
    	if ((s.equals(s.toLowerCase()) || s.equals(s.toUpperCase())))		//all letters are lowercase or all letters are uppercase
    		return true;
    	if (s.length() > 1 && Character.isUpperCase(s.charAt(0)) && s.substring(1, s.length()).equals(s.substring(1, s.length()).toLowerCase()))		// first letter is uppercase, other letters are lowercases.
    		return true;
    	return false;
	}
	/** Insert a word into the trie.
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary or it's not right case.
	 */
	public boolean addWord(String word)
	{
		if (!isRightCase(word))
			return false;
		String LowerCaseWord = word.toLowerCase();
		TrieNode node = root;
		boolean wordAdded = false;
		for (int i = 0; i < LowerCaseWord.length(); ++i)
		{
			char ch = LowerCaseWord.charAt(i);
			TrieNode nextNode = node.insert(ch);
			if (nextNode == null)
				node = node.getChild(ch);
			else
			{
				node = nextNode;
				if (!wordAdded)
					wordAdded = true;
			}
		}
		if (!node.endsWord())
			node.setEndsWord(true);
	    return wordAdded;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		int num = 0;
		Queue<TrieNode> q = new LinkedList<TrieNode>();
		q.add(root);
		while(!q.isEmpty())
		{
			TrieNode node = q.remove();
			if (node.endsWord())
				++num;
			Object[] chs =  node.getValidNextCharacters().toArray();
			for (Object ch : chs)
			{
				q.add(node.getChild(ch.toString().charAt(0)));
			}
		}
	    return num;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if (!isRightCase(s))
			return false;
		String LowerCaseS = s.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < LowerCaseS.length(); ++i)
		{
			char ch = LowerCaseS.charAt(i);
			TrieNode nextNode = node.getChild(ch);
			if (nextNode != null)
				node = nextNode;
			else
				return false;
		}
		return node.endsWord();
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> list = new LinkedList<String>();
    	 if (!isRightCase(prefix))	return list;
    	 String LowerCasePre = prefix.toLowerCase();
 		 TrieNode node = root;
 		 for (int i = 0; i < LowerCasePre.length(); ++i)
 		 {
 			 char ch = LowerCasePre.charAt(i);
 			 TrieNode nextNode = node.getChild(ch);
 			 if (nextNode != null)
 				 node = nextNode;
 			 else
 				 return list;
 		 }
 		Queue<TrieNode> q = new LinkedList<TrieNode>();
 		q.add(node);
 		int num = 0;
 		while(!q.isEmpty() && num < numCompletions)
 		{
 			TrieNode nextNode = q.remove();
 			if (nextNode.endsWord())
 			{
 				if (prefix.length() > 1 && prefix.equals(prefix.toUpperCase()))
 					list.add(nextNode.getText().toUpperCase());
 				else if (Character.isUpperCase(prefix.charAt(0)))
 				{
 					list.add(nextNode.getText().substring(0, 1).toUpperCase() + nextNode.getText().substring(1, nextNode.getText().length()));
 				}
 				else
 					list.add(nextNode.getText());
 				++num;
 			}
 			Object[] chs =  nextNode.getValidNextCharacters().toArray();
 			for (Object ch : chs)
 			{
 				q.add(nextNode.getChild(ch.toString().charAt(0)));
 			}
 		}
 		return list;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}