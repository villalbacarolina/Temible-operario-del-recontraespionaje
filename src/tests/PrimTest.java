package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import logica.Arista;
import logica.Grafo;
import logica.Prim;

public class PrimTest {

	Grafo grafoPrueba;
	
	@Before 
	public void inicializarGrafoPrueba() throws Exception{
		
		grafoPrueba=new Grafo();
		grafoPrueba.agregarVertice("A");
		grafoPrueba.agregarVertice("B");
		grafoPrueba.agregarVertice("C");
		grafoPrueba.agregarVertice("D");
		grafoPrueba.agregarVertice("E");
		grafoPrueba.agregarArista("A", "B", 1);
		grafoPrueba.agregarArista("A", "C", 2);
		grafoPrueba.agregarArista("B", "C", 4);
		grafoPrueba.agregarArista("A", "D", 1);
		grafoPrueba.agregarArista("D", "E", 1);	
		
	}
	
	@Test
	public void grafoNormaltest() {
		
		List<Arista>AGM = Prim.prim(grafoPrueba);
		assertEquals(4,AGM.size());
	}
	
	@Test
	public void unaSolaAristatest() {
	    Grafo g = new Grafo();
	    g.agregarVertice("A");
	    g.agregarVertice("B");
	    g.agregarArista("A", "B", 5.0); 

	    List<Arista> AGM = Prim.prim(g);
	    assertEquals(1, AGM.size());
	    assertEquals(5.0, g.obtenerPesoArista("A", "B"), 0.0001);
	}

	
	@Test 
	public void sumaPesosAristaTest() {
		
		List<Arista>AGM = Prim.prim(grafoPrueba);
		int pesoTotalAGM = sumaPesosAGM(AGM);
		assertEquals(5, pesoTotalAGM);
	}
	
	@Test(expected = RuntimeException.class)
	public void grafoVaciotest() {
		
		Grafo g = new Grafo();
		Prim.prim(g);
		
	}
	
	@Test(expected = RuntimeException.class)
	public void grafoNoConexotest() {
		
		grafoPrueba.agregarVertice("Z");
		Prim.prim(grafoPrueba);
		
	}
	
	//Metodo Auxiliar
	
	public int sumaPesosAGM(List<Arista>aristas) {
		int sumaPesos=0;
		for(Arista a : aristas) {
			sumaPesos+=a.getPeso();
		}
		return sumaPesos;
	}

}
