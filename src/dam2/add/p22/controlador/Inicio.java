package dam2.add.p22.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dam2.add.p22.servicio.Agenda;
import dam2.add.p22.utiles.Ruta;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Ruta.setRutaReal(request.getServletContext().getRealPath(""));
	    Agenda.iniciaPersistencia();
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
