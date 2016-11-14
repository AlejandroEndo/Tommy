package ejecutable;

import java.util.ArrayList;

import color.JuegoColor;
import processing.core.PApplet;

public class Logica {

	private PApplet app;

	private JuegoColor jColor;

	public Logica(PApplet app) {
		this.app = app;

		jColor = new JuegoColor(app);
	}

	public void draw() {
		jColor.draw();
		
	}
	
	public void click(){
		jColor.click();
	}
	
	public void drag(){
		jColor.drag();
	}
	
	public void ress(){
		jColor.ress();
	}
}
