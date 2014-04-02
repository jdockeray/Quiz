
public interface PlayerClient extends Runnable {
	/**
	 * An interface used for playing the quiz game. Needs to launch a new quiz game as well as save data at the end.
	 * 
	 *  
	 */
	
	/**
	 * @param quiz - the id for the quiz
	 */
	void run(int quiz);
	
	/**
	 * @param quiz id for quiz
	 */
	void playQuiz(int quiz);
}
