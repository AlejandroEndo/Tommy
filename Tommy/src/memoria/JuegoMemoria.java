package memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;
import processing.core.PImage;
import sonido.Sonido;

public class JuegoMemoria extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage memoriaI;
	private PImage puntaje;

	private ArrayList<Figura> figurasA = new ArrayList<>();
	private ArrayList<Figura> figurasB = new ArrayList<>();

	private ArrayList<Animal> animalesA = new ArrayList<>();
	private ArrayList<Animal> animalesB = new ArrayList<>();

	private ArrayList<Letra> letrasA = new ArrayList<>();
	private ArrayList<Letra> letrasB = new ArrayList<>();

	private ArrayList<Fruta> frutasA = new ArrayList<>();
	private ArrayList<Fruta> frutasB = new ArrayList<>();

	private Figura uf = null;
	private Figura nf = null;

	private Animal ua = null;
	private Animal na = null;
	
	private Sonido s;

	private int lvl;

	private int sec;
	private int min;

	private int puntajeLocal;

	private String reloj;
	private String nuevoReloj;

	private boolean valideFigura;
	private boolean valideAnimal;
	private boolean guarde;

	private boolean iniciar;

	public JuegoMemoria(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/memoriaFondo.png");
		memoriaI = app.loadImage("../data/memoriaI.png");
		puntaje = app.loadImage("../data/entrenamientoI.png");

		lvl = 0;

		guarde = false;
		
		s = new Sonido(app);

		loadFiguras();
		loadAnimales();

		start();
	}

	private void loadAnimales() {
		int[] id = { 0, 1, 2, 3 };
		shuffleArray(id);
		for (int i = 0; i < 4; i++) {
			animalesA.add(new Animal(app, (216 + 15) * i + 250, app.height / 2 - 50, id[i]));
		}
		shuffleArray(id);
		for (int i = 0; i < 4; i++) {
			animalesB.add(new Animal(app, (216 + 15) * i + 250, app.height / 2 + 170, id[i]));
		}
	}

	private void loadFiguras() {
		int[] id = { 0, 1, 2 };
		shuffleArray(id);
		for (int i = 0; i < 3; i++) {
			figurasA.add(new Figura(app, (216 + 50) * i + 340, app.height / 2 - 50, id[i]));
		}
		shuffleArray(id);
		for (int i = 0; i < 3; i++) {
			figurasB.add(new Figura(app, (216 + 50) * i + 340, app.height / 2 + 170, id[i]));
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

	@Override
	public void run() {
		while (true) {
			try {
				sec++;
				if (sec >= 60) {
					min++;
					sec = 0;
				}

				switch (lvl) {
				case 0:
					if (uf != null && nf != null) {
						valideFigura = true;
					}
					break;

				case 1:
					if (ua != null && na != null) {
						valideAnimal = true;
					}
					break;
				}

				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void reiniciar() {
		iniciar = false;
		sec = 0;
		min = 0;
		puntajeLocal = 0;
		for (int i = 0; i < animalesA.size(); i++) {
			animalesA.get(i).setVisible(false);
			animalesB.get(i).setVisible(false);
		}
		animalesA.clear();
		animalesB.clear();
		for (int i = 0; i < figurasA.size(); i++) {
			figurasA.get(i).setVisible(false);
			figurasB.get(i).setVisible(false);
		}
		figurasA.clear();
		figurasB.clear();
		loadAnimales();
		loadFiguras();
	}

	public void draw() {
		if ((puntajeLocal == 60 && lvl == 0) || (puntajeLocal == 80 && lvl == 1)) {
			app.image(puntaje, app.width / 2, app.height / 2);
			app.text(nuevoReloj, 900, 246);
			app.text(puntajeLocal, 900, 410);
		} else {
			if (iniciar) {
				if (sec < 10) {
					reloj = "0" + min + ":0" + sec;
				} else {
					reloj = "0" + min + ":" + sec;
				}

				app.image(fondo, app.width / 2, app.height / 2);
				app.textSize(27);
				app.text(reloj, 1138, 78);

				validacionFigura();
				validacionAnimal();

				nuevoReloj = reloj;
				switch (lvl) {
				case 0:
					for (int i = 0; i < 3; i++) {
						figurasA.get(i).draw();
						figurasB.get(i).draw();
					}
					break;

				case 1:
					for (int i = 0; i < 4; i++) {
						animalesA.get(i).draw();
						animalesB.get(i).draw();
					}
					break;
				}
			} else {
				app.image(memoriaI, app.width / 2, app.height / 2);
			}
		}
	}

	private void validacionFigura() {
		if (valideFigura) {
			if (uf.getId() == nf.getId()) {
				// System.out.println(puntajeLocal);
				puntajeLocal += 20;
				s.bueno();
			} else {
				uf.setVisible(false);
				nf.setVisible(false);
				s.malo();
			}
			uf = null;
			nf = null;
			valideFigura = false;
		}
	}

	private void validacionAnimal() {
		if (valideAnimal) {
			if (ua.getId() == na.getId()) {
				// System.out.println(puntajeLocal);
				puntajeLocal += 20;
			} else {
				ua.setVisible(false);
				na.setVisible(false);
			}
			ua = null;
			na = null;
			valideAnimal = false;
		}
	}

	public void click() {
		if ((puntajeLocal < 60 && lvl == 0) || (puntajeLocal < 80 && lvl == 1)) {
			if (iniciar) {
				switch (lvl) {
				case 0:
					for (int i = 0; i < figurasA.size(); i++) {
						for (int j = 0; j < figurasB.size(); j++) {
							Figura a = figurasA.get(i);
							Figura b = figurasB.get(j);

							if (uf == null || nf == null) {
								if (PApplet.dist(app.mouseX, app.mouseY, a.getX(), a.getY()) < 100) {
									if (!a.isVisible()) {
										a.setVisible(true);
										if (uf == null) {
											uf = a;
										} else if (nf == null) {
											nf = a;
										}
									}
								}

								if (PApplet.dist(app.mouseX, app.mouseY, b.getX(), b.getY()) < 100) {
									if (!b.isVisible()) {
										b.setVisible(true);
										if (uf == null) {
											uf = b;
										} else if (nf == null) {
											nf = b;
										}
									}
								}
							}
						}
					}
					break;

				case 1:
					for (int i = 0; i < animalesA.size(); i++) {
						for (int j = 0; j < animalesB.size(); j++) {
							Animal a = animalesA.get(i);
							Animal b = animalesB.get(j);

							if (ua == null || na == null) {
								if (PApplet.dist(app.mouseX, app.mouseY, a.getX(), a.getY()) < 100) {
									if (!a.isVisible()) {
										a.setVisible(true);
										if (ua == null) {
											ua = a;
										} else if (na == null) {
											na = a;
										}
									}
								}

								if (PApplet.dist(app.mouseX, app.mouseY, b.getX(), b.getY()) < 100) {
									if (!b.isVisible()) {
										b.setVisible(true);
										if (ua == null) {
											ua = b;
										} else if (na == null) {
											na = b;
										}
									}
								}
							}
						}
					}
					break;
				}
			} else {
				if (app.mouseX > 947 && app.mouseX < 1145 && app.mouseY > 590 && app.mouseY < 660) {
					iniciar = true;
				}
			}
		} else {
			if (app.mouseX > 800 && app.mouseX < 996 && app.mouseY > 537 && app.mouseY < 610) {
				guarde = true;
				// puntajeLocal = 0;
				reiniciar();
			}
		}
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

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
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
}
