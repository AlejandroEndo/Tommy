package menu;

import processing.core.PApplet;
import processing.core.PConstants;
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

	private float[] tam = new float[4];

	private float concentracionP;
	private float memoriaP;
	private float motricidadP;
	private float comunicacionP;
	private float trans;
	
	private float escalaCon;
	private float escalaMem;
	private float escalaMot;
	private float escalaCom;
	

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
		escalaCon = PApplet.map(concentracionP, 0, 2000, 0, 150);
		escalaMem = PApplet.map(memoriaP, 0, 2000, 0, 150);
		escalaMot = PApplet.map(motricidadP, 0, 2000, 0, 150);
		escalaCom = PApplet.map(comunicacionP, 0, 2000, 0, 150);
		if (trans >= 100) {
			if (tam[0] > -150) {
				tam[0] += -1 * PApplet.dist(tam[0], tam[0], -escalaCon, -escalaCon) * 0.02f;
			}
			if (tam[1] > -150) {
				tam[1] += -1 * PApplet.dist(tam[1], tam[1], -escalaMem, -escalaMem) * 0.02f;
			}
			if (tam[2] > -150) {
				tam[2] += -1 * PApplet.dist(tam[2], tam[2], -escalaMot, -escalaMot) * 0.02f;
			}
			if (tam[3] > -150) {
				tam[3] += -1 * PApplet.dist(tam[3], tam[3], -escalaCom, -escalaCom) * 0.02f;
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

	public void click() {

	}

	public float getTrans() {
		return trans;
	}

	public void setTrans(float trans) {
		this.trans = trans;
	}

	public float getConcentracionP() {
		return concentracionP;
	}

	public void setConcentracionP(float concentracionP) {
		this.concentracionP = concentracionP;
	}

	public float getMemoriaP() {
		return memoriaP;
	}

	public void setMemoriaP(float memoriaP) {
		this.memoriaP = memoriaP;
	}

	public float getMotricidadP() {
		return motricidadP;
	}

	public void setMotricidadP(float motricidadP) {
		this.motricidadP = motricidadP;
	}

	public float getComunicacionP() {
		return comunicacionP;
	}

	public void setComunicacionP(float comunicacionP) {
		this.comunicacionP = comunicacionP;
	}
	
	
}
