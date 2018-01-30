package search;

import java.util.ArrayList;

/**
 * This class keeps track of the number of unique strings added to the
 * dictionary
 * 
 * @author Megan and Isabelle
 * @version 1/30/18
 */
public class Dictionary {
	
	//Instead of an array you will be using something with a hash
	private ArrayList<String> dictionaryList;
	
	/**
	 * Adds a word to the dictionary
	 * 
	 * @param word the word to be added
	 */
	public void addWord(String word){
		//TODO
		if(dictionaryList.contains(word)) {
			//don't do anything
		} 
		else if (!dictionaryList.contains(word)) {
			dictionaryList.add(word);
			size();
		}
	}

	/**
	 * Get the size of the dictionary
	 * 
	 * @return the dictionary size
	 */
	public int size() {
		// TODO
		//Call dictionaryList.size() and it will return the number of 
		//elements in array
		return dictionaryList.size();
	}
}
