package game.tictactoe;

public class Spiellogik {

	public enum Player { 
		X((byte) 1), O((byte) 2);
		public byte id;
		Player(byte id) { this.id = id; }
	}

	private static final byte[] magicSquare = { 2,7,6, 9,5,1, 4,3,8 };

	/**
	 * The tic tac toe board. Each field contains the id of the {@link Player}
	 * who has ticked it. An untouched field is set to 0.
	 */
	private final byte[] board = new byte[9];
	/**
	 * A marker, that tells who's the next player. True, if player O
	 * is the next, false if player X is next.
	 */
	private boolean playerOIsNext = false;

	/**
	 * Resets the game.
	 */
	public void reset() {
		for (int i = 0; i < board.length; i++) {
			board[i] = 0;
		}
		playerOIsNext = false;
	}
	
	/**
	 * Ticks the given cell with the current player. 
	 *
	 * @param row the index of the row (first row is 0)
	 * @param col the index of the column (first column is 0)
	 * @throws IllegalStateException if the given cell is already ticked
	 * @see #tick(int)
	 */
	public void tick(int row, int col) {
		tick(3 * row + col);
	}

	/**
	 * Ticks the given cell with the current player.
	 * 
	 * @param index the index of the cell (first cell is 0)
	 * @throws IllegalStateException if the given cell is already ticked
	 */
	public void tick(int index) {
		if (board[index] != 0) {
			throw new IllegalStateException("The given cell with index " + index + " is already ticked");
		}
		board[index] = playerOIsNext ? Player.O.id : Player.X.id;
		playerOIsNext = !playerOIsNext;
	}

	/**
	 * Tells, if all fields are ticked.
	 */
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0) return false;
		}
		return true;
	}

	/**
	 * Tells, if a player won the game.
	 * @returns true if a player won the game, otherwise false.
	 */
	public boolean isSolved() {
		return getWinner() != null;
	}

	/**
	 * Returns the current player.
	 */
	public Player getCurrentPlayer() {
		return playerOIsNext ? Player.O : Player.X;
	}

	/**
	 * Calculates the winner for the current state. If there is no winner
	 * this method returns null.
	 */
	public Player getWinner() {
		if (isWinner(Player.X)) { return Player.X; }
		if (isWinner(Player.O)) { return Player.O; }
		return null;
	}

	/**
	 * A helper method for {@link #getWinner()} that checks, if a specific 
	 * {@link Player} won the game.
	 */
	private boolean isWinner(Player p) {
		for(int i = 0; i < board.length-2; i++) {
			for (int j = i+1; j < board.length-1; j++) {
				for (int k = j+1; k < board.length; k++) {
					if (p.id == board[i] 
					    && board[i] == board[j]
					    && board[j] == board[k]
					    && magicSquare[i] + magicSquare[j] + magicSquare[k] == 15) {
						return true;
					}	
				}
			}
		}
		return false;
	}

	public Player[] getBoard() {
                Player[] pboard = new Player[board.length];
                for (int i = 0; i < board.length; i++) {
                        byte id = board[i];
                        for (Player p : Player.values()) {
                                if (p.id == id) {
                                        pboard[i] = p;
                                }
                        }
                }
                return pboard;
        }

}
