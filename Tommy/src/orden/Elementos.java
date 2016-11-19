package orden;

import processing.core.PApplet;

public class Elementos {

	private PApplet app;

	private float x;
	private float y;
	private float col;
	private float trans;
	private float s;
	private int id;

	public Elementos(PApplet app, int id, float x, float y, float s, float col) {
		this.app = app;
		this.id = id;
		this.x = x;
		this.y = y;
		this.s = s;
		this.col = col;
	}

	public void draw() {
		app.fill(col, 100, 100);
		app.strokeWeight(10);
		app.stroke(col, 80, 80, trans);
		app.ellipse(x, y, s, s);
		app.fill(0);
		app.text(id, x, y);
		app.noStroke();
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

	public float getTrans() {
		return trans;
	}

	public void setTrans(float trans) {
		this.trans = trans;
	}

	public float getS() {
		return s;
	}

	public void setS(float s) {
		this.s = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
