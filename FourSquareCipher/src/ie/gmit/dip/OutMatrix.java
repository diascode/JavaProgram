package ie.gmit.dip;

public class OutMatrix {

	public String[][] output(String[][] matrix){ // Print matrix
		for (int row = 0; row < matrix.length; row++){
			System.out.print("{");
			for (int col = 0; col < matrix[row].length; col++){
				System.out.print("'" + matrix[row][col]); 
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
		return matrix;
	}  
}
