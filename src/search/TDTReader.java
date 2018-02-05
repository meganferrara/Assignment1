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
	private Tokenizer tokenizer;
	private TokenProcessor tokenProcessor = null;
	private int nextDocID = -1;
	private String nextDocText; 
	@SuppressWarnings("unused")
	private String sampleFile;
	@SuppressWarnings("unused")
	private ArrayList<Document> docArrayList; // An Array List for documents
	// Should we make an array list or a Linked List to save the documents in the
	// corpus file??? TBD  
	BufferedReader txtFile;  
	String line; 
	String file;
	ArrayList<Document> documentArray = new ArrayList<Document>();

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
	 * @throws IOException 
	 */
	public TDTReader(String documentFile) throws IOException {
		//save docs in an Array List
		docArrayList = new ArrayList<Document>(); 
		
		tokenizer = new SimpleTokenizer(); 
		this.file = documentFile; // idk how this will get the sample.txt file given to us  
		read();
		next();
	}

	// I am not sure if we need this helper method but if so make a method that
	// reads through the physical documents and use the variable nextDocText
	public void read() throws IOException {	 
		try {
			txtFile = new BufferedReader(new FileReader (file));
			//if the line isn't the beginning of the article, keep reading
			//if the line isn't the end of the article
			while ((txtFile.readLine()).equals("<DOC>") || !(txtFile.readLine()).equals("</DOC>")) {   
				//if (!(line = txtFile.readLine()).equals("</DOC>")) {
					//then read each line and add it to sampleFile
					line = txtFile.readLine();
					sampleFile+= "\n" + line; 
					System.out.println(sampleFile); 
				//}   	
			}
			 
			nextDocID++;
			Document doc = new Document (nextDocID, tokenizer.tokenize(sampleFile)); 
			documentArray.add(doc);  
			
			while (hasNext()) {
				Document nextDoc = next();
				//int docID = nextDoc.getDocID();
				documentArray.add(nextDoc);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
	 * Process current document text, tokenize it and create a Document object, get next document
	 * to process, return Document.
	 */
	public Document next() {  
		ArrayList<String> arrayList = new ArrayList<String>(); 
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
		
		try {
			while ((txtFile.readLine()).equals("<DOC>") || !(line = txtFile.readLine()).equals("</DOC>")) {   
				//if (!(line = txtFile.readLine()).equals("</DOC>")) {
					//then read each line and add it to sampleFile
					sampleFile+= "\n" + line; 
					//System.out.println(sampleFile); 
				//}   	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				//arrayList.add(sampleFile);
				
//				if (tokenProcessor != null) {
//					tokens = tokenProcessor.process(tokens);
//				}  

		Document doc = new Document(nextDocID++, tokenizer.tokenize(sampleFile)); 
		return doc;  
	}

	public void remove() {
		// method is optional, so don't worry about it
	}
}
