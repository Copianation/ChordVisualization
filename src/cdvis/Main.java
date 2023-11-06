package cdvis;

import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import cdvis.app.AppFrame;
import cdvis.app.AppPanel;
import cdvis.app.ControlPanel;
import cdvis.app.ChordLabel;
import cdvis.component.MusicalNet;
import cdvis.component.Tonnetz;
import cdvis.listener.ControlListener;
import cdvis.listener.TonnetzController;
import cdvis.listener.TonnetzMover;
import cdvis.menu.FileMenu;
import cdvis.menu.MenuBar;
import cdvis.menu.SettingsMenu;
import cdvis.sound.MidiPlayer;
import cdvis.sound.NotePlayer;

public class Main {
	private AppPanel aPanel;
	private ControlPanel cPanel;
	private ChordLabel cLabel;
	private MusicalNet net;
	private AppFrame aFrame;

	private NotePlayer player;
	private MidiPlayer midiPlayer;

	private TonnetzController TController;
	private TonnetzMover TMover;
	private ControlListener CListener;

	public Main() {
		init();
	}

	public void init() {
		net = new Tonnetz();
		aPanel = new AppPanel(net);
		cPanel = new ControlPanel();
		cLabel = new ChordLabel(net);

		try {
			player = new NotePlayer(net);
			midiPlayer = new MidiPlayer(net, aPanel, cLabel, player);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		aFrame = new AppFrame(aPanel, cPanel, cLabel);
		addListeners();
		addMenu();

	}

	public void addMenu() {
		FileMenu fileMenu = new FileMenu(player,midiPlayer);
		SettingsMenu settingsMenu = new SettingsMenu(aPanel, cPanel, CListener, TMover,
				TController, player, midiPlayer, cLabel);

		MenuBar menuBar = new MenuBar();
		menuBar.add(fileMenu);
		menuBar.add(settingsMenu);

		aFrame.setJMenuBar(menuBar);
	}
	
	public void addListeners() {
		TController = new TonnetzController(net, aPanel, player, cLabel);
		aPanel.addMouseListener(TController);

		TMover = new TonnetzMover(net, aPanel);
		aPanel.addMouseListener(TMover);
		aPanel.addMouseMotionListener(TMover);
		aPanel.addMouseWheelListener(TMover);

		CListener = new ControlListener(net, aPanel, player, cLabel);
		for (JButton b : cPanel.getButtons()) {
			b.addActionListener(CListener);
		}

		aFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				player.stop();
				midiPlayer.stop();
			}
		});
	}

	public static void main(String[] args) {
		new Main();
	}

}
