package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Juego {

	private PApplet app;

	private PImage fondoH;
	private PImage fondo;

	private PImage tommy;

	private PImage concentracion;
	private PImage memoria;
	private PImage motricidad;
	private PImage comunicacion;

	private float trans;

	public Juego(PApplet app) {
		this.app = app;

		fondoH = app.loadImage("../data/fondoHueco.png");
		fondo = app.loadImage("../data/fondoBasico.png");

		tommy = app.loadImage("../data/tommy.png");

		concentracion = app.loadImage("../data/concentracion.png");
		memoria = app.loadImage("../data/memoria.png");
		motricidad = app.loadImage("../data/motricidad.png");
		comunicacion = app.loadImage("../data/comunicacion.png");
	}

	public void draw() {
		trans += 2;
		app.image(fondo, app.width / 2, app.height / 2);
		animacion();
		app.image(fondoH, app.width / 2, app.height / 2);

		app.image(tommy, app.width / 4, app.height / 2);

		app.tint(360, trans);
		app.image(concentracion, 778, 251);
		app.image(memoria, 1015, 251);
		app.image(motricidad, 778, 445);
		app.image(comunicacion, 1015, 445);
		app.noTint();

		app.fill(277, 47, 46);
		app.textSize(70);
		app.text("Juego", app.width / 2, 100);
	}

	private void animacion() {

	}

	public float getTrans() {
		return trans;
	}

	public void setTrans(float trans) {
		this.trans = trans;
	}
}
