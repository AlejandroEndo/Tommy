package almacenamiento;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import processing.data.XML;

public class BaseDeDatos {

	private XML xml;

	private String ruta;

	private int entrenamiento;
	private int concentracion;
	private int memoria;
	private int motricidad;
	private int comunicacion;

	public BaseDeDatos(String ruta) {
		this.ruta = ruta;

		File archivo = new File(ruta);
		if (archivo.exists()) {
			try {
				xml = new XML(archivo);
			} catch (IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
		} else {
			xml = new XML("datos");
		}
	}

	public void agregarDato(Puntaje p) {
		XML nuevo = new XML("dato");
		nuevo.setInt("entrenamiento", p.getEntrenamiento());
		nuevo.setInt("concentracion", p.getConcentracion());
		nuevo.setInt("memoria", p.getMemoria());
		nuevo.setInt("motricidad", p.getMotricidad());
		nuevo.setInt("comunicacion", p.getComunicacion());
		XML removible = xml.getChild("dato");
		xml.removeChild(removible);
		xml.addChild(nuevo);
		xml.save(new File(ruta));
	}

	public void leer() {
		XML lemao = xml.getChild("dato");
		entrenamiento = lemao.getInt("entrenamiento");
		concentracion = lemao.getInt("concentracion");
		memoria = lemao.getInt("memoria");
		motricidad = lemao.getInt("motricidad");
		comunicacion = lemao.getInt("comunicacion");
		// System.out.println(concentracion);
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
