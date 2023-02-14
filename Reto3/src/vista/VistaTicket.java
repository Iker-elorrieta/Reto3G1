package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Entrada;

public class VistaTicket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Entrada[] entradas;
	private String dni;
	private Cliente[] clientes;
	/**
	 * Create the frame.
	 * @param usuarios 
	 */
	public VistaTicket(Entrada[] entradasArray, String dni_usuario, Cliente[] usuarios) {
		entradas=entradasArray;
		dni=dni_usuario;
		clientes=usuarios;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		
	}
}
