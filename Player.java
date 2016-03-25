
public abstract class Player {
	public final Board.Piece me ;
	public Player(Board.Piece p) {
		
		if(p == Board.Piece.BLANK)
			throw new IllegalArgumentException("You cannot play as the blank pieces") ;
		
		
		me = p ;
	}
	
	abstract public RowCol move(Board b) ;
}
