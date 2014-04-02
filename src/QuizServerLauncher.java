import java.rmi.Remote;


public interface QuizServerLauncher extends Remote{
	/**
	 * Starts the web server, initiates security policy, binds to the registry etc.
	 */
	
	/**
	 * Starts Quiz Web server
	 */
	void launch();
}
