package newton;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocket extends AstronomicalObject{
	protected double  fuel;//paliwo
	protected double  fuel_consumption;//zurzycie paliwa kg/s
	protected double  fuel_velocity;//prędkość gazów wylotowych względem rakiety

	protected int course_x;
	protected int course_y;
	//kierunki zwrotu rakiety
	
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
	public void set_course_x(int course_x) {
		this.course_x = course_x;
	}

	public double get_course_x() {
		return fuel_velocity;
	}
	public void set_course_y(int course_y) {
		this.course_y = course_y;
	}

	public double get_course_y() {
		return course_y;
	}
	
	
}