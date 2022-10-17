package Menus;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import Funciones.Profesor;
import Funciones.Asignatura;
import Funciones.OperacionesBD;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

public class AdminProfesor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel tablemodel;
	private static  ArrayList<Profesor> profesores=new ArrayList<>();
	private int filaSelecionada;
	private ArrayList<Asignatura> asignaturas;
	private JButton btnInsertar;

	public static void actualizarGrafico() {
		tablemodel.setRowCount(0);
		try {
			for (int i = 0; i < profesores.size(); i++) {
				String dni = profesores.get(i).getDNI();
				String nom = profesores.get(i).getNombre();
				String apell = profesores.get(i).getApellidos();
				String email = profesores.get(i).getEmail();
				String img = profesores.get(i).getImg();

				Object[] data = { dni, nom, apell, email, img };
				tablemodel.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {
		}

	}
	public void hayAsignatura() {
		if(asignaturas.isEmpty()) {
			btnInsertar.setEnabled(false);
		}else {
			btnInsertar.setEnabled(true);
		}
	}
	public AdminProfesor(Connection conn) {
		profesores=OperacionesBD.ExtraccionTablaProfesor(conn);
		asignaturas=OperacionesBD.ExtraccionAsignaturas(conn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 48, 862, 387);
		contentPane.add(scrollPane);
		
		String[] columnas = new String[] { "DNI", "Nombre", "Apellidos", "Email", "Imagen" };
		tablemodel = new DefaultTableModel(columnas, 0);
		table = new JTable(tablemodel);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSelecionada = table.rowAtPoint(evt.getPoint());
				
			}
		});
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actualizarGrafico();
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarProfesor i=new InsertarProfesor(conn);
				dispose();
			}
		});
		btnInsertar.setBounds(69, 547, 121, 40);
		contentPane.add(btnInsertar);
		hayAsignatura();
		
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarProfesor ac=new ActualizarProfesor(profesores.get(filaSelecionada).getDNI(),profesores.get(filaSelecionada).getNombre(),profesores.get(filaSelecionada).getApellidos(),profesores.get(filaSelecionada).getEmail(),profesores.get(filaSelecionada).getClave(),profesores.get(filaSelecionada).getImg(),"Asignatura",conn);
				dispose();
			}
		});
		btnActualizar.setBounds(231, 547, 121, 40);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op=JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar al profesor?");
				if(op==0) {
				File eliminar=new File(profesores.get(filaSelecionada).getImg());
				OperacionesBD.borrarDNIProfAsignatura(profesores.get(filaSelecionada).getDNI(), conn);
				OperacionesBD.borrarProfesor(profesores.get(filaSelecionada).getDNI(), conn);
				profesores=OperacionesBD.ExtraccionTablaProfesor(conn);
				try {
					if(!profesores.get(filaSelecionada).getImg().equals("\\Imagenes\\Fotos\\defecto.png")) {
						Files.delete(Paths.get(eliminar.getPath()));
					}
					
				} catch (IOException e1) {
					
				}catch(java.lang.IndexOutOfBoundsException e2) {
					
				}
				actualizarGrafico();
				}
				
			}
		});
		btnEliminar.setBounds(400, 547, 121, 40);
		contentPane.add(btnEliminar);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(567, 547, 121, 40);
		contentPane.add(btnMostrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AdminSeleccion(conn);
			}
		});
		btnVolver.setBounds(736, 547, 121, 40);
		contentPane.add(btnVolver);
		setVisible(true);
		if(asignaturas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se puede insertar profesor, no hay asignaturas libres");
		}
	}
}
