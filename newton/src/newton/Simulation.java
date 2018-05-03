package newton;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
	List<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	Rocket rocket = new Rocket();
	boolean log = true;

	public Simulation(List<AstronomicalObject> objects) {
		this.objects = objects;
		this.log = true;// jeśli wartość prawdziwa wykonuje dla obiektu astronomicznego
		
	}

	public Simulation(Rocket rocket, List<AstronomicalObject> objects) {
		this.rocket = rocket;
		this.objects = objects;
		this.log = false;// jeśli wartość prawdziwa wykonuje dla rakiety
	}

	public void run() {
		int dt = 1;
		double fx = 0;
		double fy = 0;
		double G = 1;// Trzeba zmienić potem na wartość rzeczywistą
		double tmp1 = 0;
		double tmp2 = 0;
		double tmp3 = 0;
		if (log = true) {
			// LICZENIE SIŁY
			for (int i = 0; i < objects.size(); i++) {
				for (int j = 0; i < objects.size(); i++) {
					if (i != j) {
						tmp1 = Math.pow((objects.get(i).getX() - objects.get(j).getX()), 2);
						tmp2 = Math.pow((objects.get(i).getY() - objects.get(j).getY()), 2);
						tmp3 = Math.sqrt(tmp1 + tmp2);
						tmp1 = Math.pow(tmp3, 3);

						fx += G * (objects.get(i).get_m() * objects.get(j).get_m()
								* (objects.get(i).getX() - objects.get(j).getX())) / tmp1;
						fy += G * (objects.get(i).get_m() * objects.get(j).get_m()
								* (objects.get(i).getY() - objects.get(j).getY())) / tmp1;
					}
				}
				objects.get(i).set_fx(fx);
				objects.get(i).set_fy(fy);
				fx = 0;
				fy = 0;
			}
			// LICZENIE X, Y, Vx, Vy
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).set_vx(objects.get(i).get_vx() + objects.get(i).get_fx() * dt / objects.get(i).get_m());
				objects.get(i).set_vy(objects.get(i).get_vy() + objects.get(i).get_fy() * dt / objects.get(i).get_m());
				objects.get(i).setX((int) (objects.get(i).getX() + objects.get(i).get_vx() * dt));
				objects.get(i).setY((int) (objects.get(i).getY() + objects.get(i).get_vy() * dt));
			}

			
		} else {
			// LICZENIE SIŁY
			for (int j = 0; j < objects.size(); j++) {
				tmp1 = Math.pow((rocket.getX() - objects.get(j).getX()), 2);
				tmp2 = Math.pow((rocket.getY() - objects.get(j).getY()), 2);
				tmp3 = Math.sqrt(tmp1 + tmp2);
				tmp1 = Math.pow(tmp3, 3);

				fx += G * ((rocket.get_m() + rocket.get_fuel()) * objects.get(j).get_m()* (rocket.getX() - objects.get(j).getX())) / tmp1;
				fy += G * (rocket.get_m() * objects.get(j).get_m() * (rocket.getY() - objects.get(j).getY())) / tmp1;
			}
			// DOLICZENIE SIŁY CIOŁKOWSKIEGO
			tmp1 = Math.pow((rocket.getX() - rocket.get_course_x()), 2);
			tmp2 = Math.pow((rocket.getY() - rocket.get_course_y()), 2);
			tmp3 = Math.sqrt(tmp1 + tmp2);
			fx += ((rocket.get_fuelVelo() * rocket.get_consup()) / (rocket.get_m() + rocket.get_consup() * dt)* (rocket.get_m() + rocket.get_fuel()))*(rocket.getX() - rocket.get_course_x()/tmp3);
			fy += ((rocket.get_fuelVelo() * rocket.get_consup()) / (rocket.get_m() + rocket.get_consup() * dt)* (rocket.get_m() + rocket.get_fuel()))*(rocket.getY() - rocket.get_course_y()/tmp3);
			rocket.set_fx(fx);
			rocket.set_fy(fy);
			// LICZENIE X, Y, Vx, Vy
			rocket.set_vx(rocket.get_vx() + rocket.get_fx() * dt / (rocket.get_m() + rocket.get_fuel()));
			rocket.set_vy(rocket.get_vy() + rocket.get_fy() * dt / (rocket.get_m() + rocket.get_fuel()));
			rocket.setX((int) (rocket.getX() + rocket.get_vx() * dt));
			rocket.setY((int) (rocket.getY() + rocket.get_vy() * dt));

			// RYSOWANIE
		}
	}
	
}