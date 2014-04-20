package client;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import compute.Compute;
import server.quiz.ClientSetUp;
import server.quiz.Player;
import server.quiz.Question;

public class Client {
	
    public static void main(String args[]) {
    	
    if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
     }
    	
    	Compute comp;
        try {   
        	//SET UP
        	//connect to remote
            comp = (Compute) Naming.lookup("//127.0.0.1:1099/Compute"); 
            String name= getPlayerName(comp);
            menu(comp, name);
        } catch (Exception e) {
            System.err.println("Compute exception:");
            e.printStackTrace();
        }
    }  
    
    private static String getPlayerName(Compute comp) {
    	String s="has not worked";
		boolean takingInput=true;
		while(takingInput){
			System.out.println("please enter a name");
			s=System.console().readLine();
		
			try {
				if(comp.addPlayerName(s)){
					takingInput=false;
				}
				else{
					System.out.println("sorry that name is taken");
				}
			return s;
		} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
	}
		return s;
    }
    
    
    public static void ResetQuizScoreAndGetWinner(Compute comp){
		try {
		  	System.out.println("type the name of the quiz you want to find out the winner for");
	    	String str=System.console().readLine();
	    	Integer id = comp.getQuizId(str);
    	if(id==null){
        	System.out.println("that is not a valid quiz");
    	}
    	else{
	    	System.out.println("the winner is "+comp.getWinner(id));
    	}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void savdDb(Compute comp){
    	try {
			comp.flush();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	System.out.println("changes saved");
    }
    
    public static void playQuiz(Compute comp, int id, String playerName){
    	Player p;
		try {
			p = comp.getPlayerObject();
			int score=p.playQuiz(comp.getQuiz(id));
	    	System.out.println("your score is "+score+" out of a possible "+comp.getQuiz(id).size());
	    	comp.addScore(id, score, playerName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void selectandPlayQuiz(Compute comp, String playerName){
    	try {
			Map<String, Integer> quizNames=comp.returnQuizNames();
			if(quizNames.size()>=1){
				Object[] namesArray=quizNames.keySet().toArray();
				System.out.println("The available quizes are ");
				for(Object s:namesArray){
					System.out.println((String)s);
				}
				System.out.println("Now type the name of the Quiz you want to play");
				String str=System.console().readLine();
				if(quizNames.containsKey(str)){
					playQuiz(comp,quizNames.get(str), playerName);
				}
				else{
					System.out.println("You enter an incorrect input");
				}
			}
			else {
				System.out.println("there are no names to add");
			}
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void quizBuilder(Compute comp, String playerName){
        //get the set up methods
        ClientSetUp quizBuilder;
		try {
			quizBuilder = comp.setUpClientObject();
	        System.out.println("please enter a name for your quiz");
	        String name=System.console().readLine();
	        //build the quiz
	    	ArrayList<ArrayList<String>> myQuiz=quizBuilder.questionsBuilder();
	    	//submit quiz
	    	int id=quizBuilder.submitQuiz(comp, myQuiz);
	    	boolean takingInput=true;
	    	while(takingInput){
	    		if(comp.addQuizName(name, id)){
	    			takingInput=false;
	    		}
	    		else{
	    			System.out.println("That name is taken try another one");
	    			name=System.console().readLine();
	    		}		
	    	}
	      	//Player
	    	Player p=comp.getPlayerObject();
	    	savdDb(comp);
	    	System.out.println("would you like to play the quiz now? ");
	    	System.out.println("[1] Yes Please");
	    	System.out.println("[2] No Thanks");
		    Scanner in = new Scanner(System.in);  
	        String str;
	        str=in.next();
	        if(Integer.parseInt(str)==1){
	        	playQuiz(comp, id,playerName);
	        }
	       
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void menu(Compute comp, String playerName){
	    while(true){
	    	System.out.println("what would you like to do?");
	    	System.out.println("[0] build a new quiz");
	    	System.out.println("[1] select from a list of quiz names");
	    	System.out.println("[2] reset quiz scores and get winner");
	    	int i=100;
	    	try{
	    		i=Integer.parseInt(System.console().readLine());
	    	}
	    	catch(Exception ex){
	    		System.out.println("please enter a number!");
	    	}
	    		switch (i) {
				case 0:
					quizBuilder(comp, playerName);
					break;
				case 1:
					selectandPlayQuiz(comp, playerName);
					break;
				case 2:
					ResetQuizScoreAndGetWinner(comp);
				default:
					break;
	    	}
	    }	
    }
}
