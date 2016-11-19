package orden;

import java.util.ArrayList;

import processing.core.PApplet;

public class JuegoOrden {

	private PApplet app;

	private ArrayList<Elementos> elementos = new ArrayList<>();

	private int orden;

	public JuegoOrden(PApplet app) {
		this.app = app;

		for (int i = 0; i < 6; i++) {
			float s = app.random(50, 100);
			elementos.add(new Elementos(app, i, app.random(s, app.width - s), app.random(s, app.height - s), s,
					app.random(360)));
		}
	}

	public void draw() {
		for (int i = 0; i < elementos.size(); i++) {
			Elementos e = elementos.get(i);
			e.draw();
		}
		System.out.println(orden);

		if (elementos.isEmpty()) {
			app.text("GANADOR", app.width / 2, app.height / 2);
		}

		for (int i = 0; i < elementos.size(); i++) {
			Elementos e = elementos.get(i);
			if (PApplet.dist(e.getX(), e.getY(), app.mouseX, app.mouseY) < e.getS() / 2) {
				e.setTrans(100);
			} else{
				e.setTrans(0);
			}
		}
	}

	public void click() {
		for (int i = 0; i < elementos.size(); i++) {
			Elementos e = elementos.get(i);
			if (PApplet.dist(e.getX(), e.getY(), app.mouseX, app.mouseY) < e.getS() / 2) {
				if (e.getId() == orden) {
					orden++;
					elementos.remove(i);
				}
			}
		}
	}

}
