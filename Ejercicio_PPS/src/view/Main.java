package view;

import java.awt.EventQueue;



public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new VentanaPrincipal("Ventana Principal").Inicializar();
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}

}
