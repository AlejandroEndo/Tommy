package concentracion;

import processing.core.PApplet;
import processing.core.PImage;

public class Panda {

	private PApplet app;

	private PImage creverso;

	private PImage panda;
	private PImage arcoPanda;

	private float x;
	private float y;

	private int id;

	private boolean visible;

	public Panda(PApplet app, float x, float y, int id) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.id = id;

		visible = false;

		creverso = app.loadImage("../data/creverso.png");

		panda = app.loadImage("../data/cpanda.png");
		arcoPanda = app.loadImage("../data/carcopanda.png");
	}

	public void draw() {

		if (visible) {
			if (id == 0) {
				app.image(panda, x, y);
			} else {
				app.image(arcoPanda, x, y);
			}
		} else {
			app.image(creverso, x, y);
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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

}
