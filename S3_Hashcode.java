
/**
 * @author Konstantin Schmitt, 3598504
 * Makes an entered String to a HashCode
 */
public class S3_Hashcode extends ToyCoinProgram {

	public void run() {
		setup();
		
		printHashCode();
	}
	
	private void setup() {
		setSize(400, 400);
		setFont("Courier-bold-18");
	}
	
	/*
	 * Text is entered then made to an int from there into a byte array and
	 * at the end we make it into the HashCode which we store into a String.
	 * U could make this all in one line but then its harder to read.
	 */
	private void printHashCode() {
		while(true) {
			String text = readLine("Enter text to hash: ");
			int hash = hashCode(text);
			byte[] range = intToByteArray(hash);
			String output = hashToHex(range);
			println("Hash Code is: " + output + "\n");
			}
	}
	
	//Our HashFunktion: Loves Strings and makes HashCodes out of them
	public int hashCode(String value) {
        int h = 0;
        for (int i = 0; i < value.length(); i++) {
            h = 31 * h + value.charAt(i);
        }
        return h;
    }

	//Stores bit values of an int into a byte array
	protected byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >> 24), (byte) (value >> 16),
				(byte) (value >> 8), (byte) value };
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
