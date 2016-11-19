package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Inicio extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage tommy;

	private int clock;
	private float trans;
	private float x;
	private float y;
	private float s;

	public Inicio(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/fondoBasico.png");
		tommy = app.loadImage("../data/tommy.png");

		x = app.width / 2;
		y = (app.height / 2) + 100;
		s=450;
		trans = 0.0001f;
		
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				clock++;
				if (clock >= 100) {
					if (y > app.height / 2)
						y -= PApplet.dist(y, y, app.height / 2, app.height / 2) * 0.01f;
					trans += 0.5f;
				}

				if (clock >= 400) {
					s++;
					trans -= 2;
				}
				sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void draw() {
		app.image(fondo, app.width / 2, app.height / 2);
		app.tint(360, trans);
		app.image(tommy, x, y, s, s);
		app.noTint();
	}

	public float getTrans() {
		return trans;
	}
}
