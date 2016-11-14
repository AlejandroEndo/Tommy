package color;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class JuegoColor extends Thread {

	private PApplet app;

	private boolean hilo;

	private int s;
	private int m;
	private int cant;
	private int pantalla;

	private PVector[] pos = new PVector[3];

	private ArrayList<Color> colores = new ArrayList<>();
	private ArrayList<Color> arColores = new ArrayList<>();

	private Object o = null;

	public JuegoColor(PApplet app) {
		this.app = app;

		cant = 3;
		hilo = false;

		colores.add(new Color(app, app.random(50, app.width / 2), app.random(50, app.height / cant), 240));
		colores.add(
				new Color(app, app.random(50, app.width / 2), app.random(app.height / cant, app.height / cant * 2), 0));
		colores.add(new Color(app, app.random(50, app.width / 2),
				app.random(app.height / cant * 2, (app.height / cant) * 3) - 50, 120));

		for (int i = 0; i < colores.size(); i++) {
			pos[i] = new PVector(colores.get(i).getX(), colores.get(i).getY());
		}

		arColores.add(new Color(app, (app.width / 4) * 3, ((app.height / cant) * 1) - 50, 0));
		arColores.add(new Color(app, (app.width / 4) * 3, ((app.height / cant) * 2) - 50, 120));
		arColores.add(new Color(app, (app.width / 4) * 3, ((app.height / cant) * 3) - 50, 240));

		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				s++;
				if (s >= 60) {
					s = 0;
					m++;
				}
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void draw() {
		if (hilo) {
			start();
			hilo = false;
		}

		switch (pantalla) {

		case 0:// instrucciones
			app.text("Estas son unas instrucciones jocosas", app.width / 2, app.height / 2);
			if (s >= 2) {
				s = 0;
				pantalla = 1;
			}
			break;

		case 1:// juego
			for (int i = 0; i < colores.size(); i++) {
				Color c = colores.get(i);
				c.draw();
			}

			for (int i = 0; i < arColores.size(); i++) {
				Color c = arColores.get(i);
				c.arcoDraw();
			}

			break;

		case 2:// resumen

			break;
		}
	}

	private void validar() {
		for (int i = 0; i < colores.size(); i++) {
			for (int j = 0; j < arColores.size(); j++) {
				Color a = arColores.get(j);
				Color c = colores.get(i);

				if (PApplet.dist(c.getX(), c.getY(), a.getX(), a.getY()) < c.getTam()) {
					if (a.getId() == c.getId()) {
						c.setX(a.getX());
						c.setY(a.getY());
					} else {
						c.setX(pos[i].x);
						c.setY(pos[i].y);
					}
				}
			}
		}
	}

	public void click() {
		if (o == null) {
			for (int i = 0; i < colores.size(); i++) {
				Color c = colores.get(i);
				if (PApplet.dist(c.getX(), c.getY(), app.mouseX, app.mouseY) < c.getTam()) {
					o = c;
					c.setX(app.mouseX);
					c.setY(app.mouseY);
				}
			}
		}
	}

	public void drag() {
		if (o instanceof Color) {
			((Color) o).setX(app.mouseX);
			((Color) o).setY(app.mouseY);
		}
	}

	public void ress() {
		if (o != null)
			o = null;
		validar();
	}

	public boolean isHilo() {
		return hilo;
	}

	public void setHilo(boolean hilo) {
		this.hilo = hilo;
	}
}
