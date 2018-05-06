package newton;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
	ArrayList<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	Rocket rocket = new Rocket();
	boolean log = true;

	public Simulation(ArrayList<AstronomicalObject> objects) {
		this.objects = objects;
		this.log = true;// jeśli wartość prawdziwa wykonuje dla obiektu astronomicznego
		//System.out.println("DZIAŁA");
		
	}

	public Simulation(Rocket rocket, ArrayList<AstronomicalObject> objects) {
		this.rocket = rocket;
		this.objects = objects;
		this.log = false;// jeśli wartość prawdziwa wykonuje dla rakiety
	}
	
	public ArrayList<AstronomicalObject> getAstroObject(){
		return this.objects;
	}

	public void run() {
		
		double dt = 0.1;
		double fx = 0;
		double fy = 0;
		double G = 6.67*Math.pow(10, -11)*Math.pow(88*24*3600/100,2);// Trzeba zmienić potem na wartość rzeczywistą 
		double tmp1 = 0;
		double tmp2 = 0;
		double tmp3 = 0;
		double poprawka=Math.pow(10, 9);
		//---------------------------
		double Lo=0;
		double r=0;
		if (log == true) {
			for (int i = 1; i < objects.size(); i++) {
				//System.out.println("OBIKET: "+i);
				r=objects.get(i).get_a()*(1-Math.pow(objects.get(i).get_e(), 2))/(1+objects.get(i).get_e()*Math.cos(objects.get(i).get_fi()));
				//System.out.println("X: "+objects.get(i).getX());
				//System.out.println("Y: "+objects.get(i).getY());
				Lo=objects.get(i).get_m()*r*objects.get(i).get_vy();
				//System.out.println("Lo: "+Lo);
				objects.get(i).setX(objects.get(0).getX()-r*Math.cos(objects.get(i).get_fi())/poprawka);
				//System.out.println("X NEW: "+objects.get(i).getX());
				objects.get(i).setY(objects.get(0).getY()+r*Math.sin(objects.get(i).get_fi())/poprawka);
				//System.out.println("Y NEW: "+objects.get(i).getY());
				objects.get(i).set_fi(objects.get(i).get_fi()+Lo/(Math.pow(r, 2)*objects.get(i).get_m())*10000);
				//System.out.println("FI: "+objects.get(i).get_fi());
			}
			//TA METODA MA JAKIŚ BŁĄD
			/*
			 // LICZENIE SIŁY
			 
			for (int i = 0; i < objects.size(); i++) {
				System.out.println("CIAŁO NUMER "+i+" ODDZIALUJE Z:");
				for (int j = 0; j < objects.size(); j++) {
					if (i != j) {
						tmp1 = Math.pow((objects.get(i).getX()*poprawka - objects.get(j).getX()*poprawka), 2);
						System.out.println(tmp1);
						tmp2 = Math.pow((objects.get(i).getY()*poprawka- objects.get(j).getY()*poprawka), 2);
						System.out.println(tmp2);
						tmp3 = Math.sqrt(tmp1 + tmp2);
						System.out.println(tmp3);
						tmp1 = Math.pow(tmp3, 3);
						System.out.println(tmp1);

						fx += G * (objects.get(i).get_m()*objects.get(j).get_m()*(-objects.get(i).getX()*poprawka + objects.get(j).getX()*poprawka))/ tmp1;
						fy += G * (objects.get(i).get_m()* objects.get(j).get_m()* (-objects.get(i).getY()*poprawka + objects.get(j).getY()*poprawka))/ tmp1;
						System.out.println(j+". SILA FX: "+fx);
						System.out.println(j+". SILA FY: "+fy);
					}
				}
				//System.out.println(i+". "+fx);
				objects.get(i).set_fx(fx);
				objects.get(i).set_fy(fy);
				fx = 0;
				fy = 0;
			}
			// LICZENIE X, Y, Vx, Vy
			for (int i = 0; i < objects.size(); i++) {
				tmp1=objects.get(i).get_vx() + objects.get(i).get_fx() * dt / (objects.get(i).get_m());
				System.out.println(i+". VX: "+tmp1);
				objects.get(i).set_vx(tmp1);
				tmp1=objects.get(i).get_vy() + objects.get(i).get_fy() * dt / (objects.get(i).get_m());
				System.out.println(i+". VY: "+tmp1);
				objects.get(i).set_vy(tmp1);
				tmp1=(objects.get(i).getX()*poprawka + objects.get(i).get_vx() * dt + objects.get(i).get_fx() * dt*dt / (objects.get(i).get_m()*2))/poprawka;
				System.out.println(i+". X: "+tmp1);
				objects.get(i).setX((tmp1));
				tmp1=(objects.get(i).getY()*poprawka + objects.get(i).get_vy() * dt+ objects.get(i).get_fy() * dt*dt / (objects.get(i).get_m()*2))/poprawka;
				System.out.println(i+". Y: "+tmp1);
				objects.get(i).setY((tmp1));
			}
			 */
			
		} 
		else {
			// LICZENIE SIŁY
			for (int j = 0; j < objects.size(); j++) {
				tmp1 = Math.pow((rocket.getX()*poprawka - objects.get(j).getX()*poprawka), 2);
				tmp2 = Math.pow((rocket.getY()*poprawka - objects.get(j).getY()*poprawka), 2);
				tmp3 = Math.sqrt(tmp1 + tmp2);
				tmp1 = Math.pow(tmp3, 3);

				fx += G * ((rocket.get_m() + rocket.get_fuel()) * objects.get(j).get_m()* (-rocket.getX()*poprawka + objects.get(j).getX()*poprawka)) / tmp1;
				fy += G * (rocket.get_m() * objects.get(j).get_m() * (rocket.getY()*poprawka - objects.get(j).getY()*poprawka)) / tmp1;
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
			rocket.setX((int) ((rocket.getX()*poprawka + rocket.get_vx() * dt)/poprawka));
			rocket.setY((int) ((rocket.getY()*poprawka + rocket.get_vy() * dt)/poprawka));
		}
		
	}
}