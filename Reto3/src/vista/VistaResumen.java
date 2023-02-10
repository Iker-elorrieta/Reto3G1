package vista;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaResumen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaResumen frame = new VistaResumen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the fram
	 */
	public VistaResumen() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 334, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
			contentPane.setLayout(null);
			
	    
		
				
		JButton btnImprimir = new JButton("Imprimir Ticket");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
