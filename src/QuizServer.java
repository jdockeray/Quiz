
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizServer extends Remote{
	/**
	 * @return Creates a new quiz
	 */
	String CreateQuiz() throws RemoteException;
}
