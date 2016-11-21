package ejecutable;

import java.io.File;
import java.util.ArrayList;

import almacenamiento.BaseDeDatos;
import almacenamiento.Puntaje;
import color.JuegoColor;
import concentracion.JuegoConcentracion;
import memoria.JuegoMemoria;
import menu.Home;
import menu.Inicio;
import menu.Juego;
import menu.Puntos;
import orden.JuegoOrden;
import processing.core.PApplet;
import processing.core.PFont;

public class Logica {

	private PApplet app;

	private PFont somatic;

	private BaseDeDatos datos;
	private Puntaje p;

	private Inicio inicio;
	private Home home;
	private Juego juego;
	private Puntos puntos;

	private JuegoColor jColor;
	private JuegoOrden jOrden;
	private JuegoMemoria jMemoria;
	private JuegoConcentracion jConcentracion;

	private int pantalla;

	public Logica(PApplet app) {
		this.app = app;

		somatic = app.loadFont("../data/somatic.vlw");
		app.textFont(somatic, 27);

		pantalla = 0;

		datos = new BaseDeDatos("data" + File.separator + "BaseDeDatos.xml");
		p = new Puntaje(datos.getConcentracion(), datos.getMemoria(), datos.getMotricidad(), datos.getComunicacion());

		inicio = new Inicio(app);
		home = new Home(app);
		juego = new Juego(app);
		puntos = new Puntos(app);

		jColor = new JuegoColor(app);
		jOrden = new JuegoOrden(app);
		jMemoria = new JuegoMemoria(app);
		jConcentracion = new JuegoConcentracion(app);

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
			System.out.println(datos.getComunicacion());
			home.setPromedioPuntos((p.getComunicacion() + p.getConcentracion() + p.getMemoria() + p.getMotricidad()) / 4);
			break;

		case 2: // Entrenate

			break;

		case 3: // Juegos
			break;

		case 4: // Puntos
			puntos.draw();
			break;

		case 5: // Informacion

			break;

		case 6:

			break;
		}
	}

	public void click() {
		switch (pantalla) {
		case 1:
			if (PApplet.dist(app.mouseX, app.mouseY, 778, 251) < 70) { // entrenate
				pantalla = 2;
			}
			if (PApplet.dist(app.mouseX, app.mouseY, 1015, 251) < 70) { // Juegos
				pantalla = 3;
			}
			if (PApplet.dist(app.mouseX, app.mouseY, 778, 445) < 70) { // Puntos
				pantalla = 4;
			}
			if (PApplet.dist(app.mouseX, app.mouseY, 1015, 445) < 70) { // Informacion
				pantalla = 5;
			}
			break;

		case 2: // entrenate

			break;

		case 3: // juegos

			break;

		case 4: // puntos

			break;

		case 5: // informacion

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
