package dam2.add.p22.DAO;

import java.util.ArrayList;

import dam2.add.p22.modelo.Usuario;

public interface IUsuarioDAO {
	
	public ArrayList<Usuario> getListaUsuarios();

	public Usuario getUsuarioById(String id);
	
	public Usuario getUsuarioByEmail(String email);
	
	public boolean crearCliente(Usuario usuario);
	
	public boolean actualizarUsuario(Usuario usuario);
	
	public boolean borrarUsuario(String id_us);
	
}
