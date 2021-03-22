package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * @author h4ck4fun Esta clase proveé la funcionalidad básica de una ventana.
 */
public class Ventana {

	JFrame ventana;

	public Ventana(String titulo, String layout, int filas, int columnas) {
		ventana = new JFrame(titulo);
		switch (layout) {
		case "GridLayout":
			ventana.setLayout(new GridLayout(filas, columnas));
			break;
		case "GridBagLayout":
			ventana.setLayout(new GridBagLayout());
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + layout);
		}
		ventana.setBounds(200, 200, 800, 600);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Inicializar() {
		ventana.setVisible(true);
		InicializarComponentes();
		ActivarListeners();
	}

	public void addComponent(JComponent objeto, GridBagConstraints opc) {
		if (opc != null)
			addComponent_GBCL(objeto, opc);
		else
			addComponent_GL(objeto);
	}

	private void addComponent_GBCL(JComponent objeto, GridBagConstraints opc) {
		this.ventana.getContentPane().add(objeto, opc);
		opc = null;
		opc = new GridBagConstraints();
	}

	private void addComponent_GL(JComponent objeto) {
		this.ventana.getContentPane().add(objeto);
	}

	/**
	 * Sobreescribeme
	 */
	public void InicializarComponentes() {

	}

	/**
	 * Sobreescribeme
	 */
	public void ActivarListeners() {

	}

	public JFrame getVentana() {
		return ventana;
	}

	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

}
