package logica;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

public class RedEspias implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Grafo> redesEspias; //nombre de la red, y sus caminos
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
	
    private void guardarComoJSON(Object objeto, String nombreArchivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(objeto);

        String nombreCarpeta = "../informacion";
        File carpeta = new File(nombreCarpeta);
        if (!carpeta.exists()) 
            carpeta.mkdirs();

        String rutaArchivo = nombreCarpeta + File.separator + nombreArchivo;

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(json);
            System.out.println("Archivo JSON guardado en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el archivo JSON.");
        }
    }

    public void guardarRedesEspiasComoJSON() {
        guardarComoJSON(redesEspias, "redes.json");
    }
    
    public void guardarCaminosMasSegurosComoJSON() {
        guardarComoJSON(caminosMasSeguros, "caminosMasSeguros.json");
    }
    
    private <T> T leerDesdeJSON(String nombreArchivo, Type tipoObjeto) {
        String nombreCarpeta = "../informacion";
        String rutaArchivo = nombreCarpeta + File.separator + nombreArchivo;
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo " + rutaArchivo + " no existe.");
            return null;
        }

        try (FileReader reader = new FileReader(rutaArchivo)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, tipoObjeto);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo JSON.");
            return null;
        }
    }
    
    public void leerRedesEspiasDesdeJSON() {
        Type tipoMapa = new TypeToken<HashMap<String, Grafo>>() {}.getType();
        HashMap<String, Grafo> redes = leerDesdeJSON("redesEspias.json", tipoMapa);
        if (redes != null) {
            this.redesEspias = redes;
            System.out.println("Redes de espías cargadas correctamente.");
        }
    }

    public void leerCaminosMasSegurosDesdeJSON() {
        Type tipoMapa = new TypeToken<HashMap<String, Grafo>>() {}.getType();
        HashMap<String, Grafo> caminos = leerDesdeJSON("caminosMasSeguros.json", tipoMapa);
        if (caminos != null) {
            this.caminosMasSeguros = caminos;
            System.out.println("Caminos más seguros cargados correctamente.");
        }
    }
	

}
