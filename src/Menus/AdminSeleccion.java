package Menus;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class AdminSeleccion extends JFrame {

	private JPanel contentPane;

	public AdminSeleccion(Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Administrar Alumnos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaAlumnos va= new VistaAlumnos();
				va.setVisible(true);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(143, 10, 250, 140);
		contentPane.add(btnNewButton);
		
		JButton btnProfesor = new JButton("Administrar Profesores");
		btnProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminProfesor prof=new AdminProfesor(conn);
				dispose();
			}
		});
		btnProfesor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProfesor.setBounds(143, 173, 250, 140);
		contentPane.add(btnProfesor);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InicioSesion();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(143, 497, 250, 63);
		contentPane.add(btnNewButton_1);
		
		JButton btnAdministrarAsignaturas = new JButton("Administrar Asignaturas");
		btnAdministrarAsignaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdministrarAsignaturas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdministrarAsignaturas.setBounds(143, 335, 250, 140);
		contentPane.add(btnAdministrarAsignaturas);
		setVisible(true);
	}
}
