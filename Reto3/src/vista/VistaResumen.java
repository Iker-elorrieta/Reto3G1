package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Entrada;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VistaResumen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneResumen;
	private VistaLogin login;
	private JTable table;
	private Entrada[] entradas;
	private JButton btnCancelar;
	private JLabel labelCosteTot;
	private Metodos metodos=new Metodos();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame
	 * @param users 
	 * @param vPeliculas 
	 * @param entradas_compradas 
	 */
	public VistaResumen(Entrada[] entradasC) {
		entradas = entradasC;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 503);
		contentPaneResumen = new JPanel();
		contentPaneResumen.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPaneResumen);
		contentPaneResumen.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 690, 403);
		contentPaneResumen.add(scrollPane);
		
		JButton btnImprimir = new JButton("Ir a login");
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(573, 11, 127, 23);
		contentPaneResumen.add(btnImprimir);
		
		btnCancelar = new JButton("Atras");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 11, 89, 23);
		contentPaneResumen.add(btnCancelar);
		

		String[] columns = new String[] {
	            "Numero Entrada", "Nombre Pelicula", "Fecha", "Hora", "Coste"
	        };
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");    
		String[][] datosTabla = new String[this.entradas.length][5];
		float costeTotSinDescuento=0;
		for(int i=0;i<this.entradas.length;i++) {
			costeTotSinDescuento+=entradas[i].getPrecio();
			datosTabla[i][0] = entradas[i].getCdEntrada();
			datosTabla[i][1] = entradas[i].getSesion().getPelicula().getNombre();
			datosTabla[i][2] = dt.format(entradas[i].getFecha());
			datosTabla[i][3] = entradas[i].getHora().toString();
			datosTabla[i][4] = String.valueOf(entradas[i].getPrecio())+"€";
		}
		
		table = new JTable(datosTabla,columns);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JLabel jlabel = new JLabel("Coste Total:");
		jlabel.setBounds(176, 15, 96, 14);
		contentPaneResumen.add(jlabel);
		
		float costeTotConDescuento=metodos.calcularDescuento(costeTotSinDescuento, entradas.length);
		labelCosteTot = new JLabel(String.valueOf(costeTotConDescuento)+"€");
		labelCosteTot.setBounds(260, 15, 59, 14);
		contentPaneResumen.add(labelCosteTot);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource()==btnCancelar)
			this.dispose();
		else {
			login = new VistaLogin(entradas);
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
