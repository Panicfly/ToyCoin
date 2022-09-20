import java.security.MessageDigest;


/**
 * @author Konstantin Schmitt, 3598504
 * Makes complete different HashCodes from an entered String
 */
public class S4_CryptoHash extends ToyCoinProgram {

	public void run() {
		setup();
		int y = 0;
		printHashCode();
	}
	
	private void setup() {
		setSize(500, 400);
		setFont("Courier-bold-18");
	}
	
	/*
	 * Reads entered String and generates a HashCode from it
	 */
	private void printHashCode() {
		while(true) {
			String text = readLine("Enter text to hash: ");
			byte[] range = hashCode(text);
			String hash = hashToHex(range);
			println("MD5 hash is: \n" + hash + "\n");
			}
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
