package concentracion;

import processing.core.PApplet;
import processing.core.PImage;

public class lvlUno {

	private PApplet app;

	private PImage creverso;

	private PImage panda;
	private PImage arcoPanda;

	private PImage pelota;
	private PImage arcoPelota;

	private PImage tambor;
	private PImage arcoTambor;

	private PImage tigre;
	private PImage arcoTigre;

	private float x;
	private float y;

	private int id;

	private int selector;

	private boolean visible;

	public lvlUno(PApplet app, float x, float y, int id, int selector) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.id = id;
		this.selector = selector;

		visible = false;

		creverso = app.loadImage("../data/creverso.png");

		panda = app.loadImage("../data/cpanda.png");
		arcoPanda = app.loadImage("../data/carcopanda.png");

		pelota = app.loadImage("../data/cpelota.png");
		arcoPelota = app.loadImage("../data/carcopelota.png");

		tambor = app.loadImage("../data/ctambor.png");
		arcoTambor = app.loadImage("../data/carcotambor.png");

		tigre = app.loadImage("../data/ctigre.png");
		arcoTigre = app.loadImage("../data/carcotigre.png");
	}

	public void draw() {

		if (visible) {
			switch (selector) {
			case 0:
				if (id == 0) {
					app.image(panda, x, y);
				} else {
					app.image(arcoPanda, x, y);
				}
				break;
				
			case 1:
				if (id == 0) {
					app.image(pelota, x, y);
				} else {
					app.image(arcoPelota, x, y);
				}
				break;
				
			case 2:
				if (id == 0) {
					app.image(tambor, x, y);
				} else {
					app.image(arcoTambor, x, y);
				}
				break;
				
			case 3:
				if (id == 0) {
					app.image(tigre, x, y);
				} else {
					app.image(arcoTigre, x, y);
				}
				break;
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
