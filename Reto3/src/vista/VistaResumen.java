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
	 * @param vPeliculas 
	 * @param entradas_compradas 
	 */
	public VistaResumen(Entrada[] entradasC, Cliente[] users) {
		this.users=users;
		entradas = entradasC;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 690, 403);
		contentPane.add(scrollPane);
		
		JButton btnImprimir = new JButton("Ir a login");
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(573, 11, 127, 23);
		contentPane.add(btnImprimir);
		
		btnCancelar = new JButton("Atras");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 11, 89, 23);
		contentPane.add(btnCancelar);
		

		String[] columns = new String[] {
	            "Numero Entrada", "Nombre Pelicula", "Fecha", "Hora", "Coste"
	        };
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");    
		String[][] datosTabla = new String[this.entradas.length][5];
		
		for(int i=0;i<this.entradas.length;i++) {
			datosTabla[i][0] = entradas[i].getCdEntrada();
			datosTabla[i][1] = entradas[i].getSesion().getPelicula().getNombre();
			datosTabla[i][2] = dt.format(entradas[i].getFecha());
			datosTabla[i][3] = entradas[i].getHora().toString();
			datosTabla[i][4] = String.valueOf(entradas[i].getPrecio())+"€";
		}
		
		table = new JTable(datosTabla,columns);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource()==btnCancelar)
			this.dispose();
		else {
			login = new VistaLogin(users, entradas);
			login.setVisible(true);
			entradas = new Entrada[2];
			entradas[1]=null;
			this.dispose();
		}
	}
	
	public Entrada[] loginAbierto() {
		return entradas;
	}
}
