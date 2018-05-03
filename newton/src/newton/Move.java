package newton;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;



public class Move {
	Draw draw;
	Simulation sym;
	void rozpocznijRuch() {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate((new Runnable() {

			@Override
			public void run() {
				sym=new Simulation(draw.objects);

				for (int i = 0; i<draw.objects.size();i++) {

					
				
					

				}
			}

		}), 0, 5, MILLISECONDS);
		

	}

}
