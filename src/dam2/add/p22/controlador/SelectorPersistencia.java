package dam2.add.p22.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dam2.add.p22.servicio.Agenda;
import dam2.add.p22.servicio.Propiedades;

/**
 * Servlet implementation class Inicial
 */
@WebServlet("/SelectorPersistencia")
public class SelectorPersistencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectorPersistencia() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		boolean modificacion = false;
		String persist = request.getParameter("persist");
		String idioma = request.getParameter("idioma");
		String opcionPersist = request.getParameter("opcionPersist");
		
		if (opcionPersist == null) {
			opcionPersist = "cambioRepo";
		}
		
			
		if (opcionPersist.equals("cambioRepo")){
			Propiedades.imprimeLog("w", "Cambio de persistencia");
			Agenda.borrarDAO();
			Agenda.cambiaPersistenciaAdmin(persist);
//			Propiedades.cambiaFileConfiguracion(idioma, persist);
//			Agenda.iniciaPersistencia();
			modificacion = true;
			
		} else if (opcionPersist.equals("resetBDD")) { //Resetea repositorio
			Propiedades.imprimeLog("w", "Reiniciando la BDD");
			Agenda.borrarDAO();
			Propiedades.resetearBDD();
			modificacion = true;
			
		}
		if (modificacion) {
			request.getSession().setAttribute("opcion", null);
			request.getSession().setAttribute("usuario", null);
		}
		
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
