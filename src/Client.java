public interface Client extends SetUpClient, PlayerClient {
	/**
	 * @param playGameTask
	 * Starts a new quiz game with the Server
	 */
	void playGame(Runnable playGameTask);
	
	
}
