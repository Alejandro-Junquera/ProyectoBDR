package Funciones;

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
			statement.close();
			return profesores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void insertarProfesor(String dni,String nombre,String apellidos,String email,String contrasenia,String img,ArrayList<Asignatura> asignaturas,Connection conn) {
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
			int rs=statement.executeUpdate();
			for(int i=0;i<asignaturas.size();i++) {
				statement2.setString(1,dni);
				statement2.setString(2,asignaturas.get(i).getNombre());
				int rs2=statement2.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<Asignatura> ExtraccionAsignaturas(Connection conn){
		String sql="select * from asignatura where dni_pro is null;";
		ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	public static ArrayList<Asignatura> ExtraccionAsignaturasProf(String dni,Connection conn){
		String sql="select * from asignatura where dni_pro=?;";
		ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,dni);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	public static ArrayList<Asignatura> ExtraccionTodasAsignaturas(Connection conn){
		String sql="select * from asignatura;";
		ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	
	public ArrayList<Asignatura> extraccionAsignaturasAlumno(Connection conn, String dni) {
		ArrayList<Asignatura> res=new ArrayList<Asignatura>();
		String sql="select nombre from asignatura where id=(select id_asi from matricula where dni_alu=?);";
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,dni);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				res.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),
						rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
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
	public static void actualizarProfesor(String dni,String nombre,String apellidos,String email,String contrasenia,String img,ArrayList<Asignatura> asigLibres,ArrayList<Asignatura> asigProp,Connection conn) {
		String sql="update asignatura set dni_pro=? where dni_pro=?;";
		String sql2="update profesor set nombre=?,apellidos=?,email=?,clave=?,img=? where dni=?";
		String sql3="update asignatura set dni_pro=? where nombre=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			PreparedStatement statement2=conn.prepareStatement(sql2);
			PreparedStatement statement3=conn.prepareStatement(sql3);
			for(int i=0;i<asigLibres.size();i++) {
				statement.setString(1,null);
				statement.setString(2,dni);
				int rs=statement.executeUpdate();
			}
			statement2.setString(1,nombre);
			statement2.setString(2,apellidos);
			statement2.setString(3,email);
			statement2.setString(4,contrasenia);
			statement2.setString(5,img);
			statement2.setString(6,dni);
			int rs2=statement2.executeUpdate();
			for(int i=0;i<asigProp.size();i++) {
				statement3.setString(1,dni);
				statement3.setString(2,asigProp.get(i).getNombre());
				int rs3=statement3.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static ArrayList<Alumno> ExtraccionTablaAlumno(Connection conn) {
		String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono,clave,img from alumno;";
		ArrayList<Alumno> alumnos=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("fecha_nacimiento"),rs.getInt("telefono"),rs.getString("clave"),rs.getString("img")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
		
	}
	public static Alumno insertarAlumno(String dni, String nombre, String apellidos,String fNacimiento , int telefono,String clave, String foto, Connection conn){
        PreparedStatement ps;
        String sql;
        Alumno alum = new Alumno();
        alum.setDNI(dni);
        alum.setNombre(nombre);
        alum.setApellidos(apellidos);
        alum.setFechaNac(fNacimiento);
        alum.setTlf(telefono);
        alum.setClave(clave);
        alum.setImg(foto);
        try{
            sql = "insert into alumno(dni, nombre, apellidos, fecha_nacimiento, telefono, clave, img) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, alum.getDNI());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellidos());
            ps.setString(4, alum.getFechaNac());
            ps.setInt(5, alum.getTlf());
            ps.setString(6, alum.getClave());
            ps.setString(7, alum.getImg());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
		return alum;
    }
	
	public static void BorrarAlumno(String dni,Connection conn){

		String consulta= "delete from alumno where dni = ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(consulta);
			statement.setString(1, dni);
			int rs=statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han eliminado el alumno correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getErrorCode());
			;
		}

	}

}
