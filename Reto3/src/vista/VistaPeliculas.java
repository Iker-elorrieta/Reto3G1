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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VistaPeliculas extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cine cine;
	private Pelicula[] peliculas;
	private JPanel tabPeliculas;
	private JPanel tabSesiones;
	private JTabbedPane tabbedPane;
	private JButton atras;
	JDatePickerImpl datePicker;
	Metodos metodos = new Metodos();
	private JComboBox<String> horaCB;
	private JLabel labelDuracion;
	private JLabel labelNombrePelicula;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel labelGeneroPelicula;
	private Pelicula pelicula;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPeliculas(Cine cineEscojido) {
		cine = cineEscojido;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(643, 388, 89, 23);
		tabSesiones.add(aceptar);

		horaCB = new JComboBox<String>();
		horaCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				Date fecha = (Date) datePicker.getModel().getValue();
				String[] horas = new String[0];
				if (fecha != null)
					horas = metodos.horarioSesiones(pelicula, cine, fecha);
				
				horaCB.setModel(new DefaultComboBoxModel<String>(new String[] { "-------" }));
				
				if (horas.length != 0) {
					for (int horasN = 0; horasN < horas.length; horasN++) {
						horaCB.addItem(horas[horasN]);
					}
				} else {
					datePicker.getModel().setValue(null);
				}
				
			}
		});
		horaCB.setModel(new DefaultComboBoxModel<String>(new String[] {"-------------------------"}));
		horaCB.setSelectedIndex(0);
		horaCB.setMaximumRowCount(50);
		horaCB.setBounds(441, 34, 135, 29);
		tabSesiones.add(horaCB);

		labelNombrePelicula = new JLabel("AAAAAA");
		labelNombrePelicula.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombrePelicula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombrePelicula.setBounds(118, 271, 180, 42);
		tabSesiones.add(labelNombrePelicula);

		labelDuracion = new JLabel("");
		labelDuracion.setBounds(608, 34, 124, 29);
		tabSesiones.add(labelDuracion);

		lblNewLabel = new JLabel("Nombre Pelicula:");
		lblNewLabel.setBounds(22, 272, 124, 42);
		tabSesiones.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Genero:");
		lblNewLabel_1.setBounds(60, 325, 68, 14);
		tabSesiones.add(lblNewLabel_1);

		labelGeneroPelicula = new JLabel("AAAAAA");
		labelGeneroPelicula.setHorizontalAlignment(SwingConstants.CENTER);
		labelGeneroPelicula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelGeneroPelicula.setBounds(97, 310, 100, 42);
		tabSesiones.add(labelGeneroPelicula);

		peliculas = metodos.cargarPeliculas(cine);
		botonesPelis(peliculas);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		tabSesiones.setLayout(null);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(170, 11, 202, 23);
		tabSesiones.add(datePicker);
	}

	public void botonesPelis(Pelicula[] peliculas) {
		int y1 = 40;

		for (int i = 0; i < peliculas.length; i++) {

			JButton btnpeli = new JButton(peliculas[i].getNombre());
			btnpeli.setToolTipText(String.valueOf(i));
			btnpeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pelicula=peliculas[Integer.valueOf(btnpeli.getToolTipText())];
					tabbedPane.setEnabledAt(0, false);
					tabbedPane.setEnabledAt(1, true);
					tabbedPane.setSelectedIndex(1);
					labelNombrePelicula.setText(peliculas[Integer.valueOf(btnpeli.getToolTipText())].getNombre());
					labelDuracion.setText(peliculas[Integer.valueOf(btnpeli.getToolTipText())].getDuracion() + " minutos");
					labelGeneroPelicula.setText(peliculas[Integer.valueOf(btnpeli.getToolTipText())].getGenero());
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
