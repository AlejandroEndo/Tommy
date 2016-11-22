package motricidad;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import sonido.Sonido;

public class JuegoMotricidad extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage[] instrucciones = new PImage[4];
	private PImage puntaje;

	private ArrayList<Figura> figuras = new ArrayList<>();

	private Sonido s;

	private int sec;
	private int min;

	private int puntajeLocal;
	private int id;
	private int lvl;

	private boolean iniciar;
	private boolean guarde;

	private String reloj;
	private String nuevoReloj;

	public JuegoMotricidad(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/motricidadFondo.png");
		puntaje = app.loadImage("../data/entrenamientoI.png");

		instrucciones[0] = app.loadImage("../data/motricidadCarroI.png");
		instrucciones[1] = app.loadImage("../data/motricidadHeladoI.png");
		instrucciones[2] = app.loadImage("../data/motricidadJirafaI.png");
		instrucciones[3] = app.loadImage("../data/motricidadPandaI.png");

		iniciar = false;
		guarde = false;

		s = new Sonido(app);

		lvl = 1;

		loadFiguras();

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

		if ((puntajeLocal == 300 && lvl == 1) || (puntajeLocal == 200 && lvl == 0)) {
			app.image(puntaje, app.width / 2, app.height / 2);
			app.text(nuevoReloj, 900, 246);
			app.text(puntajeLocal, 900, 410);
		} else {
			if (iniciar) {
				app.image(fondo, app.width / 2, app.height / 2);
				app.textSize(27);
				app.text(reloj, 1138, 78);

				for (int i = 0; i < figuras.size(); i++) {
					figuras.get(i).draw();
				}

				app.textAlign(PConstants.RIGHT);
				app.textSize(40);
				app.text("200/" + puntajeLocal, 1176, 677);
				app.textAlign(PConstants.CENTER);
				nuevoReloj = reloj;
			} else {
				app.image(instrucciones[id], app.width / 2, app.height / 2);
			}
		}
	}

	public void click() {
		if ((puntajeLocal <= 300 && lvl == 1) || (puntajeLocal < 200 && lvl == 0)) {
			if (iniciar) {

				for (int i = 0; i < figuras.size(); i++) {
					Figura f = figuras.get(i);

					if (PApplet.dist(f.getX(), f.getY(), app.mouseX, app.mouseY) < 50) {
						if (f.getS() == 1) {
							figuras.remove(i);
							puntajeLocal += 50;
							s.bueno();
						} else {
							s.malo();
						}
					}
				}
			} else {
				if (app.mouseX > 947 && app.mouseX < 1145 && app.mouseY > 590 && app.mouseY < 660) {
					iniciar = true;
				}
			}
		} else {
			if (app.mouseX > 800 && app.mouseX < 996 && app.mouseY > 537 && app.mouseY < 610) {
				// puntajeLocal = 0;
				guarde = true;
				reiniciar();
			}
		}
	}

	public void reiniciar() {
		iniciar = false;
		sec = 0;
		min = 0;
		puntajeLocal = 0;
		figuras.clear();
		loadFiguras();
	}

	private void loadFiguras() {
		id = (int) app.random(4);

		int[] values = new int[20];
		for (int i = 0; i < values.length; i++) {
			if (i % 2 == 0) {
				values[i] = 0;
			} else {
				values[i] = 1;
			}
		}

		shuffleArray(values);

		if (lvl == 0) {
			for (int i = 0; i < 9; i++) {
				figuras.add(new Figura(app, app.random(120, 1000), app.random(200, 580), id, values[i]));
			}
		}
		if (lvl == 1) {
			for (int i = 0; i < 11; i++) {
				figuras.add(new Figura(app, app.random(120, 1000), app.random(200, 580), id, values[i]));
			}
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

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getReloj() {
		return reloj;
	}

	public boolean isGuarde() {
		return guarde;
	}

	public void setGuarde(boolean guarde) {
		this.guarde = guarde;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public void setMin(int min) {
		this.min = min;
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
