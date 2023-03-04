package dam2.add.p22.utiles;

public class Ruta {
//   C:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ADD_P2.1_ENRIQUE_NAVARRO_ABELLAN2/WEB-INF/classes/dam2/add/p21/properties/	
//	"C:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ADD_P2.1_ENRIQUE_NAVARRO_ABELLAN2/properties/
	public static String RUTA; 
	public static String FICHERO_BBDD; 
	public static String FICHERO_CONF; 
	public static String FICHERO_LOG4; 
	
	public static final String PARAM_IDIOMA = "config.idioma";
	public static final String PARAM_PERSIS = "config.persistencia";
	public static final String PARAM_BD_NOM = "bd.nombre";
	public static final String PARAM_BD_LGN = "bd.login";
	public static final String PARAM_BD_PSW = "bd.pass";
	public static final String PARAM_BD_HST = "bd.host";
	public static final String PARAM_BD_DVR = "bd.driver";
	public static final String PARAM_BD_URL = "bd.url";
	
	
//	public static String getRutaReal() {
//		return WEB_CONTENT;
//	}
	public static void setRutaReal(String ruta) {
		RUTA = ruta + "/WEB-INF/classes/dam2/add/p22"; //La capturo desde el index.jsp
		FICHERO_BBDD = RUTA + "/properties/bbdd.properties"; 
		FICHERO_CONF = RUTA + "/properties/config.properties"; 
		FICHERO_LOG4 = RUTA + "/properties/log4j.properties";
	}
}
