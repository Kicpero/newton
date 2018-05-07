package newton;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Move {
	Draw draw;
	GameFrame game;
	Simulation symA;//symulacja dla obiektów
	//Simulation symR;//symulacja rakiety
	
	
	void rozpocznijRuch() {
		

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate((new Runnable() {
			public void run() {
				
				symA=new Simulation(draw.objects);
				symA.run();
				for (int i = 0; i<draw.objects.size();i++) {
									draw.repaint();
					
				}
				//symR=new Simulation(draw.rocket, draw.objects);
				//symR.run();
				draw.repaint();
				
			}}), 3,5,MILLISECONDS);

	}
	public Move() {
		draw=new Draw();
		rozpocznijRuch();
	}

}
