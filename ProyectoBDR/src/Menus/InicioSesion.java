package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
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
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(70, 168, 109, 25);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenia.setBounds(70, 291, 109, 25);
		contentPane.add(lblContrasenia);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(189, 163, 253, 40);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(189, 286, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		JButton btnIniSesion = new JButton("Iniciar Sesión");
		btnIniSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniSesion.setBounds(94, 501, 120, 40);
		contentPane.add(btnIniSesion);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(322, 501, 120, 40);
		contentPane.add(btnSalir);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro r=new Registro();
			}
		});
		btnRegistro.setBounds(335, 350, 107, 21);
		contentPane.add(btnRegistro);
	}
}
