
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class Test {

	public static void main(String[] args) {

	JFrame frame = new JFrame("PuzzleGui");
		frame.setSize(800, 800);
		PuzzleGui panel=new PuzzleGui();
		frame.add(panel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				 panel.onClick(e);
			}
		});
	}

}
