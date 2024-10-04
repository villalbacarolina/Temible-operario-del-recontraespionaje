package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logica.Arista;

public class AristaTest {

    @Test
    public void testConstructor() {
        // Creamos una arista y verificamos sus atributos usando los getters
        Arista arista = new Arista("A", "B", 5);
        assertEquals("A", arista.getOrigen());
        assertEquals("B", arista.getDestino());
        assertEquals(5, arista.getPeso()); // Mismo peso
    }

    @Test
    public void testGettersAndSetters() {
        // Creamos una arista y modificamos los valores usando los setters, luego verificamos los getters
        Arista arista = new Arista("A", "B", 10);
        arista.setOrigen("X");
        arista.setDestino("Y");

        assertEquals("X", arista.getOrigen());
        assertEquals("Y", arista.getDestino());
    }

    @Test
    public void testCompareToMayorPeso() {
        // Comparamos dos aristas donde la primera tiene mayor peso
        Arista arista1 = new Arista("A", "B", 10);
        Arista arista2 = new Arista("A", "C", 5);
        assertTrue(arista1.compareTo(arista2) > 0);
    }

    @Test
    public void testCompareToMenorPeso() {
        // Comparamos dos aristas donde la primera tiene menor peso
        Arista arista1 = new Arista("A", "B", 3);
        Arista arista2 = new Arista("A", "C", 7);
        assertTrue(arista1.compareTo(arista2) < 0);
    }

    @Test
    public void testCompareToIgualPeso() {
        // Comparamos dos aristas con el mismo peso
        Arista arista1 = new Arista("A", "B", 5);
        Arista arista2 = new Arista("A", "C", 5);
        assertEquals(0, arista1.compareTo(arista2));
    }

    @Test
    public void testIgualdadAristas() {
        // Verificamos que dos aristas con los mismos valores sean iguales en atributos
        Arista arista1 = new Arista("A", "B", 5);
        Arista arista2 = new Arista("A", "B", 5);
        assertEquals(arista1.getOrigen(), arista2.getOrigen());
        assertEquals(arista1.getDestino(), arista2.getDestino());
        assertEquals(arista1.compareTo(arista2), 0); // Mismo peso
    }
}


