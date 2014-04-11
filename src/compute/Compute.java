package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.quiz.ClientSetUp;


public interface Compute extends Remote {
	/**
	 * @return retrieves a unique id for a new quiz
	 */
	int getId() throws RemoteException;
	
    /**
     * @param id
     * @param question
     * @param answer
     * @param fakeAnswers
     * @throws RemoteException 
     */
    void addMultiChoiceQuestion(int id, String question,  String answer, String... fakeAnswers) throws RemoteException;
    
    ClientSetUp setUpClientObject() throws RemoteException;
}

