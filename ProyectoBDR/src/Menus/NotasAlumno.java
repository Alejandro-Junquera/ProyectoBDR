package Menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Funciones.RA;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NotasAlumno extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public NotasAlumno(int idAsig,String nombreAsig, String dni_alu,String nombreAlu, ArrayList<RA> rasAsig) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Notas de "+nombreAsig+" de "+nombreAlu);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(70, 64, 456, 43);
		contentPane.add(lblNewLabel);
		setTitle("Notas de "+nombreAsig+" de "+nombreAlu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 145, 412, 316);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
