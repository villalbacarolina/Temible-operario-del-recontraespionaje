package logica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RedEspias {
    private HashMap<String, Grafo> redesEspias; // nombre de la red, y sus caminos
    private HashMap<String, Grafo> caminosMasSeguros; // nombre de la red, y su camino más seguro

    public RedEspias() {
        redesEspias = new HashMap<>();
        caminosMasSeguros = new HashMap<>();
    }

    public void crearRed(String nombre) {
        redesEspias.put(nombre, new Grafo());
        caminosMasSeguros.put(nombre, null);
    }

    private Grafo obtenerRed(String nombreRed) {
        if (!redesEspias.containsKey(nombreRed))
            throw new IllegalArgumentException("La red " + nombreRed + " no existe");
        return redesEspias.get(nombreRed);
    }

    public void agregarEspia(String nombreRed, String nombreEspia) {
        obtenerRed(nombreRed).agregarVertice(nombreEspia);
    }

    public void borrarEspia(String nombreRed, String nombreEspia) {
        obtenerRed(nombreRed).eliminarVertice(nombreEspia);
    }

    public void establecerCamino(String nombreRed, String nombreEspia1, String nombreEspia2, double probabilidadIntercepcion) {
        if( probabilidadIntercepcion>1 || probabilidadIntercepcion<0 )
        	throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1 (incluídos)");
    	obtenerRed(nombreRed).agregarArista(nombreEspia1, nombreEspia2, probabilidadIntercepcion);
    }

    public void borrarCamino(String nombreRed, String nombreEspia1, String nombreEspia2) {
        obtenerRed(nombreRed).eliminarArista(nombreEspia1, nombreEspia2);
    }

    public void establecerCaminoMasSeguro(String nombreRed) {
        Grafo red = obtenerRed(nombreRed);
        caminosMasSeguros.put(nombreRed, ArbolGeneradorMinimo.obtenerAGMComoGrafo(red));
    }

    public void guardarComoJSON(String nombreArchivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        String nombreCarpeta = System.getProperty("user.home") + "/informacion";
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

    public static RedEspias leerJSON(String archivo) {
        Gson gson = new Gson();
        RedEspias ret = null;

        //lee el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            //deserializa: pasa de .json a RedEspias
            ret = gson.fromJson(br, RedEspias.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo JSON.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static void main(String[] args) {
        RedEspias redEspias = new RedEspias();

        redEspias.crearRed("Red1");

        redEspias.agregarEspia("Red1", "Espia1");
        redEspias.agregarEspia("Red1", "Espia2");
        redEspias.agregarEspia("Red1", "Espia3");
        redEspias.agregarEspia("Red1", "Espia4");
        redEspias.agregarEspia("Red1", "Espia5");
        redEspias.agregarEspia("Red1", "Espia6");
        redEspias.agregarEspia("Red1", "Espia7");

        redEspias.establecerCamino("Red1", "Espia1", "Espia2", 0.1); 
        redEspias.establecerCamino("Red1", "Espia1", "Espia3", 0.15); 
        redEspias.establecerCamino("Red1", "Espia2", "Espia4", 0.05);
        redEspias.establecerCamino("Red1", "Espia3", "Espia4", 0.2); 
        redEspias.establecerCamino("Red1", "Espia2", "Espia5", 0.3); 
        redEspias.establecerCamino("Red1", "Espia4", "Espia5", 0.25); 
        redEspias.establecerCamino("Red1", "Espia5", "Espia6", 0.08);
        redEspias.establecerCamino("Red1", "Espia6", "Espia7", 0.12); 
        redEspias.establecerCamino("Red1", "Espia1", "Espia6", 0.18); 
        redEspias.establecerCamino("Red1", "Espia3", "Espia7", 0.07); 

        redEspias.establecerCaminoMasSeguro("Red1");

        redEspias.guardarComoJSON("redEspias.json");

        RedEspias nuevaRedEspias = RedEspias.leerJSON(System.getProperty("user.home") + "/informacion/redEspias.json");

        if (nuevaRedEspias != null) {
            System.out.println("Archivo JSON leído correctamente.");
        } else {
            System.out.println("Error al leer el archivo JSON.");
        }
    }


}
