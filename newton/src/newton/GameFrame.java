package newton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import newton.MainFrame;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Draw draw = new Draw();
	double change = 1;// zmiana w paliwie strzałki góra dół
	int i = 1;// numer grafiki

	public GameFrame(MainFrame main) {

		mPanel = main;
		// this.addKeyListener(this);
		
		key();
		// right Panel
		rightPanel = new JPanel();
		time = new JLabel("CZAS:");
		time_to_end = new JLabel("...");
		fuel = new JLabel("PALIWO");
		level_fuel = new JLabel("...");
		points = new JLabel("PUNKTY:");
		score_points = new JLabel("...");
		exit = new JButton("Powrót");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		/*if (mPanel.n == "pl") {
			exit.setText("Powrót");
			time.setText("Czas");
			points.setText("Punkty");
			fuel.setText("Paliwo");
		}
		if (mPanel.n == "en") {
			exit.setText("Back");
			time.setText("Time");
			points.setText("Points");
			fuel.setText("Fuel");
		}
*/
		rightPanel.setLayout(new FlowLayout());
		rightPanel.add(fuel);
		rightPanel.add(level_fuel);
		rightPanel.add(time);
		rightPanel.add(time_to_end);
		rightPanel.add(points);
		rightPanel.add(score_points);
		rightPanel.add(exit);
		rightPanel.setPreferredSize(new Dimension(500, 40));
		rightPanel.setBackground(Color.WHITE);
		// koniec right Panel

		// center Panel

		Move move = new Move();

		// koniec center Panel

		// razem
		this.setSize(900, 600);
		this.setLayout(new BorderLayout());
		// this.add(rightPanel, BorderLayout.NORTH);
		this.add(move.draw, BorderLayout.CENTER);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	MainFrame mPanel;
	JPanel rightPanel;
	JPanel center;
	Draw centerPanel;
	JLabel time;
	JLabel points;
	JLabel fuel;
	JLabel level_fuel;
	JLabel time_to_end;
	JLabel score_points;
	JButton exit;
	Rocket r;

	
	public void key() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_LEFT) {
					if (i == 1)
						i = 4;
					else
						i--;
					System.out.println("Lewo");
					//draw.names[draw.names.length - 1] = "rocket1_" + i;
					draw.repaint();

				}
				if (key == KeyEvent.VK_RIGHT) {
					if (i == 4)
						i = 1;
					else
						i++;
					System.out.println("Prawo");
					//draw.names[draw.names.length - 1] = "rocket1_" + i;
					System.out.println(draw.names[draw.names.length - 1]);
					//draw.repaint();

				}
				if (key == KeyEvent.VK_UP) {
					System.out.println("Góra");
					draw.rocket.set_consup(draw.rocket.get_consup() + change);

				}
				if (key == KeyEvent.VK_DOWN) {
					System.out.println("Dół");
					draw.rocket.set_consup(draw.rocket.get_consup() - change);

				}

			}
		});

	}

}