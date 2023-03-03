package dam2.add.p22.servicio;

import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

import dam2.add.p22.DAO.IUsuarioDAO;
import dam2.add.p22.DAO.UsuarioDAOMemoria;
import dam2.add.p22.DAO.UsuarioDAObdd;
import dam2.add.p22.modelo.Usuario;
import dam2.add.p22.utiles.Utiles;

public final class Agenda {
	
	private static IUsuarioDAO dao;

	public static void iniciaPersistencia() {
		Propiedades.getPersistencia();
		if (dao == null) {
			if (Propiedades.getPersistencia().equals("memoria")) {
				dao = new UsuarioDAOMemoria();
			} else if (Propiedades.getPersistencia().equals("bdd")) {
				dao = new UsuarioDAObdd();
				Propiedades.getConexion();
			}
		}
	}
	public static void cambiaPersistenciaAdmin() {
		if (Propiedades.getPersistencia().equals("bdd")) {
			Propiedades.setPersistenciaAdmin("memoria");
		} else if (Propiedades.getPersistencia().equals("memoria")) {
			Propiedades.setPersistenciaAdmin("bdd");
		}
		if (Propiedades.getPersistencia().equals("memoria")) {
			dao = new UsuarioDAOMemoria();
		} else if (Propiedades.getPersistencia().equals("bdd")) {
			dao = new UsuarioDAObdd();
			Propiedades.getConexion();
		}
		
	}
	public static ArrayList<Usuario> getListaUsuarios() {
		if (dao == null) {
			if (Propiedades.getPersistencia().equals("memoria")) {
				dao = new UsuarioDAOMemoria();
			} else if (Propiedades.getPersistencia().equals("bdd")) {
				dao = new UsuarioDAObdd();
			}
		}
		ArrayList<Usuario> listaUsuarios = dao.getListaUsuarios();	
		return listaUsuarios;
	}
	public static ArrayList<Usuario> getListaUsuariosClientes() {
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		ArrayList<Usuario> listaUsuariosClientes = new ArrayList<Usuario>();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (!listaUsuariosTemp.get(i).isRolAdmin()) {
				listaUsuariosClientes.add(listaUsuariosTemp.get(i));
			}
		}
		return listaUsuariosClientes;
	}
	public static ArrayList<Usuario> cargarClientesBuscados(String busqueda) {
		ArrayList<String> arrayBusqueda = Utiles.separaPalabras(busqueda);
		ArrayList<Usuario> listaClientesBuscados = getListaClientesBuscados(arrayBusqueda);
		return listaClientesBuscados;
	}
	public static ArrayList<Usuario> getListaClientesBuscados(ArrayList<String> arrayBusqueda) {
		ArrayList<Usuario> listaClientesBuscados = new ArrayList<Usuario>();	
		ArrayList<Usuario> listaUsuariosClientes = getListaUsuariosClientes();
		for (int i = 0; i < listaUsuariosClientes.size(); i++) {
			boolean agregado = false;
			for (int k = 0; k < arrayBusqueda.size(); k++) {		
				boolean usada = false;
				for (int j = 0; j <= listaUsuariosClientes.get(i).toString().length() - arrayBusqueda.get(k).length(); j++) {
					//Si coincideno pero no esta agregado el Us ni usada la palabra
					if (!agregado && !usada && listaUsuariosClientes.get(i).toString().substring(j, j + arrayBusqueda.get(k).length()).equalsIgnoreCase(arrayBusqueda.get(k).substring(0, arrayBusqueda.get(k).length()))) {
						listaClientesBuscados.add(listaUsuariosClientes.get(i));
						agregado = true;
						usada = true;
					}
				}	
			}
		}
		return listaClientesBuscados;
	}
	public static void borrarDAO() {
		dao = null;
	}
	public static ArrayList<Usuario> eliminarUsuario(String id_us) {
		dao.borrarUsuario(id_us);
		ArrayList<Usuario> listaUsuarios = getListaUsuariosClientes();
		return listaUsuarios;
	}
	public static Usuario cargarUsuario(String id_us) {
		Usuario usuario = dao.getUsuarioById(id_us);
		return usuario;
	}
	public static Usuario getUsuarioByEmail(String email) {
		Usuario usuario = dao.getUsuarioByEmail(email);
		return usuario;
	}
	public static Usuario registrarUsuario(Usuario usuarioTemp) {
		dao.crearCliente(usuarioTemp);
		Usuario usuario = dao.getUsuarioByEmail(usuarioTemp.getEmail());
		return usuario;
	}
	public static Usuario loguearUsuario(Usuario usuarioTemp) {
		restaurarIntentos(usuarioTemp.getEmail());
		Usuario usuario = dao.getUsuarioByEmail(usuarioTemp.getEmail());
		return usuario;
	}
	public static boolean restaurarIntentos(String email) {
		boolean restaurado = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email)) {
				listaUsuariosTemp.get(i).setIntentos(5);
				dao.actualizarUsuario(listaUsuariosTemp.get(i));
				restaurado = true;
			}
		}
		return restaurado;
	}
	public static boolean restarIntento(String email) {
		boolean restado = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email)) {
				listaUsuariosTemp.get(i).setIntentos(listaUsuariosTemp.get(i).getIntentos() - 1);
				dao.actualizarUsuario(listaUsuariosTemp.get(i));
				restado = true;
			}
		}
		return restado;
	}
	public static boolean actualizarUsuario(Usuario usuario) {
		boolean exito = false;
		Usuario usuarioTemp = dao.getUsuarioById(usuario.getId_us());
		usuarioTemp.setNombre(usuario.getNombre());
		usuarioTemp.setApellido(usuario.getApellido());
		usuarioTemp.setApellido2(usuario.getApellido2());
		usuarioTemp.setEmail(usuario.getEmail());
		usuarioTemp.setTelefono(usuario.getTelefono());
		if (dao.actualizarUsuario(usuarioTemp)) {
			exito = true;
		}
		return exito;
	}
	public static boolean actualizarPassUsuario(Usuario usuario, String pass) {
		boolean exito = false;
		Usuario usuarioTemp = dao.getUsuarioById(usuario.getId_us());
		usuarioTemp.setPass(pass);
		if (dao.actualizarUsuario(usuarioTemp)) {
			exito = true;
		}
		return exito;
	}
	public static boolean setIdiomaUsuario(String idioma, String email) {
		Usuario usuarioTemp = dao.getUsuarioByEmail(email);
		usuarioTemp.setIdioma(idioma);
		dao.actualizarUsuario(usuarioTemp);
		return true;
	}

	
	
	////////////Comprobadores varios///////////////
	public static boolean existeEmailEnAgenda(String email) {
		boolean existe_email = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email)) {
				existe_email = true;
			}
		}
		return existe_email;
	}
	public static boolean existeEmailEnOtroUsuario(Usuario usuario) {
		boolean existe_email = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(usuario.getEmail()) && !listaUsuariosTemp.get(i).getId_us().equalsIgnoreCase(usuario.getId_us())) {
				existe_email = true;				
			}
		}
		return existe_email;
	}
	public static boolean claveCoincide(String email, String passPlano) {
		boolean coincide = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email) && claveEncriptadaCoincide(passPlano, listaUsuariosTemp.get(i).getPass())) {
				coincide = true;
			}
		}
		return coincide;
	}
	public static boolean estaBloqueado(String email) {
		boolean esta_bloqueado = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email) && listaUsuariosTemp.get(i).getIntentos() <= 0) {
				esta_bloqueado = true;
			}
		}
		return esta_bloqueado;
	}
	public static boolean claveEncriptadaCoincide(String passPlano, String passEncrypt) {
		boolean coincidencia = false;
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		if (passPlano.length() > 0) {
			if (passwordEncryptor.checkPassword(passPlano, passEncrypt)) {
				coincidencia = true;
			}
		}
		return coincidencia;
	}
}