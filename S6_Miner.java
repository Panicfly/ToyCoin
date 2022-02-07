import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;


/**
 * @author Konstantin Schmitt, 3598504
 * All in one pot.
 */
public class S6_Miner extends ToyCoinProgram {
	//Instanzvariablen
	private final String ZEROS = "0000";
	private int blockNr = 0;
	private String transactions;
	//Timestamp
	String time = "" + 1620663187618L;

	public void init() {
		setup();
		
		/*
		readTransactionsFromFile("block" + blockNr +".txt");
		transactions = readTransactionsFromFile("raw_transactions_for_block_1.txt");
		byte[] hash = hashCode(transactions);
		String hashTransactions = hashToHex(hash);
		calculateHashOfBlockFromFile(blockNr);
		String hashPreviousBlock = "00000000000000000000000000000000";
		if (blockNr > 0) {
		    hashPreviousBlock = calculateHashOfBlockFromFile(blockNr - 1);
		}
		int nonce = nonce();*/
		writeBlockToFile();
	}
	
	private void setup() {
		setSize(400, 400);
		setFont("Courier-bold-18");
		mining();
	}
	
	private void mining() {
		hashAllTrans();
		//Timestamp
		hashOfPreviousBlock();
		int nonce1 = nonce();
	}
	
	
	//Hash over all Transactions
		private String hashAllTrans() {
			String transactions = readTransactionsFromFile("raw_transactions_for_block_" + blockNr + "txt");
			byte[] hash = hashCode(transactions);
			String hashTransactions = hashToHex(hash);
			return hashTransactions;
		}
		
		private String readTransactionsFromFile(String s) {
			//int nummer = 0;
			try {
				String line;
				BufferedReader br = new BufferedReader(new FileReader("block" + blockNr +".txt"));
				
				calculateHashOfBlockFromFile(blockNr);
				
				while ((line = br.readLine()) != null) {
					if(!line.isEmpty()) {
						
					}
				}
				
			    br.close();
			    return line;
			} catch (Exception e) {
				e.printStackTrace();
				return "ne";
			}	
		}
	
	private String calculateHashOfBlockFromFile(int nummer) {
		BufferedReader br = new BufferedReader(new FileReader("block" + nummer + ".txt"));
		// =================
		br.readLine();
		// Block1
		String name = br.readLine();
		// -----------------
		br.readLine();
		// 00006CE704E258CC4D70FE89F3DD7F7F
		String hashPreviousBlock = br.readLine();
		// 1620663187618
		String time = br.readLine();
		// 116785
		nonce = br.readLine();
		// 98C494C736603753AD983E9D2199BF8F
		String hashTransactions = br.readLine();
		br.close();

	}
	
	//getDataToHash
	private String getDataToHash(String hashPreviousBlock, String time,
	        String hashTransactions) {
	    return hashPreviousBlock + time + hashTransactions;
	}
	
	//All Together
	private void writeBlockToFile() {
		FileWriter fw = new FileWriter("block" + blockNr + ".txt");
		fw.write("=================" + "\n");
		fw.write("Block" + blockNr + "\n");
		fw.write("-----------------" + "\n");
		fw.write(hashPreviousBlock + "\n");
		fw.write(time + "\n");
		fw.write(nonce + "\n");
		fw.write(hashTransactions + "\n");
		fw.write("-----------------" + "\n");
		fw.write(transactions);
		fw.close();
	}
	
	private int nonce() {
		String msg = readLine("Enter text to hash: ");
	    String zeros = readLine("How many zeros (e.g. \"00\"): ");
		  int nonce = 0;
		    while (true) {
		        byte[] hash = hashCode(msg + nonce);
		        String hex = hashToHex(hash);
		        if (hex.startsWith(zeros)) {
		            println("MD5 hash is: " + hex);
		            break;
		        }
		        nonce++;
		    }
		    return nonce;
	}
	
	private byte[] hashCode(String msg) {
        byte[] hashCode = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            hashCode = md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashCode;
    }
	
	protected String hashToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte aByte : bytes) {
			result.append(String.format("%02X", aByte));
		}
		return result.toString();
	}
	
	//Previous Hash
	private void hashOfPreviousBlock() {
		String hashPreviousBlock = "00000000000000000000000000000000";
		if (blockNr > 0) {
		    hashPreviousBlock = calculateHashOfBlockFromFile(blockNr - 1);
		}

	}
 }
