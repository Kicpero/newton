package newton;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
	List<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	public Simulation(List<AstronomicalObject> objects) {
		this.objects=objects;
	}
	
	
	public void run() {
		int dt=1;
		double fx=0;
		double fy=0;
		double G=1;
		double tmp1=0;
		double tmp2=0;
		double tmp3=0;
		//LICZENIE SIŁY
		for (int i = 0; i<objects.size() ; i++) {
			for (int j = 0; i<objects.size() ; i++) {
				if (i!=j)
				{
					tmp1=Math.pow((objects.get(i).getX()-objects.get(j).getX()),2);
					tmp2=Math.pow((objects.get(i).getY()-objects.get(j).getY()),2);
					
					fx+=G*(objects.get(i).get_m()*objects.get(j).get_m()*(objects.get(i).getX()-objects.get(j).getX()))/tmp1;
					fy+=G*(objects.get(i).get_m()*objects.get(j).get_m()*(objects.get(i).getY()-objects.get(j).getY()))/tmp1;
				}
			}
			objects.get(i).set_fx(fx);
			objects.get(i).set_fy(fy);
			fx=0;
			fy=0;
		}
		//LICZENIE X, Y, Vx, Vy
		//RYSOWANIE
	}
}
