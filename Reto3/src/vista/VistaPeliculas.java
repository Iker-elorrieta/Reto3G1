package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.Metodos;
import modelo.Cine;
import modelo.DateLabelFormatter;
import modelo.Pelicula;
import javax.swing.JTabbedPane;

public class VistaPeliculas extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cine cine;
	private Pelicula[] peliculas ;
	private JPanel tabPeliculas;
	private JPanel tabSesiones;
	private JTabbedPane tabbedPane;
	private JButton atras;
	JDatePickerImpl datePicker;
	Metodos metodos = new Metodos();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPeliculas(Cine cineEscojido) {
		cine = cineEscojido;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		atras = new JButton("Atras");
		atras.setBounds(338, 0, 71, 29);
		atras.addActionListener(this);
		contentPane.setLayout(null);
		contentPane.add(atras);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 28, 747, 450);
		contentPane.add(tabbedPane);

		tabPeliculas = new JPanel();
		tabbedPane.addTab("Peliculas", null, tabPeliculas, null);

		tabSesiones = new JPanel();
		tabbedPane.addTab("Sesiones", null, tabSesiones, null);
		tabbedPane.setEnabledAt(1, false);
		tabSesiones.setLayout(null);
		
		
		peliculas = metodos.cargarPeliculas(cine);
		botonesPelis(peliculas);
	}

	public void botonesPelis(Pelicula[] peliculas) {
		int y1 = 40;

		for (int i = 0; i < peliculas.length; i++) {

			JButton btnpeli = new JButton(peliculas[i].getNombre());
			btnpeli.setToolTipText(String.valueOf(i));
			btnpeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					elegirSesion(peliculas[Integer.valueOf(btnpeli.getToolTipText())]);
					tabbedPane.setEnabledAt(0, false);
					tabbedPane.setEnabledAt(1, true);
					tabbedPane.setSelectedIndex(1);
					
				}
			});

			if (i % 2 == 0) {
				btnpeli.setBounds(50, y1, 200, 70);
			} else {
				btnpeli.setBounds(350, y1, 200, 70);
				y1 += 100;
			}
			tabPeliculas.add(btnpeli);
			btnpeli.setEnabled(true);
		}

	}

	protected void elegirSesion(Pelicula pelicula) {
		// TODO Auto-generated method stub
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(1990, 8, 24);
		Properties p = new Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		tabSesiones.setLayout(null);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(170, 11, 202, 23);
		tabSesiones.add(datePicker);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==atras)
			this.dispose();
		else {
			
			Date selectedDate = (Date) datePicker.getModel().getValue();
			
		}
			
	}
}
