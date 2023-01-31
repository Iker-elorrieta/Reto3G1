package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cines;

import javax.swing.JLabel;
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
	JPanel panelCines;
	Metodos metodos = new Metodos();
	private Cines[] cines =null;
	VistaPeliculas salas;
	int i=0;
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
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				metodos.bienvSleep();
				panelCines.setVisible(true);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelBienvenido = new JLabel("Bienvenido");
		labelBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		labelBienvenido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cines = metodos.cuantosCines();
				botonesCine(cines.length);
				panelCines.updateUI();
				metodos.bienvSleep();
				panelCines.setVisible(true);
				labelBienvenido.setVisible(false);
				
				
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
				
			}
		});
		btnFinalizar.setBounds(225, 404, 126, 46);
		panelCines.add(btnFinalizar);
		panelCines.setVisible(false);
		
		
	}
	
	public void botonesCine (int cuantosCines) {
		int y1=40;
		
		for (i=0;i < cuantosCines;i++) {
			
			JButton btncine = new JButton(cines[i].getNombre_cine());
			btncine.setToolTipText(String.valueOf(i));
			btncine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aSalas(cines[Integer.valueOf(btncine.getToolTipText())]);
				}
			});
			if (i%2==0) {
				btncine.setBounds(50, y1, 200, 70);
			}
			else {
				btncine.setBounds(350, y1, 200, 70);
				y1+=100;
			}
			panelCines.add(btncine);
			btncine.setEnabled(true);
		}
	}
	
	
	public void aSalas(Cines cine) {
		salas = new VistaPeliculas(cine);
		salas.setVisible(true);
	}
	
}
