package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Credenciales;
import dao.AccesoDatos;
import utilities.LimpiarEntradas;
import utilities.sha256;

public class VentanaPrincipal extends Ventana {

	JTextField txtUsuario;
	JPasswordField txtPassword;
	JButton btnLogin, btnAddUser, btnLimpiar, btnSeguro;
	private boolean seguro = false;

	public VentanaPrincipal(String titulo) {

		super(titulo, "GridBagLayout", 0, 0);
	}

	@Override
	public void InicializarComponentes() {

		GridBagConstraints opc = new GridBagConstraints();
		Insets espacios = new Insets(20, 20, 20, 20);

		opc.gridx = 0;
		opc.gridy = 0;
		opc.insets = espacios;
		addComponent(new JLabel("Usuario :  "), opc);

		opc.gridx = 1;
		opc.gridy = 0;
		opc.insets = espacios;
		opc.fill = GridBagConstraints.HORIZONTAL;
		txtUsuario = new JTextField("");
		addComponent(txtUsuario, opc);

		opc.gridx = 0;
		opc.gridy = 1;
		opc.insets = espacios;
		addComponent(new JLabel("Password :  "), opc);

		txtPassword = new JPasswordField();
		opc.gridx = 1;
		opc.gridy = 1;
		opc.insets = espacios;
		opc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(txtPassword, opc);

		btnLogin = new JButton("Login");
		opc.gridx = 0;
		opc.gridy = 2;
		opc.insets = espacios;
		opc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(btnLogin, opc);

		btnAddUser = new JButton("Registrarse");
		opc.gridx = 1;
		opc.gridy = 2;
		opc.insets = espacios;
		opc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(btnAddUser, opc);

		btnLimpiar = new JButton("Limpiar");
		opc.gridx = 2;
		opc.gridy = 2;
		opc.insets = espacios;
		opc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(btnLimpiar, opc);

		btnSeguro = new JButton("INSEGURO");
		btnSeguro.setBackground(Color.RED);
		opc.gridx = 1;
		opc.gridy = 3;
		opc.insets = espacios;
		opc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(btnSeguro, opc);
	}

	@Override
	public void ActivarListeners() {
		AccesoDatos ac = new AccesoDatos();
		btnLogin.addActionListener((e) -> {
			String usuario = txtUsuario.getText().toString();
			char[] pass = txtPassword.getPassword();
			String password = "";
			for (int i = 0; i < pass.length; i++) {
				password += pass[i];
			}
			String hash = sha256.calcularSHA256(password);
			if (seguro)
				JOptionPane
						.showMessageDialog(null,
								ac.ConsultarLoginSeguro(usuario, hash) != null ? "Credenciales Validas"
										: "Credenciales Invalidas : (HASH) " + hash,
								null, JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, ac.ConsultarLogin(usuario, hash) != null ? "Credenciales Validas"
						: "Credenciales Invalidas : (HASH) " + hash, null, JOptionPane.INFORMATION_MESSAGE);
			btnLimpiar.doClick();

		});

		btnAddUser.addActionListener((e) -> {
			String usuario = txtUsuario.getText().toString();
			char[] pass = txtPassword.getPassword();
			String password = "";
			for (int i = 0; i < pass.length; i++) {
				password += pass[i];
			}
			String hash = sha256.calcularSHA256(password);
			
			Credenciales credenciales_insertar = (seguro
					? (LimpiarEntradas.esValido(usuario) ? new Credenciales(usuario, hash) : null)
					: new Credenciales(usuario, hash));
			if (seguro) {
				if (credenciales_insertar != null)
					ac.InsertarDatosSeguro(credenciales_insertar);
				else
					JOptionPane.showMessageDialog(null, "ENTRADA INVALIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else
				ac.InsertarDatos(credenciales_insertar);
			btnLimpiar.doClick();
		});

		btnLimpiar.addActionListener((e) -> {
			txtUsuario.setText(null);
			txtPassword.setText(null);
		});

		btnSeguro.addActionListener((e) -> {
			if (seguro) {
				btnSeguro.setBackground(Color.RED);
				btnSeguro.setText("INSEGURO");
				seguro = false;

			} else {
				btnSeguro.setBackground(Color.GREEN);
				btnSeguro.setText("SEGURO");
				seguro = true;
			}
		});

	}

}
