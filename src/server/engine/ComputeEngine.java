package server.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import compute.Compute;
import server.quiz.ClientSetUp;
import server.quiz.Player;
import server.quiz.PlayerImpl;
import server.quiz.Question;
import server.quiz.QuestionImpl;
import server.quiz.QuizUtilities;
import server.quiz.playerScore;
import server.quiz.playerScoreComparator;


public class ComputeEngine extends UnicastRemoteObject implements Compute {

	private static final long serialVersionUID = 1L;
	public Map<Integer, List<Question>> quizArray = new HashMap<Integer, List<Question>>();
	public Map<String, Integer> quizNames = new HashMap<String, Integer>();
	public Map<Integer, List<playerScore>> quizScores = new HashMap<Integer, List<playerScore>>();
	public List<String> playerNames = new ArrayList<String>();
	
	
	synchronized public boolean addPlayerName(String name)throws RemoteException{
		if(!playerNames.contains(name)){
		playerNames.add(name);
		return true;
		}
		else{
			return false;
		}
	}
	
	public Integer getQuizId(String name)throws RemoteException{
		return quizNames.get(name);
	};
	
	synchronized public void addScore(int quizId, int score, String playerName)throws RemoteException{
		playerScore ps=new playerScore(score, playerName);
		if(quizScores.containsKey(quizId)){
			quizScores.get(quizId).add(ps);
		}
		else{
			List<playerScore> temp = new ArrayList<playerScore>();
			temp.add(ps);
			quizScores.put(quizId, temp);
		}
	}
	public String getWinner(int quizId)throws RemoteException{
		String winner=Collections.max(quizScores.get(quizId), new playerScoreComparator()).getName();
		return winner;
	}
	synchronized public boolean addQuizName(String s, Integer i)throws RemoteException{
		if(quizNames.containsKey(s)){
			return false;
		}
			quizNames.put(s, i);
			return true;
	}
	
	public Map<String, Integer> returnQuizNames() throws RemoteException{
		return quizNames;
	}
	
	public ComputeEngine() throws RemoteException {
        super();
    }

	synchronized public void addMultiChoiceQuestion(int id, String question,  String answer, String... fakeAnswers)throws RemoteException, IllegalArgumentException{
    	
    	QuizUtilities.sanitizeString(fakeAnswers);
    	QuizUtilities.sanitizeString(question);
    	QuizUtilities.sanitizeString(answer);


    	Question q=new QuestionImpl(question, answer, fakeAnswers);
		if(quizArray.get(id)==null){
			quizArray.put(id, new ArrayList<Question>());
		}
		quizArray.get(id).add(q);
    }
    
	synchronized public int addFirstMultiChoiceQuestion(String question, String answer, String... fakeAnswers)
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
   
   public Player getPlayerObject() throws RemoteException{
	   return new PlayerImpl();
   }

   public List<Question> getQuiz(int id) throws RemoteException{
		return quizArray.get(id);	
   }
   
   synchronized public void builddb() throws RemoteException{
	   String csvFile = "quizes.txt";
	   BufferedReader br = null;
	   String line = "";
	   String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// 0 Quiz Id
				// 1 Question
				// 2 Answer 
				// 3 FakeAnswers...
				
				String[] tempArray = line.split(cvsSplitBy);
				if(tempArray.length<3){
					//do nothing as has been corrupted
				}
				else{
					int id=Integer.parseInt(tempArray[0]);
					String question=tempArray[1];
					String answer=tempArray[2];
					String[] fakeAnswers=Arrays.copyOfRange(tempArray, 3, tempArray.length);
					addMultiChoiceQuestion(id, question, answer, fakeAnswers);
				}
			}	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		csvFile = "names.txt";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// 0 Quiz Id
				// 1 Question
				// 2 Answer 
				// 3 FakeAnswers...
				String[] tempArray = line.split(cvsSplitBy);
				String question=tempArray[0];
				int id=Integer.parseInt(tempArray[1]);
				addQuizName(question, id);
			}	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
   }
   
	/**
	* Save all data to disk.
	*
	* This method must be executed when the program is closed and when/if the
	* user requests it.
	*/
   synchronized public void flush() throws RemoteException{
	   String eol = System.getProperty("line.separator"); 
		try {
			FileWriter writer = new FileWriter("quizes.txt");
			// 0 Quiz Id
			// 1 Question
			// 2 Answer 
			// 3 FakeAnswers...
			for (Map.Entry<Integer, List<Question>> entry : quizArray.entrySet()) {
				for(Question q: entry.getValue()){
					writer.append(entry.getKey()+"");
					writer.append(',');
					writer.append(q.getQuestion());
					writer.append(',');
					writer.append(q.getAnswer());
					
						for(String s:q.getMultipleChoiceAnswers()){
							writer.append(',');
							writer.append(s);
						}
					writer.append(eol);
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileWriter writer = new FileWriter("names.txt");
			for (Map.Entry<String, Integer> entry : quizNames.entrySet()) {
					writer.append(entry.getKey());
					writer.append(',');
					writer.append(""+entry.getValue());
					writer.append(eol);
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }

}