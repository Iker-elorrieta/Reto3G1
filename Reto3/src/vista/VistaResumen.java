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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class VistaResumen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VistaLogin login;
	private JTable table;
	private Entrada[] entradas;
	private Cliente[] users;
	private JButton btnCancelar;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame
	 * @param users 
	 * @param entradas_compradas 
	 */
	public VistaResumen(Entrada[] entradas, Cliente[] users) {
		this.users=users;
		this.entradas=entradas;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JButton btnImprimir = new JButton("Ir a ticket");
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(94, 23, 127, 23);
		contentPane.add(btnImprimir);
		
		btnCancelar = new JButton("Atras");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 100, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnMostrar = new JButton("Mostrar Resumen");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] columns = new String[] {
			            "Numero Entrada", "Nombre Pelicula", "Fecha", "Hora", "Coste"
			        };
				DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");    
				String[][] datosTabla = new String[entradas.length][5];
				
				for(int i=0;i<entradas.length;i++) {
					datosTabla[i][0] = entradas[i].getCdEntrada();
					datosTabla[i][1] = entradas[i].getSesion().getPelicula().getNombre();
					datosTabla[i][2] = dt.format(entradas[i].getFecha());
					datosTabla[i][3] = entradas[i].getHora().toString();
					datosTabla[i][4] = String.valueOf(entradas[i].getPrecio())+"€";
				}
				
				table = new JTable(datosTabla,columns);
				JFrame frametabla = new JFrame();
				frametabla.getContentPane().add(new JScrollPane(table));
				frametabla.setTitle("Resumen de las entradas");
				frametabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
				frametabla.pack();
				frametabla.setVisible(true);
				table.setEnabled(false);
			}
		});
		btnMostrar.setBounds(94, 57, 127, 23);
		contentPane.add(btnMostrar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource()==btnCancelar)
			this.dispose();
		else {
			login = new VistaLogin(users, entradas);
			login.setVisible(true);
			this.dispose();
		}
	}

}
