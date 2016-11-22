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
	
	private float promedioPuntos;
	private float entrenamiento;
	
	private float escalaP;
	private float escalaE;
	
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
		escalaE = PApplet.map(entrenamiento, 0, 10000, 0, 150);
		escalaP = PApplet.map(promedioPuntos, 0, 1000, 0, 150); 
		if (trans >= 100) {
			if (tam[0] > -150) {
				tam[0] += -1 * PApplet.dist(tam[0], tam[0], -escalaE, -escalaE) * 0.02f;
			}
			if (tam[1] > -150) {
				tam[1] += -1 * PApplet.dist(tam[1], tam[1], -escalaP, -escalaP) * 0.02f;
			}
			if (tam[2] > -150) {
				tam[2] += -1 * PApplet.dist(tam[2], tam[2], -((escalaE+escalaP)/2), -((escalaE+escalaP)/2)) * 0.02f;
			}
			if (tam[3] > -150) {
				tam[3] += -1 * PApplet.dist(tam[3], tam[3], -150, -150) * 0.02f;
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

	public float[] getTam() {
		return tam;
	}
	
	public void setTam(float[] tam) {
		this.tam = tam;
	}
	
	public float getEntrenamiento() {
		return entrenamiento;
	}
	
	public void setEntrenamiento(float entrenamiento) {
		this.entrenamiento = entrenamiento;
	}
	
	public float getTrans() {
		return trans;
	}

	public void setTrans(float trans) {
		this.trans = trans;
	}
	
	public float getPromedioPuntos() {
		return promedioPuntos;
	}
	
	public void setPromedioPuntos(float promedioPuntos) {
		this.promedioPuntos = promedioPuntos;
	}
}
