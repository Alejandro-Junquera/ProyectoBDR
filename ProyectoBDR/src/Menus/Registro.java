package Menus;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textContrasenia;
	private JLabel lblConfirmarContrasea;
	private JTextField textField;
	private JLabel lblCurso;
	private JTextField textDNI;


	/**
	 * Create the frame.
	 */
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(70, 73, 109, 25);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenia.setBounds(70, 165, 109, 25);
		contentPane.add(lblContrasenia);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(217, 68, 253, 40);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(217, 160, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		lblConfirmarContrasea = new JLabel("Confirmar \r\nContraseña");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmarContrasea.setBounds(70, 265, 137, 25);
		contentPane.add(lblConfirmarContrasea);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(217, 260, 253, 40);
		contentPane.add(textField);
		
		lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurso.setBounds(402, 362, 44, 25);
		contentPane.add(lblCurso);
		
		JRadioButton rdbtn1DAM = new JRadioButton("1ºDAM");
		rdbtn1DAM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtn1DAM.setBounds(402, 401, 103, 21);
		contentPane.add(rdbtn1DAM);
		
		JRadioButton rdbtn2DAM = new JRadioButton("2ºDAm");
		rdbtn2DAM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtn2DAM.setBounds(402, 444, 103, 21);
		contentPane.add(rdbtn2DAM);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(70, 362, 34, 25);
		contentPane.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(120, 362, 253, 40);
		contentPane.add(textDNI);
		
		ButtonGroup cursos = new ButtonGroup();
		cursos.add(rdbtn1DAM);
		cursos.add(rdbtn2DAM);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrarse.setBounds(87, 506, 120, 40);
		contentPane.add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(326, 506, 120, 40);
		contentPane.add(btnVolver);
		setVisible(true);
	}
}
