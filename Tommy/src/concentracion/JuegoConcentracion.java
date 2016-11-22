package concentracion;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class JuegoConcentracion extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage instrucicones;
	private PImage puntaje;

	private ArrayList<Figura> lvlUno = new ArrayList<>();
	private ArrayList<Figura> lvlDos = new ArrayList<>();

	private int inicio;
	private int lvl;

	private int selector;

	private int sec;
	private int min;

	private int puntajeLocal;

	private boolean iniciar;
	private boolean guarde;

	private String reloj;
	private String nuevoReloj;

	public JuegoConcentracion(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/concentracionFondo.png");
		instrucicones = app.loadImage("../data/concentracionI.png");
		puntaje = app.loadImage("../data/entrenamientoI.png");

		lvl = 1;

		loadlvlUno();
		loadlvlDos();

		iniciar = false;
		guarde = false;
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

				if (iniciar) {
					inicio++;
					if (inicio >= 2) {
						for (int i = 0; i < lvlUno.size(); i++) {
							lvlUno.get(i).setVisible(true);
							inicio = 0;
						}
						for (int i = 0; i < lvlDos.size(); i++) {
							lvlDos.get(i).setVisible(true);
							inicio = 0;
						}
					}
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

				if (lvl == 0) {
					for (int i = 0; i < lvlUno.size(); i++) {
						Figura p = lvlUno.get(i);
						p.draw();
					}
				} else if (lvl == 1) {
					for (int i = 0; i < lvlDos.size(); i++) {
						Figura p = lvlDos.get(i);
						p.draw();
					}
				}
				app.textAlign(PConstants.RIGHT);
				app.textSize(40);
				app.text("200/" + puntajeLocal, 1176, 677);
				app.textAlign(PConstants.CENTER);
				nuevoReloj = reloj;
			} else {
				app.image(instrucicones, app.width / 2, app.height / 2);
			}
		}
	}

	private void loadlvlUno() {
		int[] values = { 1, 0, 0, 0, 0, 0 };
		shuffleArray(values);
		selector = (int) app.random(4);

		for (int i = 0; i < 6; i++) {
			if (i < 3)
				lvlUno.add(new Figura(app, (216 + 50) * i + 340, app.height / 2 - 50, values[i], selector));
			else
				lvlUno.add(new Figura(app, (216 + 50) * (i - 3) + 340, app.height / 2 + 170, values[i], selector));
		}
	}

	private void loadlvlDos() {
		int[] values = { 1, 0, 0, 0, 0, 0, 0, 0 };
		shuffleArray(values);
		selector = (int) app.random(4);

		for (int i = 0; i < 8; i++) {
			if (i < 4)
				lvlDos.add(new Figura(app, (216 + 50) * i + 200, app.height / 2 - 50, values[i], selector));
			else
				lvlDos.add(new Figura(app, (216 + 50) * (i - 4) + 200, app.height / 2 + 170, values[i], selector));
		}
	}

	public void reiniciar() {
		iniciar = false;
		inicio = 0;
		sec = 0;
		min = 0;
		puntajeLocal = 0;
		for (int i = 0; i < lvlUno.size(); i++) {
			lvlUno.get(i).setVisible(false);
		}
		for (int i = 0; i < lvlUno.size(); i++) {
			lvlUno.get(i).setVisible(false);
		}
		lvlUno.clear();
		lvlDos.clear();
		loadlvlUno();
		loadlvlDos();
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

	public void click() {
		if (puntajeLocal < 200) {
			if (iniciar) {
				for (int i = 0; i < lvlUno.size(); i++) {
					Figura p = lvlUno.get(i);

					if (PApplet.dist(p.getX(), p.getY(), app.mouseX, app.mouseY) < 100) {
						if (p.getId() == 0) {
							// System.out.println("nonas mai");
						} else {
							puntajeLocal += 50;
							lvlUno.clear();
							loadlvlUno();
							// System.out.println("Sisas prro");
						}
					}
				}
				for (int i = 0; i < lvlDos.size(); i++) {
					Figura p = lvlDos.get(i);

					if (PApplet.dist(p.getX(), p.getY(), app.mouseX, app.mouseY) < 100) {
						if (p.getId() == 0) {
							// System.out.println("nonas mai");
						} else {
							puntajeLocal += 50;
							lvlDos.clear();
							loadlvlDos();
							// System.out.println("Sisas prro");
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
				puntajeLocal = 0;
				reiniciar();
				guarde = true;
			}
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

	public int getPuntajeLocal() {
		return puntajeLocal;
	}

	public void setPuntajeLocal(int puntajeLocal) {
		this.puntajeLocal = puntajeLocal;
	}

	public boolean isIniciar() {
		return iniciar;
	}

	public void setIniciar(boolean iniciar) {
		this.iniciar = iniciar;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public boolean isGuarde() {
		return guarde;
	}

	public void setGuarde(boolean guarde) {
		this.guarde = guarde;
	}
}
