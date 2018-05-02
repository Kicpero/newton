package newton;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocket extends AstronomicalObject{
	protected double  fuel;//paliwo
	protected double  fuel_consumption;//zurzycie paliwa kg/s
	protected double  fuel_velocity;//prędkość gazów wylotowych względem rakiety
	protected double course_x;
	protected double course_y;//kierunki zwrotu rakiety
	public void set_fuel(double fuel) {
		this.fuel = fuel;
	}

	public double get_fuel() {
		return fuel;
	}
	
	public void set_consup(double fuel_consumption) {
		this.fuel_consumption = fuel_consumption;
	}

	public double get_consup() {
		return fuel_consumption;
	}
	
	public void set_fuelVelo(double fuel_velocity) {
		this.fuel_velocity = fuel_velocity;
	}

	public double get_fuelVelo() {
		return fuel_velocity;
	}
	
	/*public void draw(Graphics2D g) {
		//g.drawImage(image, posX, posY, game.centerPanel);
		g.drawRect(posX, posY, 30, 30);
	}

	public void setImage() {

		String name_file = "rocket.png";

		File imageFile = new File(name_file);
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu grafiki dla rakiety");
			e.printStackTrace();
		}

	}*/

}
