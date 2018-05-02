package newton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw extends JPanel {
	AstronomicalObject object;
	GameFrame game;
	BufferedImage image;
	
	ArrayList<BufferedImage> images= new ArrayList<BufferedImage>();
	String [] names= {"earth", "jupiter", "mars", "mercury", "neptune", "pluto"};

	
	public void addImages() {
		for(int i =0; i<names.length; i++) {
			object.set_name(names[i]);
			String name_file = object.get_name() + ".png";
			File imageFile = new File(name_file);
			try {
				image = ImageIO.read(imageFile);
				images.add(image);
				System.out.println("Dodano"+i);
			} catch (IOException e) {
				System.err.println("Blad odczytu grafiki dla obiektu" + object.get_name());
				e.printStackTrace();
			}
			
		}
	}
	public void paint(Graphics2D g) {
		
		for(int i = 0; i<images.size(); i++) {
			addImages();
			g.setColor(Color.BLUE);
			g.drawRect(200, 200, 100, 100);
			g.drawImage(images.get(i), 100+10*i, 100+10*i, 100+10*i, 100+10*i, game.centerPanel);
		}
		
	}
	
	public void paintComponent(Graphics2D g) {
		super.paintComponent(g);

		game.centerPanel.paint(g);
	}
	

}
