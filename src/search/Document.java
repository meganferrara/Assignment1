package search;

import java.util.ArrayList;

/**
 * A representation of the documents that will be stored in our
 * data collection.
 * 
 * @author dkauchak
 */
public class Document{
	private int docID;
	private ArrayList<String> text;
	
	/**
	 * Create a new document
	 * 
	 * @param docID the unique ID associated with this document
	 * @param text the words in the document
	 */
	public Document(int docID, ArrayList<String> text){
		this.docID = docID;
		this.text = text;
	}

	
	/**
	 * Get the document ID
	 * 
	 * @return the docID
	 */
	public int getDocID() {
		return docID;
	}

	/**
	 * Set the document ID
	 * 
	 * @param docID
	 */
	public void setDocID(int docID) {
		this.docID = docID;
	}

	/**
	 * Get the text (i.e. words) that make up this document.
	 * 
	 * @return
	 */
	public ArrayList<String> getText() {
		return text;
	}
	
	/**
	 * Set the text (i.e. words) that make up this document.
	 * 
	 * @param text
	 */
	public void setText(ArrayList<String> text) {
		this.text = text;
	}
}
