public interface MoveObserver {
	
	/**
	 * 
	 * @param player
	 * @param position
	 * @return Move was valid (successful).
	 */
	boolean move(Player player, int[] position);
	
}
