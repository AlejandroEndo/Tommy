package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Inicio {

	private PApplet app;

	private PImage fondo;
	private PImage tommy;

	public Inicio(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/fondoBasico.png");
		tommy = app.loadImage("../data/tommy.png");
	}

	public void draw() {
		app.image(fondo, app.width / 2, app.height / 2);
		app.image(tommy, app.width / 2, app.height / 2);
	}
}
