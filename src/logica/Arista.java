package logica;

public class Arista {
	@SuppressWarnings("unused")
	private String nombreEspiaOrigen;
	@SuppressWarnings("unused")
	private String nombreEspiaDestino;
	@SuppressWarnings("unused")
	private Integer probabilidad;
	
	public Arista(String nombreEspiaOrigen, String nombreEspiaDestino, Integer probabilidad){
		this.nombreEspiaOrigen=nombreEspiaOrigen;
		this.nombreEspiaDestino=nombreEspiaDestino;
		this.probabilidad=probabilidad;
	}
}
