package ie.gmit.dip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GetOutstream {

	public void outStreamTextDecryptedText(String s) throws IOException {// save file on the decryptText
		File file = new File("DecryptedText.txt");

		OutputStream out = new FileOutputStream(file);

		out.write(s.getBytes());
		out.flush();
		out.close();
	}

	public void outStreamTextCypherText(String s) throws IOException {// save file on the cipherText
		File file = new File("CipherText.txt");

		OutputStream out = new FileOutputStream(file);

		out.write(s.getBytes());
		out.flush();
		out.close();
	}

}
