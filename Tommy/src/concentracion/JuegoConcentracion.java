package concentracion;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;
import processing.core.PImage;

public class JuegoConcentracion extends Thread {

	private PApplet app;

	private PImage fondo;
	private PImage instrucicones;

	private ArrayList<lvlUno> pandas = new ArrayList<>();

	private int inicio;

	private int selector;
	
	private int sec;
	private int min;

	private boolean iniciar;

	private String reloj;

	public JuegoConcentracion(PApplet app) {
		this.app = app;

		fondo = app.loadImage("../data/concentracionFondo.png");
		instrucicones = app.loadImage("../data/concentracionI.png");

		loadlvlUno();

		iniciar = false;
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
						for (int i = 0; i < pandas.size(); i++) {
							pandas.get(i).setVisible(true);
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
		if (iniciar) {
			app.image(fondo, app.width / 2, app.height / 2);
			app.textSize(27);
			app.text(reloj, 1138, 78);

			for (int i = 0; i < pandas.size(); i++) {
				lvlUno p = pandas.get(i);

				p.draw();
			}
		} else {
			app.image(instrucicones, app.width / 2, app.height / 2);
		}
	}

	private void loadlvlUno() {
		int[] values = { 1, 0, 0, 0, 0, 0 };
		shuffleArray(values);
		selector = 0;

		for (int i = 0; i < 6; i++) {
			if (i < 3)
				pandas.add(new lvlUno(app, (216 + 50) * i + 340, app.height / 2 - 50, values[i], selector));
			else
				pandas.add(new lvlUno(app, (216 + 50) * (i - 3) + 340, app.height / 2 + 170, values[i], selector));
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

	public void click() {
		if (iniciar) {
			for (int i = 0; i < pandas.size(); i++) {
				lvlUno p = pandas.get(i);

				if (PApplet.dist(p.getX(), p.getY(), app.mouseX, app.mouseY) < 100) {
					if (p.getId() == 0) {
						System.out.println("nonas mai");
					} else {
						System.out.println("Sisas prro");
					}
				}
			}
		} else {
			if (app.mouseX > 947 && app.mouseX < 1145 && app.mouseY > 590 && app.mouseY < 660) {
				iniciar = true;
			}
		}
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
}
