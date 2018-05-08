package newton;

import java.util.ArrayList;

public class Simulation implements Runnable {
	
	ArrayList<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	Rocket rocket = new Rocket();
	boolean log = true;
	static double totalEnergy0=0;

	public Simulation(ArrayList<AstronomicalObject> objects) {
		this.objects = objects;
		this.log = true;// jeśli wartość prawdziwa wykonuje dla obiektu astronomicznego
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

		double G = 6.67*Math.pow(10, -6);// Trzeba zmienić potem na wartość rzeczywistą 
		double tmp1 = 0;
		double tmp2 = 0;
		double tmp3 = 0;

		double potentialEnergy=0;
		double kineticEnergy=0;
		double totalEnergy=0;
		double epsilon=0.0001;//dokładność ZZE
		//---------------------------
		/*double Lo=0;
		double r=0;*/
		if (log == true) {
		/*	for (int i = 1; i < objects.size(); i++) {
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
			}*/
			 // LICZENIE SIŁY METODĄ LEAP FROG
			 
			for (int i = 0; i < objects.size(); i++) {
				//----------------------------OBLICZENIA ENERGI KINETYCZNEJ-------------------
				kineticEnergy=(Math.pow(objects.get(i).get_vx(),2)+Math.pow(objects.get(i).get_vy(),2))*objects.get(i).get_m()/2;
				for (int j = 0; j < objects.size(); j++) {
					if (i != j) {
						//----------------------------OBLICZENIA POMOCNICZE-------------------
						tmp1 = Math.pow((objects.get(i).getX() - objects.get(j).getX()), 2);
						tmp2 = Math.pow((objects.get(i).getY()- objects.get(j).getY()), 2);
						tmp3 = Math.sqrt(tmp1 + tmp2);
						tmp1 = Math.pow(tmp3, 3);
						//----------------------------OBLICZENIA SIŁY-------------------
						fx += G * (objects.get(i).get_m()*objects.get(j).get_m()*(-objects.get(i).getX() + objects.get(j).getX()))/ tmp1;
						fy += G * (objects.get(i).get_m()* objects.get(j).get_m()* (-objects.get(i).getY() + objects.get(j).getY()))/ tmp1;
						//---------------------OBLICZENIA ENERGI POTENCJALNEJ-------------------
						potentialEnergy+=(-G*objects.get(i).get_m()*objects.get(j).get_m()/(tmp3));
					}
				}
				objects.get(i).set_fx(fx);
				objects.get(i).set_fy(fy);
				totalEnergy+=potentialEnergy+kineticEnergy;
				objects.get(i).set_energy(potentialEnergy+kineticEnergy);
				fx = 0;
				fy = 0;
				//----------------------------DODANIE E0----------------------------------
				if (objects.get(i).get_loop()==true) {
					objects.get(i).set_energy0(potentialEnergy+kineticEnergy);
					totalEnergy0+=potentialEnergy+kineticEnergy;
					objects.get(i).set_loop(false);
				}
				potentialEnergy=0;
				kineticEnergy=0;
			}

			// LICZENIE X, Y, Vx, Vy
			for (int i = 0; i < objects.size(); i++) {
				//----------------------------OKREŚLENIE VX I VY---------------------------
				tmp1=objects.get(i).get_vx() + objects.get(i).get_fx() * dt / (objects.get(i).get_m());
				objects.get(i).set_vx(tmp1);
				tmp1=objects.get(i).get_vy() + objects.get(i).get_fy() * dt / (objects.get(i).get_m());
				objects.get(i).set_vy(tmp1);
				//----------------------------OKREŚLENIE X I Y-----------------------------
				tmp1=(objects.get(i).getX() + objects.get(i).get_vx() * dt + objects.get(i).get_fx() * dt*dt / (objects.get(i).get_m()*2));
				objects.get(i).setX((tmp1));
				tmp1=(objects.get(i).getY() + objects.get(i).get_vy() * dt+ objects.get(i).get_fy() * dt*dt / (objects.get(i).get_m()*2));
				objects.get(i).setY((tmp1));
				//----------------------------POPRAWKA ENERGETYCZNA-----------------------------
				if(Math.abs(totalEnergy0-totalEnergy)>epsilon) {
					//System.out.println("ENERGIA 0: "+totalEnergy0+" ENERGIA POTEM: "+totalEnergy);
				}
			}
			totalEnergy=0;
		} 
		else {
			// LICZENIE SIŁY
			for (int j = 0; j < objects.size()-1; j++) {
				tmp1 = Math.pow((rocket.getX() - objects.get(j).getX()), 2);
				tmp2 = Math.pow((rocket.getY() - objects.get(j).getY()), 2);
				tmp3 = Math.sqrt(tmp1 + tmp2);
				tmp1 = Math.pow(tmp3, 3);
				fx += G * ((rocket.get_m() + rocket.get_fuel()) * objects.get(j).get_m()* (-rocket.getX() + objects.get(j).getX()))/tmp1;
				fy += G * ((rocket.get_m() + rocket.get_fuel()) * objects.get(j).get_m() * (-rocket.getY() + objects.get(j).getY()))/tmp1;
			}
			// DOLICZENIE SIŁY CIOŁKOWSKIEGO
			if(rocket.get_fuel()>0)
			{
				//System.out.println("ILOŚĆ PALIWA TO:"+rocket.get_fuel());
				tmp1 = Math.pow((rocket.getX() - rocket.get_course_x()), 2);
				tmp2 = Math.pow((rocket.getY() - rocket.get_course_y()), 2);
				tmp3 = Math.sqrt(tmp1 + tmp2);
				fx += ((rocket.get_fuelVelo() * rocket.get_consup()) / (rocket.get_m() - rocket.get_consup() * dt)* (rocket.get_m() + rocket.get_fuel()))*(rocket.getX() - rocket.get_course_x()/tmp3);
				fy += ((rocket.get_fuelVelo() * rocket.get_consup()) / (rocket.get_m() - rocket.get_consup() * dt)* (rocket.get_m() + rocket.get_fuel()))*(rocket.getY() - rocket.get_course_y()/tmp3);
				rocket.set_fuel(rocket.get_fuel()-rocket.get_consup() * dt);
			}
			else
			{
				System.out.println("KONIEC PALIWA");
			}
			rocket.set_fx(fx);
			rocket.set_fy(fy);
			//System.out.println("FX RAKIETY: "+fx);
			//System.out.println("FY RAKIETY: "+fy);
			fx = 0;
			fy = 0;
			// LICZENIE X, Y, Vx, Vy
			tmp1=rocket.get_vx() + rocket.get_fx() *dt / (rocket.get_m() + rocket.get_fuel());
			rocket.set_vx(tmp1);
			//System.out.println("VX RAKIETY: "+rocket.get_vx());
			tmp1=rocket.get_vy() + rocket.get_fy() *dt / (rocket.get_m() + rocket.get_fuel());
			rocket.set_vy(tmp1);
			//System.out.println("VY RAKIETY: "+rocket.get_vy());
			tmp1=(rocket.getX() + rocket.get_vx()*dt + rocket.get_fx()*dt*dt / (2*(rocket.get_m() + rocket.get_fuel())));
			if(tmp1<0)rocket.setX(tmp1+900);
			else if(tmp1>900)rocket.setX(tmp1-900);
			else rocket.setX(tmp1);
			//System.out.println("X: "+rocket.getX());
			tmp1=(rocket.getY() + rocket.get_vy() *dt+ rocket.get_fy()*dt*dt / (2*(rocket.get_m() + rocket.get_fuel())));
			if(tmp1<0)rocket.setY(tmp1+600);
			else if(tmp1>600)rocket.setY(tmp1-600);
			else rocket.setY(tmp1);
			
			//System.out.println("Y: "+rocket.getY());
		}
		
	}
}