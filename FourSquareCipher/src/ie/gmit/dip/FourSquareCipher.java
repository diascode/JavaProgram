package ie.gmit.dip;

import java.io.IOException;
import java.util.Arrays;

public class FourSquareCipher {
	private String[] k1;
	private String[] k2;
	private static final int MINIMUM_KEY_LENGTH =25;

	private String[] PlainText;
	private String CipherText;
	private String DecrypText;

	private String[][] matrix = {
			{"A", "B", "C", "D", "E", "Z", "G", "P", "T", "F"},
			{"F", "G", "H", "I", "K", "O", "I", "H", "M", "U"},
			{"L", "M", "N", "O", "P", "W", "D", "R", "C", "N"},
			{"Q", "R", "S", "T", "U", "Y", "K", "E", "Q", "A"},
			{"V", "W", "X", "Y", "Z", "X", "V", "S", "B", "L"},
			{"M", "F", "N", "B", "D", "A", "B", "C", "D", "E"},
			{"C", "R", "H", "S", "A", "F", "G", "H", "I", "K"},
			{"X", "Y", "O", "G", "V", "L", "M", "N", "O", "P"},
			{"I", "T", "U", "E", "W", "Q", "R", "S", "T", "U"},
			{"L", "Q", "Z", "K", "P", "V", "W", "X", "Y", "Z"}
	};


	//*****************KEYS***********************//

	public void setkey(String[] key1, String[] key2 ) throws Exception{

		/* make sure key is not null or less than 25 elements*/

		if (key1 == null || key1.length < MINIMUM_KEY_LENGTH) throw new Exception("Invalid Key.");
		this.k1 = key1;
		System.out.println("Key1: "+Arrays.toString(getkey1()));

		if (key2 == null || key2.length < MINIMUM_KEY_LENGTH) throw new Exception("Invalid Key.");
		this.k2 = key2;
		System.out.println("Key2: "+Arrays.toString(getkey2()));

	}
	public String[] getkey1() {
		return k1;
	}
	public String[] getkey2() {
		return k2;
	}

	//******************MATRIX***********************//

	public String[][] getMatrix() {
		OutMatrix mt = new OutMatrix();

		return mt.output(matrix);
	}

	public String[][] setMatrix(String[] k1, String[] k2 ){
		int temp1=0;
		int temp2=0;

		// print values of the Top Right quadrant
		System.out.println("Top Right Quadrant");

		for (int row = 0; row < matrix.length; row++){
			if(row>4)break;
			System.out.print("{");

			for (int col = 5; col < matrix[row].length; col++){
				// get value from k1 and put it in the Matrix 2nd quadrant

				matrix[row][col] = k1[temp1];
				temp1++; 
				System.out.print("'" + matrix[row][col]);
				// System.out.print("'" + matrix[row][col]); 
				if (col < matrix[row].length-1 == true) {
					System.out.print("',");}
			}
			if (row < matrix.length-1){
				System.out.print("},"); 
			}else
			{
				System.out.print("}");  
			}

			System.out.println();
		}
		System.out.println();

		//****

		// print values of the Bottom left quadrant
		System.out.println("Bottom left Quadrant");

		for (int row = 5; row < matrix.length; row++){
			System.out.print("{");
			for (int col = 0; col < matrix[row].length-5; col++){

				matrix[row][col] = k2[temp2];
				temp2++; 
				System.out.print("'" + matrix[row][col]);
				// System.out.print("'" + matrix[row][col]); 
				if (col < matrix[row].length-6 == true) {
					System.out.print("',");}
			}
			if (row < matrix.length-1){
				System.out.print("},"); 
			}else{
				System.out.print("}");  
			}

			System.out.println();
		}

		System.out.println();
		return matrix;
	}
	//****************PLAINTEXT**************//

	public String[] getPlaintext() {
		return PlainText;
	}


	public void setPlaintext(String[] text) {
		PlainText = text;
		//System.out.println(Arrays.toString(PlainText));
	} 

	//******************* ENCRYPT ***************************//

	public void encrypt(String[] plain) throws IOException {
		String [] plainText = plain;
		StringBuilder sb = new StringBuilder();


		for(int i=0; i< plainText.length; i++) {
			String plainchar = plainText[i];
			if(plainchar.length()%2 ==1) {
				plainchar = plainchar+'X';
			}
			int tempRow1=0;
			int tempCol1=0;

			int tempRow2=0;
			int tempCol2=0;

			int index = 0;	

			//*************** Find Row and Col in the first and fourth quadrant **********************

			for (int row = 0; row < matrix.length-5; row++){
				for (int col = 0; col < matrix[row].length-5; col++){
					//System.out.println(matrix[row][col]);

					if(matrix[row][col].charAt(index) == plainchar.charAt(0)) {
						//System.out.println(row+" and "+col);
						tempRow1 = row;	
						tempCol1 = col;	

					}    		            
				}
			}   


			for (int row = 5; row < matrix.length; row++){
				for (int col = 5; col < matrix[row].length; col++){
					//System.out.println(matrix[row][col]);

					if(matrix[row][col].charAt(index) == plainchar.charAt(1)) {
						//System.out.println(row+" and "+col);
						tempRow2 = row;	
						tempCol2 = col;     	

					}		            	
				}
			}   

			// ************* Get encrypted text and save cipherText****************

			for (int row = 0; row < matrix.length-5; row++){
				for (int col = 5; col < matrix[row].length; col++){
					//System.out.println(matrix[row][col]);

					if(row == tempRow1 && col == tempCol2 ) {
						sb.append(matrix[row][col]);

					}
				}
			}

			for (int row = 5; row < matrix.length; row++){
				for (int col = 0; col < matrix[row].length; col++){
					//System.out.println(matrix[row][col]);

					if(row == tempRow2 && col == tempCol1 ) {
						sb.append(matrix[row][col]);

					}	            		            	
				}					
			}
		}
		GetOutstream f = new GetOutstream();
		f.outStreamTextCypherText(sb.toString());
		setCipherText(sb.toString());

	}

	public String getCipherText() {
		return CipherText;

	}

	public void setCipherText(String EncrypText) {
		CipherText = EncrypText;

	}

	//**********************DECRYT***********************//

	public void decrypt(String cipherText) throws IOException{
		String[] encryptedText = cipherText.toUpperCase().split("(?<=\\G.{2})");
		StringBuilder sb = new StringBuilder();


		for(int i=0; i< encryptedText.length; i++) {
			String plainchar = encryptedText[i];
			if(plainchar.length()%2 ==1) {
				plainchar = plainchar+'X';
			}
			int tempRow1=0;
			int tempCol1=0;

			int tempRow2=0;
			int tempCol2=0;

			int index = 0;	

			for (int row = 0; row < matrix.length-5; row++){
				for (int col = 5; col < matrix[row].length; col++){


					if(matrix[row][col].charAt(index) == plainchar.charAt(0)) {
						//System.out.println(row+" and "+col);
						tempRow1 = row;	
						tempCol1 = col;	

					}    		            
				}
			}   


			for (int row = 5; row < matrix.length; row++){
				for (int col = 0; col < matrix[row].length-5; col++){
					//System.out.println(matrix[row][col]);

					if(matrix[row][col].charAt(index) == plainchar.charAt(1)) {
						//System.out.println(row+" and "+col);
						tempRow2 = row;	
						tempCol2 = col;     	

					}		            	
				}
			}   

			for (int row = 0; row < matrix.length-5; row++){
				for (int col = 0; col < matrix[row].length-5; col++){
					//System.out.println(matrix[row][col]);

					if(row == tempRow1 && col == tempCol2 ) {
						sb.append(matrix[row][col]);
					}
				}
			}

			for (int row = 5; row < matrix.length; row++){
				for (int col = 0; col < matrix[row].length; col++){
					//System.out.println(matrix[row][col]);

					if(row == tempRow2 && col == tempCol1 && matrix[row][col].charAt(0)!='X' ) {
						sb.append(matrix[row][col]);
					}
				}
			}				 			
		}
		GetOutstream f = new GetOutstream();
		f.outStreamTextDecryptedText(sb.toString());
		setDecrypText(sb.toString());	
	}

	public String getDecrypText() {
		return DecrypText;
	}
	public void setDecrypText(String decrypText) {
		DecrypText = decrypText;
	}

}

