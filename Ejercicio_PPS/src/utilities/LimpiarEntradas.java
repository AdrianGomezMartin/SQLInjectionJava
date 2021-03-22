package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LimpiarEntradas {

	public static boolean esValido(String cadena) {
		Pattern p = Pattern.compile("^[a-zA-Z'.\\s]{1,40}$");
		Matcher matcher = p.matcher(cadena);
		boolean cadenaValida = matcher.matches();
		return cadena.length() > 0 && cadenaValida;
	}
}