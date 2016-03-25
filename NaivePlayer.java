public class NaivePlayer extends Player{
	public NaivePlayer(Board.Piece p){
		super(p);
	}
	public RowCol move(Board b){
		/*Board.Piece[][] p = new Board.Piece[8][8];

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				p[i][j] = b.getPiece(i,j);
			}
		}*/


		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				RowCol rc = new RowCol(i,j);
				if(b.isLegal(rc,me)){
					return rc;
				}
			}
		}
		return null;
	}
}