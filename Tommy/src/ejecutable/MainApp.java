package ejecutable;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class MainApp extends PApplet {

	private Logica logica;

	public static void main(String[] args) {
		PApplet.main("ejecutable.MainApp");
	}

	@Override
	public void settings() {
		size(1200, 700);
		smooth();
	}

	@Override
	public void setup() {
		noStroke();
		colorMode(HSB, 360, 100, 100, 100);
		logica = new Logica(this);
	}

	@Override
	public void draw() {
		background(360);
		logica.draw();
	}

	@Override
	public void mousePressed() {
		logica.click();
	}

	@Override
	public void mouseDragged() {
		logica.drag();
	}

	@Override
	public void mouseReleased() {
		logica.ress();
	}

}