package Funciones;

import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OperacionesBD {

	public static ArrayList<Profesor> ExtraccionTablaProfesor(Connection conn) {
		String sql="select dni,nombre,apellidos,clave,email,img from profesor;";
		ArrayList<Profesor> profesores=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				profesores.add(new Profesor(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("clave"),rs.getString("img"),rs.getString("email")));
			}
			return profesores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static ArrayList<Alumno> ExtraccionTablaAlumno(Connection conn) {
		String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono,clave,foto from alumno;";
		ArrayList<Alumno> alumnos=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("fecha_nacimiento"),rs.getString("clave"),rs.getString("foto"),rs.getInt("telefono")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
		
	}
	public static Alumno insertarAlumno(String dni, String nombre, String apellidos, int telefono,String fNacimiento, String foto, String clave, Connection conn){
        PreparedStatement ps;
        String sql;
        Alumno alum = new Alumno();
        alum.setDNI(dni);
        alum.setNombre(nombre);
        alum.setApellidos(apellidos);
        alum.setTlf(telefono);
        alum.setFechaNac(fNacimiento);
        alum.setClave(clave);
        alum.setImg(foto);
        try{
            sql = "insert into alumno(dni, nombre, apellidos, fecha_nacimiento, telefono, clave, foto) values(?,?,?,?,?,?,?)";
            System.out.println(conn.getClientInfo());
            ps = conn.prepareStatement(sql);
            ps.setString(1, alum.getDNI());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellidos());
            ps.setInt(5, alum.getTlf());
            ps.setString(4, alum.getFechaNac());
            ps.setString(6, alum.getClave());
            ps.setString(7, alum.getImg());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
		return alum;
    }
	
	public static String OperacionesBD(int dni,Connection conn){
		PreparedStatement ps;
		String consulta= "delete from alumno where dni = ?";
		try {
			ps = conn.prepareStatement(consulta);
			ps.setInt(1, dni);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han eliminado el alumno correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getErrorCode());
			;
		}
		return consulta;
	}

}
