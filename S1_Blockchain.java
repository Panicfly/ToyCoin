import java.io.BufferedReader;
import java.io.FileReader;

import acm.program.ConsoleProgram;

/**
 * @author Konstantin Schmitt, 3598504
 * Implementation of releasing the blockchain's transactions in the console
 */
public class S1_Blockchain extends ConsoleProgram {
	//Instanzvaraiblen
	private final String FILE = "block_chain.txt";
	
	
	public void init() {
		setup();
		loadBlockchain();
		int x = 0;
	}
	
	private void setup() {
		setSize(400, 400);
		setFont("Arial-bold-18");
	}
	
	private void loadBlockchain() {
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(FILE));
			
			while ((line = br.readLine()) != null) {
				if(!line.isEmpty()) {
					if(line.charAt(0) == 'G') {
						System.out.println(line);
						println(line);
					}
					if(line.charAt(0) == 'T') {
						System.out.println(line);
						println(line);
					}
				}
			}
			
		    br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
} 
