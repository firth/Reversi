
public class PlayReversi {
	public static String play(Player p1, Player p2) {
		Board b = new Board() ;
		
		while(!b.win()){
			b.move(p1.move(b), p1.me);
			b.move(p2.move(b), p2.me);
		}
		
		Board.Piece w = b.winner();
		
		if(w == Board.Piece.WHITE) {
			return "WHITE WON!" ;
		}
		if(w == Board.Piece.BLACK) {
			return "BLACK WON!" ;
		} else {
			return "TIE!" ;
		}
	}
}
