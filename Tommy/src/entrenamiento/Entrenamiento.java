package entrenamiento;

import comunicacion.JuegoComunicacion;
import concentracion.JuegoConcentracion;
import memoria.JuegoMemoria;
import motricidad.JuegoMotricidad;
import processing.core.PApplet;
import processing.core.PImage;

public class Entrenamiento {

	private PApplet app;

	private PImage puntaje;

	private JuegoConcentracion jConcentracion;
	private JuegoMemoria jMemoria;
	private JuegoMotricidad jMotricidad;
	private JuegoComunicacion jComunicacion;

	private int juego;
	private int puntajeLocal;
	private int progreso;

	private boolean inicio;
	private boolean finalizado;
	private boolean guarde;
	
	private String nuevoReloj;

	public Entrenamiento(PApplet app) {
		this.app = app;

		finalizado = false;

		jConcentracion = new JuegoConcentracion(app);
		jMemoria = new JuegoMemoria(app);
		jMotricidad = new JuegoMotricidad(app);
		jComunicacion = new JuegoComunicacion(app);
		
		guarde = false;

		puntaje = app.loadImage("../data/entrenamientoI.png");
	}

	public void draw() {
		switch (juego) {
		case 0:
			jConcentracion.draw();
			nuevoReloj = jConcentracion.getReloj();
			if (jConcentracion.getPuntajeLocal() >= 200) {
				puntajeLocal += jConcentracion.getPuntajeLocal();
				juego = 4;
			}
			break;

		case 1:
			jMemoria.draw();
			nuevoReloj = jMemoria.getReloj();
			if ((jMemoria.getPuntajeLocal() >= 60 && jMemoria.getLvl() == 0)
					|| (jMemoria.getPuntajeLocal() >= 80 && jMemoria.getLvl() == 1)) {
				puntajeLocal += jMemoria.getPuntajeLocal();
				juego = 4;
			}
			break;

		case 2:
			jMotricidad.draw();
			nuevoReloj = jMotricidad.getReloj();
			if (jMotricidad.getPuntajeLocal() >= 200) {
				puntajeLocal += jMotricidad.getPuntajeLocal();
				juego = 4;
			}
			break;

		case 3:
			jComunicacion.draw();
			nuevoReloj = jComunicacion.getReloj();
			if (jComunicacion.getPuntajeLocal() >= 200) {
				puntajeLocal += jComunicacion.getPuntajeLocal();
				juego = 4;
			}
			break;

		case 4:
			app.image(puntaje, app.width / 2, app.height / 2);
			app.text(nuevoReloj, 900, 246);
			app.text(puntajeLocal, 900, 410);
			break;
		}
	}

	public void click() {
		switch (juego) {
		case 0:
			jConcentracion.click();
			break;

		case 1:
			jMemoria.click();
			break;

		case 2:
			jMotricidad.click();
			break;

		case 3:
			jComunicacion.click();
			break;

		case 4:
			if (app.mouseX > 800 && app.mouseX < 996 && app.mouseY > 537 && app.mouseY < 610) {
				progreso++;
				juego = progreso;
				guarde = true;
				if (progreso >= 4) {
					finalizado = true;
				}
			}
			break;
		}
	}

	public void drag() {
		if (juego == 3) {
			jComunicacion.drag();
		}
	}

	public void res() {
		if (juego == 3) {
			jComunicacion.res();
		}
	}

	public void reiniciar() {
		jConcentracion.reiniciar();
		jMemoria.reiniciar();
		jMotricidad.reiniciar();
		jComunicacion.reiniciar();
		progreso=0;
		juego = 0;
	}
	
	public boolean isGuarde() {
		switch (juego) {
		case 0:
			guarde = jConcentracion.isGuarde();
			break;
		case 1:
			guarde = jMemoria.isGuarde();
			break;
		case 2:
			guarde = jMotricidad.isGuarde();
			break;
		case 3:
			guarde = jComunicacion.isGuarde();
			break;
		}
		return guarde;
	}
	
	public void setGuarde(boolean guarde) {
		this.guarde = guarde;
		switch (juego) {
		case 0:
			jConcentracion.setGuarde(guarde);
			break;
		case 1:
			jMemoria.setGuarde(guarde);
			break;
		case 2:
			jMotricidad.setGuarde(guarde);
			break;
		case 3:
			jComunicacion.setGuarde(guarde);
			break;
		}
	}

	public boolean isIniciar() {
		switch (juego) {
		case 0:
			inicio = jConcentracion.isIniciar();
			break;
		case 1:
			inicio = jMemoria.isIniciar();
			break;
		case 2:
			inicio = jMotricidad.isIniciar();
			break;
		case 3:
			inicio = jComunicacion.isIniciar();
			break;
		}
		return inicio;
	}

	public int getPuntajeLocal() {
		return puntajeLocal;
	}

	public void setPuntajeLocal(int puntajeLocal) {
		this.puntajeLocal = puntajeLocal;
	}

	public int getJuego() {
		return juego;
	}

	public void setJuego(int juego) {
		this.juego = juego;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
}
