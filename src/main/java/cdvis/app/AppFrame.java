package cdvis.app;

import java.awt.Dimension;

import javax.swing.JFrame;

public class AppFrame extends JFrame {
	
	public AppFrame(AppPanel a, ControlPanel c, ChordLabel cl) {
		setUp(a, c, cl);
	}
	
	
	public void setUp(AppPanel aPanel, ControlPanel cPanel, ChordLabel cLabel) {
		setTitle("Chord Visualization");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
		setResizable(false);

		setLayout(null);
		add(aPanel);
		add(cPanel);
		add(cLabel);
		pack();
		setVisible(true);
		
		try {
			setLocationRelativeTo(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
