package newton;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Move {
	Draw draw=new Draw();
	Simulation sym;
	void rozpocznijRuch() {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate((new Runnable() {
			public void run() {
				System.out.println(draw.objects.size());
				sym=new Simulation(draw.objects);
				sym.run();
				System.out.println(draw.objects.size());
				for (int i = 0; i<draw.objects.size();i++) {
					draw.updateAstroObject(sym.getAstroObject());
					draw.repaint();
				}
			}

		}), 100, 30, TimeUnit.MILLISECONDS);
		

	}

}
