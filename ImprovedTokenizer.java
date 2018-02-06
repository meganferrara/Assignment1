package search;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * An improved tokenizer class that uses the following tokenization rules: -
 * tokens are delimited by whitespace - Single quotes at the beginning and end
 * of words should be separate tokens - Numbers should stay together. A number
 * can start with a `+' or a `-', can have any number of digits, commas and
 * periods interspersed, but must end in a digit (note this is a more general
 * definition that accepts things like ``192.168.1" and other things like
 * ``-129.,24.34"). - An abbreviation is any single letter followed by a period
 * repeated 2 or more times. In regex terms, ``(\w\.){2,}". For example,
 * ``I.B.M.", ``S.A.T." and ``p.m." are all valid abbreviations, while ``Mr." or
 * ``I.B" are not. All abbreviations should have the periods removed, i.e.
 * ``I.B.M" becomes ``IBM". - Finally, ``. , ? : ; " ` ( ) % $" should all be
 * treated as separate tokens, regardless of where they appear (as long as they
 * don't conflict with the above rules). So ``$10,000" becomes two tokens ``$"
 * and ``10,000" and ``I wondered,is this a test?" becomes 8 tokens, with the
 * ``," and ``?" as separate tokens.
 * 
 * @author Megan and Isabelle
 * @version 1/30/18
 *
 */
public class ImprovedTokenizer implements Tokenizer {
	// Given text, tokenize the text into individual tokens
	// and return an ArrayList with those tokens
	
	ArrayList<String> aList;
	/**
	 * Given text, tokenize the text into individual tokens and 
	 * return an ArrayList with those tokens
	 */
	public ArrayList<String> tokenize(String text){	
		//TODO
		aList = new ArrayList<String>();
		//1: separate words based on white space (look at simpleTokenizer)
		text = text.replaceAll("\\s+", " ");
		
		if(text.startsWith(" ")) {
			//put something here that will take care of the whitespace before word
			text = text.substring(1, text.length());
		}
		if(text.endsWith(" ")) {
			//put something here that will take care of the whitespace after word
			text = text.substring(0, text.length()-1);
		}
		//You will probably need something that will save this word after going through if statements
		//splits the tokens above from whitespace around it
		String[] tempTokens = text.split(" "); 
		System.out.println("# of Tokens split on whitespace:"+tempTokens.length);
		
		
		
		//2: Check for single quotes at the beginning and end of words and separate from tokens
		//You will probably pass the tempTokens through two checkers to check for at the beginning and at the end
		//ArrayList<String> singleQuotes = new ArrayList<String>;
		
		//Temp: tempTokens --> this is basically duplicating the tempTokens string to look through
		for(String temp: tempTokens) {
			while(temp.startsWith("'")) {
				temp.split("'");
				System.out.println("# of Starts with SingleQuote Tokens:"+tempTokens.length);
				//singleQuotes.add("'");
				//text = text.substring(1);
			}
			while(temp.endsWith("'")) {
				temp.split("'");
				//System.out.println("Ends with SingleQuote Tokens:"+tempTokens.length);
				//text = text.substring(0, text.length()-1);
			}
				
		}
		

		//3: Numbers stay together. Can start with "+" or "-". Can have any number of digits, commas and periods. 
		//Must end in a digit   
		String regex = [\d+-.]{2}; 
		if (text.matches(regex) {
			
		}
		
		//4:Check for a single letter followed by a period, if the period is followed by another single letter and period 
		//then this will be counted as an abbreviation and should be checked until there is no more single letters and periods following
		//Once the end of the abbreviation is found you will go through and remove all the periods
		//if not an abbreviation separate these into individual tokens.
		
		//5: These characters  ``. , ? : ; " ` ( ) % $"  should be treated as separate tokens 
		
		return null;
	}

	/**
	 * This is just here to help you test some examples. You may remove it if you
	 * want, but I encourage you to write similar tests.
	 */
	public void test() {
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
	 * Test helper. Tokenizes the test string and compares the result to the answer.
	 * Outputs pass or fail.
	 * 
	 * @param test
	 *            test string
	 * @param answer
	 *            the correct tokenization of test
	 */
	private void runTest(String test, ArrayList<String> answer) {
		// tokenize the test string
		ArrayList<String> result = tokenize(test);

		if (!checkResult(result, answer)) {
			System.out.println("Failed: " + test);

			for (String s : result) {
				System.out.print(s + " ");
				System.out.println();
			}
		} else {
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
	private boolean checkResult(ArrayList<String> result, ArrayList<String> answer) {
		if (result.size() != answer.size()) {
			return false;
		} else {
			for (int i = 0; i < result.size(); i++) {
				if (!result.get(i).equals(answer.get(i))) {
					return false;
				}
			}

			return true;
		}
	}
}