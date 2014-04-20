package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import server.quiz.ClientSetUp;
import server.quiz.Player;
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
    
    /**
     * @return returns the player objects
     * @throws RemoteException; 
     */
    Player getPlayerObject() throws RemoteException;
    
    /**
     * builds the data base
     * @return true if db is built
     * @throws RemoteException 
     */
    void builddb() throws RemoteException;
    
    /**
     * Saves the data base
     * @throws RemoteException 
     */
    void flush() throws RemoteException;
    
    
    boolean addQuizName(String s, Integer i)throws RemoteException;
    
    public void addScore(int quizId, int score, String playerName)throws RemoteException;
    public String getWinner(int quizId)throws RemoteException;
    public Map<String, Integer> returnQuizNames() throws RemoteException;
    public boolean addPlayerName(String name)throws RemoteException;
    public Integer getQuizId(String name)throws RemoteException;
}

