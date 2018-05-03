package newton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw extends JPanel {
	private static final long serialVersionUID = 1L;
	GameFrame game;
	BufferedImage image;
	AstronomicalObject object;
	
	ArrayList<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	String[] names = { "sun", "mercury", "venus", "earth", "mars", "jupiter" , "neptune", "pluto", "saturn", "uranus"};

	public Draw() {
		super();
		this.setBackground(Color.red);
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
		for (int i = 0; i < images.size(); i++) {
			
			int x= objects.get(i).getX();
			int y=objects.get(i).getY();
			int r=objects.get(i).get_r();
			g.drawImage(images.get(i),x, y, r, r, null);
		}

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
	public void updateAstroObject(ArrayList<AstronomicalObject> objects) {
		for(int i=0;i<this.objects.size();i++) {
			this.objects.get(i).set_fx(objects.get(i).get_fx());
			this.objects.get(i).set_fy(objects.get(i).get_fy());
			this.objects.get(i).set_vx(objects.get(i).get_vx());
			this.objects.get(i).set_vy(objects.get(i).get_vy());
			this.objects.get(i).setX(objects.get(i).getX());
			this.objects.get(i).set_fx(objects.get(i).getY());
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint(g);

	}

}