package logica;

import java.io.Serializable;

public class Arista implements Comparable<Arista>, Serializable {
    private static final long serialVersionUID = 1L;

    private String origen, destino;
    private double peso;

    public Arista(String origen, String destino, double peso) { 
        this.setOrigen(origen);
        this.setDestino(destino);
        this.setPeso(peso);
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPeso() { 
        return peso; 
    }

    public void setPeso(double peso) { 
        this.peso = peso; 
    }

    @Override
    public int compareTo(Arista otra) {
        return Double.compare(this.getPeso(), otra.getPeso()); //ordenar por peso
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Arista arista = (Arista) obj;
        return Double.compare(arista.peso, peso) == 0 && //comparar pesos como double
                origen.equals(arista.origen) &&
                destino.equals(arista.destino);
    }
}
