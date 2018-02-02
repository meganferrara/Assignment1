package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * A document reader for the TDT corpus. Opens the document file, creates one
 * Document at a time.
 * 
 * @author Megan and Isabelle
 * @version 1/30/18
 * 
 */
public class TDTReader implements DocumentReader {
	private Tokenizer tokenizer = null;
	private TokenProcessor tokenProcessor = null;
	private int nextDocID = 0;
	private String nextDocText; //think this should be a Document type
	@SuppressWarnings("unused")
	private String sampleFile;
	@SuppressWarnings("unused")
	private ArrayList<Document> docArrayList; // An Array List for documents
	// Should we make an array list or a Linked List to save the documents in the
	// corpus file??? TBD 
	
	//creating a Document object 
	

	/**
	 * Prepares documentFile for reading and gets text of first document
	 * 
	 * @param documentFile
	 *            - The text file containing the TDT data with documents delimited
	 *            by <DOC> ... </DOC>
	 */
	public TDTReader(String documentFile) {
		// TODO
		docArrayList = new ArrayList<Document>(); // save docs in an Array List 
		//I think when TDTReader is called, the document we're putting in parameters needs to be converted
		//to String
		this.sampleFile = documentFile; // idk how this will get the sample.txt file given to us  
		
		//i'm not too sure how to get the txt file
		File egFile = new File ("C:\Users\itmh\assignment1\Assignment1\sample.txt");
		BufferedReader txtFile = new BufferedReader(new FileReader(egFile)); 
		//read through each line of the txt file
		while (sampleFile = txtFile.readLine() != null) {  
			//if the text isn't the end
			if (!sampleFile.equals("</DOC>")) { 
				//then keep adding the lines to the ArrayList
				docArrayList.add((sampleFile); 
				System.out.print(docArrayList);
				
			}
			//once it reaches the end, then create a Document object
			Document doc = new Document (0, docArrayList);
		}
	}

	// I am not sure if we need this helper method but if so make a method that
	// reads through the physical documents and use the variable nextDocText
	public void read() {

	}

	/**
	 * Set the tokenizer for this reader
	 * 
	 * @param tokenizer
	 */
	public void setTokenizer(Tokenizer tokenizer) {
		// TODO
		// This sets the tokenizer variable initialized at the top to the parameter
		// being passed in
		this.tokenizer = tokenizer;
	}

	/**
	 * set the token processor for this reader
	 * 
	 * @param tokenProcessor
	 */
	public void setTokenProcessor(TokenProcessor tokenProcessor) {
		// TODO
		// This sets the tokenProcessor variable initialized at the top to the parameter
		// being passed in
		this.tokenProcessor = tokenProcessor;
	}

	/**
	 * Are there more documents to be processed?
	 */
	public boolean hasNext() {
		// TODO
		// This will return true if there is more text to be read after current document
		return nextDocText != null;
	}

	/**
	 * Process current document text and create a Document object, get next document
	 * to process, return Document.
	 */
	public Document next() {
		// TODO
		// You will want an Array List of Type String for your tokens of your doc text
		// Then you will have this array list of tokens processed in the TokenProcessor
		// Class
		// You will then return a doc that calls the next document ID and sets the
		// tokens
		if (!hasNext()) {
			System.out.println("There are no more documents!");
			return null;
		}
		ArrayList<String> tokens = tokenizer.tokenize(nextDocText);
		if (tokenProcessor != null) {
			tokens = tokenProcessor.process(tokens);
		}
		Document doc = new Document(nextDocID, tokens);
		return doc;
	}

	public void remove() {
		// method is optional, so don't worry about it
	}
}
