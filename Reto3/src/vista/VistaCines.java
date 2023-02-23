package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cine;
import modelo.Entrada;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VistaCines extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCines;
	private Metodos metodos = new Metodos();
	private Cine[] cines = metodos.cuantosCines();
	static Entrada[] entradas_compradas = new Entrada[0];
	private VistaPeliculas vPeliculas;
	int i = 0;
	private VistaLogin login;
	private JTable table;
	private JButton btnCancelar;
	private JLabel labelCosteTot;
	private JButton btnImprimir;
	private JPanel contentPaneResumen;
	private JButton btnFinalizar;
	private JLabel labelBienvenido;
	private JLabel jlabelcoste;
	private JScrollPane scrollPane;
	private String[] cinesYsalas;
	private float costeTotConDescuento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCines frame = new VistaCines();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaCines() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		labelBienvenido = new JLabel("Bienvenido");
		labelBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		labelBienvenido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				labelBienvenido.setVisible(false);
				actuador();
			}
		});
		labelBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 50));
		labelBienvenido.setBounds(0, 0, 710, 461);
		contentPane.add(labelBienvenido);

		panelCines = new JPanel();
		panelCines.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCines.setForeground(new Color(0, 0, 0));
		panelCines.setBounds(0, 0, 710, 461);
		contentPane.add(panelCines);
		panelCines.setLayout(null);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(this);
		btnFinalizar.setBounds(292, 404, 126, 46);
		panelCines.add(btnFinalizar);
		panelCines.setVisible(false);

		contentPaneResumen = new JPanel();
		contentPaneResumen.setBounds(0, 0, 710, 461);
		contentPane.add(contentPaneResumen);
		contentPaneResumen.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 690, 403);
		contentPaneResumen.add(scrollPane);

		btnImprimir = new JButton("Ir a login");
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(573, 11, 127, 23);
		contentPaneResumen.add(btnImprimir);
		btnImprimir.setVisible(false);

		btnCancelar = new JButton("Atras");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(10, 11, 89, 23);
		contentPaneResumen.add(btnCancelar);
		btnCancelar.setVisible(false);

		jlabelcoste = new JLabel("Coste Total:");
		jlabelcoste.setBounds(176, 15, 96, 14);
		contentPaneResumen.add(jlabelcoste);
		jlabelcoste.setVisible(false);

		labelCosteTot = new JLabel("");
		labelCosteTot.setBounds(260, 15, 59, 14);
		contentPaneResumen.add(labelCosteTot);
	}
	
	//Aqui se hacen los botones segun los cines que haya dinamicamente
	public void botonesCine(int cuantosCines) {
		int y1 = 40;

		for (i = 0; i < cuantosCines; i++) {

			JButton btncine = new JButton(cines[i].getNombre_cine());
			btncine.setToolTipText(String.valueOf(i));
			btncine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aPelis(cines[Integer.valueOf(btncine.getToolTipText())]);
				}
			});
			if (i % 2 == 0) {
				btncine.setBounds(50, y1, 200, 70);
			} else {
				btncine.setBounds(350, y1, 200, 70);
				y1 += 100;
			}
			panelCines.add(btncine);
			btncine.setEnabled(true);
		}
	}

	//esta es una funcion que entra en uso cuando se pulsa un boton de cine,
	//manda un numero almacenado en su tooltip acorde con su posicion en la base de datos
	//despues, abre la ventana de peliculas que estan relacionadas con ese cine ordenadas segun su fecha, las mas cercanas siendo las primeras
	public void aPelis(Cine cine) {
		if (vPeliculas != null) {
			if (vPeliculas.obtenerEntradas()[vPeliculas.obtenerEntradas().length - 1] != null)
				entradas_compradas = vPeliculas.obtenerEntradas();
		}

		vPeliculas = new VistaPeliculas(cine, entradas_compradas);
		vPeliculas.setVisible(true);
	}

	//aqui se cargan los datos de la base de datos para que no este abriendo conexiones todo el rato, junto con una espera de 3 segundos
	public void actuador() {
		botonesCine(cines.length);
		panelCines.updateUI();

		try {
			Thread.sleep(3000);
		} catch (Exception f) {
			f.printStackTrace();
		}

		panelCines.setVisible(true);
	}

	//aqui esta un actionListener para que cuando de a un boton que no tenga efecto propio, segun que boton sea haga varias cosas
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//cuando pulsa el boton finalizar se mira si hay peliculas en el array o se a abierto la ventana de seleccionar peliculas
		//si esta vacio se finaliza la aplicacion, si hay entradas se va a login, asi mismo, si se vuelve de login y no se selecciona alguna pelicula
		//se cierra la aplicacion
		if (e.getSource() == btnFinalizar) {

			try {
				vPeliculas.obtenerEntradas();

				if (vPeliculas.obtenerEntradas()[vPeliculas.obtenerEntradas().length - 1] != null)
					entradas_compradas = vPeliculas.obtenerEntradas();

				if (entradas_compradas[0] == null) {
					this.dispose();
				} else {
					//aqui esta el resumen en forma de tabla con las peliculas que se han seleccionado, con sus dias, horas, cines, salas, y costes.
					//segun la cantidad de peliculas que se seleccionen se hara un mayor descuento en el coste total
					
					panelCines.setVisible(false);
					contentPaneResumen.setVisible(true);
					btnImprimir.setVisible(true);
					btnCancelar.setVisible(true);
					jlabelcoste.setVisible(true);
					String[] columns = new String[] { "Numero Entrada", "Nombre Pelicula", "Fecha", "Hora", "Sala",
							"Coste" };
					DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
					String[][] datosTabla = new String[entradas_compradas.length][6];
					cinesYsalas = new String[entradas_compradas.length];
					float costeTotSinDescuento = 0;
					DecimalFormat dfSharp = new DecimalFormat("0.00");
					for (int i = 0; i < entradas_compradas.length; i++) {
						cinesYsalas[i] = metodos.salaConFechaYPelicula(entradas_compradas[i].getSesion(), cines);

						costeTotSinDescuento += entradas_compradas[i].getPrecio();
						datosTabla[i][0] = entradas_compradas[i].getCdEntrada();
						datosTabla[i][1] = entradas_compradas[i].getSesion().getPelicula().getNombre();
						datosTabla[i][2] = dt.format(entradas_compradas[i].getFecha());
						datosTabla[i][3] = entradas_compradas[i].getHora().toString();
						datosTabla[i][4] = cinesYsalas[i];
						datosTabla[i][5] = String.valueOf(dfSharp.format(entradas_compradas[i].getPrecio())) + "€";
					}

					contentPaneResumen.add(scrollPane);
					table = new JTable(datosTabla, columns);
					table.setEnabled(false);
					scrollPane.setViewportView(table);

					costeTotConDescuento = metodos.calcularDescuento(costeTotSinDescuento,
							(float) entradas_compradas.length);
					labelCosteTot.setText(String.valueOf(costeTotConDescuento) + "€");
					labelCosteTot.setBounds(260, 15, 59, 14);
					contentPaneResumen.add(labelCosteTot);
					contentPane.updateUI();
				}
			} catch (Exception ex) {
				this.dispose();
			}
		} else if (e.getSource() == btnCancelar) {
			//aqui, si estamos en la ventana de resumen y pulsamos el boton volver, nos devuelve a cines
			panelCines.setVisible(true);
			contentPaneResumen.setVisible(false);
		} else if (e.getSource() == btnImprimir) {
			//desde aqui nos manda a login
			panelCines.setVisible(true);
			contentPaneResumen.setVisible(false);
			btnImprimir.setVisible(false);
			btnCancelar.setVisible(false);
			jlabelcoste.setVisible(false);
			login = new VistaLogin(entradas_compradas, cinesYsalas, costeTotConDescuento);
			login.setVisible(true);
			entradas_compradas = vPeliculas.limpiarEntradas();
			vPeliculas=null;
		}

	}
}
