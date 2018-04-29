package newton;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AstronomicalObject  {
	protected Integer posX;
	protected Integer posY;
	protected double velocityX;
	protected double velocityY;
	protected double forceX;
	protected double forceY;
	protected double mass;
	private String name;//nazwa obrazka musi być taka sama jak nazwa obiektu
	BufferedImage image;
	GameFrame game;

	public void setX(int posX) {
		this.posX = posX;
	}

	public double getX() {
		return posX;
	}

	public void setY(int posY) {
		this.posY = posY;
	}

	public double getY() {
		return posY;
	}

	public void set_vx(double velocityX) {
		this.velocityX = velocityX;
	}

	public double get_vx() {
		return velocityX;
	}

	public void set_vy(double velocityY) {
		this.velocityY = velocityY;
	}

	public double get_vy() {
		return velocityY;
	}

	public void set_fx(double forceX) {
		this.forceX = forceX;
	}

	public double get_fx() {
		return forceX;
	}

	public void set_fy(double forceY) {
		this.forceY = forceY;
	}

	public double get_fy() {
		return forceY;
	}

	public double get_m() {
		return mass;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, posX, posY, game.centerPanel);
	}

	public void setImage() {

		String name_file = name + ".jpg";

		File imageFile = new File(name_file);
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu grafiki dla obiektu" + name);
			e.printStackTrace();
		}

	}
}
