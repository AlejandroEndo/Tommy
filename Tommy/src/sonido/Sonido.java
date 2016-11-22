package sonido;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Sonido {

	private Minim minim;
	private AudioSample malo;
	private AudioSample bueno;

	public Sonido(PApplet app) {
		minim = new Minim(app);
		malo = minim.loadSample("../data/Ouch.wav");
		bueno = minim.loadSample("../data/yay.wav");
	}

	public void bueno() {
		bueno.trigger();
	}

	public void malo() {
		malo.trigger();
	}
}
