package utilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha256 {
	private static byte[] getSHA(String entrada) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(entrada.getBytes(StandardCharsets.UTF_8));
	}

	private static String toHexString(byte[] hash) {
		BigInteger num = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(num.toString(16));
		while (hexString.length() < 32)
			hexString.insert(0, '0');
		return hexString.toString();
	}

	public static String calcularSHA256(String entrada) {
		String hash = "";
		try {
			hash = toHexString(getSHA(entrada));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

}