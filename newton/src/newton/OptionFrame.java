package newton;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OptionFrame  extends JFrame{
	Draw draw;
	String s="rocket1_1";
	
	private static final long serialVersionUID = 1L;

	
	
	public OptionFrame(/*MainFrame main*/) {
		
		//mainFrame=main;
		
		rocket1 = new JCheckBox("rakieta 1");
		rocket2 = new JCheckBox("rakieta 2");
		rocket3 = new JCheckBox("rakieta 3");
		
		rocket1.addActionListener(l);
		rocket2.addActionListener(l);
		rocket3.addActionListener(l);
		
		exit=new JButton("Powrót");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		/*if(mainFrame.n=="pl")
		{
			exit.setText("Powrót");
			
		}
		if(mainFrame.n=="en")
		{
			exit.setText("Back");
			
		}*/
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
		
		
		rankingPanel.add(exit);
		rankingPanel.add(rocket1);
		rankingPanel.add(rocket2);
		rankingPanel.add(rocket3);
		
		
		c.ipady = 40;   
		c.ipadx = 60;
		c.gridwidth = 0;
		c.gridx = 1;
		c.gridy = 1;
		this.add(rankingPanel, c);
        
        //this.setVisible(true);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	ActionListener l = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object source = e.getSource();
			if(source==rocket1) {
				s="rocket1_1";
				//draw.names[draw.size-1]=s;
				
			}
			if(source==rocket2) {
				s="rocket2";
				//draw.names[draw.size-1]=s;
			}
			if(source==rocket3) {
				s="rocket3";
				//draw.names[draw.size-1]=s;
			}
			
		}
	};

	public String getS() {
		return s;
	}

	JPanel rankingPanel;
	JCheckBox rocket1;
	JCheckBox rocket2;
	JCheckBox rocket3;
	JButton exit;
	
	MainFrame mainFrame;
	

}
