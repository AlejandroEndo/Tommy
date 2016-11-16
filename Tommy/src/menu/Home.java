package menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Home {

	private PApplet app;

	private PImage fondoH;

	private PImage tommy;

	private PImage informacion;
	private PImage entrenate;
	private PImage puntos;
	private PImage juegos;

	public Home(PApplet app) {
		this.app = app;

		fondoH = app.loadImage("../data/fondoHueco.png");

		tommy = app.loadImage("../data/tommy.png");

		fondoH = app.loadImage("../data/fondoHueco.png");
		fondoH = app.loadImage("../data/fondoHueco.png");
		fondoH = app.loadImage("../data/fondoHueco.png");
		fondoH = app.loadImage("../data/fondoHueco.png");
		fondoH = app.loadImage("../data/fondoHueco.png");
	}

	public void draw() {
		app.image(fondoH, app.width / 2, app.height / 2);
	}
}
