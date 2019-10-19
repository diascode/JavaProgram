package ie.gmit.dip;

import java.io.*;
import java.net.URL;

public class Parser {

	static String[] charPairs = new String[100];


	public String[] parse(String resource, boolean isURL) {

		File f = new File(resource);
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();


		try {
			if (isURL) {// read from a URL

				URL url = new URL(resource);
				InputStream stream = url.openStream();

				br = new BufferedReader(new InputStreamReader(stream)); 

				String line = null;
				String lineDirty = null;

				while ((lineDirty = br.readLine()) != null) {// read and treat line excluding unwanted characters
					line = lineDirty.trim().replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").replaceAll("[0-9]", "");
					sb.append(line);				
				}
				br.close();
				charPairs = sb.toString().toUpperCase().split("(?<=\\G.{2})"); // transform to bigrams

			} else {// read from text file

				br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

				String line = null;
				String lineDirty = null;

				while ((lineDirty = br.readLine()) != null) { // read and treat line excluding unwanted characters
					line = lineDirty.trim().replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").replaceAll("[0-9]", "");


					sb.append(line);

				}
				charPairs = sb.toString().toUpperCase().split("(?<=\\G.{2})"); // transform to bigrams

				br.close();
			}

		} catch (Exception e) {
			System.out.println("No file or URL found");
			e.printStackTrace();
		}

		return charPairs;
	}

}
