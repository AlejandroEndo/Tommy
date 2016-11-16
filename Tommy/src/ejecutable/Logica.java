package ejecutable;

import java.util.ArrayList;

import color.JuegoColor;
import menu.Home;
import menu.Inicio;
import processing.core.PApplet;

public class Logica {

	private PApplet app;

	private Inicio inicio;
	private Home home;

	private JuegoColor jColor;

	private int pantalla;

	public Logica(PApplet app) {
		this.app = app;

		inicio = new Inicio(app);
		home = new Home(app);

		jColor = new JuegoColor(app);
	}

	public void draw() {
		switch (pantalla) {

		case 0:
			inicio.draw();
			break;

		case 1:
			home.draw();
			break;

		case 2:
			jColor.draw();
			break;
		}
	}

	public void click() {
		jColor.click();
	}

	public void drag() {
		jColor.drag();
	}

	public void ress() {
		jColor.ress();
	}
}
