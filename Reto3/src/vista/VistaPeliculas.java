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
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

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
	private JLabel labelCoste;
	private JLabel labelHorario;
	private JButton aceptarHora;
	private JButton aceptar;
	private Entrada[] entradas;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param entradas_compradas 
	 */
	public VistaPeliculas(Cine cineEscojido, Entrada[] entradas_compradas) {
		cine = cineEscojido;
		entradas = metodos.siguienteEntrada(entradas_compradas);
		entradas_compradas=entradas;
		
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

		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setBounds(643, 388, 89, 23);
		aceptar.setEnabled(false);
		tabSesiones.add(aceptar);

		horaCB = new JComboBox<String>();
		horaCB.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		horaCB.setSelectedIndex(0);
		horaCB.setMaximumRowCount(50);
		horaCB.setBounds(415, 92, 271, 29);
		tabSesiones.add(horaCB);
		horaCB.setVisible(false);
		
		labelNombrePelicula = new JLabel("AAAAAA");
		labelNombrePelicula.setHorizontalAlignment(SwingConstants.LEFT);
		labelNombrePelicula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombrePelicula.setBounds(136, 184, 180, 42);
		tabSesiones.add(labelNombrePelicula);

		labelDuracion = new JLabel("");
		labelDuracion.setBounds(515, 62, 135, 29);
		tabSesiones.add(labelDuracion);

		lblNewLabel = new JLabel("Nombre Pelicula:");
		lblNewLabel.setBounds(29, 185, 135, 42);
		tabSesiones.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Genero:");
		lblNewLabel_1.setBounds(78, 238, 68, 14);
		tabSesiones.add(lblNewLabel_1);

		labelGeneroPelicula = new JLabel("AAAAAA");
		labelGeneroPelicula.setHorizontalAlignment(SwingConstants.CENTER);
		labelGeneroPelicula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelGeneroPelicula.setBounds(115, 223, 100, 42);
		tabSesiones.add(labelGeneroPelicula);

		peliculas = metodos.cargarPeliculas(cine);
		botonesPelis(peliculas);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		tabSesiones.setLayout(null);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = (Date) datePicker.getModel().getValue();
				horaCB.setVisible(true);
				aceptarHora.setVisible(true);
				labelHorario.setVisible(true);
				String[] horas = new String[0];
				if (fecha != null)
					horas = metodos.horarioSesiones(pelicula, cine, fecha);
				
				horaCB.setModel(new DefaultComboBoxModel<String>(new String[] {"-------------------------"}));
				
				if (horas.length != 0) {
					for (int horasN = 0; horasN < horas.length; horasN++) {
						horaCB.addItem(horas[horasN]);
					}
				} else {
					datePicker.getModel().setValue(null);
					JOptionPane.showMessageDialog(null,
						"Parece que no hay sesion para esa pelicula el dia que has seleccionado.\n"
						+ "¿Porqué no pruebas a buscar sesion en otro dia?",
						"Ups!",
						JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		datePicker.setBounds(170, 11, 202, 23);
		tabSesiones.add(datePicker);
		
		JLabel lblNewLabel_2 = new JLabel("Coste:");
		lblNewLabel_2.setBounds(83, 283, 48, 14);
		tabSesiones.add(lblNewLabel_2);
		
		labelCoste = new JLabel("AAAAAA");
		labelCoste.setHorizontalAlignment(SwingConstants.LEFT);
		labelCoste.setBounds(141, 283, 74, 14);
		tabSesiones.add(labelCoste);
		
		labelHorario = new JLabel("Horario:");
		labelHorario.setBounds(417, 67, 89, 14);
		labelHorario.setVisible(false);
		tabSesiones.add(labelHorario);
		
		aceptarHora = new JButton("Seleccionar Hora");
		aceptarHora.setBounds(415, 135, 135, 23);
		aceptarHora.setVisible(false);
		aceptarHora.addActionListener(this);
		tabSesiones.add(aceptarHora);
		
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
					labelDuracion.setText("Duracion: "+peliculas[Integer.valueOf(btnpeli.getToolTipText())].getDuracion() + " minutos");
					labelGeneroPelicula.setText(peliculas[Integer.valueOf(btnpeli.getToolTipText())].getGenero());
					labelCoste.setText(peliculas[Integer.valueOf(btnpeli.getToolTipText())].getPrecio()+"€");
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
		if (e.getSource()==atras)
			this.dispose();
		else if(e.getSource()==aceptarHora) {
			if (!String.valueOf(horaCB.getSelectedItem()).equals("-------------------------")) {
			String[] horaSala = String.valueOf(horaCB.getSelectedItem()).split(" - ");
			String hora = horaSala[0];
			Date fecha = (Date) datePicker.getModel().getValue();
			String sala = horaSala[1];
			Sesion sesion=metodos.queSesion(cine, sala, fecha, hora, pelicula);
			entradas[entradas.length-1]=metodos.nuevaEntrada(sesion, entradas.length);
			
			JOptionPane.showMessageDialog(null,
					"Se ha determinado la sesion",
					"",
					JOptionPane.INFORMATION_MESSAGE);
			
			aceptar.setEnabled(true);
			}
		}
		else {
			this.dispose();
		}
	}
	
	public Entrada[] obtenerEntradas() {
		return entradas;
	}
	
	public Entrada[] LimpiarEntradasLogin() {
		entradas=null;
		return entradas;
	}
}
