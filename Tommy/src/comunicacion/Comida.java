package comunicacion;

import processing.core.PApplet;
import processing.core.PImage;

public class Comida {

	private PApplet app;

	private PImage[] comida = new PImage[4];
	private String[] comidas = { "Helado", "Perro", "Bombom", "Dona" };

	private float x;
	private float y;

	private int id;

	public Comida(PApplet app, float x, float y, int id) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.id = id;

		comida[0] = app.loadImage("../data/cohelado.png");
		comida[1] = app.loadImage("../data/coperro.png");
		comida[2] = app.loadImage("../data/cobombom.png");
		comida[3] = app.loadImage("../data/codona.png");
	}

	public void draw() {
		app.image(comida[id], x, y);
	}

	public void texto() {
		app.textSize(60);
		app.text(comidas[id], 300, 250);
	}

	public String[] getComidas() {
		return comidas;
	}

	public void setComidas(String[] comidas) {
		this.comidas = comidas;
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
