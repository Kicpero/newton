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
	
	ArrayList<BufferedImage> images= new ArrayList<BufferedImage>();
	String [] names= {"earth", "jupiter", "mars", "mercury", "neptune", "pluto"};

	public Draw(){
		super();
	}
	
	public void addImages() {
		for(int i =0; i<names.length; i++) {
			String name_file = names[i] + ".png";
			URL sciezka = getClass().getResource(name_file);
			try {

				image = ImageIO.read(sciezka);
				images.add(image);
				System.out.println("Dodano"+i);
			} catch (IOException e) {
				System.err.println("Blad odczytu grafiki dla obiektu" + names[i]);
				e.printStackTrace();
			}
			
		}
	}
	
	public void paint(Graphics g) {
		for(int i = 0; i<images.size(); i++) {
			g.drawImage(images.get(i), 0+100*i, 0+100*i, null);
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		paint(g);
	}
	

}
