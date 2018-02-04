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
	private BufferedReader txtFile;
	private String sampleFile;
	private ArrayList<Document> docArrayList; 
	
	//***-----NOTES OR CONCERNS--***//
	//for the nextDocID should we start that at -1 so it numbers the docs starting at 0
	//The txtFile will get the nextDocText to be read through the Buffered reader
	//I moved the Buffered reader to the read method
	
	//-------------------------------//
	
	

	/**
	 * Prepares documentFile for reading and gets text of first document
	 * 
	 * @param documentFile
	 *            - The text file containing the TDT data with documents delimited
	 *            by <DOC> ... </DOC>
	 */
	public TDTReader(String documentFile) {
		// TODO
		docArrayList = new ArrayList<Document>();

		this.sampleFile = documentFile;
		read();

	}

	/**
	 * Read gets the sampleFile from the constructor and passes that into the readNextDoc 
	 * class then saves the document to an ID
	 */
	public void read() {
		//I had to put the try catch statement in because Eclipse was yelling at me 
		try {
			txtFile = new BufferedReader(new FileReader(sampleFile));
			nextDocText = readNextDoc();
			nextDocID++;
			//We need this here too because once we enter the while loop we
			//will be creating new documents by calling the next() method
			//but this will generate our first document that we save
			Document doc = new Document(nextDocID, tokenizer.tokenize(nextDocText)); 
			
			
			while (hasNext()) {
				Document nextDoc = next();
				int docID = nextDoc.getDocID();
				docArrayList.add(nextDoc);
				nextDocText = readNextDoc();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String readNextDoc() {
		// read through each line of the txt file
		try {
			while (txtFile.readLine().equals("<TEXT>")) {
				// //then keep adding the lines to the ArrayList
				// docArrayList.add();
				String line = txtFile.readLine();
				nextDocText += line;

				if (txtFile.readLine().equals("</TEXT>")) {
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
	 * Process current document text, tokenize it and create a Document object, get
	 * next document to process, return Document.
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