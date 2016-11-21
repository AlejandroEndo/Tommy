package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Home extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage fondoH;

	private PImage tommy;

	private PImage informacion;
	private PImage entrenate;
	private PImage puntos;
	private PImage juegos;

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

		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				trans++;
				sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void draw() {
		app.image(fondo, app.width / 2, app.height / 2);
		app.fill(180, 100, 100);
		app.rect(660, 200, 200, 150);
		app.image(fondoH, app.width / 2, app.height / 2);

		app.image(tommy, app.width / 4, app.height / 2);

		app.tint(360, trans);
		app.image(juegos, 778, 251);
		app.image(entrenate, 1015, 251);
		app.image(puntos, 778, 445);
		app.image(informacion, 1015, 445);
		app.noTint();
	}

	public float getTrans() {
		return trans;
	}

	public void setTrans(float trans) {
		this.trans = trans;
	}
}
