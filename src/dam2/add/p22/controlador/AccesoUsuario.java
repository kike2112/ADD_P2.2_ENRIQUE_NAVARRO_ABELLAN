package dam2.add.p22.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.StrongPasswordEncryptor;

import dam2.add.p22.modelo.Usuario;
import dam2.add.p22.servicio.Agenda;
import dam2.add.p22.servicio.ComprobadorFormularios;
import dam2.add.p22.servicio.Propiedades;



/**
 * Servlet implementation class AccesoUsuario
 */
@WebServlet("/AccesoUsuario")
public class AccesoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesoUsuario() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		String id_us = request.getParameter("id_us");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String apellido2 = request.getParameter("apellido2");
	    String email = request.getParameter("email");
	    String telefono = request.getParameter("telefono");
	    if (opcion == null) {
			opcion = "";
		}
	    if (email == null) {
			email = "";
		}
	    
	    Usuario usuarioTemp = new Usuario(nombre, apellido, apellido2, email, telefono);       
	
		if (opcion.equals("registro")) { //Deriva a la vista de registro
			Propiedades.imprimeLog("i", "Entrando a registro", email);
			request.setAttribute("usuarioTemp", usuarioTemp);
			request.setAttribute("opcion", "registro");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else if (opcion.equals("login")) { //Deriva a la vista de login	
			Propiedades.imprimeLog("i", "Entrando a login", email);
			request.setAttribute("opcion", "login");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else if (opcion.equals("perfil")) {		
			Propiedades.imprimeLog("i", "Entrando a perfil", email);
			request.getSession().setAttribute("opcion", "perfil");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		} else if (opcion.equals("pintaEditar")) { //Deriva a la vista de perfil editable
			Propiedades.imprimeLog("i", "Entrando a Editarperfil", email);
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin() && id_us != null) {
				Usuario usuarioEditable = Agenda.cargarUsuario(Integer.parseInt(id_us));
				if (usuarioEditable != null) {
					request.getSession().setAttribute("usuarioEditable", usuarioEditable);
				} else {
					Propiedades.imprimeLog("w", "noTieneAcceso", email);
					request.getSession().setAttribute("opcion", "noTieneAcceso");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
	
				}
			} else {
				request.getSession().setAttribute("usuarioEditable", usuario);
			}
			request.getSession().setAttribute("opcion", "editarPerfil");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		} else if (opcion.equals("pintaEditarPass")) {
			Propiedades.imprimeLog("i", "Entrando a EditarPass", email);
			request.getSession().setAttribute("opcion", "editarPass");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		
		} else if (opcion.equals("pintaEditarPersistencia")) {
			Propiedades.imprimeLog("i", "Entrando a EditarPersistencia", email);
			request.getSession().setAttribute("opcion", "editarPersistencia");
			response.sendRedirect(request.getContextPath() + "/index.jsp");				
			
		} else if (opcion.equals("eliminar")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Propiedades.imprimeLog("i", "eliminar", email);
			if (usuario != null) {
				if (usuario.isRolAdmin() && usuario.getId_us() != Integer.parseInt(id_us)) {
					ArrayList<Usuario> listaUsuariosClientes = Agenda.eliminarUsuario(Integer.parseInt(id_us));
					request.getSession().setAttribute("listaUsuariosClientes", listaUsuariosClientes);
					request.getSession().setAttribute("opcion", "acceso_admin");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					
				} else {
					Propiedades.imprimeLog("w", "noTieneAcceso", email);
					request.getSession().setAttribute("opcion", "noTieneAcceso");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					
				}
			} else {
				Propiedades.imprimeLog("w", "noTieneAcceso", email);
				request.getSession().setAttribute("opcion", "noTieneAcceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				
			}			
		} else if (opcion.equals("cerrarSesion")) {
			Propiedades.imprimeLog("i", "cerrarSesion", email);
			request.getSession().setAttribute("opcion", null);
			request.getSession().setAttribute("usuario", null);
			request.getSession().setAttribute("idioma", null);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		} else if (opcion.equals("inicio")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin()) {
				request.getSession().setAttribute("opcion", "acceso_admin");
			} else {
				request.getSession().setAttribute("opcion", null);
			}	
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		} else if (opcion.equals("")) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String opcion = request.getParameter("opcion");
		if (opcion == null) {
			opcion = "";
		}
		String id_us = request.getParameter("id_us");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String apellido2 = request.getParameter("apellido2");
	    String email = request.getParameter("email");
		if (email == null) {
			email = "";
		}
	    String telefono = request.getParameter("telefono");
	    String pass = ComprobadorFormularios.encriptarPass(request.getParameter("pass"));
		String pass2 = request.getParameter("pass2");
		if (pass2 == null) {
			pass2 = "";
		}

	    Usuario usuarioTemp = new Usuario(nombre, apellido, apellido2, email, telefono, pass);       
	
		if (opcion.equals("agregar")) { //Formaliza el registro y lo añade a la Base de Datos, luego la manda a la vista de bienvenida.
			Propiedades.imprimeLog("i", "Intentando registro", email);
			ArrayList<Usuario> listaUsuarios = Agenda.getListaUsuarios();//Carga los usuarios que podrán ver los administradores	
			HashMap<String,String> mensaje = ComprobadorFormularios.formalizarRegistro(usuarioTemp, pass2);
			if (mensaje.containsKey("error_email") || mensaje.containsKey("error_pass")) {
				Propiedades.imprimeLog("w", "Error en registro");
				request.setAttribute("error_email", mensaje.get("error_email"));
				request.setAttribute("error_pass", mensaje.get("error_pass"));
				request.setAttribute("email", email);
				request.setAttribute("usuarioTemp", usuarioTemp);
				request.setAttribute("opcion", "registro");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else { // exito. pasar usuario y sesion	
				Propiedades.imprimeLog("i", "Registro correcto", email);
				usuarioTemp.setIdioma((String) request.getSession().getAttribute("idioma"));
				Usuario usuario = Agenda.registrarUsuario(usuarioTemp);			
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("idioma", usuario.getIdioma());
				request.getSession().setAttribute("opcion", "acceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				
			}
		} else if (opcion.equals("acceso")) { //Formaliza el logueo. Hace comprobaciones hasta que se loguea correctamente o lo manda al registro
			Propiedades.imprimeLog("i", "Intentando acceder", email);
			ArrayList<Usuario> listaUsuarios = Agenda.getListaUsuarios();//Carga los usuarios que podrán ver los administradores
			HashMap<String,String> mensaje = ComprobadorFormularios.formalizarLogueo(email, pass2);		
			if (mensaje.containsKey("error_email") || mensaje.containsKey("error_pass")) {
				Propiedades.imprimeLog("w", "Acceso denegado", email);
				request.setAttribute("error_email", mensaje.get("error_email"));
				request.setAttribute("error_pass", mensaje.get("error_pass"));
				request.setAttribute("email", email);
				request.setAttribute("opcion", "login");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else { // exito. pasar usuario y sesion
				Usuario usuario = Agenda.loguearUsuario(usuarioTemp);
				Agenda.actualizarUsuario(usuario);
				request.getSession().setAttribute("idioma", usuario.getIdioma());
				request.getSession().setAttribute("usuario", usuario);
				if (usuario.isRolAdmin()) {
					ArrayList<Usuario> listaUsuariosClientes = Agenda.getListaUsuariosClientes();
					request.getSession().setAttribute("listaUsuariosClientes", listaUsuariosClientes);
					request.getSession().setAttribute("opcion", "acceso_admin");
				} else {
					request.getSession().setAttribute("opcion", "acceso");
				}
				Propiedades.imprimeLog("i", "Acceso correcto", email);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				
			}
		} else if (opcion.equals("buscador")) {
			Propiedades.imprimeLog("i", "Petición de busqueda", email);
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin()) {
				Propiedades.imprimeLog("i", "busqueda realizada", email);
				String busqueda = request.getParameter("busqueda");
				ArrayList<Usuario> listaClientesBuscados = Agenda.cargarClientesBuscados(busqueda);
				request.setAttribute("listaClientesBuscados", listaClientesBuscados);		
				request.setAttribute("opcionListado", "opcionBusqueda");
				request.setAttribute("busqueda", busqueda);
				request.getSession().setAttribute("opcion", "acceso_admin");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else {
				Propiedades.imprimeLog("w", "Petición de noTieneAcceso", email);
				request.getSession().setAttribute("opcion", "noTieneAcceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				
			}	
		} else if (opcion.equals("editar")) {
			Propiedades.imprimeLog("i", "Petición de edicion de perfil", email);
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin() || (!usuario.isRolAdmin() && usuario.getId_us() == Integer.parseInt(id_us))) {
				usuarioTemp.setId_us(Integer.parseInt(id_us));
				HashMap<String,String> mensaje = ComprobadorFormularios.formalizarEdicionPerfil(usuarioTemp);
				if (mensaje.containsKey("error_email")) {
					Propiedades.imprimeLog("w", "Error de edicion", email);
					request.setAttribute("error_email", mensaje.get("error_email"));
					request.setAttribute("usuarioTemp", usuarioTemp);
					request.setAttribute("opcion", "editarPerfil");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				} else { // exito. cambiar datos		 		
					Agenda.actualizarUsuario(usuarioTemp);
					usuario = Agenda.cargarUsuario(usuario.getId_us());
					Propiedades.imprimeLog("i", "edición realizada correctamente", email);
					request.getSession().setAttribute("usuario", usuario);
		            if (usuario.isRolAdmin()) {
		            	ArrayList<Usuario> listaUsuariosClientes = Agenda.getListaUsuariosClientes();
						request.getSession().setAttribute("listaUsuariosClientes", listaUsuariosClientes);
		            	request.getSession().setAttribute("opcion", "acceso_admin");
					} else {
						request.getSession().setAttribute("opcion", "acceso");
					}		
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					
				}
			} else {
				Propiedades.imprimeLog("w", "noTieneAcceso", email);
				request.getSession().setAttribute("opcion", "noTieneAcceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				
			}	
		} else if (opcion.equals("editaPass")) {
			Propiedades.imprimeLog("i", "Petición de edición de pasword", email);
			String pass3 = request.getParameter("pass3");
			if (pass3 == null) {
				pass3 = "";
			}
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			HashMap<String,String> mensaje = ComprobadorFormularios.formalizarEdicionPass(usuario, pass, pass2, pass3);
			if (mensaje.containsKey("error_pass")) {
				Propiedades.imprimeLog("w", "Error de cambio", email);
				request.setAttribute("error_pass", mensaje.get("error_pass"));
				request.getSession().setAttribute("opcion", "editarPass");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else { // exito. cambiar contraseñas
				Agenda.actualizarPassUsuario(usuario, pass);
				Propiedades.imprimeLog("i", "Contraseña cambiada correctamente", email);
	            if (usuario.isRolAdmin()) {
	            	request.getSession().setAttribute("opcion", "acceso_admin");
				} else {
					request.getSession().setAttribute("opcion", "acceso");
				}		
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				
			}	
		} else if (opcion.equals("")) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		}
	}
}