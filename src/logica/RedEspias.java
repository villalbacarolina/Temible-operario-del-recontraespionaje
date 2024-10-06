package logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RedEspias implements Serializable{
	
	private HashMap<String, Grafo> redesEspias; //nombre de la red, y su camino
	private HashMap<String, Grafo> caminosMasSeguros; //nombre de la red, y su camino más seguro
	
	public RedEspias() {
		redesEspias = new HashMap<String, Grafo>();
		caminosMasSeguros = new HashMap<String, Grafo>();
	}
	
	public void crearRed(String nombre){
		redesEspias.put(nombre, new Grafo());
		caminosMasSeguros.put(nombre, null);
	}
	
	private Grafo obtenerRed(String nombreRed) {
		if(!redesEspias.containsKey(nombreRed))
			throw new IllegalArgumentException("La red "+nombreRed+" no existe");
		else
			return redesEspias.get(nombreRed);	
	}
	
	public void agregarEspia(String nombreRed, String nombreEspia){
		obtenerRed(nombreRed).agregarVertice(nombreEspia);
	}
	
	public void borrarEspia(String nombreRed, String nombreEspia){
		obtenerRed(nombreRed).eliminarVertice(nombreEspia);
	}
	
	public void establecerCamino(String nombreRed, String nombreEspia1, String nombreEspia2, int probabilidadIntercepcion){
		obtenerRed(nombreRed).agregarArista(nombreEspia1, nombreEspia2, probabilidadIntercepcion);
	}
	
	public void borrarCamino(String nombreRed, String nombreEspia1, String nombreEspia2){
		obtenerRed(nombreRed).eliminarArista(nombreEspia1, nombreEspia2);
	}
	
	public void establecerCaminoMasSeguro(String nombreRed) {
		Grafo red = obtenerRed(nombreRed);
		caminosMasSeguros.put(nombreRed, ArbolGeneradorMinimo.obtenerAGMComoGrafo(red));
	}
	

}
