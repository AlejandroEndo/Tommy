package comunicacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class JuegoComunicacion extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage instrucciones;
	private PImage puntaje;

	private PVector[] pos = new PVector[4];

	private Object o = null;

	private Comida comida;

	private ArrayList<Comida> comidas = new ArrayList<>();
	private ArrayList<Comida> comidatxt = new ArrayList<>();

	private int sec;
	private int min;

	private int puntajeLocal;
	private int id;

	private boolean iniciar;
	private boolean inicio;

	private String reloj;
	private String nuevoReloj;

	public JuegoComunicacion(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/comunicacionFondo.png");
		instrucciones = app.loadImage("../data/comunicacionI.png");
		puntaje = app.loadImage("../data/entrenamientoI.png");

		iniciar = false;

		comida = new Comida(app, 0, 0, id);

		loadComida();
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				sec++;
				if (sec >= 60) {
					min++;
					sec = 0;
				}
				if (inicio) {
					comidas.clear();
					loadComida();
					inicio = false;
				}
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void draw() {
		if (sec < 10) {
			reloj = "0" + min + ":0" + sec;
		} else {
			reloj = "0" + min + ":" + sec;
		}

		if (puntajeLocal == 200) {
			app.image(puntaje, app.width / 2, app.height / 2);
			app.text(nuevoReloj, 900, 246);
			app.text(puntajeLocal, 900, 410);
		} else {
			if (iniciar) {
				app.image(fondo, app.width / 2, app.height / 2);
				app.textSize(27);
				app.text(reloj, 1138, 78);

				for (int i = 0; i < comidas.size(); i++) {
					comidas.get(i).draw();
				}

				comida.texto();

				app.textAlign(PConstants.RIGHT);
				app.textSize(40);
				app.fill(275, 40, 80);
				app.text("200/" + puntajeLocal, 1176, 677);
				app.textAlign(PConstants.CENTER);
				nuevoReloj = reloj;
				app.fill(277, 47, 46);
			} else {
				app.image(instrucciones, app.width / 2, app.height / 2);
			}
		}
	}

	public void reiniciar() {
		iniciar = false;
		sec = 0;
		min = 0;
		puntajeLocal = 0;
		comidas.clear();
		loadComida();
	}

	public void click() {
		if (puntajeLocal < 200) {
			if (iniciar) {

				for (int i = 0; i < comidas.size(); i++) {
					Comida c = comidas.get(i);
					if (PApplet.dist(app.mouseX, app.mouseY, c.getX(), c.getY()) < 80) {
						if (o == null) {
							o = c;
						}
					}
				}
			} else {
				if (app.mouseX > 947 && app.mouseX < 1145 && app.mouseY > 590 && app.mouseY < 660) {
					iniciar = true;
					sec = 0;
					min = 0;
				}
			}
		} else {
			if (app.mouseX > 800 && app.mouseX < 996 && app.mouseY > 537 && app.mouseY < 610) {
				reiniciar();
			}
		}
	}

	public void drag() {
		if (o instanceof Comida) {
			((Comida) o).setX(app.mouseX);
			((Comida) o).setY(app.mouseY);
		}
	}

	public void res() {
		if (o != null) {
			o = null;

			for (int i = 0; i < comidas.size(); i++) {
				Comida c = comidas.get(i);

				if (PApplet.dist(c.getX(), c.getY(), 300, 420) < 80) {
					if (c.getId() == comida.getId()) {
						puntajeLocal += 50;
						c.setX(300);
						c.setY(438);
						inicio = true;
					}
				} else {
					c.setX(pos[i].x);
					c.setY(pos[i].y);
				}
			}
		}
	}

	private void loadComida() {
		int[] values = { 0, 1, 2, 3 };
		id = (int) app.random(4);
		comida.setId(id);
		shuffleArray(values);
		for (int i = 0; i < 4; i++) {
			if (i < 2) {
				comidas.add(new Comida(app, 300 * i + 750, 280, values[i]));
			} else {
				comidas.add(new Comida(app, 300 * (i - 2) + 750, 560, values[i]));
			}
		}

		for (int i = 0; i < comidas.size(); i++) {
			pos[i] = new PVector(comidas.get(i).getX(), comidas.get(i).getY());
		}
		shuffleArray(values);
		for (int i = 0; i < 4; i++) {
			comidatxt.add(new Comida(app, 0, 0, values[i]));
		}
	}

	static void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public boolean isIniciar() {
		return iniciar;
	}

	public void setIniciar(boolean iniciar) {
		this.iniciar = iniciar;
	}

	public int getPuntajeLocal() {
		return puntajeLocal;
	}

	public void setPuntajeLocal(int puntajeLocal) {
		this.puntajeLocal = puntajeLocal;
	}
}
