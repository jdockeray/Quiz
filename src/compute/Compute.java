package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Compute extends Remote {
	/**
	 * @return retrieves a unique id for a new quiz
	 */
	int getId() throws RemoteException;
	
	/**
	 * Retrieves two s from the client which represent 
	 * a single quiz questions,
	 * 
	 * 
	 * @param str
	 * @param str2
	 * @param the quiz id
	 * @return Returns the array once it has been added
	 * @throws RemoteException
	 */
	String addQuestion (String str, String str2, int quizId) throws RemoteException;
	
}

