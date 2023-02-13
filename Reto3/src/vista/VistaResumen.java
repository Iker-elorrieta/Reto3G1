package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Entrada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaResumen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VistaLogin login;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the fram
	 * @param users 
	 * @param entradas_compradas 
	 */
	public VistaResumen(Entrada[] entradas_compradas, Cliente[] users) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
			contentPane.setLayout(null);
			
	    
		
				
		JButton btnImprimir = new JButton("Ir a ticket");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login = new VistaLogin(users);
				login.setVisible(true);
			}
		});
		btnImprimir.setBounds(94, 23, 127, 23);
		contentPane.add(btnImprimir);
		
		JButton btnCancelar = new JButton("Atras");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCancelar.setBounds(10, 100, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnMostrar = new JButton("Mostrar Resumen");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columns = new String[] {
			            "Id", "Name", "Hourly Rate", "Part Time"
			        };
			         
				 Object[][] data = new Object[][] {
				 
			        };
				table = new JTable(data,columns);
				JFrame frametabla = new JFrame();
				frametabla.getContentPane().add(new JScrollPane(table));
		        
				frametabla.setTitle("Table Example");
				frametabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
				frametabla.pack();
				frametabla.setVisible(true);
			}
		});
		btnMostrar.setBounds(94, 57, 127, 23);
		contentPane.add(btnMostrar);
	}

}
