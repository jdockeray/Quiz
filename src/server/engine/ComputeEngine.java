package server.engine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import compute.Compute;
import server.quiz.ClientSetUp;
import server.quiz.Question;
import server.quiz.QuestionImpl;
import server.quiz.QuizUtilities;


public class ComputeEngine extends UnicastRemoteObject implements Compute {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<Integer, List<Question>> quizArray = new HashMap<Integer, List<Question>>();
	
	public ComputeEngine() throws RemoteException {
        super();
    }

    public void addMultiChoiceQuestion(int id, String question,  String answer, String... fakeAnswers)throws RemoteException, IllegalArgumentException{
    	
    	QuizUtilities.sanitizeString(fakeAnswers);
    	QuizUtilities.sanitizeString(question);
    	QuizUtilities.sanitizeString(answer);


    	Question q=new QuestionImpl(question, answer, fakeAnswers);
		if(quizArray.get(id)==null){
			quizArray.put(id, new ArrayList<Question>());
		}
		quizArray.get(id).add(q);
    }
    
    public int addFirstMultiChoiceQuestion(String question, String answer, String... fakeAnswers)
    throws RemoteException, IllegalArgumentException {
    	QuizUtilities.sanitizeString(fakeAnswers);
    	QuizUtilities.sanitizeString(question);
    	QuizUtilities.sanitizeString(answer);
    	Question q=new QuestionImpl(question, answer, fakeAnswers);
    	
    	int id=getId();
    	List<Question> aList=new ArrayList<Question>();
		quizArray.put(id, aList);
		quizArray.get(id).add(q);
		return id;   	
    }
    

   public int getId() throws RemoteException{
    	Random r = new Random();
		int x = r.nextInt(99999999);
    	try{
    		if(quizArray.containsKey(x)){
    			throw new IllegalArgumentException();
    		};
    	}
    	catch(IllegalArgumentException ex){
    		getId();
    	}
    	return x;
    }
   
   public Map<Integer, List<Question>> getQuizArray() throws RemoteException{
	   return quizArray;
   }


   public ClientSetUp setUpClientObject() throws RemoteException{
	   return new ClientSetUp();
   }

   public List<Question> getQuiz(int id) throws RemoteException{
		return quizArray.get(id);
		
	}


}