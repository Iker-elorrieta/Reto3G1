package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCines extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panelCines;
	Metodos metodos = new Metodos();
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
		setBounds(100, 100, 587, 443);
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
		labelBienvenido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				metodos.bienvSleep();
				panelCines.setVisible(true);
			}
		});
		
		panelCines = new JPanel();
		panelCines.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCines.setForeground(new Color(0, 0, 0));
		panelCines.setBounds(0, 0, 571, 404);
		contentPane.add(panelCines);
		panelCines.setLayout(null);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnFinalizar.setBounds(243, 352, 89, 23);
		panelCines.add(btnFinalizar);
		labelBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 50));
		labelBienvenido.setBounds(159, 163, 248, 61);
		contentPane.add(labelBienvenido);
		panelCines.setVisible(false);
		
		
	}
}
