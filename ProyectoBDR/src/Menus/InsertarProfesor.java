package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import Funciones.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InsertarProfesor extends JFrame {

	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApell;
	private JTextField textEmail;
	private JTextField textContr;
	private static ArrayList<Asignatura> a;
	private String relativa;
	private JTable tableAsig;
	private JTable tableAsigEli;
	private static DefaultTableModel tablemodel;
	private DefaultTableModel tablemodel2;

	
	public static void actualizarGrafico() {
		tablemodel.setRowCount(0);
		try {
			for (int i = 0; i < a.size(); i++) {
				String asignatura=a.get(i).getNombre();

				Object[] data = { asignatura };
				tablemodel.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {
		}

	}
	public InsertarProfesor(Connection conn) {
		a=OperacionesBD.ExtraccionAsignaturas(conn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImg = new JLabel("Insertar Imagen");
		lblImg.setBounds(659, 38, 232, 210);
		contentPane.add(lblImg);
		
		JButton btnImg = new JButton("Añadir Imagen");
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relativa=insertarImagenes.generarRutaImg(relativa,lblImg);
			}
		});
		btnImg.setBounds(659, 262, 222, 35);
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
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacionesBD.insertarProfesor(textDNI.getText(),textNombre.getText(),textApell.getText(),textEmail.getText(),textContr.getText(),relativa,"patata",conn);
				new AdminProfesor(conn);
				dispose();
			}
		});
		btnAadir.setBounds(258, 663, 121, 40);
		contentPane.add(btnAadir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminProfesor(conn);
				dispose();
			}
		});
		btnVolver.setBounds(600, 663, 121, 40);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane_AsigDisp = new JScrollPane();
		scrollPane_AsigDisp.setBounds(424, 351, 277, 195);
		contentPane.add(scrollPane_AsigDisp);
		
		String[] columnas = new String[] { "Asignaturas disponibles" };
		tablemodel = new DefaultTableModel(columnas, 0);
		tableAsig = new JTable(tablemodel);
		scrollPane_AsigDisp.setViewportView(tableAsig);
		actualizarGrafico();
		
		JScrollPane scrollPane_AsigElim = new JScrollPane();
		scrollPane_AsigElim.setBounds(711, 351, 290, 195);
		contentPane.add(scrollPane_AsigElim);
		
		String[] columnas2 = new String[] { "Asignaturas elegidas" };
		tablemodel2 = new DefaultTableModel(columnas2, 0);
		tableAsigEli = new JTable(tablemodel2);
		scrollPane_AsigElim.setViewportView(tableAsigEli);
		setVisible(true);
	}
}
