package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cine;

public class VistaPeliculas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cine cine;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VistaPeliculas(Cine cineEscojido) {
		cine = cineEscojido;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	
	public void botonesSalas (int cuantasSalas) {
		int y1=40;
		
		for (int i=0;i < cuantasSalas;i++) {
			
			JButton btnsala = new JButton("");
			btnsala.setToolTipText(String.valueOf(i));
			btnsala.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
				}
			});

			if (i%2==0) {
				btnsala.setBounds(50, y1, 200, 70);
			}
			else {
				btnsala.setBounds(350, y1, 200, 70);
				y1+=100;
				
			}
			contentPane.add(btnsala);
			btnsala.setEnabled(false);
		}
		
		
		
	}
	
	
	
	
	
}
