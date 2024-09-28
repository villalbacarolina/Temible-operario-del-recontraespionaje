package logica;

public class Arista {
	@SuppressWarnings("unused")
	private int nombreEspiaOrigen;
	@SuppressWarnings("unused")
	private int nombreEspiaDestino;
	private int probabilidad;
	
	public Arista(int nombreEspiaOrigen, int nombreEspiaDestino, int probabilidad) {
		this.nombreEspiaOrigen = nombreEspiaOrigen;
		this.nombreEspiaDestino = nombreEspiaDestino;
		this.probabilidad = probabilidad;
	}

	public int obtenerProbabilidad() {
		return probabilidad;
	}
	

	
	
}
