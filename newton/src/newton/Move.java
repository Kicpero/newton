package newton;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Move {
	Draw centerPanel;
	Simulation sym;
	void rozpocznijRuch() {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate((new Runnable() {
			public void run() {
				System.out.println("TU DZIAŁA");
				sym=new Simulation(centerPanel.objects);
				sym.run();
				System.out.println(centerPanel.objects.size());
				for (int i = 0; i<centerPanel.objects.size();i++) {
					centerPanel.updateAstroObject(sym.getAstroObject());
					centerPanel.repaint();
				}
			}

		}), 100, 30, TimeUnit.MILLISECONDS);
		

	}

}
