package newton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import newton.MainFrame;

public class GameFrame extends JFrame {
	List<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
	private static final long serialVersionUID = 1L;
	
	public GameFrame(MainFrame main)  {
		
		mPanel=main;

		// right Panel
		rightPanel= new JPanel();
		time=new JLabel("CZAS:");
		time_to_end= new JLabel("...");
		fuel=new JLabel("PALIWO");
		level_fuel= new JLabel ("...");
		points=new JLabel("PUNKTY:");
		score_points= new JLabel("...");
		exit=new JButton("Powrót");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		if(mPanel.n=="pl")
		{
			exit.setText("Powrót");
			time.setText("Czas");
			points.setText("Punkty");
			fuel.setText("Paliwo");
			}
		if(mPanel.n=="en")
		{
			exit.setText("Back");
			time.setText("Time");
			points.setText("Points");
			fuel.setText("Fuel");
		}
		
		rightPanel.setLayout(new FlowLayout());
		rightPanel.add(fuel);
		rightPanel.add(level_fuel);
		rightPanel.add(time);
		rightPanel.add(time_to_end);
		rightPanel.add(points);
		rightPanel.add(score_points);
		rightPanel.add(exit);
		rightPanel.setVisible(true);
		rightPanel.setPreferredSize(new Dimension(500, 30));
		rightPanel.setBackground(Color.WHITE);
		
		// koniec right Panel
		
		// center Panel
		
		centerPanel = new JPanel();
		centerPanel.setSize(500, 500);
		java.net.URL im = getClass().getResource("background2.jpg");//path to image
		ImageIcon imageIcon = new ImageIcon(im); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(centerPanel.getWidth(), centerPanel.getHeight(),java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		centerPanel.add(new JLabel(imageIcon));
		
		// koniec center Panel
		
		//razem
		this.setSize(500, 550);
		this.setLayout( new BorderLayout());
		this.add(rightPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//DODAWANIE OBIEKTÓW I RAKIETY
		addAstroObject("Slonce",300,300,10,10,10,10,100);
		addAstroObject("Merkury",250,300,10,10,10,10,10);
		addAstroObject("Wenus",200,300,10,10,10,10,10);
		addAstroObject("Ziemie",150,300,10,10,10,10,10);
		addAstroObject("Mars",100,300,10,10,10,10,10);
		addAstroObject("Jowisz",50,300,10,10,10,10,20);
		addRocket("Rakieta",50,300,10,10,10,10,20,100,1,1);
		
		//URUCHAMIANIE SYMULACJI (COŚ TU NIE DZIAŁA, CHYBA RUN SIĘ UMIESZCZAŁO)
		final ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
		Simulation sym=new Simulation(objects);
		//exec.scheduleAtFixedRate(sym.run(),100, 30, TimeUnit.MILLISECONDS);
	}	
	
	public void addAstroObject(String name,int x, int y, double Vx, double Vy,double fx,double fy,double m) {
		AstronomicalObject o = new AstronomicalObject();
		o.set_name(name);
		o.set_vx(Vx);
		o.set_vy(Vy);
		o.setX(x);
		o.setY(y);
		o.set_fx(fx);
		o.set_fy(fy);	
		o.set_m(m);
		objects.add(o);		
		
	}
	public void addRocket(String name,int x, int y, double Vx, double Vy,double fx,double fy,double m, double fuel, double fuel_consump, double fuel_velo) {
		r = new Rocket();
		r.set_name(name);
		r.set_vx(Vx);
		r.set_vy(Vy);
		r.setX(x);
		r.setY(y);
		r.set_fx(fx);
		r.set_fy(fy);	
		r.set_m(m);
		r.set_fuel(fuel);
		r.set_consup(fuel_consump);
		r.set_fuelVelo(fuel_velo);
		
	}
	/*public void repaint(Graphics g) {
	}*/
	
	MainFrame mPanel;
	JPanel rightPanel;
	JPanel centerPanel;
	JLabel time;
	JLabel points;
	JLabel fuel;
	JLabel level_fuel;
	JLabel time_to_end;
	JLabel score_points;
	JButton exit;
	Rocket r;

}