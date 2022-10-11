package Menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Conexiones.Conexion;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import Funciones.ComprobarUsuario;

public class InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textContrasenia;
	private Conexion connbd;
	private Connection conn;

	public static void main(String[] args) {
		try {
			InicioSesion frame = new InicioSesion();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			}	
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	public InicioSesion() {
		connbd=new Conexion();
		conn=connbd.conectarMySQL();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("DNI");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(70, 178, 109, 25);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setForeground(new Color(0, 0, 0));
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenia.setBounds(70, 291, 109, 25);
		contentPane.add(lblContrasenia);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(189, 173, 253, 40);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(189, 286, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		JButton btnIniSesion = new JButton("Iniciar Sesión");
		btnIniSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ComprobarUsuario.comprobarAdmin(textUsuario.getText(),textContrasenia.getText())){
					//Instancia de la clase administrador
					AdminSeleccion a=new AdminSeleccion(conn);
					setVisible(false);
				}else if(ComprobarUsuario.comprobarAlumno(textUsuario.getText(),textContrasenia.getText(), conn)) {
					//Instancia de alumno
					AlumnoInfo al = new AlumnoInfo(conn, textUsuario.getText());
					setVisible(false);
				}else if(ComprobarUsuario.comprobarProfesor(textUsuario.getText(),textContrasenia.getText(), conn)) {
					//Instancia de profesor
				}else {
					System.out.println("Usuario o contraseña erroneo");
				}
			}
		});
		btnIniSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniSesion.setBounds(94, 501, 120, 40);
		contentPane.add(btnIniSesion);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 536, 613);
		lblFondo.setIcon(new ImageIcon("Imagenes/Iconos/fondo.jpg"));
		lblFondo.setIcon(new ImageIcon("Imagenes/Iconos/salesianos.jpg"));
		contentPane.add(lblFondo);
		setVisible(true);
	}
}
