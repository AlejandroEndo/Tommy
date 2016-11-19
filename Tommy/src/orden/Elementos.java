package orden;

import processing.core.PApplet;

public class Elementos {

	private PApplet app;

	private float x;
	private float y;
	private float col;
	private float trans;
	private float s;
	private float id;

	public Elementos(PApplet app, float id, float x, float y, float s) {
		this.app = app;
		this.id = id;
		this.x = x;
		this.y = y;
		this.s = s;
	}

	public void draw() {
		app.fill(col, trans);
		app.stroke(col, 80, 80, trans);
		app.ellipse(x, y, s, s);
		app.text(id, x, y);
		app.noStroke();
	}
}
