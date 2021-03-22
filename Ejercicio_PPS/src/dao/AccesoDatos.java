package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import clases.Credenciales;
import utilities.LimpiarEntradas;

public class AccesoDatos {
	private Connection conexion;

	public void InsertarDatos(Credenciales c) {
		try {
			conexion = MySQL.getConexion();
			String SQL = "INSERT INTO USERS(NICK,PASS) VALUES ('" + c.getNick() + "', '" + c.getPassword() + "');";
			Statement st = conexion.createStatement();
			if (st.executeUpdate(SQL) == 1)
				JOptionPane.showMessageDialog(null, "Accion Realizada con éxito", null,
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Oops. Algo ha fallado", null, JOptionPane.ERROR_MESSAGE);

			st.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Credenciales ConsultarLogin(String user, String pass) {
		Credenciales c = null;
		try {
			conexion = MySQL.getConexion();
			String SQL = "SELECT * FROM USERS WHERE NICK='" + user + "' AND PASS='" + pass + "';";
			System.out.println(SQL);
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				c = new Credenciales(rs.getString(1), rs.getString(2));
			}
			st.close();
			rs.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public void InsertarDatosSeguro(Credenciales c) {
		try {
			conexion = MySQL.getConexion();
			String SQL = "INSERT INTO USERS(NICK,PASS) VALUES(?,?);";
			PreparedStatement ps = conexion.prepareStatement(SQL);
			ps.setString(1, c.getNick());
			ps.setString(1, c.getPassword());
			if (ps.execute())
				JOptionPane.showMessageDialog(null, "Accion Realizada con éxito", null,
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Credenciales ConsultarLoginSeguro(String user, String pass) {
		Credenciales c = null;
		if (LimpiarEntradas.esValido(user) ) {
			try {
				conexion = MySQL.getConexion();
				String SQL = "SELECT * FROM USERS WHERE NICK = ? AND PASS = ?;";
				PreparedStatement ps = conexion.prepareStatement(SQL);
				ps.setString(1, user);
				ps.setString(2, pass);
				ResultSet rs = ps.executeQuery(SQL);
				while (rs.next()) {
					c = new Credenciales(rs.getString(1), rs.getString(2));
				}
				ps.close();
				rs.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "ENTRADA INVALIDA","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return c;
	}
}
