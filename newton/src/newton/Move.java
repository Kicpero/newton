package newton;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Move  {
	Draw draw;
	GameFrame game;
	Simulation symA;//symulacja dla obiektów
	Simulation symR;//symulacja rakiety
	
	
	void rozpocznijRuch() {
		

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate((new Runnable() {
			public void run() {
				
				symA=new Simulation(draw.objects);
				symR=new Simulation(draw.rocket, draw.objects);
				symA.run();
				symR.run();
				draw.repaint();
				
			}}), 0,10,MILLISECONDS);

	}
	public Move() {
		draw=new Draw();
		rozpocznijRuch();
	}
	

}