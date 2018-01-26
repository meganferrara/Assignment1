package search;

import java.io.File;

/**
 * A document reader for the TDT corpus.  Opens the document file,
 * creates one Document at a time.
 * @author Megan and Isabelle
 * @version 1/26/18
 * 
 */
public class TDTReader implements DocumentReader{

	/**
	 * Prepares documentFile for reading and gets text of first document
	 * 
	 * @param documentFile - The text file containing the TDT data
	 * with documents delimited by <DOC> ... </DOC>
	 */
	public TDTReader(String documentFile){
		//TODO
		//First get the corpus file (that holds all of the articles that will be split up into individual documents)
		File sampleFile = new File("sample.txt");
	}
	
	/**
	 * Set the tokenizer for this reader
	 * 
	 * @param tokenizer
	 */
	public void setTokenizer(Tokenizer tokenizer) {
		//TODO
	}
	
	/**
	 * set the token processor for this reader
	 * 
	 * @param tokenProcessor
	 */
	public void setTokenProcessor(TokenProcessor tokenProcessor) {
		//TODO
	}

	/**
	 * Are there more documents to be processed?
	 */
	public boolean hasNext() {
		//TODO
		return false;
	}

	/**
	 * Process current document text and create a Document object,
	 * get next document to process, return Document.
	 */
	public Document next(){
		//TODO
		return null;
	}

	public void remove() {
		// method is optional, so don't worry about it
	}
}
