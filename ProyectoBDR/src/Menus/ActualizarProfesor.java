package Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Funciones.OperacionesBD;
import Funciones.insertarImagenes;

public class ActualizarProfesor extends JFrame {

	private JPanel contentPane;
	private String relativa;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApell;
	private JTextField textEmail;
	private JTextField textContr;
	private ArrayList<String> a;
	private String[] asignaturas;

	public ActualizarProfesor(String dni, String nombre, String apellidos, String email, String clave,String img,String asignatura,Connection conn) {
		//a=OperacionesBD.ExtraccionAsignaturas(conn);
		asignaturas=new String[a.size()];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImg = new JLabel("Insertar Imagen");
		lblImg.setBounds(476, 30, 175, 210);
		contentPane.add(lblImg);

		JButton btnImg = new JButton("Añadir Imagen");
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relativa = insertarImagenes.generarRutaImg(relativa, lblImg);
			}
		});
		btnImg.setBounds(476, 271, 175, 35);
		contentPane.add(btnImg);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setForeground(new Color(64, 0, 64));
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(31, 129, 109, 25);
		contentPane.add(lblDNI);

		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(138, 126, 253, 40);
		contentPane.add(textDNI);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(64, 0, 64));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(31, 229, 109, 25);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(138, 229, 253, 40);
		contentPane.add(textNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(new Color(64, 0, 64));
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(31, 331, 109, 25);
		contentPane.add(lblApellidos);

		textApell = new JTextField();
		textApell.setColumns(10);
		textApell.setBounds(138, 331, 253, 40);
		contentPane.add(textApell);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(64, 0, 64));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(31, 433, 109, 25);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(138, 433, 253, 40);
		contentPane.add(textEmail);

		JLabel lblContr = new JLabel("Contraseña");
		lblContr.setForeground(new Color(64, 0, 64));
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContr.setBounds(31, 536, 109, 25);
		contentPane.add(lblContr);

		textContr = new JTextField();
		textContr.setColumns(10);
		textContr.setBounds(138, 524, 253, 40);
		contentPane.add(textContr);

		JLabel lblAsignatura = new JLabel("Asignatura");
		lblAsignatura.setForeground(new Color(64, 0, 64));
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAsignatura.setBounds(524, 372, 109, 25);
		contentPane.add(lblAsignatura);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(asignaturas));
		comboBox.setSelectedItem(asignatura);
		comboBox.setBounds(476, 437, 157, 21);
		contentPane.add(comboBox);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnActualizar.setBounds(163, 622, 121, 40);
		contentPane.add(btnActualizar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminProfesor(conn);
				dispose();
			}
		});
		btnVolver.setBounds(420, 622, 121, 40);
		contentPane.add(btnVolver);
		textDNI.setText(dni);
		textNombre.setText(nombre);
		textApell.setText(apellidos);
		textEmail.setText(email);
		textContr.setText(clave);
		lblImg.setIcon(insertarImagenes.ResizableImage(img, lblImg));
		setVisible(true);
	}
}
