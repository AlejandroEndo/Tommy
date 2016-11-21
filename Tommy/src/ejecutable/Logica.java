package ejecutable;

import java.io.File;

import almacenamiento.BaseDeDatos;
import almacenamiento.Puntaje;
import concentracion.JuegoConcentracion;
import menu.Home;
import menu.Inicio;
import menu.Juego;
import menu.Puntos;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {

	// PROCESSING ------------
	private PApplet app;
	private PFont somatic;
	// -----------------------

	// BASE DE DATOS ------------
	private BaseDeDatos datos;
	private Puntaje p;
	// ---------------------------

	// PANTALLAS ------------------
	private Inicio inicio;
	private Home home;
	private Juego juego;
	private Puntos puntos;
	// -----------------------------

	// JUEGOS-------------------------------------
	private JuegoConcentracion jConcentracion;
	// --------------------------------------------

	private int pantalla;

	// MOUSE -------------------
	private boolean mouse;
	private PImage a;
	private PImage b;
	// --------------------------

	private float[] reinicio = { 0, 0, 0, 0 };

	public Logica(PApplet app) {
		this.app = app;

		somatic = app.loadFont("../data/somatic.vlw");
		app.textFont(somatic, 27);

		pantalla = 6;

		datos = new BaseDeDatos("data" + File.separator + "BaseDeDatos.xml");
		p = new Puntaje(datos.getEntrenamiento(), datos.getConcentracion(), datos.getMemoria(), datos.getMotricidad(),
				datos.getComunicacion());

		// JUEGOS INICIALIZACION ------------------
		jConcentracion = new JuegoConcentracion(app); // ID = 6
		// -----------------------------------------

		// PANTALLAS INICIALIZACION ------
		inicio = new Inicio(app);
		home = new Home(app);
		juego = new Juego(app);
		puntos = new Puntos(app);
		// -------------------------------

		// MOUSE INICIALIZACiON ------------
		mouse = true;
		a = app.loadImage("../data/a.png");
		b = app.loadImage("../data/b.png");
		// ----------------------------------

	}

	public void draw() {
		if (mouse) {
			app.cursor(PConstants.ARROW);
		} else {
			app.cursor(PConstants.HAND);
		}
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
			leer();
			home.setPromedioPuntos(
					(p.getComunicacion() + p.getConcentracion() + p.getMemoria() + p.getMotricidad()) / 4);
			home.setEntrenamiento(p.getEntrenamiento());
			home.draw();
			break;

		case 2: // Entrenate
			home.setTam(reinicio);
			break;

		case 3: // Juegos
			juego.setComunicacionP(p.getComunicacion());
			juego.setConcentracionP(p.getConcentracion());
			juego.setMemoriaP(p.getMemoria());
			juego.setMotricidadP(p.getMotricidad());
			juego.draw();
			home.setTam(reinicio);
			break;

		case 4: // Puntos
			puntos.draw();
			home.setTam(reinicio);
			break;

		case 5: // Informacion
			home.setTam(reinicio);
			break;

		case 6: // Concentracion
			jConcentracion.draw();
			home.setTam(reinicio);
			break;

		case 7: // Memoria
			home.setTam(reinicio);
			break;

		case 8: // Motricidad
			home.setTam(reinicio);
			break;

		case 9: // Comunicacion
			home.setTam(reinicio);
			break;
		}
	}

	public void click() {
		// datos.agregarDato(new Puntaje(50,100,100,100,100));
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
			juego.click();

			if (PApplet.dist(app.mouseX, app.mouseY, 778, 251) < 70) { // concentracion
				jConcentracion.setSec(0);
				jConcentracion.setMin(0);
				pantalla = 6;
			}
			if (PApplet.dist(app.mouseX, app.mouseY, 1015, 251) < 70) { // memoria
				pantalla = 7;
			}
			if (PApplet.dist(app.mouseX, app.mouseY, 778, 445) < 70) { // motricidad
				pantalla = 8;
			}
			if (PApplet.dist(app.mouseX, app.mouseY, 1015, 445) < 70) { // comunicacion
				pantalla = 9;
			}
			break;

		case 4: // puntos
			if (app.mouseX > 515 && app.mouseX < 685 && app.mouseY > 620 && app.mouseY < 662)
				datos.agregarDato(new Puntaje(0, 0, 0, 0, 0));
			if (PApplet.dist(65, 65, app.mouseX, app.mouseX) < 50)
				pantalla = 1;
			break;

		case 5: // informacion

			break;

		case 6: // concentracion
			jConcentracion.click();
			break;

		case 7: // memoria

			break;

		case 8: // motricidad

			break;

		case 9: // comunicacion

			break;
		}
	}

	private void leer() {
		datos.leer();
		p.setEntrenamiento(datos.getEntrenamiento());
		p.setComunicacion(datos.getComunicacion());
		p.setConcentracion(datos.getConcentracion());
		p.setMemoria(datos.getMemoria());
		p.setMotricidad(datos.getMotricidad());
	}

	public void drag() {
	}

	public void ress() {
	}
}
