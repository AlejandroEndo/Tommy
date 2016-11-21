package ejecutable;

import java.util.ArrayList;

import color.JuegoColor;
import memoria.JuegoMemoria;
import menu.Home;
import menu.Inicio;
import menu.Juego;
import orden.JuegoOrden;
import processing.core.PApplet;
import processing.core.PFont;

public class Logica {

	private PApplet app;

	private PFont somatic;

	private Inicio inicio;
	private Home home;
	private Juego juego;

	private JuegoColor jColor;
	private JuegoOrden jOrden;
	private JuegoMemoria jMemoria;

	private int pantalla;

	public Logica(PApplet app) {
		this.app = app;

		somatic = app.loadFont("../data/somatic.vlw");
		app.textFont(somatic, 27);

		pantalla = 1;

		inicio = new Inicio(app);
		home = new Home(app);
		juego = new Juego(app);

		jColor = new JuegoColor(app);
		jOrden = new JuegoOrden(app);
		jMemoria = new JuegoMemoria(app);
	}

	public void draw() {
		// System.out.println(app.mouseX + " " + app.mouseY);
		switch (pantalla) {

		case 0: // Inicio, no hay interaccion
			inicio.draw();

			if (inicio.getTrans() <= 0) {
				pantalla = 1;
				home.setTrans(0);
			}
			break;

		case 1: // Menu home... desde aqui se comienza a navegar
			home.draw();
			break;

		case 2:
			juego.draw();
			break;
			
		case 5: // Juego 1, arrastrar bolitas
			jColor.draw();
			break;

		case 3: // Juego 2, clickear en orden
			jOrden.draw();
			break;

		case 4:
			jMemoria.draw();
			break;
		}
	}

	public void click() {
		switch (pantalla) {

		case 1:
			if (PApplet.dist(app.mouseX, app.mouseY, 778, 251) < 70) { // entrenate
				pantalla++;
			}
			break;

		case 2:
			
			break;
			
		case 5:
			jColor.click();
			break;

		case 3:
			jOrden.click();
			break;

		case 4:
			jMemoria.click();
			break;
		}
	}

	public void drag() {
		jColor.drag();
	}

	public void ress() {
		jColor.ress();
	}
}
