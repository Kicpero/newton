package newton;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Racket extends AstronomicalObject{
	protected double  fuel;
	
	public void draw(Graphics2D g) {
		g.drawImage(image, posX, posY, game.centerPanel);
	}

	public void setImage() {

		String name_file = "racket.jpg";

		File imageFile = new File(name_file);
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu grafiki dla rakiety");
			e.printStackTrace();
		}

	}

}
