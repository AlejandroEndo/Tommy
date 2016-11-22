package motricidad;

import processing.core.PApplet;
import processing.core.PImage;

public class Figura {

	private PApplet app;

	private PImage[] figuras = new PImage[4];

	private float h;
	private float x;
	private float y;

	private int id;
	private int s;

	public Figura(PApplet app, float x, float y, int id, int s) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.id = id;
		this.s = s;

		h = app.random(360);

		figuras[0] = app.loadImage("../data/mocarro.png");
		figuras[1] = app.loadImage("../data/mohelado.png");
		figuras[2] = app.loadImage("../data/mojirafa.png");
		figuras[3] = app.loadImage("../data/mopanda.png");
	}

	public void draw() {
		if (s == 0) {
			app.tint(h, 80, 80);
		}
		app.image(figuras[id], x, y);
		app.noTint();
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

}
