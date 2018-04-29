package newton;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OptionFrame  extends JFrame{
	private static final long serialVersionUID = 1L;

	
	
	public OptionFrame(MainFrame main) {
		
		mainFrame=main;
		
		vehicle_color = new JButton("Wybierz kolor rakiety");
		
		vehicle_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null,"Choose Color",color);
				
				
			}
		});
		exit=new JButton("Powrót");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		if(mainFrame.n=="pl")
		{
			exit.setText("Powrót");
			vehicle_color.setText("Wybierz kolor rakiety");
		}
		if(mainFrame.n=="en")
		{
			exit.setText("Back");
			vehicle_color.setText("Choose color of vehicle");
		}
        this.setSize(300, 300);
        
        java.net.URL im = getClass().getResource("background.jpg"); // path to image
        ImageIcon imageIcon = new ImageIcon(im); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(this.getWidth(), this.getHeight(),java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		setContentPane(new JLabel(imageIcon));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		rankingPanel=new JPanel();
		
		rankingPanel.setLayout(new GridLayout(2,1));
		rankingPanel.add(vehicle_color);
		
		rankingPanel.add(exit);
		
		c.ipady = 40;   
		c.ipadx = 60;
		c.gridwidth = 0;
		c.gridx = 1;
		c.gridy = 1;
		this.add(rankingPanel, c);
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	

	JPanel rankingPanel;
	JButton vehicle_color;
	JButton exit;
	Color color;
	MainFrame mainFrame;
	

}
