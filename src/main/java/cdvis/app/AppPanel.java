package cdvis.app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import cdvis.component.MusicalNet;


public class AppPanel extends JPanel{
	
	private MusicalNet net;

	public AppPanel(MusicalNet n) {
		net = n;
		setBounds(Config.SCREEN_WIDTH/4, 0, Config.SCREEN_WIDTH*3/4, Config.SCREEN_HEIGHT);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		net.plot(g2d);
	}

	public void changeMusicalNet(MusicalNet n) {
		net = n;
	}

}
