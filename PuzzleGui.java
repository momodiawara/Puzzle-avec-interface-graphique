import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PuzzleGui extends JPanel{

	protected BufferedImage image;
	protected BufferedImage[] imageEnOrdre=new BufferedImage[9];
	protected BufferedImage[] plateau=new BufferedImage[9];
	protected int w;
	protected int h;
	protected int nX=-1;
	protected int nY=-1;	
	protected int aX=-20;
	protected int aY=-20;

	public PuzzleGui(){
		try{
			image=ImageIO.read(new File("/Image/moodle_logo.png"));
		}catch(Exception e){
			System.out.println(e.getMessage());	
		}
		w=image.getWidth()/3;
		h=image.getHeight()/3;
		creerPuzzle();
	}

	public void creerPuzzle(){
		LinkedList<BufferedImage> l=new LinkedList<BufferedImage>();
		int x=0;
		int y=0;
		int o=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				l.add(image.getSubimage(x,y,w,h));
				plateau[o]=image.getSubimage(x,y,w,h);
				o++;
				x+=w;
			}
			x=0;
			y+=h;
		}
		for(int i=0;i<9;i++){
			imageEnOrdre[i]=l.get(i);
		}
		Collections.shuffle(l);
		for(int i=0;i<9;i++){
			plateau[i]=l.get(i);
		}
	}

	public boolean winner(){
		for(int i=0;i<9;i++){
			if(plateau[i]!=imageEnOrdre[i]){
				return false;
			}
		}
		return true;
	}

	@Override
	public void paintComponent(Graphics g){
		int x=0;
		int y=0;
		int o=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				g.drawImage(plateau[o],x,y,null);
				  g.drawRect(x,y,w,h);
				o++;
				x+=w;
			}
			x=0;
			y+=h;
		}
	}

	public void onClick(MouseEvent e){
		if(winner()){
			JOptionPane.showMessageDialog(null, "GG !");
			creerPuzzle();
		}
		if(aX==-20 && aY==-20){
			aX=e.getX();
			aY=e.getY();
		}else{
			nX=e.getX();
			nY=e.getY();
			BufferedImage temp=plateau[getNum(aX,aY)];
			plateau[getNum(aX,aY)]=plateau[getNum(nX,nY)];
			plateau[getNum(nX,nY)]=temp;
			repaint();
			aX=-20;
			aY=-20;
		}
		if(winner()){
			JOptionPane.showMessageDialog(null, "GG !");
			//creerPuzzle();
		}
	}
	public int getNum(int i, int j){
		if(i>0 && i<w && j>0 && j<h){
			return 0;
		}
		if(i>w && i<w*2 && j>0 && j<h){
			return 1;
		}
		if(i>w*2 && i<w*3 && j>0 && j<h){
			return 2;
		}
		if(i>0 && i<w && j>h && j<h*2){
			return 3;
		}
		if(i>w && i<w*2 && j>h && j<h*2){
			return 4;
		}
		if(i>w*2 && i<w*3 && j>h && j<h*2){
			return 5;
		}
		if(i>0 && i<w && j>h*2 && j<h*3){
			return 6;
		}
		if(i>w && i<w*2 && j>h*2 && j<h*3){
			return 7;
		}
		return 8;
	}
}
