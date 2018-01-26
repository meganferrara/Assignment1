package search;

import java.util.ArrayList;

/**
 * This class keeps track of the number of unique strings added to the
 * dictionary
 * 
 * @author dkauchak
 *
 */
public class Dictionary {
	
	private ArrayList<String> dictionaryList;
	int dictionarySize;
	
	/**
	 * Adds a word to the dictionary
	 * 
	 * @param word the word to be added
	 */
	public void addWord(String word){
		//TODO
		//Before adding a word --> check to see if this word already exists
		//if it doesnt exist --> add the String word to the dictionary list AND increment the size of the dictionary
		//if it does exist --> do NOT add to the dictionary list and do NOT increment the size of the dictionary
		//idea: return the list at the end of the method to see if it works right
		
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
		// Idea: create a variable here that when method is called increments the size
		// of dictionary instead of returning 0
		
		return dictionarySize++;
	}
}
