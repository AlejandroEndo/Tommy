package color;

import processing.core.PApplet;
import processing.core.PImage;

public class Color {

	private PApplet app;

	private PImage image;

	private float x;
	private float y;
	private float tam;
	private int id;

	public Color(PApplet app, float x, float y, int id) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.id = id;

		tam = 200;
	}

	public void draw() {
		app.fill(id, 100, 100);
		app.ellipse(x, y, tam, tam);
	}

	public void arcoDraw() {
		app.fill(id, 100, 100, 50);
		app.ellipse(x, y, tam, tam);
	}

	public PImage getImage() {
		return image;
	}

	public void setImage(PImage image) {
		this.image = image;
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

	public float getTam() {
		return tam;
	}

	public void setTam(float tam) {
		this.tam = tam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
