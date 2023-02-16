package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cliente;
import modelo.Entrada;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaTicket extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnImprimirFactura;
	private Metodos metodos=new Metodos();
	/**
	 * Create the frame.
	 * @param usuarios 
	 */
	public VistaTicket(Entrada[] entradasArray, String dni_usuario, Cliente[] usuarios) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hola, ");
		lblNewLabel.setBounds(25, 24, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel labelNombreUser = new JLabel("New label");
		labelNombreUser.setBounds(57, 24, 46, 14);
		contentPane.add(labelNombreUser);
		
		metodos.nombreUsuario(usuarios, dni_usuario);
		
		JLabel lblNewLabel_1 = new JLabel(", Tu compra ha sido registrada.");
		lblNewLabel_1.setBounds(113, 24, 166, 14);
		contentPane.add(lblNewLabel_1);
		
		btnImprimirFactura = new JButton("Imprimir factura");
		btnImprimirFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnImprimirFactura.setBounds(311, 54, 130, 23);
		contentPane.add(btnImprimirFactura);
		
		JLabel lblNewLabel_2 = new JLabel("Si quieres puedes imprimir la factura pulsando este boton:");
		lblNewLabel_2.setBounds(25, 58, 317, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton volver = new JButton("Inicio");
		volver.addActionListener(this);
		volver.setBounds(10, 96, 89, 23);
		contentPane.add(volver);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
