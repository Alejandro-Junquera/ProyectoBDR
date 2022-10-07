package Menus;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import Funciones.Profesor;
import Funciones.OperacionesBD;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminProfesor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static DefaultTableModel tablemodel;
	private static  ArrayList<Profesor> profesores=new ArrayList<>();

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
	public AdminProfesor(Connection conn) {
		profesores=OperacionesBD.ExtraccionTablaProfesor(conn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
				//
			}
		});
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actualizarGrafico();
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(69, 547, 121, 40);
		contentPane.add(btnNewButton);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(231, 547, 121, 40);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(400, 547, 121, 40);
		contentPane.add(btnEliminar);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(567, 547, 121, 40);
		contentPane.add(btnMostrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new InicioSesion();
			}
		});
		btnVolver.setBounds(736, 547, 121, 40);
		contentPane.add(btnVolver);
		setVisible(true);
	}
}
