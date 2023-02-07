package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VistaCines extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCines;
	private Metodos metodos = new Metodos();
	private Cine[] cines;
	private Cliente[] users;
	private Sesion[] sesiones;
	private Entrada[] entradas_compradas=null;
	private Pelicula[] peliculas;
	private VistaLogin login;
	private VistaPeliculas vPeliculas;
	int i = 0;

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
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelBienvenido = new JLabel("Bienvenido");
		labelBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		labelBienvenido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				labelBienvenido.setVisible(false);
				actuador();
			}
		});
		labelBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 50));
		labelBienvenido.setBounds(0, 0, 584, 461);
		contentPane.add(labelBienvenido);

		panelCines = new JPanel();
		panelCines.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCines.setForeground(new Color(0, 0, 0));
		panelCines.setBounds(0, 0, 584, 461);
		contentPane.add(panelCines);
		panelCines.setLayout(null);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(entradas_compradas==null) {
					JOptionPane.showMessageDialog(null,
						"Tienes que elegir alguna pelicula.",
						"Error",
						JOptionPane.INFORMATION_MESSAGE);
				}else {
					login = new VistaLogin(users);
					login.setVisible(true);
				}
			}
		});
		btnFinalizar.setBounds(225, 404, 126, 46);
		panelCines.add(btnFinalizar);
		panelCines.setVisible(false);

	}

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

	public void aPelis(Cine cine) {
		vPeliculas = new VistaPeliculas(cine);
		vPeliculas.setVisible(true);
	}

	public void actuador() {
		
		cines = metodos.cuantosCines();
		cines = metodos.cuantasSalas(cines);
		users =metodos.usuariosArray();
		peliculas=metodos.cuantasPeliculas();
		botonesCine(cines.length);
		panelCines.updateUI();
		
		try {
			Thread.sleep(3000);
		} catch (Exception f) {
			f.printStackTrace();
		}

		panelCines.setVisible(true);
	}

}
