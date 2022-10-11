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
				profesores.add(new Profesor(rs.getString("dni"),rs.getString("nombre"),
						rs.getString("apellidos"),rs.getString("clave"),
						rs.getString("img"),rs.getString("email")));
			}
			return profesores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static ArrayList<Alumno> ExtraccionTablaAlumno(Connection conn) {
		String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono,clave,img from alumno;";
		ArrayList<Alumno> alumnos=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"),rs.getString("nombre"),
						rs.getString("apellidos"),rs.getString("clave"),
						rs.getString("img"),rs.getString("fechaNac"),rs.getInt("tlf")));
			}
			return alumnos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
