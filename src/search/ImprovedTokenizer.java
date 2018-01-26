package search;

import java.util.ArrayList;

/**
 * An improved tokenizer class that uses the following tokenization rules:
 * - tokens are delimited by whitespace
 * - Single quotes at the beginning and end of words should be separate tokens
 * - Numbers should stay together.  A number can start with a `+' or a `-', 
 *   can have any number of digits, commas and periods interspersed, but must 
 *   end in a digit (note this is a more general definition that accepts things like ``192.168.1" 
 *   and other things like ``-129.,24.34").
 * - An abbreviation is any single letter followed by a period repeated 2 or more times.  
 *   In regex terms, ``(\w\.){2,}".  For example, ``I.B.M.", ``S.A.T." and ``p.m." are all 
 *   valid abbreviations, while ``Mr." or ``I.B" are not.  All abbreviations should have 
 *   the periods removed, i.e. ``I.B.M" becomes ``IBM".
 * - Finally, ``. , ? : ; " ` ( ) % $" should all be treated as separate tokens, regardless of 
 *   where they appear (as long as they don't conflict with the above rules).  So ``$10,000" 
 *   becomes two tokens ``$" and ``10,000" and ``I wondered,is this a test?" becomes 8 
 *   tokens, with the ``," and ``?" as separate tokens.
 * 
 * @author dkauchak
 *
 */
public class ImprovedTokenizer implements Tokenizer{
	// Given text, tokenize the text into individual tokens
	// and return an ArrayList with those tokens
	/**
	 * Given text, tokenize the text into individual tokens and 
	 * return and ArrayList with those tokens
	 */
	public ArrayList<String> tokenize(String text){	
		//TODO
		return null;
	}
	
	/**
	 * This is just here to help you test some examples.
	 * You may remove it if you want, but I encourage you to write similar tests.
	 */
	public void test(){
		String test = "The N.Y.S.E. is up $10,000 or 1%.";
		ArrayList<String> answer = new ArrayList<String>();
		answer.add("The");
		answer.add("NYSE");
		answer.add("is");
		answer.add("up");
		answer.add("$");
		answer.add("10,000");
		answer.add("or");
		answer.add("1");
		answer.add("%");
		answer.add(".");
		runTest(test, answer);
	
		test = "1,000,000.00";
		answer = new ArrayList<String>();
		answer.add(test);
		runTest(test, answer);
		
		test = "0.1234";
		answer = new ArrayList<String>();
		answer.add(test);
		runTest(test, answer);	
	}
	
	/**
	 * Test helper.  Tokenizes the test string and compares
	 * the result to the answer.  Outputs pass or fail.
	 * 
	 * @param test test string
	 * @param answer the correct tokenization of test
	 */
	private void runTest(String test, ArrayList<String> answer){
		// tokenize the test string
		ArrayList<String> result = tokenize(test);
		
		if( !checkResult(result, answer) ){
			System.out.println("Failed: " + test);
			
			for( String s: result ){
				System.out.print(s + " ");
				System.out.println();
			}
		}else{
			System.out.println("Passed: " + test);
		}
	}
		
	/**
	 * Compares two ArrayLists of strings to see if they're the same.
	 * 
	 * @param result
	 * @param answer
	 * @return true if the ArrayLists are the same, false otherwise
	 */
	private boolean checkResult(ArrayList<String> result, ArrayList<String> answer){
		if( result.size() != answer.size() ){
			return false;
		}else{
			for( int i = 0; i < result.size(); i++ ){
				if( !result.get(i).equals(answer.get(i)) ){
					return false;
				}
			}
			
			return true;
		}
	}
}
