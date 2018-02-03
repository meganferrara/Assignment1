package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private String nextDocText; 
	@SuppressWarnings("unused")
	private String sampleFile;
	@SuppressWarnings("unused")
	private ArrayList<Document> docArrayList; // An Array List for documents
	// Should we make an array list or a Linked List to save the documents in the
	// corpus file??? TBD  
	BufferedReader txtFile;  
	String line;

	/**
	 * Prepares documentFile for reading and gets text of first document
	 * 
	 * @param documentFile
	 *            - The text file containing the TDT data with documents delimited
	 *            by <DOC> ... </DOC>
	 * @throws IOException 
	 */
	public TDTReader(String documentFile) throws IOException {
		// TODO
		docArrayList = new ArrayList<Document>(); // save docs in an Array List 
		
		//I think when TDTReader is called, the document we're putting in parameters needs to be converted
		//to String
		//this.sampleFile = documentFile; // idk how this will get the sample.txt file given to us  
		try {
			txtFile = new BufferedReader(new FileReader (documentFile)); 
			while (!(txtFile.readLine()).equals("<TEXT>")) {  
			} 
			while (!(line = txtFile.readLine()).equals("</TEXT>")) {  
				sampleFile+= "\n" + line; 
			}
			System.out.println(sampleFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if (sampleFile != null) {
			return true;
		}
		return false;
	}

	/**
	 * Process current document text, tokenize it and create a Document object, get next document
	 * to process, return Document.
	 */
	public Document next() {
		// TODO
		// You will want an Array List of Type String for your tokens of your doc text
		// Then you will have this array list of tokens processed in the TokenProcessor
		// Class
		// You will then return a doc that calls the next document ID and sets the
		// tokens
		if (sampleFile.equals(null)) {
			System.out.println("There are no more documents!");
		} 
		ArrayList<String> arrayList = tokenizer.tokenize(sampleFile);
//		if (tokenProcessor != null) {
//			tokens = tokenProcessor.process(tokens);
//		}  
		Document doc = new Document(nextDocID, arrayList); 
		sampleFile = " ";
		try {
			while (!(line = txtFile.readLine()).equals("</TEXT>")) {  
				sampleFile+= "\n" + line; 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;  
	}

	public void remove() {
		// method is optional, so don't worry about it
	}
}
