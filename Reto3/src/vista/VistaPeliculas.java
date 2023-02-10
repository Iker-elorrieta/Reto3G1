package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cine;
import modelo.Pelicula;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPeliculas(Cine cineEscojido, Pelicula[] peliculas) {
		cine = cineEscojido;
		this.peliculas = peliculas;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JButton atras = new JButton("Atras");
		atras.setBounds(319, 0, 71, 29);
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

		JLabel lblNewLabel = new JLabel("Hola");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(234, 98, 238, 142);
		tabSesiones.add(lblNewLabel);

		botonesPelis(this.peliculas);
	}

	public void botonesPelis(Pelicula[] peliculas) {
		int y1 = 40;

		for (int i = 0; i < peliculas.length; i++) {

			JButton btnpeli = new JButton(peliculas[i].getNombre());
			btnpeli.setToolTipText(String.valueOf(i));
			btnpeli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
