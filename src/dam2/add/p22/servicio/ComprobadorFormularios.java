package dam2.add.p22.servicio;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.jasypt.util.password.StrongPasswordEncryptor;

import dam2.add.p22.modelo.Usuario;

public class ComprobadorFormularios {
	private static ResourceBundle rb = null;
//	
//	/////////////////Comprobador de login ///////////////
	public static HashMap<String, String> formalizarLogueo(String email, String pass2) {
		rb = Propiedades.getIdiomaActivo(email);
		HashMap<String,String> mensaje = new HashMap<String,String>();			
			if (Agenda.existeEmailEnAgenda(email) && Agenda.estaBloqueado(email)) {
				mensaje.put("error_email", rb.getString("El_usuario_con_email") + " \"" + email + "\" " + rb.getString("esta_bloqueado") + ".");		
			} else if (pass2.length() < 1) {
				mensaje.put("error_pass", rb.getString("Introduzca_contrasena") + ".");
			} else if (Agenda.existeEmailEnAgenda(email)) {
				if (Agenda.claveCoincide(email, pass2)) {
					//exito
				} else {
					mensaje.put("error_pass", rb.getString("Contrasena_incorrecta"));
					if (!Agenda.getUsuarioByEmail(email).isRolAdmin() && pass2.length() > 0) {
						Agenda.restarIntento(email);
					}	
					if (Agenda.getUsuarioByEmail(email).getIntentos() == 0) {
						mensaje.put("error_email", rb.getString("El_usuario_con_email") + " \"" + email + "\" " + rb.getString("esta_bloqueado") + ".");
					} else if (Agenda.getUsuarioByEmail(email).getIntentos() > 0 && pass2.length() > 0 && !Agenda.getUsuarioByEmail(email).isRolAdmin()) {
						mensaje.put("error_pass", rb.getString("Incorrecto_Le_quedan") + " \""+ Agenda.getUsuarioByEmail(email).getIntentos() + "\" " + rb.getString("intentos") + ".");
					}
				}
			} else if (email.length() > 0) {
				mensaje.put("error_email", rb.getString("No_existe_el_usuario_con_email") + " \"" + email + "\".");
			}
			if (email.length() < 1) {
				mensaje.put("error_email", rb.getString("Introduzca_un_email_de_usuario"));
			}
		return mensaje;
	}
//	//////////////////Comprobador edicion de perfil////////////////////
	public static HashMap<String, String> formalizarEdicionPerfil(Usuario usuarioTemp) {
		rb = Propiedades.getIdiomaActivo(usuarioTemp.getEmail());
		HashMap<String,String> mensaje = new HashMap<String,String>();
		if (Agenda.existeEmailEnOtroUsuario(usuarioTemp)) {
			mensaje.put("error_email", rb.getString("El_email") + " \"" + usuarioTemp.getEmail() + "\" " + rb.getString("ya_esta_registrado"));
		} else if (usuarioTemp.getEmail().length() < 1) {
			mensaje.put("error_email", rb.getString("Introduzca_un_email_de_usuario"));
		}
		if (usuarioTemp.getNombre().length() < 1) {
			mensaje.put("error_email", rb.getString("Debe_introducir_un_nombre"));
		}
		return mensaje;
	}
	public static HashMap<String, String> formalizarEdicionPass(Usuario usuario, String pass, String pass2, String pass3) {
		rb = Propiedades.getIdiomaActivo(usuario.getEmail());
		HashMap<String,String> mensaje = new HashMap<String,String>();
		if (pass.length() < 1 || pass2.length() < 1 || pass3.length() < 1) {
			mensaje.put("error_pass", rb.getString("Introduzca_todos_los_campos_requeridos"));
		} else if (Agenda.claveCoincide(usuario.getEmail(), pass2)) {
			if (Agenda.claveEncriptadaCoincide(pass3, pass) && pass3.length() > 0) {				
				// exito //
			} else if (pass2.length() < 1) {
				mensaje.put("error_pass", rb.getString("Introduzca_contrasena") + ".");	
			} else {
				mensaje.put("error_pass", rb.getString("No_coinciden_las_contrasenas"));
			}
		} else {
			mensaje.put("error_pass", rb.getString("Contrasena_incorrecta"));
		}	
		return mensaje;
	}
	//////////////////Comprobador de registro///////////////////////////////
	public static HashMap<String, String> formalizarRegistro(Usuario usuarioTemp, String pass2) {
		rb = Propiedades.getIdiomaActivo(usuarioTemp.getEmail());
		HashMap<String,String> mensaje = new HashMap<String,String>();
		if (!Agenda.existeEmailEnAgenda(usuarioTemp.getEmail())) {
			if (usuarioTemp.getEmail().length() < 1) {
				mensaje.put("error_email", rb.getString("Debe_introducir_un_email_de_usuario"));		
			} else if (usuarioTemp.getNombre().length() < 1) {
			mensaje.put("error_email", rb.getString("Debe_introducir_un_nombre"));		
			} else if (usuarioTemp.getPass().length() > 0 && pass2.length() > 0) {
				Agenda.claveEncriptadaCoincide(pass2, usuarioTemp.getPass()); // exito //
			} else if (pass2.length() < 1) {
				mensaje.put("error_pass", rb.getString("Introduzca_contrasena") + ".");
			} else {
				mensaje.put("error_pass", rb.getString("No_coinciden_las_contrasenas"));
			}
		} else {
			mensaje.put("error_email", rb.getString("El_email") + " \"" + usuarioTemp.getEmail() + "\" " + rb.getString("ya_esta_registrado"));
		}		
		return mensaje;
	}
	///////////////////ENCRIPTADO//////////////////////////
	public static String encriptarPass(String PassPlano) {
		String pass = "";
		if (PassPlano != null) {
			if (PassPlano.length() > 0) {
				StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
				pass = passwordEncryptor.encryptPassword(PassPlano);
			}
		}
		return pass;
	}
}
