package dam2.add.p22.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dam2.add.p22.modelo.Usuario;
import dam2.add.p22.servicio.Agenda;
import dam2.add.p22.servicio.Propiedades;

/**
 * Servlet implementation class Controlidioma
 */
@WebServlet("/Controlidioma")
public class Controlidioma extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlidioma() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Propiedades.imprimeLog("i", "Cambio de idioma");
		String idioma = request.getParameter("idioma");
		request.getSession().setAttribute("idioma", idioma);
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		String opcion = request.getParameter("opcion");
		if (opcion == null) {
			opcion = "";
		}
		
		if (usuario != null) {
			Agenda.setIdiomaUsuario(idioma, usuario.getEmail());
			usuario = Agenda.cargarUsuario(usuario.getId_us());
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		} else {
			Usuario usuarioTemp = new Usuario();
			request.setAttribute("usuarioTemp", usuarioTemp);
			request.setAttribute("opcion", opcion);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
