package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cliente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VistaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jUser;
	Metodos metodo = new Metodos();
	boolean correcto=false;
	Cliente[] usuario;
	private JPasswordField jPassw;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
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
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jPassw = new JPasswordField();
		jPassw.setBounds(74, 113, 175, 20);
		contentPane.add(jPassw);
		
		jUser = new JTextField();
		jUser.setBounds(74, 57, 175, 20);
		contentPane.add(jUser);
		jUser.setColumns(10);
		
		JLabel labelUser = new JLabel("Usuario:");
		labelUser.setBounds(74, 32, 90, 14);
		contentPane.add(labelUser);
		
		JLabel labelPassw = new JLabel("Contraseña:");
		labelPassw.setBounds(74, 88, 90, 14);
		contentPane.add(labelPassw);
		
		JLabel labelIncorrecto = new JLabel("");
		labelIncorrecto.setForeground(new Color(255, 0, 0));
		labelIncorrecto.setBounds(257, 88, 284, 14);
		contentPane.add(labelIncorrecto);
		
		JButton validarBtn = new JButton("Iniciar sesion");
		validarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				usuario=metodo.usuariosArray();
				correcto=metodo.validarUsers(usuario, jUser.getText(), String.valueOf(jPassw.getPassword()));
				if (correcto) {
					labelIncorrecto.setText("");
					jUser.setText("");
					jPassw.setText("");
					
				}
				else {
					labelIncorrecto.setText("Usuario o Contraseña Incorrectos.");
				}
				
			}
		});
		validarBtn.setBounds(74, 196, 146, 23);
		contentPane.add(validarBtn);
		
	
	}
}
