package ie.gmit.dip;

import java.io.IOException;

import java.util.*;

public class Menu {
	private Scanner scanner;
	private String text = "----------4-Square-Cipher ----------n/Select Options below";
	private FourSquareCipher fq;
	private boolean keepRunnning = true;


	public Menu() {
		scanner = new Scanner(System.in);
	}
	public void start() throws Exception {// Instantiate variables
		scanner = new Scanner(System.in);// take info from the command line
		fq = new FourSquareCipher();// create an instance of FourSquareCipher.

		while (keepRunnning) {
			System.out.println(text);
			showOptions();
			int selection = Integer.parseInt(scanner.next());

			if (selection == 1 ) {
				getplainText();// set two keys.
			}else if (selection == 2 ) {
				getplainTextURL();// set two keys.
			}else if (selection == 3 ) {
				setkey();// set two keys.
			}else if (selection == 4 ) {
				startEncrypt();	// encrypt
			}else if (selection == 5 ) {
				startDecrypt(); //decrypt
			}else if (selection == 6 ) {
				fq.getMatrix(); // print matrix
			}else if (selection == 7 ) {
				keepRunnning = false; // quit
			}else {
				System.out.println("Invalid option");
			}		
		}	
	}	
	private void getplainTextURL() {// call Parser and set URL to variable PlainText in the FourSquareCipher.
		Parser p = new Parser();
		String[] text = p.parse("http://www.gutenberg.org/cache/epub/730/pg730.txt", true);
		fq.setPlaintext(text);
		System.out.println(Arrays.toString(fq.getPlaintext()));

	}
	private void getplainText() {// call Parser and set text to variable PlainText in the FourSquareCipher. 
		Parser p = new Parser();
		String[] text = p.parse("PlainText", false);
		fq.setPlaintext(text);

		System.out.println(Arrays.toString(fq.getPlaintext()));

	}
	private void startEncrypt() throws IOException { // Get plaintext and encrypt it.
		try {
			fq.encrypt(fq.getPlaintext());
			System.out.println(fq.getCipherText());
		} catch (Exception e) {

			System.out.println(" No PlainText Found,  Select option 1 or 2 before option 4. ");
			System.out.println(" ** Please review text to be encrypted ** ");
			e.printStackTrace();
		}

	}

	private void startDecrypt() throws IOException {// get CipherText and decrypt it 
		try {
			fq.decrypt(fq.getCipherText());
			System.out.println(fq.getDecrypText());
		} catch (Exception e) {

			System.out.println("No CipherText Found  Select option 1 or 2, then select option 4, before option 5");
			System.out.println(" ** Please review text to be encrypted ** ");
			e.printStackTrace();
		}
		
	}
	private void showOptions() { // Option displayed on the console (Menu)

		System.out.println("1) GET TEXT PLAINTEXT");
		System.out.println("2) GET URL PLAINTEXT");
		System.out.println("3) GET KEYS");
		System.out.println("4) ENCRYPT");
		System.out.println("5) DECRYPT");
		System.out.println("6) PRINT MATRIX");
		System.out.println("7) QUIT");

	}
	private void setkey() throws Exception { // Call FisherYatesFuffle to generate keys randomly

		FisherYatesSuffle sh1 = new FisherYatesSuffle();
		String[] key1 = sh1.shuffle();


		FisherYatesSuffle sh2 = new FisherYatesSuffle();
		String[] key2 = sh2.shuffle();

		fq.setkey(key1, key2);

		System.out.println();

		fq.setMatrix(fq.getkey1(), fq.getkey2());
	}
}
