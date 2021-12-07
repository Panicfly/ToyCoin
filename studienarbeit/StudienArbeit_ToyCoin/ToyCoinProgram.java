import acm.program.ConsoleProgram;

/**
 * Your comment goes here...
 */
public abstract class ToyCoinProgram extends ConsoleProgram {

	public ToyCoinProgram() {
		super();
	}

	protected byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >> 24), (byte) (value >> 16),
				(byte) (value >> 8), (byte) value };
	}

	protected String hashToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte aByte : bytes) {
			result.append(String.format("%02X", aByte));
		}
		return result.toString();
	}

}