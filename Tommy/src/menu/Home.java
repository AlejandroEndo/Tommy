package menu;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Home {

	private PApplet app;

	private PImage fondo;
	private PImage fondoH;

	private PImage tommy;

	private PImage informacion;
	private PImage entrenate;
	private PImage puntos;
	private PImage juegos;

	private float[] tam = new float[4];

	private float trans;

	public Home(PApplet app) {
		this.app = app;

		fondoH = app.loadImage("../data/fondoHueco.png");
		fondo = app.loadImage("../data/fondoBasico.png");

		tommy = app.loadImage("../data/tommy.png");

		informacion = app.loadImage("../data/informacion.png");
		entrenate = app.loadImage("../data/entrenate.png");
		puntos = app.loadImage("../data/puntos.png");
		juegos = app.loadImage("../data/juegos.png");

	}

	public void draw() {
		trans += 2;
		app.image(fondo, app.width / 2, app.height / 2);
		animacion();
		app.image(fondoH, app.width / 2, app.height / 2);

		app.image(tommy, app.width / 4, app.height / 2);

		app.tint(360, trans);
		app.image(entrenate, 778, 251);
		app.image(juegos, 1015, 251);
		app.image(puntos, 778, 445);
		app.image(informacion, 1015, 445);
		app.noTint();

		app.fill(277, 47, 46);
		app.textSize(70);
		app.text("Menu", app.width / 2, 100);
		// animacion();
	}

	private void animacion() {
		if (trans >= 100) {
			if (tam[3] > -150) {
				tam[3] += -1 * PApplet.dist(tam[3], tam[3], -100, -100) * 0.02f;
			}
		}
		
		app.rectMode(PConstants.CENTER);
		app.fill(175, 50, 95);
		// Entrenate
		app.rect(778, 251, 150, 150);
		// Juegos
		app.rect(1015, 251, 150, 150);
		// Puntos
		app.rect(778, 445, 150, 150);
		// Informacion
		app.rect(1015, 445, 150, 150);

		app.rectMode(PConstants.CORNER);
		app.fill(56, 40, 93);
		// Entrenate
		app.rect(703, 326, 150, tam[0]);
		// Juegos
		app.rect(940, 326, 150, tam[1]);
		// Puntos
		app.rect(703, 518, 150, tam[2]);
		// Informacion
		app.rect(940, 518, 150, tam[3]);
	}

	public float getTrans() {
		return trans;
	}

	public void setTrans(float trans) {
		this.trans = trans;
	}
}
