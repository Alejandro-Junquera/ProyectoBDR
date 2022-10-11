package Menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Funciones.Alumno;
import Funciones.OperacionesBD;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AlumnoInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private String dni;
	private String nombre;
	private String apellidos;
	private String fechaNac;
	private String tlf;
	private JTextField txtTusDatos;

	public AlumnoInfo(Connection conn, String dniAlumno) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();

		try {
			model= new DefaultTableModel() {
				
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int x,int y) {
					return false;	
				}
			};
			String[] columnas = { "DNI", "Nombre", "Apellidos", "Fecha de nacimiento", "Telefono"};
			model.setColumnIdentifiers(columnas);
			
			String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono from alumno where dni=?;";
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, dniAlumno);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = String.valueOf(rs.getString("apellidos"));
				fechaNac = String.valueOf(rs.getString("fecha_nacimiento"));
				tlf = String.valueOf(rs.getString("telefono"));
			}
			Object[] datos = {dni, nombre, apellidos, fechaNac, tlf};
			model.addRow(datos);
			
		} catch (Exception ex) {
		}
		contentPane.add(panel1, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("Cerrar sesion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InicioSesion();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(143, 497, 250, 63);
		contentPane.add(btnNewButton_1);
		table = new JTable();
		table.setEnabled(true);
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(36, 74, 452, 39);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Inserta imagen");
		lblNewLabel.setBounds(394, 196, 94, 110);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(391, 319, 97, 25);
		contentPane.add(btnNewButton);
		
		txtTusDatos = new JTextField();
		txtTusDatos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtTusDatos.setText("Tus datos");
		txtTusDatos.setBounds(192, 38, 117, 29);
		contentPane.add(txtTusDatos);
		txtTusDatos.setColumns(10); 
		txtTusDatos.setEditable(false);
		setVisible(true);
	}
}
