package dam2.add.p22.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", length = 255)
	private String nombre;
	
	@Column(name = "apellido", length = 255)
	private String apellido;
	
	@Column(name = "apellido2", length = 255)
	private String apellido2;
	
	@Column(name = "email", length = 255, unique = true)
	private String email;
	
	@Column(name = "telefono", length = 255)
	private String telefono;
	
	@Column(name = "pass", length = 255)
	private String pass;
	
	@Column(name = "intentos")
	private int intentos;
	
	@Column(name = "rolAdmin")
	private boolean rolAdmin;
	
	@Column(name = "idioma", length = 255)
	private String idioma;
	
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String pass) {
		super();
		this.nombre = nombre;
		this.pass = pass;
	}
	public Usuario(String nombre, String apellido, String apellido2, String email, String telefono,
			String pass) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
	}
	public Usuario(String nombre, String apellido, String apellido2, String email, String telefono,
			String pass, boolean rolAdmin, int intentos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.rolAdmin = rolAdmin;
		this.intentos = intentos;
	}
	public Usuario(int id, String nombre, String apellido, String apellido2, String email, String telefono,
			String pass, boolean rolAdmin, int intentos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.rolAdmin = rolAdmin;
		this.intentos = intentos;
	}
	public Usuario(int id, String nombre, String apellido, String apellido2, String email, String telefono,
			String pass, boolean rolAdmin, int intentos, String idioma) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.rolAdmin = rolAdmin;
		this.intentos = intentos;
		this.idioma = idioma;
	}
	
	public Usuario(String nombre, String apellido, String apellido2, String email, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
	}
	public int getId_us() {
		return id;
	}
	public void setId_us(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isRolAdmin() {
		return rolAdmin;
	}
	public void setRolAdmin(boolean rolAdmin) {
		this.rolAdmin = rolAdmin;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	@Override
	public String toString() {
		return this.email + this.nombre + this.apellido + this.apellido2 + this.telefono;
	}
}
