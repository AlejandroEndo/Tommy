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

	public Entrenamiento(PApplet app) {
		this.app = app;

		finalizado = false;

		jConcentracion = new JuegoConcentracion(app);
		jMemoria = new JuegoMemoria(app);
		jMotricidad = new JuegoMotricidad(app);
		jComunicacion = new JuegoComunicacion(app);

		puntaje = app.loadImage("../data/entrenamientoI.png");
	}

	public void draw() {
		switch (juego) {
		case 0:
			jConcentracion.draw();

			if (jConcentracion.getPuntajeLocal() >= 200) {
				juego = 4;
			}
			break;

		case 1:
			jMemoria.draw();
			if ((jMemoria.getPuntajeLocal() >= 60 && jMemoria.getLvl() == 0)
					|| (jMemoria.getPuntajeLocal() >= 80 && jMemoria.getLvl() == 1)) {
				juego = 4;
			}
			break;

		case 2:
			jMotricidad.draw();
			if (jMotricidad.getPuntajeLocal() >= 200) {
				juego = 4;
			}
			break;

		case 3:
			jComunicacion.draw();
			if (jComunicacion.getPuntajeLocal() >= 200) {
				juego = 4;
			}
			break;

		case 4:
			app.image(puntaje, app.width / 2, app.height / 2);
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
