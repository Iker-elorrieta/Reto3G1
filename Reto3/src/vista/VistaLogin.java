package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import modelo.Cliente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VistaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Metodos metodo = new Metodos();
	boolean correcto=false;
	Cliente[] usuarios;
	private JPasswordField jPassw;
	private JTextField jUser;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param users 
	 */
	public VistaLogin(Cliente[] users) {
		usuarios=users;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 562, 377);
		contentPane.add(tabbedPane);
		
		JPanel panelLogin = new JPanel();
		tabbedPane.addTab("Login", null, panelLogin, null);
		panelLogin.setLayout(null);
		
		jPassw = new JPasswordField();
		jPassw.setBounds(31, 112, 175, 20);
		panelLogin.add(jPassw);
		
		jUser = new JTextField();
		jUser.setColumns(10);
		jUser.setBounds(31, 56, 175, 20);
		panelLogin.add(jUser);
		
		JLabel labelUser = new JLabel("DNI:");
		labelUser.setBounds(31, 31, 90, 14);
		panelLogin.add(labelUser);
		
		JLabel labelPassw = new JLabel("Contraseña:");
		labelPassw.setBounds(31, 87, 90, 14);
		panelLogin.add(labelPassw);
		
		JLabel labelIncorrecto = new JLabel("");
		labelIncorrecto.setForeground(Color.RED);
		labelIncorrecto.setBounds(214, 87, 284, 14);
		panelLogin.add(labelIncorrecto);
		
		JButton validarBtn = new JButton("Iniciar sesion");
		validarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelIncorrecto.setText("");
				String dni = jUser.getText();
				String pass = String.valueOf(jPassw.getPassword());
				if(metodo.validarUsers(usuarios, dni, pass)) {
					//manda a ticket
					
				}
				else
					labelIncorrecto.setText("DNI o Contraseña inorrectos.");
				
			}
		});
		validarBtn.setBounds(31, 195, 146, 23);
		panelLogin.add(validarBtn);
		
		JPanel panelRegistrarse = new JPanel();
		tabbedPane.addTab("Registrarse", null, panelRegistrarse, null);
		panelRegistrarse.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(169, 58, 149, 20);
		panelRegistrarse.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(169, 89, 149, 20);
		panelRegistrarse.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(169, 120, 149, 20);
		panelRegistrarse.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(169, 27, 149, 20);
		panelRegistrarse.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"H", "M"}));
		comboBox.setBounds(169, 157, 64, 22);
		panelRegistrarse.add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 190, 149, 20);
		panelRegistrarse.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(132, 30, 64, 14);
		panelRegistrarse.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(115, 61, 64, 14);
		panelRegistrarse.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido 1:");
		lblNewLabel_2.setBounds(105, 92, 64, 14);
		panelRegistrarse.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido 2:");
		lblNewLabel_3.setBounds(105, 123, 64, 14);
		panelRegistrarse.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Genero:");
		lblNewLabel_4.setBounds(115, 161, 64, 14);
		panelRegistrarse.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Contraseña:");
		lblNewLabel_5.setBounds(95, 193, 74, 14);
		panelRegistrarse.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Validar Contraseña:");
		lblNewLabel_5_1.setBounds(60, 224, 109, 14);
		panelRegistrarse.add(lblNewLabel_5_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(169, 221, 149, 20);
		panelRegistrarse.add(passwordField_1);
		
		JButton registrarseBtn = new JButton("Registrarse");
		registrarseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		registrarseBtn.setBounds(174, 277, 119, 40);
		panelRegistrarse.add(registrarseBtn);
		
		
	}
}
