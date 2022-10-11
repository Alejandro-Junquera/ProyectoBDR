package Menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Conexiones.Conexion;
import Funciones.Alumno;
import Funciones.OperacionesBD;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VistaAlumnos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaAlumnos;
	private DefaultTableModel model;
	private JPanel panel;
	private JButton btnVolverAdmin;
	private JLabel lblTitulo;
	private Object[] fila;
	private ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
	private JButton btnInsertarAlumno;
	private JButton btnActualizarAlumno;
	private JButton btnEliminarAlumno;
	private JButton btnMostrarNotas;
	private JScrollPane scrollPane;
	
	
	public VistaAlumnos() {
		setBounds(100, 100, 769, 536);
		Conexion conn = new Conexion();
		alumnos=OperacionesBD.ExtraccionTablaAlumno(conn.conectarMySQL());
		componentes();
	}

	private void llenarTabla() {
		/*
		
		for (Alumno alum:alumnos) {
			model.setRowCount(0);
			try {
				String dni=alum.getDni();
				String nom=alum.getNombre();
				String apell=alum.getApellidos();
				String fech=alum.getfNacimiento();
				String tel=alum.getTelefono();
				String fot=alum.getFoto();
				String pass=alum.getClave();
				Object[] fila = { dni, nom, apell, fech, tel, fot, pass };
				model.addRow(fila);
			}
			catch(NullPointerException te) {	
			}
		
		}
		 
		*/
		for (Alumno alum:alumnos) {
			this.fila = new Object[7];
			try {
				System.out.println(alum.getNombre());
				fila[0]=alum.getDNI();
				fila[1]=alum.getNombre();
				fila[2]=alum.getApellidos();
				fila[3]=alum.getFechaNac();
				fila[4]=alum.getTlf();
				fila[5]=alum.getImg();
				fila[6]=alum.getClave();
			}
			catch(NullPointerException te) {	
			}
		model.addRow(fila);
		}
		
	}

	private void componentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 755, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnVolverAdmin = new JButton("Volver");
		btnVolverAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolverAdmin.setBounds(48, 30, 85, 21);
		panel.add(btnVolverAdmin);
		
		lblTitulo = new JLabel("Administrar alumnos");
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTitulo.setBounds(261, 53, 178, 48);
		panel.add(lblTitulo);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 253, 576, -117);
		
		panel.add(scrollPane);
		
		tablaAlumnos = new JTable();

		model= new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int x,int y) {
				return false;	
			}
		};
		/*String[] columnas = new String[] { "Dni","Nombre","Apellidos","Fecha de nacimiento","Telefono","Contraseña","Imagen" };
		model = new DefaultTableModel(columnas, 0);
		tablaAlumnos = new JTable(model);
		scrollPane.setViewportView(tablaAlumnos);
		tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llenarTabla();*/
		
		Object[]columna={"Dni","Nombre","Apellidos","Fecha de nacimiento","Telefono","Contraseña","Imagen"};
		model.setColumnIdentifiers(columna);
		tablaAlumnos.setModel(model);
		scrollPane.setViewportView(tablaAlumnos);
		tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llenarTabla();
		
		btnInsertarAlumno = new JButton("Insertar");
		btnInsertarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarAlumno ia = new InsertarAlumno();
				ia.setVisible(true);
			}
		});
		btnInsertarAlumno.setBounds(66, 352, 121, 40);
		panel.add(btnInsertarAlumno);
		
		btnActualizarAlumno = new JButton("Actualizar");
		btnActualizarAlumno.setBounds(228, 352, 121, 40);
		panel.add(btnActualizarAlumno);
		
		btnEliminarAlumno = new JButton("Eliminar");
		btnEliminarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion conn = new Conexion();
				OperacionesBD.BorrarAlumno(tablaAlumnos.getSelectedRow(), conn.conectarMySQL());
			}
		});
		btnEliminarAlumno.setBounds(397, 352, 121, 40);
		panel.add(btnEliminarAlumno);
		
		btnMostrarNotas = new JButton("Mostrar notas");
		btnMostrarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMostrarNotas.setBounds(564, 352, 121, 40);
		panel.add(btnMostrarNotas);
		
	}
}
