package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Metodos;
import modelo.Cliente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VistaLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Metodos metodo = new Metodos();
	boolean correcto=false;
	Cliente[] usuarios;
	private JButton atras;
	private JPasswordField jPassw;
	private JTextField jUser;
	private JTextField nombre_reg;
	private JTextField apell1_reg;
	private JTextField apell2_reg;
	private JTextField dni_reg;
	private JPasswordField pass_reg;
	private JPasswordField passval_reg;
	private Color todoOk=Color.GREEN;
	private JButton registrarseBtn;
	private JComboBox<String> sexoCB;
	private JTabbedPane tabbedPane;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param users 
	 */
	public VistaLogin(Cliente[] users) {
		usuarios=users;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		
		JLabel labelIncorrecto = new JLabel("DNI o Contraseña inorrectos.");
		labelIncorrecto.setForeground(Color.RED);
		labelIncorrecto.setBounds(214, 87, 284, 14);
		panelLogin.add(labelIncorrecto);
		labelIncorrecto.setVisible(false);
		
		JButton validarBtn = new JButton("Iniciar sesion");
		validarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelIncorrecto.setText("");
				String dni = jUser.getText();
				String pass = String.valueOf(jPassw.getPassword());
				if(metodo.validarUsers(usuarios, dni, pass)) {
					
					labelIncorrecto.setVisible(false);
					JOptionPane.showMessageDialog(null,
							"Sesion iniciada",
							"Bienvenido.",
						JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					labelIncorrecto.setForeground(Color.RED);
					labelIncorrecto.setVisible(true);
				}
			}
		});
		validarBtn.setBounds(31, 195, 146, 23);
		panelLogin.add(validarBtn);
		
		JPanel panelRegistrarse = new JPanel();
		tabbedPane.addTab("Registrarse", null, panelRegistrarse, null);
		panelRegistrarse.setLayout(null);
		
		dni_reg = new JTextField();
		dni_reg.setBounds(169, 27, 149, 20);
		panelRegistrarse.add(dni_reg);
		dni_reg.setColumns(10);
		 
		nombre_reg = new JTextField();
		nombre_reg.setBounds(169, 58, 149, 20);
		panelRegistrarse.add(nombre_reg);
		nombre_reg.setColumns(10);
		
		apell1_reg = new JTextField();
		apell1_reg.setBounds(169, 89, 149, 20);
		panelRegistrarse.add(apell1_reg);
		apell1_reg.setColumns(10);
		
		apell2_reg = new JTextField();
		apell2_reg.setBounds(169, 120, 149, 20);
		panelRegistrarse.add(apell2_reg);
		apell2_reg.setColumns(10);
		
		sexoCB = new JComboBox<String>();
		sexoCB.setModel(new DefaultComboBoxModel<String>(new String[] {"H", "M"}));
		sexoCB.setBounds(169, 157, 64, 22);
		panelRegistrarse.add(sexoCB);
		
		pass_reg = new JPasswordField();
		pass_reg.setBounds(169, 190, 149, 20);
		panelRegistrarse.add(pass_reg);
		
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
		lblNewLabel_5_1.setBounds(49, 224, 184, 14);
		panelRegistrarse.add(lblNewLabel_5_1);
		
		passval_reg = new JPasswordField();
		passval_reg.setBounds(169, 221, 149, 20);
		panelRegistrarse.add(passval_reg);
		
		registrarseBtn = new JButton("Registrarse");
		registrarseBtn.addActionListener(this);
		registrarseBtn.setBounds(174, 277, 119, 40);
		panelRegistrarse.add(registrarseBtn);
		
		atras = new JButton("Atras");
		atras.addActionListener(this);
		atras.setBounds(569, 275, 89, 57);
		contentPane.add(atras);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==atras)
			this.dispose();
		else if (e.getSource()==registrarseBtn) {
			
			
			
			if(metodo.validarDni(dni_reg.getText(), usuarios)) {
				for (int i = 0; i < usuarios.length; i++) {
					if (dni_reg.getText().equals(usuarios[i].getDni()))
						dni_reg.setBackground(Color.GREEN);
				}
		 		
			}
		 	else
				dni_reg.setBackground(Color.RED);
			
			if(metodo.esVacio(nombre_reg.getText()))
				nombre_reg.setBackground(Color.RED);
		 	else
		 		nombre_reg.setBackground(Color.GREEN);
			
			if(metodo.esVacio(apell1_reg.getText()))
				apell1_reg.setBackground(Color.RED);
		 	else
		 		apell1_reg.setBackground(Color.GREEN);
			
			if(metodo.esVacio(apell2_reg.getText()))
				apell2_reg.setBackground(Color.RED);
		 	else
		 		apell2_reg.setBackground(Color.GREEN);
			
			if(!metodo.esVacio(String.valueOf(pass_reg.getPassword())) && String.valueOf(pass_reg.getPassword()).equals(String.valueOf(passval_reg.getPassword()))) {
				pass_reg.setBackground(Color.GREEN);
				passval_reg.setBackground(Color.GREEN);
			}
		 	else {
		 		pass_reg.setBackground(Color.RED);
		 		passval_reg.setBackground(Color.RED);
			}
			if(dni_reg.getBackground() == todoOk && nombre_reg.getBackground() == todoOk 
				&& apell1_reg.getBackground() == todoOk && apell2_reg.getBackground() == todoOk 
				&& pass_reg.getBackground() == todoOk && passval_reg.getBackground() == todoOk) {
				
					usuarios=metodo.registrarUsuario(dni_reg.getText(), nombre_reg.getText(), apell1_reg.getText(), apell2_reg.getText(), sexoCB.getSelectedItem().toString(), String.valueOf(pass_reg.getPassword()));
					limpiarCamposReg();
					tabbedPane.setSelectedIndex(1);
					
					JOptionPane.showMessageDialog(null,
							"Prueba a hacer login.",
							"Registrado correctamente",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,
					"Rellena los campos con la informacion correcta.",
					"Error",
				JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}
			
	}
	
	public void limpiarCamposReg() {
		dni_reg.setText("");
		nombre_reg.setText("");
		apell1_reg.setText("");
		apell2_reg.setText("");
		pass_reg.setText("");
		passval_reg.setText("");
	}
	
}
