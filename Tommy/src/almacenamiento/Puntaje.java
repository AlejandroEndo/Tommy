package almacenamiento;

public class Puntaje {

	private int entrenamiento;
	private int concentracion;
	private int memoria;
	private int motricidad;
	private int comunicacion;

	public Puntaje(int entrenamiento, int concentracion, int memoria, int motricidad, int comunicacion) {
		this.entrenamiento = entrenamiento;
		this.concentracion = concentracion;
		this.memoria = memoria;
		this.motricidad = motricidad;
		this.comunicacion = comunicacion;
	}

	public int getEntrenamiento() {
		return entrenamiento;
	}

	public void setEntrenamiento(int entrenamiento) {
		this.entrenamiento = entrenamiento;
	}

	public int getConcentracion() {
		return concentracion;
	}

	public void setConcentracion(int concentracion) {
		this.concentracion = concentracion;
	}

	public int getMemoria() {
		return memoria;
	}

	public void setMemoria(int memoria) {
		this.memoria = memoria;
	}

	public int getMotricidad() {
		return motricidad;
	}

	public void setMotricidad(int motricidad) {
		this.motricidad = motricidad;
	}

	public int getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(int comunicacion) {
		this.comunicacion = comunicacion;
	}

}
