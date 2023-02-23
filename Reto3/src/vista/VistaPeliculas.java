package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePickerImpl;
import com.toedter.calendar.JCalendar;

import controlador.Metodos;
import modelo.Cine;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
	private Entrada[] entradas;
	private JCalendar prueba;
	private Date fecha;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param entradas_compradas 
	 */
	public VistaPeliculas(Cine cineEscojido, Entrada[] entradas_compradas) {
		
		//cargamos los datos enviados de vistaCines y los metemos en variables locales para manipularlas más facilmente
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

		horaCB = new JComboBox<String>();
		horaCB.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		horaCB.setSelectedIndex(0);
		horaCB.setMaximumRowCount(50);
		horaCB.setBounds(461, 89, 271, 29);
		tabSesiones.add(horaCB);
		horaCB.setVisible(false);
		
		labelNombrePelicula = new JLabel("");
		labelNombrePelicula.setHorizontalAlignment(SwingConstants.LEFT);
		labelNombrePelicula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombrePelicula.setBounds(136, 184, 180, 42);
		tabSesiones.add(labelNombrePelicula);

		labelDuracion = new JLabel("");
		labelDuracion.setBounds(561, 59, 135, 29);
		tabSesiones.add(labelDuracion);

		lblNewLabel = new JLabel("Nombre Pelicula:");
		lblNewLabel.setBounds(29, 185, 135, 42);
		tabSesiones.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Genero:");
		lblNewLabel_1.setBounds(78, 238, 68, 14);
		tabSesiones.add(lblNewLabel_1);

		labelGeneroPelicula = new JLabel("");
		labelGeneroPelicula.setHorizontalAlignment(SwingConstants.CENTER);
		labelGeneroPelicula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelGeneroPelicula.setBounds(115, 223, 100, 42);
		tabSesiones.add(labelGeneroPelicula);

		//aqui se manda el cine para sacarnos sus peliculas y se introducen en forma de botones
		//al seleccionar una fecha se hara visible un comboBox con la hora y la sala si hay ese dia, de lo contrario nos lo hara saber
		peliculas = metodos.cargarPeliculas(cine);
		botonesPelis(peliculas);
		prueba = new JCalendar();
		prueba.addPropertyChangeListener("calendar", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    	fecha = prueba.getDate();
		    	String[] horas = new String[0];
		    	
				if (fecha != null)
					horas = metodos.horarioSesiones(pelicula, cine, fecha);

				if (horas.length != 0) {
					horaCB.setModel(new DefaultComboBoxModel<String>(new String[] {"-------------------------"}));
					horaCB.setVisible(true);
					aceptarHora.setVisible(true);
					labelHorario.setVisible(true);
					for (int horasN = 0; horasN < horas.length; horasN++) {
						horaCB.addItem(horas[horasN]);
					}
				} else {
					horaCB.setModel(new DefaultComboBoxModel<String>(new String[] {"-------------------------"}));
					horaCB.setVisible(false);
					aceptarHora.setVisible(false);
					labelHorario.setVisible(false);
					JOptionPane.showMessageDialog(null,
						"Parece que no hay sesion para esa pelicula el dia que has seleccionado.\n"
						+ "¿Porqué no pruebas a buscar sesion en otro dia?",
						"Ups!",
						JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		prueba.getMonthChooser().getComboBox().setEnabled(false);
		prueba.getYearChooser().getSpinner().setEnabled(false);
		prueba.setLocation(212, 5);
		prueba.setSize(219, 168);
		tabSesiones.add(prueba);
		
		tabSesiones.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Coste:");
		lblNewLabel_2.setBounds(83, 283, 48, 14);
		tabSesiones.add(lblNewLabel_2);
		
		labelCoste = new JLabel("");
		labelCoste.setHorizontalAlignment(SwingConstants.LEFT);
		labelCoste.setBounds(141, 283, 74, 14);
		tabSesiones.add(labelCoste);
		
		labelHorario = new JLabel("Horario:");
		labelHorario.setBounds(463, 64, 89, 14);
		labelHorario.setVisible(false);
		tabSesiones.add(labelHorario);
		
		aceptarHora = new JButton("Seleccionar Hora");
		aceptarHora.setBounds(461, 132, 135, 23);
		aceptarHora.setVisible(false);
		aceptarHora.addActionListener(this);
		tabSesiones.add(aceptarHora);
		
	}
	
	//Aqui se generan los botones de peliculas de forma dinamica segun el cine que se mande
	//cuando se pulsa una, se manda el rango de dias en las que hay sesiones
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
					Date[] fechaMaxfechaMin=metodos.fechasPelicula(cine, pelicula);
					prueba.setSelectableDateRange(fechaMaxfechaMin[0], fechaMaxfechaMin[fechaMaxfechaMin.length-1]);
					prueba.setDate(fechaMaxfechaMin[fechaMaxfechaMin.length-1]);
					labelNombrePelicula.setText(pelicula.getNombre());
					labelDuracion.setText("Duracion: "+pelicula.getDuracion() + " minutos");
					labelGeneroPelicula.setText(peliculas[Integer.valueOf(btnpeli.getToolTipText())].getGenero());
					labelCoste.setText(metodos.sacarPrecio(peliculas[Integer.valueOf(btnpeli.getToolTipText())])+"€");
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
		
		//si pulsamos atras nos manda de vuelta a VistaCines
		if (e.getSource()==atras)
			this.dispose();
		else if(e.getSource()==aceptarHora) {
			//Si pulsamos aceptar Hora nos crea una entrada con los valores seleccionados
			
			if (!String.valueOf(horaCB.getSelectedItem()).equals("-------------------------")) {
			String[] horaSala = String.valueOf(horaCB.getSelectedItem()).split(" - ");
			String hora = horaSala[0];
			String sala = horaSala[1];
			Sesion sesion=metodos.queSesion(cine, sala, fecha, hora, pelicula);
			entradas[entradas.length-1]=metodos.nuevaEntrada(sesion, entradas.length);
			
			JOptionPane.showMessageDialog(null,
					"Se ha determinado la sesion",
					"",
					JOptionPane.INFORMATION_MESSAGE);
			
			this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Porfavor, selecciona una sesion.",
						"Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	//Aqui tenemos dos funciones que llaman las otras clases para recibir el array de las entradas o para limpiarlas.
	public Entrada[] obtenerEntradas() {
		return entradas;
	}
	
	public Entrada[] limpiarEntradas() {
		entradas=new Entrada[0];
		return entradas;
	}
}
