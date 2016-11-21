package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Puntos {

	private PApplet app;

	private PImage puntos;

	public Puntos(PApplet app) {
		this.app = app;

		puntos = app.loadImage("../data/puntosFondo.png");
	}

	public void draw() {
		app.image(puntos, app.width / 2, app.height / 2);
	}

	public void click() {

	}
}
