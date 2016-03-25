import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Reversi{
	public static void main(String[] args){
		JFrame f = new JFrame();
		DrawPanel p = new DrawPanel();
		f.add(p);

		f.setSize(1200,1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//f.setUndecorated(true);
		f.setVisible(true);

		while(true){
			p.update();
			try{
			Thread.sleep(1000/60);
			}catch(Exception e){}
		}
	}
}

class DrawPanel extends JComponent{
	Board b = new Board();
	Player p1 = new SupremePlayer(Board.Piece.WHITE);
	Player p2 = new SupremePlayer(Board.Piece.BLACK);

	boolean blackTurn = true;
	int c = 0;
	boolean first = true;

	public void update(){

		if(c % 20 == 0){
			if(blackTurn){
				RowCol rc = p2.move(b);
				if(rc != null)
					b.move(rc, Board.Piece.BLACK);
				else
					System.out.println("BLACK ran out of moves");
			}
			else{
				RowCol rc = p1.move(b);
				if(rc != null)
					b.move(rc, Board.Piece.WHITE);
				else
					System.out.println("WHITE ran out of moves");
			}
			blackTurn = !blackTurn;
		}
		c++;

		repaint();
	}

	public void paintComponent(Graphics g){
		b.draw(g);

		g.setColor(Color.RED);
		g.setFont(new Font("Jokerman", Font.PLAIN, 100));
		g.drawString(b.getWhiteCount()+"", 100, 900);

		g.setFont(new Font("Jokerman", Font.PLAIN, 50));
		g.drawString(p1.getClass().getSimpleName()+"", 100, 1000);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Jokerman", Font.PLAIN, 100));
		g.drawString(b.getBlackCount()+"", 600, 900);

		g.setFont(new Font("Jokerman", Font.PLAIN, 50));
		g.drawString(p2.getClass().getSimpleName()+"", 600, 1000);
	}


	public DrawPanel(){
		super();


	}

}