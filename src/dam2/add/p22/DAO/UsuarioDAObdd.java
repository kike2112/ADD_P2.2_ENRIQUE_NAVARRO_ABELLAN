package dam2.add.p22.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dam2.add.p22.modelo.Usuario;
import dam2.add.p22.servicio.Propiedades;

public final class UsuarioDAObdd implements IUsuarioDAO {
	
	private static Connection conexion;

	@Override
	public ArrayList<Usuario> getListaUsuarios() {
		conexion = Propiedades.getConexion();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String query = "SELECT * FROM usuario";
		Propiedades.imprimeLog("i", "Consultar registros: SELECT * FROM usuario");
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			// Columnas de la tabla: nombre, apellidos, telefono,...
			while (rs.next()) {
				//listaUsuarios.add(new Usuario(String.valueOf(rs.getInt("id")), rs.getString("nombre"), rs.getString("apellido"), rs.getString("apellido2"), rs.getString("email"), rs.getString("telefono"), rs.getString("pass"), rs.getBoolean("rolAdmin"), rs.getInt("intentos"), rs.getString("idioma")));
				
				int id_us = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String apellido2 = rs.getString(4);
				String email = rs.getString(5);
				String telefono = rs.getString(6);
				String pass = rs.getString(7);
				int intentos = rs.getInt(8);
				boolean rolAdmin = rs.getBoolean(9);
				String idioma = rs.getString(10);
				
				listaUsuarios.add(new Usuario(String.valueOf(id_us), nombre, apellido, apellido2, email, telefono, pass, rolAdmin, intentos, idioma));
			}
			rs.close();
			ps.close();
			Propiedades.desconectar();
		} catch (SQLException e) {
			//e.printStackTrace();
			Propiedades.imprimeLog("f", "SQLException");
		}
		return listaUsuarios;
	}

	@Override
	public Usuario getUsuarioById(String id) {
		conexion = Propiedades.getConexion();
		Usuario usuario = null;
		String query = "SELECT * FROM usuario WHERE id like ?";
		System.out.println("Consultar registros: " + query + "=" + id);
		Propiedades.imprimeLog("i", "Consultar registros: SELECT * FROM usuario WHERE id");
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			
			ResultSet rs = ps.executeQuery();
			// Columnas de la tabla: nombre, apellidos, telefono,...
			while (rs.next()) {
				//usuario = new Usuario(String.valueOf(rs.getInt("id")), rs.getString("nombre"), rs.getString("apellido"), rs.getString("apellido2"), rs.getString("email"), rs.getString("telefono"), rs.getString("pass"), rs.getBoolean("rolAdmin"), rs.getInt("intentos"), rs.getString("idioma"));
				
				int id_us = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String apellido2 = rs.getString(4);
				String email = rs.getString(5);
				String telefono = rs.getString(6);
				String pass = rs.getString(7);
				int intentos = rs.getInt(8);
				boolean rolAdmin = rs.getBoolean(9);
				String idioma = rs.getString(10);
				
				usuario = new Usuario(String.valueOf(id_us), nombre, apellido, apellido2, email, telefono, pass, rolAdmin, intentos, idioma);
			}
			rs.close();
			ps.close();
			Propiedades.desconectar();
		} catch (SQLException e) {
			//e.printStackTrace();
			Propiedades.imprimeLog("f", "SQLException");
		}
		return usuario;	
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		conexion = Propiedades.getConexion();
		Usuario usuario = null;
		String query = "SELECT * FROM usuario WHERE email like ?";
		Propiedades.imprimeLog("i", "Consultar registros: SELECT * FROM usuario WHERE email");
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			// Columnas de la tabla: nombre, apellidos, telefono,...
			while (rs.next()) {
				//usuario = new Usuario(String.valueOf(rs.getInt("id")), rs.getString("nombre"), rs.getString("apellido"), rs.getString("apellido2"), rs.getString("email"), rs.getString("telefono"), rs.getString("pass"), rs.getBoolean("rolAdmin"), rs.getInt("intentos"), rs.getString("idioma"));
				
				int id_us = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String apellido2 = rs.getString(4);
				//String email = rs.getString(5);
				String telefono = rs.getString(6);
				String pass = rs.getString(7);
				int intentos = rs.getInt(8);
				boolean rolAdmin = rs.getBoolean(9);
				String idioma = rs.getString(10);
				
				usuario = new Usuario(String.valueOf(id_us), nombre, apellido, apellido2, email, telefono, pass, rolAdmin, intentos, idioma);
			}
			rs.close();
			ps.close();
			Propiedades.desconectar();
		} catch (SQLException e) {
			//e.printStackTrace();
			Propiedades.imprimeLog("f", "SQLException");
		}
		return usuario;
	}

	@Override
	public boolean crearCliente(Usuario usuario) {
		boolean exito = false;
		conexion = Propiedades.getConexion();
		String query = "INSERT INTO usuario (nombre, apellido, apellido2, email, telefono, pass, intentos, rolAdmin, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Propiedades.imprimeLog("i", "Insertar registro: INSERT INTO usuario...");
		Propiedades.imprimeLog("i", "INSERT INTO usuario (nombre, apellido, apellido2, email, telefono, pass, intentos, rolAdmin, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(query);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setString(3, usuario.getApellido2());
			ps.setString(4, usuario.getEmail());
			ps.setString(5, usuario.getTelefono());
			ps.setString(6, usuario.getPass());
			ps.setInt(7, 5); //intentos
			ps.setBoolean(8, usuario.isRolAdmin());
			ps.setString(9, usuario.getIdioma());

			if (ps.executeUpdate() == 0) {
				Propiedades.imprimeLog("e", "NO se ha podido insertar");
			}
			conexion.commit();
			System.out.println(query.substring(0, 108) + "(" + usuario.getNombre() + ", " + usuario.getApellido() + ", " + usuario.getApellido2() + ", " + usuario.getEmail() + ", " + usuario.getTelefono() + ", " + "***, 5, " + usuario.isRolAdmin() + ", " + usuario.getIdioma() + ")");
			ps.close();
			Propiedades.desconectar();
			exito = true;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e);
			Propiedades.imprimeLog("f", "");
		}
		return exito;
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		boolean exito = false;
		conexion = Propiedades.getConexion();
		String query = "UPDATE usuario SET nombre=?, apellido=?, apellido2=?, email=?, telefono=?, pass=?, intentos=?, idioma=? WHERE id=?";
		Propiedades.imprimeLog("i", "Actualizar registro: UPDATE usuario...");
		Propiedades.imprimeLog("i", "UPDATE usuario SET nombre=?, apellido=?, apellido2=?, email=?, telefono=?, pass=?, intentos=?, idioma=? WHERE id=?");
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(query);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setString(3, usuario.getApellido2());
			ps.setString(4, usuario.getEmail());
			ps.setString(5, usuario.getTelefono());
			ps.setString(6, usuario.getPass());
			ps.setInt(7, usuario.getIntentos());
			ps.setString(8, usuario.getIdioma());
			ps.setString(9, usuario.getId_us());
			
			if (ps.executeUpdate() == 0) {
				Propiedades.imprimeLog("e", "NO se ha podido actualizar");
			}
			conexion.commit();
			System.out.println("UPDATE usuario SET .... WHERE id=" + usuario.getId_us());
			ps.close(); //??? no se pone en apuntes
			Propiedades.desconectar();
			exito = true;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e);
			Propiedades.imprimeLog("f", "SQLException");
		}	
		return exito;
	}

	@Override
	public boolean borrarUsuario(String id_us) {
		boolean exito = false;
		conexion = Propiedades.getConexion();
		String query = "DELETE FROM usuario WHERE id=?";
		System.out.println("Borrar registro: " + query + "=" + id_us);
		Propiedades.imprimeLog("w", "Borrar registro: DELETE FROM usuario WHERE id=?");
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id_us));
			
			if (ps.executeUpdate() == 0) {
				Propiedades.imprimeLog("w", "NO se ha podido borrar");
			}		
			conexion.commit();
			ps.close(); //??? no se pone en apuntes
			Propiedades.desconectar();
			exito = true;
		} catch (SQLException e) {
			e.printStackTrace();
			Propiedades.imprimeLog("f", "SQLException");
		}
		return exito;
	}
}