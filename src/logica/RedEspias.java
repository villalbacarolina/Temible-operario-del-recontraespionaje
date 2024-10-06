package logica;

import java.io.Serializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RedEspias implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public void establecerCamino(String nombreRed, String nombreEspia1, String nombreEspia2, int probabilidadIntercepcion) {
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

        // Crear un directorio en la carpeta del usuario
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

        // Lee el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            // Deserializa: pasa de .json a RedEspias
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
        redEspias.crearRed("Red2");

        redEspias.agregarEspia("Red1", "Espia1");
        redEspias.agregarEspia("Red1", "Espia2");
        redEspias.agregarEspia("Red1", "Espia3");


        redEspias.establecerCamino("Red1", "Espia1", "Espia2", 50);
        redEspias.establecerCamino("Red1", "Espia1", "Espia3", 4);
        
        redEspias.establecerCaminoMasSeguro("Red1");

        redEspias.guardarComoJSON("redEspias.json");

        // Leer el archivo JSON
        RedEspias nuevaRedEspias = RedEspias.leerJSON(System.getProperty("user.home") + "/informacion/redEspias.json");

        // Comprobar si se leyeron correctamente los datos
        if (nuevaRedEspias != null) {
            System.out.println("Archivo JSON leído correctamente.");
        } else {
            System.out.println("Error al leer el archivo JSON.");
        }
    }
}
