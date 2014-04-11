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


public class ComputeEngine extends UnicastRemoteObject implements Compute {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<Integer, List<Question>> quizArray = new HashMap<Integer, List<Question>>();
	
	public ComputeEngine() throws RemoteException {
        super();
    }

    public void addMultiChoiceQuestion(int id, String question,  String answer, String... fakeAnswers)throws RemoteException{
    	String[] allAnswers=Arrays.copyOf(fakeAnswers, fakeAnswers.length+1);
    	allAnswers[allAnswers.length-1]=answer;
    	shuffleArray(allAnswers);
    	Question q=new QuestionImpl(question, answer, fakeAnswers);
    	List<Question> QuestionArray=quizArray.get(id);
		if(QuestionArray==null){
			QuestionArray=new ArrayList<Question>();
		}
		QuestionArray.add(q);
    }
    
    // Implementing Fisher–Yates shuffle
    public void shuffleArray(String[] ar)
    {
      Random rnd = new Random();
      for (int i = ar.length - 1; i > 0; i--)
      {
    	// Generates a random index, from a decreasing range of numbers
        int index = rnd.nextInt(i + 1);
        // Save the value
        String a = ar[index];
        // Swap it with position i
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
    
   public int getId(){
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
   
   public ClientSetUp setUpClientObject() throws RemoteException{
	   return new ClientSetUp();
   }
   
   
   
   
 


}