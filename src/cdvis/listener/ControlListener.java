package cdvis.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cdvis.app.ControlPanel;
import cdvis.component.Tonnetz;
import cdvis.sound.NotePlayer;

public class ControlListener implements ActionListener {
	private final Tonnetz net;
	private final ControlPanel cPanel;
	private final NotePlayer player;


	public ControlListener(Tonnetz n, ControlPanel c, NotePlayer p) {
		this.net = n;
		this.cPanel = c;
		this.player = p;
		
		for (JButton b :cPanel.getButtons()) {
			b.addActionListener(this);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		net.moveNotes( Integer.parseInt(e.getActionCommand()) );
		player.setNotes();
	}

}
