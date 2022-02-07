import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * @author Konstantin Schmitt, 3598504
 * Reads out the transactions out of the blockchain and puts the right amount to the right users.
 * If users dont exists it can add the to the hashmap so they can end up in our blockchain.
 */
public class S2_Wallet extends ConsoleProgram {
	//Instanzvariablen
	Map<String, Integer> userAccounts = new HashMap<String, Integer>();
	//Instanzvariablen
	private final String FILE = "block_chain.txt";
	private String from;
	private String to;
	private int amount;
	
	public void init() {
		setup();
		
		loadBlockchain();
	}

	private void setup() {
		setSize(400, 400);
		setFont("Arial-bold-18");
	}

	//reads out the blockchain and should make the right transactions
	private void loadBlockchain() {
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(FILE));
		
			while ((line = br.readLine()) != null) {
				if(!line.isEmpty()) {
					if(line.charAt(0) == 'G') {
						toki(line);
						converstion();
					}
					
					if(line.charAt(0) == 'T') {
						toki(line);
						converstion();
					}
				}
			}
			printMap();
		
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//A StringTokenizer to read out the 4 elements in one row of the blockchain
	private void toki(String s) {
		 StringTokenizer st = new StringTokenizer( s, "," );
		 String useless = st.nextToken().trim();
		 from = st.nextToken().trim();
		 to = st.nextToken().trim();
		 amount = Integer.parseInt(st.nextToken().trim());
	}
	
	//puts the right amount on the right accounts
	private void converstion() {
		if(userAccounts.keySet().contains( to )) {
			userAccounts.put(to, amount);
		} else {
			userAccounts.put(from, amount);
			int balanceFrom;
			if(userAccounts.keySet().contains( from )) {
				balanceFrom = userAccounts.get( from );
			}
			int balanceTo = 0;
			if(userAccounts.keySet().contains(to)) {
				balanceTo = userAccounts.get(to);
				balanceTo += amount;
			}
			balanceFrom = amount;
			balanceTo += amount;
			userAccounts.put(from, balanceFrom);
			userAccounts.put(to, balanceTo);
		}
	}
	
	//Gives the map out. Which includes users and accounts.
	private void printMap() {
		println("Users: ");
		println(userAccounts.keySet());
		println("Accounts: ");
		println(userAccounts);
	}
	
}
