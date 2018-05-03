package newton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw extends JPanel {
	private static final long serialVersionUID = 1L;
	GameFrame game;
	BufferedImage image;
	AstronomicalObject object;
	Rocket rocket;
	
	ArrayList<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	String[] names = { "sun", "mercury", "venus", "earth", "mars", "jupiter" , "neptune", "pluto", "saturn", "uranus","rocket1"};

	public Draw() {
		super();
		this.setVisible(true);
		addImages();
		addAstroObject("Slonce",300,300,40,10,30,10,10,100);
		addAstroObject("Merkury",250,300,40,10,30,10,10,10);
		addAstroObject("Wenus",200,300,40,10,30,10,10,10);
		addAstroObject("Ziemie",150,300,40,10,30,10,10,10);
		addAstroObject("Mars",100,300,40,10,30,10,10,10);
		addAstroObject("Jowisz",50,300,40,10,30,10,10,20);
		addAstroObject("Neptun",350,300,40,10,30,10,10,20);
		addAstroObject("Pluton",400,300,40,10,30,10,10,20);
		addAstroObject("Saturn",450,300,40,10,30,10,10,20);
		addAstroObject("Uran",500,300,40,10,30,10,10,20);
		addRocket("Rakieta",50,300,10,10,30,10,10,20,100,1,1);
	}

	public void addImages() {
		for (int i = 0; i < names.length; i++) {
			String name_file = names[i] + ".png";
			URL sciezka = getClass().getResource(name_file);
			try {

				image = ImageIO.read(sciezka);
				images.add(image);
			} catch (IOException e) {
				System.err.println("Blad odczytu grafiki dla obiektu" + names[i]);
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(0, 0, 900, 600);
		for (int i = 0; i < images.size()-1; i++) {
			
			int x= objects.get(i).getX();
			int y=objects.get(i).getY();
			int r=objects.get(i).get_r();
			g.drawImage(images.get(i),x, y, r, r, null);
		}
		g.drawImage(images.get(images.size()-1),rocket.getX(), rocket.getY(), rocket.get_r(), rocket.get_r(), null);
		
	}
	public void addAstroObject(String name,int x, int y, double Vx, double Vy,int r,double fx,double fy,double m) {
		AstronomicalObject o = new AstronomicalObject();
		o.set_name(name);
		o.set_vx(Vx);
		o.set_vy(Vy);
		o.setX(x);
		o.setY(y);
		o.set_r(r);
		o.set_fx(fx);
		o.set_fy(fy);	
		o.set_m(m);
		objects.add(o);		
		
	}
	public void addRocket(String name,int x, int y, double Vx, double Vy,int r,double fx,double fy,double m, double fuel, double fuel_consump, double fuel_velo) {
		rocket = new Rocket();
		rocket .set_name(name);
		rocket .set_vx(Vx);
		rocket .set_vy(Vy);
		rocket .setX(x);
		rocket .setY(y);
		rocket.set_r(r);
		rocket .set_fx(fx);
		rocket .set_fy(fy);	
		rocket .set_m(m);
		rocket .set_fuel(fuel);
		rocket .set_consup(fuel_consump);
		rocket .set_fuelVelo(fuel_velo);
		
}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint(g);

	}

}