
import java.io.*;

import java_cup.runtime.Symbol;

public class AP2 {


	public static void main(String[] args) {

		System.out.println("test");
		String inFile = "MS3/Go1.in";

		if (args.length > 1) {
			inFile = args[0];
		}

		try {
			FileInputStream fis = new FileInputStream(inFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			BufferedWriter writer = new BufferedWriter(new FileWriter("Sample.out"));

			parser parser = new parser(new Lexer(dis));
			Symbol res = parser.parse();

			String value = res.value.toString();
			writer.write(value);

			System.out.println(value);
			

			fis.close();
			bis.close();
			dis.close();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
