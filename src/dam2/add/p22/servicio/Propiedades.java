package dam2.add.p22.servicio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import dam2.add.p22.modelo.Usuario;
import dam2.add.p22.utiles.Ruta;

public class Propiedades {

	private static String idioma;
	private static String persistencia;
	private static Connection conexion; //atributo para guardar el objeto Connection
	private static Statement stDeclaracion; // solo para la entrada desde SelectorPersistencia
	private static Logger log = Logger.getLogger(Propiedades.class);

	
	
	public static void imprimeLog(String prioridad, String mensaje) {
		PropertyConfigurator.configure(Ruta.FICHERO_LOG4);
		if (prioridad.equals("d")) {
			log.debug(mensaje);
		} else if (prioridad.equals("i")) {
			log.info(mensaje);
		} else if (prioridad.equals("w")) {
			log.warn(mensaje);
		} else if (prioridad.equals("e")) {
			log.error(mensaje);
		} else if (prioridad.equals("f")) {
			log.fatal(mensaje);
		} else {
			log.error("traza no registrada correctamente");
		}
	}
	public static void imprimeLog(String prioridad, String mensaje, String email) {
		if (email.equals("")) {
			email = ""; //ANONIMO
		}
		PropertyConfigurator.configure(Ruta.FICHERO_LOG4);
		if (prioridad.equals("d")) {
			log.debug(mensaje + " - " +email);
		} else if (prioridad.equals("i")) {
			log.info(mensaje + " - " +email);
		} else if (prioridad.equals("w")) {
			log.warn(mensaje + " - " +email);
		} else if (prioridad.equals("e")) {
			log.error(mensaje + " - " +email);
		} else if (prioridad.equals("f")) {
			log.fatal(mensaje + " - " +email);
		} else {
			log.error("traza no registrada correctamente");
		}
	}
	public static String getIdiomaDefecto() {
		if (idioma == null) {
			setParametroIdioma(Ruta.PARAM_IDIOMA);//"es en";
	    }
	    return idioma;
	}
	public static void setParametroIdioma(String parametro) {
		imprimeLog("i", "setParametroIdioma");
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(Ruta.FICHERO_CONF));
		} catch (IOException e) {
			imprimeLog("f", "No existe el fichero de configuración");
		}
		idioma = propiedades.getProperty(parametro);
	}
	public static ResourceBundle getIdiomaSesion(String email, String idiomaSesion) {
		Locale local;
		ResourceBundle rb = null;
		if (!idiomaSesion.equals("")) {
			local = new Locale(idiomaSesion);
		} else {
			local = new Locale(Propiedades.getIdiomaDefecto());
		}
		if(Agenda.existeEmailEnAgenda(email)){
			if(Agenda.getUsuarioByEmail(email).getIdioma() != null){
				local = new Locale(Agenda.getUsuarioByEmail(email).getIdioma());
			} else {		
				Usuario usuarioTemp = Agenda.getUsuarioByEmail(email);
				usuarioTemp.setIdioma(idiomaSesion);
				Agenda.actualizarUsuario(usuarioTemp);
		    }
		}
		rb = ResourceBundle.getBundle("idioma", local);
		return rb;
	}
	public static ResourceBundle getIdiomaActivo(String email) {
		Locale local;
		ResourceBundle rb = null;
		if(Agenda.existeEmailEnAgenda(email)){
			if(Agenda.getUsuarioByEmail(email).getIdioma() != null){
				local = new Locale(Agenda.getUsuarioByEmail(email).getIdioma());
			} else {		
				local = new Locale(Propiedades.getIdiomaDefecto());
		    }
		} else {
			local = new Locale(Propiedades.getIdiomaDefecto());
		}
		rb = ResourceBundle.getBundle("idioma", local);
		return rb;
	}
	public static String getPersistencia() {
		if (persistencia == null) {
			setPersistencia(Ruta.PARAM_PERSIS); // memoria bdd
	    }
	    return persistencia;
	}
	public static void setPersistenciaAdmin(String persist) {
		persistencia = persist;
	}
	private static void setPersistencia(String parametro) {
		imprimeLog("i", "setPersistencia");
		Properties propiedades = new Properties();
		try {
			String ficheroConfig = Ruta.FICHERO_CONF;
			propiedades.load(new FileInputStream(ficheroConfig));
		} catch (IOException e) {
			//e.printStackTrace();
			imprimeLog("f", "No existe el fichero de configuración");
		}
		persistencia = propiedades.getProperty(parametro);	
	}
    public static Connection getConexion() {
	    if (conexion == null) {
	    	crearConexion();
	    }
	    return conexion;
    }
    private static boolean crearConexion() {
    	imprimeLog("i", "");
	    try {
	    	String bd = getParametroConexion(Ruta.PARAM_BD_NOM);//"p21";
	    	String login = getParametroConexion(Ruta.PARAM_BD_LGN);//"root";
	    	String password = getParametroConexion(Ruta.PARAM_BD_PSW);
	    	String host = getParametroConexion(Ruta.PARAM_BD_HST);//"127.0.0.1"; //localhost
	        //cargo el driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    	String url = "jdbc:mysql://";
	    	conexion = DriverManager.getConnection(url + host + "/"+ bd, login, password);
	    	
	    	//String url = "jdbc:sqlite:"+"./ficheros/misqlite.db";
	        //conexion = DriverManager.getConnection(url);
	        
	    	// en false hay que --> conexion.commit(); --> en métodos
	    	conexion.setAutoCommit(false);
	    	imprimeLog("i", "");
	    	System.out.println("Conexión a la base de datos \"" + bd + "\" INICIADA");
	    	// Para comprobar el driver JDBC utilizado
            //DatabaseMetaData meta = conexion.getMetaData();
            //System.out.println("The driver name is " + meta.getDriverName());
	        
	    } catch (SQLException e) {
	    	System.out.println(e);
	    	imprimeLog("f", "SQLException");
	    	return false;
	    }
	    catch (Exception e) {
	    	System.out.println(e);
	    	imprimeLog("f", "Exception");
	    	return false;
	    }
	    return true;
    }
	public static String getParametroConexion(String parametro) {
		imprimeLog("i", "getParametroConexion");
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(Ruta.FICHERO_BBDD));
		} catch (IOException e) {
			//e.printStackTrace();
			imprimeLog("f", "No existe el fichero de configuración");
		}
		String param = propiedades.getProperty(parametro);
		return param;
	}
    public static void desconectar(){
    	imprimeLog("i", "desconectar bdd");
    	try {
    		conexion.close();
            conexion = null;
            imprimeLog("i", "Conexión a la base de datos '" + getParametroConexion(Ruta.PARAM_BD_NOM) + "' TERMINADA");
    	
    	} catch (SQLException e) {
    		imprimeLog("f", "Error al cerrar la conexion");
        }
    }

 
    
    
    /**/	// RESTAURAR BDD
	public static void resetearBDD() {
		imprimeLog("i", "resetearBDD");
		conexion = Propiedades.getConexion();
		try {
			stDeclaracion = conexion.createStatement();
			
			if (conexion != null) {
				//crearBaseDeDatos();
				//consultar2();
				//actualizar2();
				//borrar2();
				//borrarBaseDeDatos();
				imprimeLog("w", "Reseteando BD");
				borrarTabla();
				crearTabla();		
				insertarInicial();
				
				stDeclaracion.close();
				Propiedades.desconectar();
			} else {
				imprimeLog("e", "Conexion no realizada");
			}
		} catch (SQLException e) {
			imprimeLog("e", "SQLException");
			if(conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					imprimeLog("f", "SQLException");
				}
			}
		}
	}
	


/**/	// CREAR TABLA
	public static void crearTabla() throws SQLException {
		imprimeLog("i", "crearTabla");
		// Crea la tabla profesores (comprueba que NO existe)		
		stDeclaracion.executeUpdate("CREATE TABLE IF NOT EXISTS usuario ("
				+ "id INT AUTO_INCREMENT, "
				+ "nombre VARCHAR(255), "
				+ "apellido VARCHAR(255), "
				+ "apellido2 VARCHAR(255), "
				+ "email VARCHAR(255) NOT NULL UNIQUE, " //con unique me da problemas al actualizar ARREGLADO
				//+ "email VARCHAR(255), "
				+ "telefono VARCHAR(255), "
				+ "pass VARCHAR(255), "
				+ "intentos INT, "
				+ "rolAdmin INT, "
				+ "idioma VARCHAR(255), "
				+ "PRIMARY KEY(id)"
				+ ")");
		imprimeLog("i", "CREATE TABLE IF NOT EXISTS usuario");
	}
/**/	// INSERTAR REGISTO INICIAL
	public static void insertarInicial() throws SQLException {
		ArrayList<Usuario> listaUsuariosInicial = new ArrayList<Usuario>();
		listaUsuariosInicial.add(new Usuario("1", "bddAngel", "Blasco", "Cano", "angel@blasco.es", "111111111", "d8MeP4zGXPM4qJGUPLXAzawYDnp8tkyYiBBDxim3egFo0X0gXfVN3aRECNFtOPst", false, 5, "es"));
		listaUsuariosInicial.add(new Usuario("2", "bddBeatriz", "Cano", "Domingo", "beatriz@cano.es", "222222222", "0QeuCMNAnCXA3YmUpOWfjs62ERUze8/HbC9W7IZLNZSMbPXEdw9jIKS/M24UBAdE", true, 5, "es"));
		listaUsuariosInicial.add(new Usuario("3", "bddCarlos", "Domingo", "Egido", "carlos@domingo.es", "333333333", "hlf7dB1h/9ATZJU9sfefkCiA5uwMORe1mvc55lw5r6wdT4gbUPdRpS0wVVoH2Joz", false, 5, "en"));
		listaUsuariosInicial.add(new Usuario("4", "bddDiego", "Egido", "Floren", "diego@egido.es", "444444444", "FFtzNLNPWwvmp6PhY6pST0Z9+tRPdBr/x3aDwX3wGRxnrHuN7yOnHQ9ABkCZeb6s", false, 5, "es"));
		listaUsuariosInicial.add(new Usuario("5", "bddEric", "Floren", "Guilabert", "eric@floren.es", "555555555", "3FJ3w8drRJbs71crYBBP3LMVqOrgs0krmgObgMBVqFhsH51ushVT8VfwRFlThb7A", false, 5));
		listaUsuariosInicial.add(new Usuario("6", "bddFrancisco", "Guilabert", "Huerta", "fran@guilabert.es", "666666666", "PyyMPRRBv7lIuZD9zsctKnnIqR4fsQ5bKt796AFAZcLIdaFKoUDaabd5zKJrUC8I", true, 5, "es"));
		imprimeLog("i", "insertarInicial");
		for (int i = 0; i < listaUsuariosInicial.size(); i++) {

			String query = "INSERT INTO usuario (nombre, apellido, apellido2, email, telefono, pass, intentos, rolAdmin, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			imprimeLog("i", "Insertar registro:");
			imprimeLog("i", "INSERT INTO usuario (nombre, apellido, apellido2, email, telefono, pass, intentos, rolAdmin, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setString(1, listaUsuariosInicial.get(i).getNombre());
			ps.setString(2, listaUsuariosInicial.get(i).getApellido());
			ps.setString(3, listaUsuariosInicial.get(i).getApellido2());
			ps.setString(4, listaUsuariosInicial.get(i).getEmail());
			ps.setString(5, listaUsuariosInicial.get(i).getTelefono());
			ps.setString(6, listaUsuariosInicial.get(i).getPass());
			ps.setInt(7, listaUsuariosInicial.get(i).getIntentos());
			ps.setBoolean(8, listaUsuariosInicial.get(i).isRolAdmin());
			ps.setString(9, listaUsuariosInicial.get(i).getIdioma());

			if (ps.executeUpdate() == 0) {
				imprimeLog("e", "NO se ha podido insertar datos");
			}
			conexion.commit();
			ps.close();
		}
	}
/**/	// BORRAR TABLA
	public static void borrarTabla() throws SQLException {
		//st.executeQuery("use dam");
		// Borra la tabla usuario (comprueba que existe)
		stDeclaracion.executeUpdate("DROP TABLE IF EXISTS usuario");
		imprimeLog("w", "DROP TABLE IF EXISTS usuario");
	}
/**/	// BORRAR BASE DE DATOS
	public static void borrarBaseDeDatos() throws SQLException {
		// Borra la bbdd (comprueba que NO existe)
		stDeclaracion.executeUpdate("DROP DATABASE p211");
		imprimeLog("w", "DROP DATABASE p21");
	}
	
	
	
	
//	// CONSULTAR REGISTRO (METODO 2)
	//https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
	public static void consultar2() throws SQLException {
		System.out.println("Consultar registros:");
		String query = "SELECT * FROM profesor WHERE nombre like ?";
		//System.out.println(query);
		PreparedStatement ps = conexion.prepareStatement(query);
		ps.setString(1, "P%");
		
		ResultSet rs = ps.executeQuery();
		
		// Columnas de la tabla: nombre, apellidos y telefono
		while (rs.next()) {
			String nombre = rs.getString(2); // rs.getString("nombre");
			String apellidos = rs.getString(3); // rs.getString("apellidos");
			String email = rs.getString(4); // rs.getString("email");

			System.out.println(nombre + "\t" + apellidos + "\t" + email);
		}
		rs.close();
		ps.close();
	}

//	// ACTUALIZAR REGISTRO (METODO 2)
	//https://alvinalexander.com/blog/post/jdbc/sample-jdbc-preparedstatement-sql-update/
	public static void actualizar2() throws SQLException {
		System.out.println("Actualizar registro:");
		String query = "UPDATE profesor SET nombre=?, email=? WHERE id=?";
		//System.out.println(query);
		
		PreparedStatement ps = conexion.prepareStatement(query);
		ps.setString(1, "Emilio");
		ps.setString(2, "erubio@gmail.com");
		ps.setInt(3, 1);				
		
		int resultado = ps.executeUpdate();
		
		if (resultado == 0)
			System.out.println("NO se ha podido actualizar");
		conexion.commit();
	}
//	// BORRAR REGISTRO (METODO 2)
	public static void borrar2() throws SQLException {
		System.out.println("Borrar registro:");
		String query = "DELETE FROM profesor WHERE id=?";
		//System.out.println(query);
		
		PreparedStatement ps = conexion.prepareStatement(query);
		ps.setInt(1, 2);
		
		int resultado = ps.executeUpdate();
		
		if (resultado == 0)
			System.out.println("NO se ha podido borrar");
		conexion.commit();
	}
}