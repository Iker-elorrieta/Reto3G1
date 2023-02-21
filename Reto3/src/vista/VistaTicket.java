package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cliente;
import modelo.Entrada;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
	 * @param cinesYSalas 
	 * @param descontado 
	 */
	public VistaTicket(Entrada[] entradasArray, String dni_usuario, Cliente[] usuarios, String[] cinesYSalas, float descontado) {
		
		metodos.compraRealizada(entradasArray, dni_usuario, descontado);
		
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
		labelNombreUser.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreUser.setBounds(57, 24, 69, 14);
		contentPane.add(labelNombreUser);
		
		labelNombreUser.setText(metodos.nombreUsuario(usuarios, dni_usuario));
		
		JLabel lblNewLabel_1 = new JLabel(", Tu compra ha sido registrada.");
		lblNewLabel_1.setBounds(130, 24, 212, 14);
		contentPane.add(lblNewLabel_1);
		
		btnImprimirFactura = new JButton("Imprimir factura");
		btnImprimirFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.imprimirFactura(entradasArray, usuarios, dni_usuario, cinesYSalas, descontado);
				JOptionPane.showMessageDialog(null,
						"La informacion pertinente ha sido almacenada en el fichero factura.txt", "éxito!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnImprimirFactura.setBounds(408, 54, 130, 23);
		contentPane.add(btnImprimirFactura);
		
		JLabel lblNewLabel_2 = new JLabel("Si quieres puedes imprimir la factura pulsando este boton:");
		lblNewLabel_2.setBounds(25, 58, 347, 14);
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
