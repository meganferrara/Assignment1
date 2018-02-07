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
<<<<<<< HEAD
=======
	private boolean setLowercase = false; 
	private boolean setStem = false;
	private boolean setFoldNumbers = false;
	private ArrayList<String> stopList = new ArrayList<String>(); 
	
	private Porter porter;
	
	
>>>>>>> b398ec41c0fc5470dfe86a8a1a7de53442595056
	private boolean setLowercase = false; 
	private boolean setStem = false;
	private boolean setFoldNumbers = false;
	private ArrayList<String> stopList = new ArrayList<String>(); 
	
	private Porter porter;
	
	
	
	/**
	 * Set whether or not to lowercase the tokens.
	 * 
	 * @param b
	 */
<<<<<<< HEAD

	
	public void setLowercase(boolean b){   
		setLowercase = b;  		

=======
<<<<<<< HEAD
	public void setLowercase(boolean b){
		//TODO
		setLowercase = b;
=======
	public void setLowercase(boolean b){   
		setLowercase = b;  		
>>>>>>> 3e7356b96486f79bece2ae6ceec95910f399bbd6
>>>>>>> b398ec41c0fc5470dfe86a8a1a7de53442595056
	}
	
	/**
	 * Set whether or not to stem the tokens using the Porter stemmer
	 * @param b
	 */
	public void setStem(boolean b){
<<<<<<< HEAD
		//TODO
		setStem = b;
=======
		setStem = b; 
<<<<<<< HEAD

=======
		
>>>>>>> 3e7356b96486f79bece2ae6ceec95910f399bbd6
>>>>>>> b398ec41c0fc5470dfe86a8a1a7de53442595056
	}

	/**
	 * Set whether to replace numbers with <NUM> when processing
	 * 
	 * @param b
	 */
	public void setFoldNumbers(boolean b){
<<<<<<< HEAD

=======
<<<<<<< HEAD
		//TODO
=======
>>>>>>> 3e7356b96486f79bece2ae6ceec95910f399bbd6
>>>>>>> b398ec41c0fc5470dfe86a8a1a7de53442595056
		setFoldNumbers = b;
	}
	
	/**
	 * Set the list of words to use as a stoplist.  If the setStopList method is
	 * not called, then don't do any stoplisting.
	 * 
	 * @param list The list of stop words
	 */
	public void setStopList(ArrayList<String> list){
		
		//BufferedReader list =  
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
		return null;
	}
}
