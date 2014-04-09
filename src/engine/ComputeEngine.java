package engine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import compute.Compute;


public class ComputeEngine extends UnicastRemoteObject implements Compute {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<Integer, List<Question>> Questions = new HashMap<Integer, List<Question>>();
	
	public ComputeEngine() throws RemoteException {
        super();
    }

    
    public String addQuestion (String question, String answer, int id)throws RemoteException{
    		Question theQuestion = new BooleanQuestion(question, Integer.parseInt(answer)==1?true:false);
    		List<Question> QuestionArray=Questions.get(id);
    		if(QuestionArray==null){
    			QuestionArray=new ArrayList<Question>();
    		}
    		QuestionArray.add(theQuestion);
    		return "";
    }
    
    public int getId(){
    	Random r = new Random();
		int x = r.nextInt(99999999);
    	try{
    		if(Questions.containsKey(x)){
    			throw new IllegalArgumentException();
    		};
    	}
    	catch(IllegalArgumentException ex){
    		getId();
    	}
    	return x;
    }


}