package ie.gmit.dip;

public class FisherYatesSuffle {
	String[] alph;
	
	public String[] shuffle() {
		String[] alph = {"A", "B", "C", "D", "E",
				  		  "F", "G", "H", "I", "K",
				  		  "L", "M", "N", "O", "P",
				  		  "Q", "R", "S", "T", "U",
				  		  "V", "W", "X", "Y", "Z"
						};
		
		
		for(int i = 0; i<alph.length;i++) {
			int index = (int)(Math.random()*alph.length);
			String temp = alph[i];
			alph[i] = alph[index];
			alph[index]= temp;
		}
		return alph;
	}
	
}
