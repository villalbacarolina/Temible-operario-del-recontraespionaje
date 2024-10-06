package logica;
import java.io.Serializable;
import java.util.*;

public class Grafo implements Serializable{
	private static final long serialVersionUID = 1L;
	
              //Vertice1  //Vertice2 //Peso
    private Map<String, Map<String, Integer>> vertices;
    private List<Arista> aristas; 
    
    public Grafo() {
        vertices = new HashMap<>();
        aristas = new ArrayList<>();
    }

    public Grafo(List<String> vertices) {
        this();
        for (String vertice : vertices) 
            this.vertices.put(vertice, new HashMap<String, Integer>());
    }
  
  
    public void agregarVertice(String v) {
    	if(existeVertice(v))
    		throw new RuntimeException("El vértice '" +v+ "' ya existe.");
    	vertices.put(v, new HashMap<String, Integer>());
    }

    public void agregarArista(String vertice1, String vertice2, int peso) {
        verificarVertices(vertice1, vertice2);

        vertices.get(vertice1).put(vertice2, peso);
        vertices.get(vertice2).put(vertice1, peso);

        aristas.add(new Arista(vertice1, vertice2, peso));
    }
    
    public void eliminarArista(String vertice1, String vertice2) {
        existeVertice(vertice1);
        existeVertice(vertice2);
        if(existeArista(vertice1, vertice2)) {
        	vertices.get(vertice1).remove(vertice2);
        	vertices.get(vertice2).remove(vertice1);
        
        	aristas.removeIf(arista -> (arista.getOrigen().equals(vertice1) && arista.getDestino().equals(vertice2))
        							|| (arista.getOrigen().equals(vertice2) && arista.getDestino().equals(vertice1)));
        }
        else 
        	throw new IllegalArgumentException("No existe una arista entre " +vertice1+ " y "
        									   +vertice2+ ".");
    }
    
    public void eliminarVertice(String vertice) {
    	vertices.remove(vertice);
    }
    
    public int tamano() {
        return vertices.size();
    }
    
    public String obtenerVerticeAleatorio() {
        if (!vertices.isEmpty()) {
        	String vertice = vertices.keySet().iterator().next();
            return vertice;
        } 
        else 
            throw new RuntimeException("Aún no existen vértices.");
    }

    
    // Getters //
    
    //Pendiente: Mejorar encapsulamiento
    public Map<String, Map<String, Integer>> obtenerVertices() {
        return this.vertices;
    }

    //Pendiente: Mejorar encapsulamiento
    public List<Arista> obtenerAristas() {
        return this.aristas;
    }
    
    //Pendiente: Mejorar encapsulamiento
    public Map<String, Integer> obtenerVecinosVertice(String v) {
        if (existeVertice(v))
            return this.vertices.get(v);
        throw new IllegalArgumentException("Vértice no válido: " + v);
    }
    
    public int obtenerPesoArista(String i, String j) {
        if (existeArista(i, j)) 
            return vertices.get(i).get(j);
        throw new IllegalArgumentException("La arista no existe entre " + i + " y " + j);
    }
    
    // Auxiliares //
    
    private boolean existeVertice(String v) {
        if (!vertices.containsKey(v))
            return false;
        return true;
    }
        
    private boolean existeArista(String i, String j) {
        verificarVertices(i, j);
        return vertices.get(i).containsKey(j);
    }
    
    private void verificarVerticesDistintos(String v1, String v2) {
        if (v1.equals(v2))
            throw new IllegalArgumentException("Los vértices son iguales. No se permiten loops.");
    }

    private void verificarVertices(String v1, String v2) {
        if(!existeVertice(v1))
        	throw new IllegalArgumentException("No existe el vértice '"+v1+"'." );
        if(!existeVertice(v2))
        	throw new IllegalArgumentException("No existe el vértice '"+v2+"'." );
        verificarVerticesDistintos(v1, v2);
    }

}

