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
 * @version 2/7/18
 * 
 */
public class TDTReader implements DocumentReader {
	private Tokenizer tokenizer;
	private TokenProcessor tokenProcessor = null;
	private int nextDocID = 0;
	private String nextDocText;

	private String sampleFile;

	private ArrayList<Document> docArrayList; // An Array List for documents
	// Should we make an array list or a Linked List to save the documents in the
	// corpus file??? TBD
	BufferedReader txtFile;
	String line;
	private String file;
	ArrayList<Document> documentArray = new ArrayList<Document>();

	// ***-----NOTES OR CONCERNS--***//
	// for the nextDocID should we start that at -1 so it numbers the docs starting
	// at 0
	// The txtFile will get the nextDocText to be read through the Buffered reader
	// I moved the Buffered reader to the read method

	// -------------------------------//

	/**
	 * Prepares documentFile for reading and gets text of first document
	 * 
	 * @param documentFile
	 *            - The text file containing the TDT data with documents delimited
	 *            by <DOC> ... </DOC>
	 * @throws IOException
	 */
	public TDTReader(String documentFile) throws IOException {
		// save docs in an Array List
		// docArrayList = new ArrayList<Document>();

		// tokenizer = new SimpleTokenizer();
		tokenizer = new ImprovedTokenizer();
		this.file = documentFile;
		read();
		next();
	}

	public void read() throws IOException {
		try {

			txtFile = new BufferedReader(new FileReader(file));
			line = txtFile.readLine();

			// this while loop takes the pointer to the first line of article
			while (!(txtFile.readLine()).equals("<DOC>")) {
			}

			if (line.equals("<DOC>")) {
				sampleFile += "\n" + line;
			}

			// pointer still at first line of article
			// while (line != null && !(line = txtFile.readLine()).equals("</DOC>")) {
			while (line != null && !line.equals("</DOC>")) {

				line = txtFile.readLine();
				// takes that line and adds it to sampleFile
				sampleFile += "\n" + line;
				// sampleFile+= line + "\n";
				// System.out.println(sampleFile);

			}
			// System.out.println(sampleFile);
			Document doc = new Document(nextDocID, tokenizer.tokenize(sampleFile));
			nextDocID++;
			sampleFile = " ";
			documentArray.add(doc);

			while (hasNext()) {
				Document nextDoc = next();
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
		this.tokenizer = tokenizer;
	}

	/**
	 * set the token processor for this reader
	 * 
	 * @param tokenProcessor
	 */
	public void setTokenProcessor(TokenProcessor tokenProcessor) {
		// TODO
		this.tokenProcessor = tokenProcessor;
	}

	/**
	 * Are there more documents to be processed?
	 */
	public boolean hasNext() {
		// TODO
		return nextDocText != null;
	}

	/**
	 * Process current document text, tokenize it and create a Document object, get
	 * next document to process, return Document.
	 */
	public Document next() {
		ArrayList<String> arrayList = new ArrayList<String>();
		// TODO
		if (!hasNext()) {
			System.out.println("There are no more documents!");
			return null;
		}
		try {
			while ((txtFile.readLine()).equals("<DOC>") || !(line = txtFile.readLine()).equals("</DOC>")) {
				sampleFile += "\n" + line;
				System.out.println(sampleFile);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = new Document(nextDocID++, tokenizer.tokenize(sampleFile));
		sampleFile = " ";
		return doc;
	}

	public void remove() {
		// method is optional, so don't worry about it
	}
}