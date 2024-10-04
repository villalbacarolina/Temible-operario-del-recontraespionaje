package logica;

public class Arista implements Comparable<Arista> {
    private String origen, destino;
    private int peso;

    public Arista(String origen, String destino, int peso) {
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

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.getPeso(), otra.getPeso()); //Ordenar por peso
    }
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Arista arista = (Arista) obj;
        return peso == arista.peso &&
                origen.equals(arista.origen) &&
                destino.equals(arista.destino);
    }
}
