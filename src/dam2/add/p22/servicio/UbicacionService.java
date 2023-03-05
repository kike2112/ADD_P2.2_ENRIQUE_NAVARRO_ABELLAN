package dam2.add.p22.servicio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import com.google.gson.Gson;

import dam2.add.p22.modelo.Ubicacion;
import dam2.add.p22.utiles.Ruta;

public class UbicacionService {
	
	public static String getUbicacion(String codigo) {
		String cadenaJson;
		String nombre = "";
		if (codigo.length() == 2) {
			cadenaJson = leerUrl(Ruta.URL_PROVINCIA);
		} else {
			cadenaJson = leerUrl(Ruta.URL_LOCALIDAD);
		}
		Ubicacion[] ubicaciones = new Gson().fromJson(cadenaJson, Ubicacion[].class);
		for (int i = 0; i < ubicaciones.length; i++) {
			if (ubicaciones[i].getId().equals(codigo)) {
				nombre = ubicaciones[i].getNm();
			}
		}
		return nombre; 
	}
	public static Ubicacion[] getUbicaciones(String ubicacion) {
		String cadenaJson;
		if (ubicacion.equals("provincias")) {
			cadenaJson = leerUrl(Ruta.URL_PROVINCIA);
		} else {
			cadenaJson = leerUrl(Ruta.URL_LOCALIDAD);
		}
		Ubicacion[] ubicaciones = new Gson().fromJson(cadenaJson, Ubicacion[].class);
		Arrays.sort(ubicaciones, Comparator.comparing(Ubicacion::getNm));
		return ubicaciones; 
	}
	public static Ubicacion[] getLocalidadesDeProvincia(String nombreProvincia) {
		int contador = 0;
		Ubicacion[] ubicaciones = getUbicaciones("provincias");
		for (int i = 0; i < ubicaciones.length; i++) {
			if (nombreProvincia.equals(ubicaciones[i].getNm())) {
				contador++;
			}
		}
		Ubicacion[] total = new Ubicacion[contador];
		contador = 0;
		for (int i = 0; i < ubicaciones.length; i++) {
			if (nombreProvincia.equals(ubicaciones[i].getNm())) {
				total[i] = ubicaciones[i];
				contador++;
			}
		}
		return total;
	}
	
	public static String leerUrl(String sUrl) {
		String output = "";
		try {
			URL url = new URL(sUrl);
		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Accept", "application/json");
		
			if (conn.getResponseCode() != 200) {
				// si la respuesta del servidor es distinta al codigo 200 lanzaremos una
				// Exception
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
		
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		
			// creamos un StringBuilder para almacenar la respuesta del web service
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = br.read()) != -1) {
				sb.append((char) cp);
			}
			// en la cadena output almacenamos toda la respuesta del servidor
			output = sb.toString();
			// System.out.println(output);
		
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return output;
	}
}
