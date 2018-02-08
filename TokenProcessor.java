package search;

import java.util.ArrayList;

/**
 * A class for performing various data normalization techniques
 * on tokens.
 * 
 * @author Megan and Isabelle
 * @version 1/26/18
 *
 */
public class TokenProcessor{

	public boolean lowercaseSet = false; 
	public boolean stemSet = false;
	public boolean foldNumbersSet = false;
	public ArrayList<String> stopList = null; 
	public Porter stemmer = null; //I changed the name to make more sense

	/**
	 * Set whether or not to lowercase the tokens.
	 * 
	 * @param b
	 */

	public void setLowercase(boolean b){   
		lowercaseSet = b;  		

}
	
	/**
	 * Set whether or not to stem the tokens using the Porter stemmer
	 * @param b
	 */
	public void setStem(boolean b){
		//TODO
		stemSet = b;
		if(stemmer == null) {
			stemmer = new Porter();
		}
		
	}

	/**
	 * Set whether to replace numbers with <NUM> when processing
	 * 
	 * @param b
	 */
	public void setFoldNumbers(boolean b){
		foldNumbersSet = b;
	}
	
	/**
	 * Set the list of words to use as a stoplist.  If the setStopList method is
	 * not called, then don't do any stoplisting.
	 * 
	 * @param list The list of stop words
	 */
	public void setStopList(ArrayList<String> list){
		//Maybe use a new Hash Set of type String 
		this.stopList = list;
	}

	/**
	 * Go through the strings in "tokens", apply all normalization techniques
	 * that are enabled and return the new set of tokens.
	 * 
	 * @param tokens
	 * @return The normalized tokens
	 */
	public ArrayList<String> process(ArrayList<String> tokens){
		//TODO	
		ArrayList<String> tokensProcessed = new ArrayList<String>();
		for(String temp: tokens) {//This makes a duplicate of tokens in the form of a string
			if(stopList == null ) {//idk about this statement
				if(lowercaseSet) {
					temp = temp.toLowerCase();
				}
				if(stopList.contains(temp)) {
					temp = temp.replaceAll(temp, "");
				}
				if(stemSet) {
					temp = stemmer.stem(temp);
				}
				
				if(foldNumbersSet && temp.matches("[+-]{0,}\\d+[.,]{0,}\\d+")) {//Ask about how to write the symbols in +-,. 
					temp = "<NUM>";
				}
				

				tokensProcessed.add(temp);
			}
		}
		System.out.println("Tokens Processed: "+tokensProcessed);
		return tokensProcessed;
	}
	
	
	
	
}
