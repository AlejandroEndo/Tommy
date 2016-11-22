package ejecutable;

import java.io.File;

import almacenamiento.BaseDeDatos;
import almacenamiento.Puntaje;
import comunicacion.JuegoComunicacion;
import concentracion.JuegoConcentracion;
import memoria.JuegoMemoria;
import menu.Home;
import menu.Inicio;
import menu.Juego;
import menu.Puntos;
import motricidad.JuegoMotricidad;
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
	private JuegoMemoria jMemoria;
	private JuegoMotricidad jMotricidad;
	private JuegoComunicacion jComunicacion;
	// --------------------------------------------

	private int pantalla;

	// MOUSE -------------------
	private PImage a;
	// --------------------------

	// MENUS ----------------------------
	private PImage borrarPuntos;
	private PImage deseaContinuar;
	private PImage menuJuegos;
	private PImage menuJuegosReiniciar;
	private PImage about;

	private boolean menu;
	private boolean menuAntes;
	private boolean menuDespues;
	// -----------------------------------

	private float[] reinicio = { 0, 0, 0, 0 };

	public Logica(PApplet app) {
		this.app = app;

		somatic = app.loadFont("../data/somatic.vlw");
		app.textFont(somatic, 27);

		pantalla = 1;

		datos = new BaseDeDatos("data" + File.separator + "BaseDeDatos.xml");
		p = new Puntaje(datos.getEntrenamiento(), datos.getConcentracion(), datos.getMemoria(), datos.getMotricidad(),
				datos.getComunicacion());

		// JUEGOS INICIALIZACION ------------------
		jConcentracion = new JuegoConcentracion(app); // ID = 6
		jMemoria = new JuegoMemoria(app); // ID = 7
		jMotricidad = new JuegoMotricidad(app); // ID = 8
		jComunicacion = new JuegoComunicacion(app); // ID = 9
		// -----------------------------------------

		// PANTALLAS INICIALIZACION ------
		inicio = new Inicio(app);
		home = new Home(app);
		juego = new Juego(app);
		puntos = new Puntos(app);
		// -------------------------------

		// MOUSE INICIALIZACiON ------------
		a = app.loadImage("../data/a.png");
		// ----------------------------------

		// MENUS INICIALIZACION -----------------------------------
		borrarPuntos = app.loadImage("../data/borrarPuntos.png");
		deseaContinuar = app.loadImage("../data/deseaContinuar.png");
		menuJuegos = app.loadImage("../data/menuJuegos.png");
		menuJuegosReiniciar = app.loadImage("../data/menuJuegosReiniciar.png");
		about = app.loadImage("../data/about.png");

		menu = false;
		menuAntes = false;
		menuDespues = false;
		// --------------------------------------------------------
	}

	public void draw() {
		app.cursor(a, 0, 0);
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
			home.setTam(reinicio);
			puntos.draw();
			if (menu) {
				app.image(borrarPuntos, app.width / 2, app.height / 2);
			}
			break;

		case 5: // Informacion
			app.image(about, app.width / 2, app.height / 2);
			home.setTam(reinicio);
			break;

		case 6: // Concentracion
			jConcentracion.draw();
			if (menuAntes) {
				app.image(menuJuegos, app.width / 2, app.height / 2);
			}
			if (menuDespues) {
				app.image(menuJuegosReiniciar, app.width / 2, app.height / 2);
			}
			home.setTam(reinicio);
			break;

		case 7: // Memoria
			jMemoria.draw();
			if (menuAntes) {
				app.image(menuJuegos, app.width / 2, app.height / 2);
			}
			if (menuDespues) {
				app.image(menuJuegosReiniciar, app.width / 2, app.height / 2);
			}
			home.setTam(reinicio);
			break;

		case 8: // Motricidad
			jMotricidad.draw();
			if (menuAntes) {
				app.image(menuJuegos, app.width / 2, app.height / 2);
			}
			if (menuDespues) {
				app.image(menuJuegosReiniciar, app.width / 2, app.height / 2);
			}
			home.setTam(reinicio);
			break;

		case 9: // Comunicacion
			jComunicacion.draw();
			if (menuAntes) {
				app.image(menuJuegos, app.width / 2, app.height / 2);
			}
			if (menuDespues) {
				app.image(menuJuegosReiniciar, app.width / 2, app.height / 2);
			}
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
			if (menu) {
				if (app.mouseX > 433 && app.mouseX < 529 && app.mouseY > 419 && app.mouseY < 462) {
					datos.agregarDato(new Puntaje(0, 0, 0, 0, 0));
					menu = false;
				}
				if (app.mouseX > 664 && app.mouseX < 764 && app.mouseY > 419 && app.mouseY < 462) {
					menu = false;
				}
			}
			if (app.mouseX > 515 && app.mouseX < 685 && app.mouseY > 620 && app.mouseY < 662) {
				menu = true;
			}
			if (PApplet.dist(65, 65, app.mouseX, app.mouseX) < 50) {
				pantalla = 1;
			}
			break;

		case 5: // informacion
			if (PApplet.dist(65, 65, app.mouseX, app.mouseY) < 50) {
				pantalla = 1;
			}
			break;

		case 6: // concentracion
			if (!menuAntes && !menuDespues)
				jConcentracion.click();

			if (PApplet.dist(65, 65, app.mouseX, app.mouseY) < 50) {
				if (!jConcentracion.isIniciar() || jConcentracion.getPuntajeLocal() == 200)
					menuAntes = true;
				else
					menuDespues = true;
			}

			if (menuAntes) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 254 && app.mouseY < 325) {
					pantalla = 1;
					menuAntes = false;
				}
				if (app.mouseX > 503 && app.mouseX < 695 && app.mouseY > 370 && app.mouseY < 443) {
					pantalla = 3;
					menuAntes = false;
				}
				if (PApplet.dist(862, 211, app.mouseX, app.mouseY) < 30) {
					menuAntes = false;
				}
			}
			if (menuDespues) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 218 && app.mouseY < 287) {
					pantalla = 1;
					menuDespues = false;
				}
				if (app.mouseX > 501 && app.mouseX < 697 && app.mouseY > 315 && app.mouseY < 382) {
					pantalla = 3;
					menuDespues = false;
				}
				if (app.mouseX > 490 && app.mouseX < 710 && app.mouseY > 407 && app.mouseY < 473) {
					jConcentracion.reiniciar();
					menuDespues = false;
				}
				if (PApplet.dist(862, 182, app.mouseX, app.mouseY) < 30) {
					menuDespues = false;
				}

			}
			break;

		case 7: // memoria
			if (!menuAntes && !menuDespues)
				jMemoria.click();

			if (PApplet.dist(65, 65, app.mouseX, app.mouseY) < 50) {
				if (!jMemoria.isIniciar() || jMemoria.getPuntajeLocal() == 200)
					menuAntes = true;
				else
					menuDespues = true;
			}

			if (menuAntes) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 254 && app.mouseY < 325) {
					pantalla = 1;
					menuAntes = false;
				}
				if (app.mouseX > 503 && app.mouseX < 695 && app.mouseY > 370 && app.mouseY < 443) {
					pantalla = 3;
					menuAntes = false;
				}
				if (PApplet.dist(862, 211, app.mouseX, app.mouseY) < 30) {
					menuAntes = false;
				}
			}
			if (menuDespues) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 218 && app.mouseY < 287) {
					pantalla = 1;
					menuDespues = false;
				}
				if (app.mouseX > 501 && app.mouseX < 697 && app.mouseY > 315 && app.mouseY < 382) {
					pantalla = 3;
					menuDespues = false;
				}
				if (app.mouseX > 490 && app.mouseX < 710 && app.mouseY > 407 && app.mouseY < 473) {
					jMemoria.reiniciar();
					menuDespues = false;
				}
				if (PApplet.dist(862, 182, app.mouseX, app.mouseY) < 30) {
					menuDespues = false;
				}

			}
			break;

		case 8: // motricidad
			if (!menuAntes && !menuDespues)
				jMotricidad.click();

			if (PApplet.dist(65, 65, app.mouseX, app.mouseY) < 50) {
				if (!jMotricidad.isIniciar() || jMotricidad.getPuntajeLocal() == 200)
					menuAntes = true;
				else
					menuDespues = true;
			}

			if (menuAntes) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 254 && app.mouseY < 325) {
					pantalla = 1;
					menuAntes = false;
				}
				if (app.mouseX > 503 && app.mouseX < 695 && app.mouseY > 370 && app.mouseY < 443) {
					pantalla = 3;
					menuAntes = false;
				}
				if (PApplet.dist(862, 211, app.mouseX, app.mouseY) < 30) {
					menuAntes = false;
				}
			}
			if (menuDespues) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 218 && app.mouseY < 287) {
					pantalla = 1;
					menuDespues = false;
				}
				if (app.mouseX > 501 && app.mouseX < 697 && app.mouseY > 315 && app.mouseY < 382) {
					pantalla = 3;
					menuDespues = false;
				}
				if (app.mouseX > 490 && app.mouseX < 710 && app.mouseY > 407 && app.mouseY < 473) {
					jMotricidad.reiniciar();
					menuDespues = false;
				}
				if (PApplet.dist(862, 182, app.mouseX, app.mouseY) < 30) {
					menuDespues = false;
				}

			}
			break;

		case 9: // comunicacion
			if (!menuAntes && !menuDespues)
				jComunicacion.click();

			if (PApplet.dist(65, 65, app.mouseX, app.mouseY) < 50) {
				if (!jComunicacion.isIniciar() || jComunicacion.getPuntajeLocal() == 200)
					menuAntes = true;
				else
					menuDespues = true;
			}

			if (menuAntes) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 254 && app.mouseY < 325) {
					pantalla = 1;
					menuAntes = false;
				}
				if (app.mouseX > 503 && app.mouseX < 695 && app.mouseY > 370 && app.mouseY < 443) {
					pantalla = 3;
					menuAntes = false;
				}
				if (PApplet.dist(862, 211, app.mouseX, app.mouseY) < 30) {
					menuAntes = false;
				}
			}
			if (menuDespues) {
				if (app.mouseX > 512 && app.mouseX < 682 && app.mouseY > 218 && app.mouseY < 287) {
					pantalla = 1;
					menuDespues = false;
				}
				if (app.mouseX > 501 && app.mouseX < 697 && app.mouseY > 315 && app.mouseY < 382) {
					pantalla = 3;
					menuDespues = false;
				}
				if (app.mouseX > 490 && app.mouseX < 710 && app.mouseY > 407 && app.mouseY < 473) {
					jComunicacion.reiniciar();
					menuDespues = false;
				}
				if (PApplet.dist(862, 182, app.mouseX, app.mouseY) < 30) {
					menuDespues = false;
				}

			}
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
		switch(pantalla){
		case 9:
			jComunicacion.drag();
			break;
		}
	}

	public void ress() {
		switch(pantalla){
		case 9:
			jComunicacion.res();
			break;
		}
	}

}
