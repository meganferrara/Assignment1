package search;

import java.io.IOException;

/**
 *  This class does the following: Create a new SimpleTokenizer object. Create a new TDTReader object 
 *  to read the data from the ap data file. Set the SimpleTokenizer as the tokenizer for the reader. 
 *  Create a new Dictionary object. Iterate through all of the documents in the file using the TDTReader 
 *  and add all of words to the dictionary. Output the number of words in the dictionary. 
 *  Call this function from a main class. 
 *  
 * @author Megan and Isabelle 
 * @version 1/31/18
 *
 */

public class Experimenter {
	
	public static void main (String [] args) throws IOException {
		TDTReader readFile = new TDTReader ("ap89.txt"); 
		
		SimpleTokenizer sToken = new SimpleTokenizer();  
		readFile.setTokenizer(sToken);
	} 
	
	
}
