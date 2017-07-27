package gui;

import javax.swing.JApplet;

public class Applet extends JApplet
{
	private GBoard board;

	public void init()
	{
		board = new GBoard();
		board.setVisible(true);
	}
}
