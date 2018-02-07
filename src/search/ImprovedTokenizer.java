package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

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

	ArrayList<String> tokenList; 
	/**
	 * Given text, tokenize the text into individual tokens and 
	 * return an ArrayList with those tokens
	 */
	public ArrayList<String> tokenize(String text){	
		//TODO
		//1: separate words based on white space (look at simpleTokenizer) and put into ArrayList 
		//this ArrayList contains all the words
		tokenList = new ArrayList<String>(Arrays.asList(text.split("\\s+")));
		
		//splitting text based on whitespace and punctuation, and putting them in an array
//		aList = text.split("\p{Punct}"); 
		
		//not too sure if we need this for loop because the text won't have whitespace at the front or back
		for (int i=0; i<tokenList.size(); i++) {
			if(tokenList.get(i).startsWith(" ")) {
				text = text.substring(1, text.length());
			}
			if(tokenList.get(i).endsWith(" ")) {
				text = text.substring(0, text.length()-1);
			}
		}
		
//		String[] tempTokens = text.split(" "); 
		
		//2: Check for single quotes at the beginning and end of words and separate from tokens
		//You will probably pass the tempTokens through two checkers to check for at the beginning and at the end
		ArrayList<String> firstPass = new ArrayList<String>();
		
		//Temp: tempTokens --> this is basically duplicating the tempTokens string to look through
		for(int i=0; i<tokenList.size(); i++) {
			String temp = tokenList.get(i);
			while(temp.startsWith("'")) {
				firstPass.add("'");
				temp = temp.substring(1, temp.length());
				//System.out.print(temp);
			}
			
			int endingSingleQuotes = 0;
			
			while(temp.endsWith("'")) {
				endingSingleQuotes++;
				temp = temp.substring(0, temp.length()-1);
				//System.out.println("words that end with ': " + temp);
			}
			
			//firstPass.add(temp);
			//what is this for loop for?
//			for(int i = 0; i < endingSingleQuotes; i++) {
//				firstPass.add("'");				
//			}	
		}
		
		//System.out.println("First Pass: "+ firstPass);
	
		//3: Numbers stay together. Can start with "+" or "-". Can have any number of digits, commas and periods. 
		//Must end in a digit 
		for (int i=0; i<tokenList.size(); i++) { 
			String temp = tokenList.get(i);
			if (Pattern.matches("[$(']{0,}[+-]{0,}\\d+[,.]+\\d+[.,?!::'%)]", tokenList.get(i))) {
				temp.replaceAll("[,.!?();:']", ""); 
				//System.out.print(temp);
			}
		}
		 
		//4:Check for a single letter followed by a period, if the period is followed by another single letter and period 
		//then this will be counted as an abbreviation and should be checked until there is no more single letters and periods following
		//Once the end of the abbreviation is found you will go through and remove all the periods
		//if not an abbreviation separate these into individual tokens.
		
		ArrayList<String> secondPass = new ArrayList<String>();
		//I.B.M.   <-- for reference when looking at the for loop and thinking in terms of index
		for(int i = 0; i < firstPass.size(); i++) {
			int k = i; //This will be to check the next position in the token
			while(k+1 < firstPass.size() && firstPass.get(k).length() == 1 && firstPass.get(k+1).equals(".")) {
				k+= 2;
			}
			if(k - i > 2) {
				//making an empty string that we will add our abbreviation to
				String abbrevTokens = "";  
				for(int j = i; j < k; j+=2) {
					abbrevTokens += firstPass.get(j);
				}
				
				secondPass.add(abbrevTokens);
				
				
				i = k-1;
			}else {
				secondPass.add(firstPass.get(i));
			}
		}
		
		
		//5: These characters  ``. , ? : ; " ` ( ) % $"  should be treated as separate tokens 
		//NEED TO THINK ABOUT THIS MORE because punctuation may not be separated from words
		String [] punct = {".", ",", "?", ":", ";", "'", "(", ")", "%", "$", "!"};  
		for (int i=0; i<punct.length; i++) { 
			for (int j=0; j<tokenList.size(); j++) {
				String temp = tokenList.get(j);
				if (temp.equals(punct[i])) { 
					temp.replaceAll("[,.!$%?();:']", " " + punct[i]+ " ");
					System.out.println(temp);
				}
			}
		} 
		return tokenList;
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