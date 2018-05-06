package newton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw extends JPanel {
	private static final long serialVersionUID = 1L;
	GameFrame game;
	BufferedImage image;
	AstronomicalObject object;
	Rocket rocket;
	double skala = 1;
	double xslonca = 450;
	double yslonca = 300;
	ArrayList<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	String[] names = { "sun", "mercury", "venus", "earth", "mars", "jupiter", "neptune", "saturn", "uranus",
			"rocket1_1" };
	int count_star = 100;
	Random r_x = new Random() ;
	Random r_y = new Random();
	Integer [] position_s_x=new Integer[100];//pozycje x gwiazdek
	Integer [] position_s_y=new Integer[100];//pozycje y gwiazdek

	public Draw() {
		super();
		this.setVisible(true);
		set_points();

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int i = names.length;
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					System.out.println("tralalala");
					names[i - 1] = "rocket1_4";
					repaint();
					if (rocket.course_x == 1) {
						rocket.course_x = -1;

					}

				}
				if (key == KeyEvent.VK_RIGHT) {
					names[i - 1] = "rocket1_2";
					repaint();
					if (rocket.course_x == -1) {
						rocket.course_x = 1;

					}

				}
				if (key == KeyEvent.VK_UP) {
					names[i - 1] = "rocket1_1";
					repaint();
					if (rocket.course_y == -1) {
						rocket.course_y = 1;

					}

				}
				if (key == KeyEvent.VK_DOWN) {
					names[i - 1] = "rocket1_3";
					repaint();
					if (rocket.course_y == 1) {
						rocket.course_y = -1;

					}

				}

			}
		});
		addImages();
		// nazwa,x,y,vx,vy,r,m,a,e
		addAstroObject("Slonce", xslonca, yslonca, 0, 0, 10, 20000000, 1, 1,60);

		addAstroObject("Merkury", xslonca - 46, yslonca, 0, 59000, (int) (5 * skala * 2), 3.3, 58 * skala, 0.2056, 15);
		addAstroObject("Wenus", xslonca - 107, yslonca, 0, 35000, (int) (10 * skala * 2), 47, 108.21 * skala, 0.006773,20);
		addAstroObject("Ziemie", xslonca - 147, yslonca, 0, 30300, (int) (10 * skala * 2), 59, 150 * skala, 0.01671,25);
		addAstroObject("Mars", xslonca - 206, yslonca, 0, 26500, (int) (5 * skala * 2), 6.4, 228 * skala, 0.0934,40);
		addAstroObject("Jowisz", xslonca - 740, yslonca, 0, 13720, (int) (110 * skala * 2), 18990, 799 * skala, 0.0484,40);
		addAstroObject("Neptun", xslonca - 4444, yslonca, 0, 5500, (int) (40 * skala * 2), 1024, 4495 * skala, 0.0086,40);
		addAstroObject("Saturn", xslonca - 1352, yslonca, 0, 10180, (int) (90 * skala * 2), 5685, 1433 * skala, 0.0542,40);
		addAstroObject("Uran", xslonca - 2741, yslonca, 0, 7110, (int) (40 * skala * 2), 868, 2872 * skala, 0.0471,40);
		// nazwa,x,y,vx,vy,r,m,f,fc,fv
		addRocket("Rakieta", xslonca - 50, yslonca, 10, 10, 10, 20, 100, 1, 1, 1, 1);
	}

	public void set_points() {
		for (int i = 0; i < count_star; i++) {
			
			int p_x = r_x.nextInt(900);// ranodomowe położenie x punktu
			int p_y = r_y.nextInt(600);//ranodomowe położenie y punktu
			position_s_x[i]=p_x;
			position_s_y[i]=p_y;
			
		}
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

		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 700);
		for(int i=0; i<count_star;i++) {
			g.setColor(Color.yellow);
			g.fillOval(position_s_x[i], position_s_y[i], 5,5);
		}
		for (int i = 0; i < images.size() - 1; i++) {
			double x = objects.get(i).getX();
			double y = objects.get(i).getY();
			double r = objects.get(i).size;
			g.drawImage(images.get(i), (int) x, (int) y, (int)r, (int)r, null);
		}
		g.drawImage(images.get(images.size() - 1), (int) rocket.getX(), (int) rocket.getY(), rocket.get_r(),
				rocket.get_r(), null);
		
		

	}
	

	public void addAstroObject(String name, double x, double y, double Vx, double Vy, int r, double m, double a,
			double e, double s) {
		AstronomicalObject o = new AstronomicalObject();
		o.set_name(name);
		o.set_vx(Vx);
		o.set_vy(Vy);
		o.setX(x);
		o.setY(y);
		o.set_r(r);
		o.set_m(m * Math.pow(10, 23));
		o.set_a(a * Math.pow(10, 9));
		o.set_e(e);
		o.size=s;
		objects.add(o);

	}

	public void addRocket(String name, double x, double y, double Vx, double Vy, int r, double m, double fuel,
			double fuel_consump, double fuel_velo, double course_x, double course_y) {
		rocket = new Rocket();
		rocket.set_name(name);
		rocket.set_vx(Vx);
		rocket.set_vy(Vy);
		rocket.setX(x);
		rocket.setY(y);
		rocket.set_r(r);
		rocket.set_m(m);
		rocket.set_fuel(fuel);
		rocket.set_consup(fuel_consump);
		rocket.set_fuelVelo(fuel_velo);
		rocket.set_course_x(course_x);
		rocket.set_course_y(course_y);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint(g);
		

	}

}