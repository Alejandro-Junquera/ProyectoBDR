package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexiones.Conexion;

import com.mysql.cj.xdevapi.PreparableStatement;

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
			statement.close();
			return profesores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void insertarProfesor(String dni,String nombre,String apellidos,String email,String contrasenia,String img,String asignatura,Connection conn) {
		String sql="INSERT INTO profesor VALUES (?,?,?,?,?,?);";
		String sql2="UPDATE asignatura set dni_pro=? where nombre=?";
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			PreparedStatement statement2=conn.prepareStatement(sql2);
			statement.setString(1,dni);
			statement.setString(2,nombre);
			statement.setString(3,apellidos);
			statement.setString(4,email);
			statement.setString(5,contrasenia);
			statement.setString(6,img);
			statement2.setString(1,dni);
			statement2.setString(2,asignatura);
			int rs=statement.executeUpdate();
			int rs2=statement2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<String> ExtraccionAsignaturas(Connection conn){
		String sql="select nombre from asignatura where dni_pro is null;";
		ArrayList<String> asignaturas=new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(rs.getString("nombre"));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	public static void borrarDNIProfAsignatura(String dni,Connection conn) {
		String sql="update asignatura set dni_pro=? where dni_pro=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,null);
			statement.setString(2,dni);
			int rs=statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void borrarProfesor(String dni,Connection conn) {
		String sql="delete from profesor where dni=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,dni);
			int rs=statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
