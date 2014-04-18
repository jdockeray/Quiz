package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import server.quiz.ClientSetUp;
import server.quiz.Question;


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
     * @throws RemoteException, 
     * @throws  Illegal Argument Exception if any of the arguments is null
     */
    void addMultiChoiceQuestion(int id, String question,  String answer, String... fakeAnswers) throws RemoteException, IllegalArgumentException;
    
    
    /**
     * Use this when adding first Question to db, it add the first question as well as creates an id for this quiz
     * 
     * @param question
     * @param answer
     * @param fakeAnswers
     * @return The id for the quiz
     * @throws RemoteException
     * @throws IllegalArgumentException
     */
    int addFirstMultiChoiceQuestion(String question,  String answer, String... fakeAnswers) throws RemoteException, IllegalArgumentException;

    /**
     * Returns the a SetUp Object that contains methods for creating new quizes
     * 
     * @return a ClientSetUp Object
     * @throws RemoteException
     */
    ClientSetUp setUpClientObject() throws RemoteException;
    
    
    /**
     * @return The Map of all Quizes
     * @throws RemoteException
     */
    public Map<Integer, List<Question>> getQuizArray() throws RemoteException;
    
    /**
     * Returns the Quiz for the set id
     * 
     * @param id
     * @return
     * @throws RemoteException
     */
    public List<Question> getQuiz(int id) throws RemoteException;
}

