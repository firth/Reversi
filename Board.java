import java.awt.*;


public class Board {
	public enum Piece {BLANK, BLACK, WHITE} ;

	private boolean win = false;
	private Piece winner ;
	//private boolean pass = false ;

	public boolean win() {
		return win ;
	}

	public Piece winner() {
		return winner ;
	}

	private Piece[][] vals = new Piece[8][8] ;

	private int blackCount = 2 ;
	private int whiteCount = 2 ;

	private void init(){
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				vals[i][j] = Piece.BLANK ;
	}

	public int getBlackCount() {
		return blackCount ;
	}


	public int getWhiteCount() {
		return whiteCount ;
	}

	public Board clone() {
		Board b = new Board() ;

		//b.vals = vals.clone() ;

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				b.vals[i][j] = vals[i][j];
			}
		}

		b.win = win ;
		b.winner = winner ;
		//b.pass = false ;
		b.blackCount = blackCount ;
		b.whiteCount = whiteCount ;

		return b;
	}

	public Board() {
		init() ;
		vals[4][4] = Piece.WHITE ;
		vals[4][5] = Piece.BLACK ;
		vals[5][4] = Piece.BLACK ;
		vals[5][5] = Piece.WHITE ;
	}

	public Piece value(RowCol rc){
		return vals[rc.r][rc.c] ;
	}

	private boolean hasCapture(RowCol rc, Piece p, int rdir, int cdir) {

		int x = 10, y = 10 ;

		if(rdir == 1)
			x = 7 - rc.r ;
		if(rdir == 0)
			x = 10 ;
		if(rdir == -1)
			x = rc.r ;
		if(cdir == 1)
			y = 7 - rc.c ;
		if(cdir == 0)
			y = 10 ;
		if(cdir == -1)
			y = rc.c ;


		for(int n = 1; n <= Math.min(x, y); n++) {
			Piece current = vals[rc.r + rdir*n][rc.c+cdir*n] ;

			if(current == Piece.BLANK) {
				return false ;
			}
			if(current == p && n == 1){
				return false ;
			}
			if(current == p) {
				return true;
			}
		}

		return false ;
	}

	public boolean isLegal(RowCol rc, Piece p) {

		if(vals[rc.r][rc.c] != Piece.BLANK)
			return false ;

		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(hasCapture(rc, p, i, j)){
					return true;
				}
			}
		}

		return false ;
	}



	private void calcWinner() {
		win = true ;
		if(blackCount > whiteCount) {
			winner = Piece.BLACK;
		}else if(whiteCount > blackCount) {
			winner = Piece.WHITE ;
		} else {
			winner = Piece.BLANK ;
		}
	}

	public void move(RowCol rc, Piece p) {

		/*if(win) {
			return ;
		}*/

		/*if(!hasMove(p)) {
			System.out.println(p.toString() + " has no moves!");

			if(pass){
				calcWinner() ;
			}
			pass = true ;
			return ;
		}*/

		//pass = false ;

		if(isLegal(rc, p)) {

			vals[rc.r][rc.c] = p ;
			if(p == Piece.BLACK)
				blackCount++ ;
			else {
				whiteCount++ ;
			}

			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(hasCapture(rc, p, i, j))
						flip(rc, i, j, p) ;
				}
			}

			if(blackCount <= 0 || whiteCount <= 0) {
				calcWinner() ;
			}

		} else {
			if(p == Piece.BLACK) {
				System.out.print("Black");
			} else {
				System.out.print("White");
			}
			System.out.println(" made an illegal move!");

		}
	}

	private void flip(RowCol rc, int left, int up, Piece p) {

		int x = 10, y = 10 ;

		if(left == 1)
			x = 7 - rc.r ;
		if(left == 0)
			x = 10 ;
		if(left == -1)
			x = rc.r ;

		if(up == 1)
			y = 7 - rc.c ;
		if(up == 0)
			y = 10 ;
		if(up == -1)
			y = rc.c ;


		for(int n = 1; n <= Math.min(x, y); n++) {
			if(vals[rc.r + left*n][rc.c + up*n] != p) {
				vals[rc.r + left*n][rc.c + up*n] = p ;

				if(p == Piece.BLACK) {
					blackCount++ ;
					if(n != 0)
						whiteCount-- ;
				} else {
					whiteCount++ ;
					if(n != 0)
						blackCount-- ;
				}
			}
			else{
				break ;
			}
		}

	}

	public Piece getPiece(RowCol rc) {
		return vals[rc.r][rc.c] ;
	}


	public void draw(Graphics g) {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++) {
				if(vals[i][j] == Piece.WHITE) {
					g.setColor(Color.RED);
					g.fillOval(100*i, 100*j, 100, 100) ;
				}
				if(vals[i][j] == Piece.BLACK) {
					g.setColor(Color.BLACK);
					g.fillOval(100*i, 100*j, 100, 100) ;
				}

			}
		}
	}

	public String toString() {

		String s = "" ;
		for(int i = 0; i< 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(value(new RowCol(i, j)) == Board.Piece.BLANK) {
					s += " " ;
				}
				if(value(new RowCol(i, j)) == Board.Piece.WHITE) {
					s += "W";
				}
				if(value(new RowCol(i, j)) == Board.Piece.BLACK) {
					s += "B";
				}
			}
				s += "\n" ;
		}

		s += "/////////" ;

		return s;
	}
}