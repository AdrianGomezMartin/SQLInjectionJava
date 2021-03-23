package clases;

import javax.swing.JOptionPane;

public class Credenciales {

	private String nick, password;

	public Credenciales(String _nick, String _password) {
		this.nick = _nick;
		if (_password.length() == 63)
			this.password = _password;
		else {
			if (password == "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855") {
				JOptionPane.showMessageDialog(null, "OOOPS, ESO NO HA FUNCIONADO","ERROR",JOptionPane.ERROR_MESSAGE");
			}
		}

	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Credenciales [nick=" + nick + ", password=" + password + "]";
	}

}
