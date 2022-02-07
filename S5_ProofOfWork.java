import java.security.MessageDigest;


/**
 * @author Konstantin Schmitt, 3598504
 * Proof of Work. A relatively hard mathematical function with an easy proof that u did it right.
 * Enter a String. Enter the wish of entry zeros(difficulty).
 *  It will give out the HashCode with the wished amount of zeros, the nonce and the needed time.
 */
public class S5_ProofOfWork extends ToyCoinProgram {

	public void run() {
		long start = System.currentTimeMillis();
		setup();
		
		bringsProof();
	    long measuredTime = System.currentTimeMillis() - start;
	    println("Time: " + measuredTime + "ms");
	}
	
	private void setup() {
		setSize(550, 400);
		setFont("Courier-bold-18");
	}
	
	/*
	 * Asks the User for a String and the difficulty.The nonce are the tries it needed to find the right HashCode.
	 * The rest is the same as the method s4 just it checks if the HashCode starts with the wished amount of zeros.
	 */
	private void bringsProof() {
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
	    println("Nonce: " + nonce);
	}
	
	//MD5 hash function
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

	//Makes our byte Array to a String
	protected String hashToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte aByte : bytes) {
			result.append(String.format("%02X", aByte));
		}
		return result.toString();
	}
}
