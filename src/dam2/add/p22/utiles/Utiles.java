package dam2.add.p22.utiles;

import java.util.ArrayList;


public class Utiles {
	public static ArrayList<String> separaPalabras(String busqueda) {
		String letra = "";
		String previa = " "; //letra previa a la que está leyendo. iniciada así por si la frase empieza con espacio.

		ArrayList<String> fraseArray = new ArrayList<String>();
		String palabra = ""; //almacena palabra
		
		for (int i = 0; i < busqueda.length(); i++) {
			letra = busqueda.substring(i, i + 1);
			if (letra.equalsIgnoreCase(" ")) {	//si la letra es espacio podría estar cerrando una palabra
				if (previa.equalsIgnoreCase(" ")) { //si la previa de este espacio era otro espacio no considerará fin de palabra
					previa = " ";
				} else if (palabra != "") { //Si todavia no hay palabra, no hay nada que guardar
						fraseArray.add(palabra); //se guarda en el índice que corresponde
						palabra = ""; //se reinicia la palabra
						previa = " ";
				}
			} else {
				//si el carácter no es espacio aquí se concatena y se forma la palabra.
				palabra = palabra + letra;
				previa = letra;
			}
		}	
		//si la frase no ha terminado en espacio (que sería lo normal) la palabra no se ha guardado
		if (!letra.equalsIgnoreCase(" ") && !letra.equalsIgnoreCase("")) { 
			fraseArray.add(palabra);  //se guarda la palabra que falta por guardar
		}	
		return fraseArray;
	}
}
