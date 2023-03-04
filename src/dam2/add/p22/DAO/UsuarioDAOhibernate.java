package dam2.add.p22.DAO;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dam2.add.p22.HibernateManager;
import dam2.add.p22.modelo.Usuario;
import dam2.add.p22.servicio.Propiedades;

public final class UsuarioDAOhibernate implements IUsuarioDAO {

	@Override
	public ArrayList<Usuario> getListaUsuarios() {
		String query = "FROM Usuario";
		Session session = HibernateManager.getSessionFactory().openSession();
		ArrayList<Usuario> listaUsuarios = null;
		try {
			listaUsuarios = (ArrayList<Usuario>)session.createQuery(query).list();
			Propiedades.imprimeLog("i", "Consultar registros: " + query);
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			Propiedades.imprimeLog("f", "HibernateException");
		}
		//session.close();/////////////////////////////////////////////
		Propiedades.imprimeLog("i", "desconectar session");  /////////////////
		return listaUsuarios;
	}

	@Override
	public Usuario getUsuarioById(int id) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Usuario usuario = (Usuario)session.get(Usuario.class, id);
		Propiedades.imprimeLog("i", "Consultar registros: FROM usuario WHERE id");
		session.close();
		Propiedades.imprimeLog("i", "desconectar session");
		return usuario;	
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		int id = 0;
		Session session = HibernateManager.getSessionFactory().openSession();
		ArrayList<Usuario> listaUsuarios = getListaUsuarios();
		for (Usuario us : listaUsuarios) {
			if (us.getEmail().equals(email)) {
				id = us.getId_us();
			}
		}
		Usuario usuario = (Usuario)session.get(Usuario.class, id);
		Propiedades.imprimeLog("i", "Consultar registros: FROM usuario WHERE email");
		session.close();
		Propiedades.imprimeLog("i", "desconectar session");
		return usuario;
	}

	@Override
	public boolean crearCliente(Usuario usuario) {
		usuario.setIntentos(5);
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Usuario usuarioN = (Usuario)session.save(usuario);
		Propiedades.imprimeLog("i", "H.save usuario...");
		tx.commit();
		session.close();
		Propiedades.imprimeLog("i", "desconectar session");
		return true;		
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(usuario);
		Propiedades.imprimeLog("i", "Actualizar registro: H.update usuario...");
		tx.commit();
		session.close();
		Propiedades.imprimeLog("i", "desconectar session");
		return true;
	}

	@Override
	public boolean borrarUsuario(int id_us) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Usuario usuario = getUsuarioById(id_us);
		session.delete(usuario);
		Propiedades.imprimeLog("w", "Borrar registro: H.delete usuario WHERE id=?");
		tx.commit();
		session.close();
		Propiedades.imprimeLog("i", "desconectar session");
		return true;
	}
}