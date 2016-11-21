package memoria;

import processing.core.PApplet;
import processing.core.PImage;

public class Fruta {

	private PApplet app;

	private PImage[] mc = new PImage[5];
	private PImage reverso;

	private float x;
	private float y;

	private int id;

	private boolean visible;

	public Fruta(PApplet app, float x, float y, int id) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.id = id;

		visible = false;

		for (int i = 1; i < 6; i++) {
			mc[i-1] = app.loadImage("../data/mc" + i + ".png");
		}
		reverso = app.loadImage("../data/mreverso.png");
	}

	public void draw() {
		if (visible)
			app.image(mc[id], x, y);
		else
			app.image(reverso, x, y);
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

}
