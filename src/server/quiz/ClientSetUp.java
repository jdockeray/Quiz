package server.quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import compute.Compute;

public class ClientSetUp implements Serializable{

	private static final long serialVersionUID = 6043014352790477802L;

	public ArrayList<ArrayList<String>> questionsBuilder(){
			//each node in List will contain a String List, in the Sting Array it goes 0 - Question, 1 - Answer, 2... - Other Answers
			ArrayList<ArrayList<String>> QList = new ArrayList<ArrayList<String>>();
		    String str = null;
		    boolean takingInput=true;
		    while(takingInput){
		    	
		        System.out.println("Please enter a question to be added to the server db");
		        str=System.console().readLine();
		        QList.add(new ArrayList<String>());
		        QList.get(QList.size()-1).add(str);
		        
		        System.out.println("Please enter an answer to be added to the server db");
		        str=System.console().readLine();
		        QList.get(QList.size()-1).add(str);
		       
		        boolean takinganswers=true;
		        do{
		        	System.out.println("Please add a wrong answer");
			        str=System.console().readLine();
				    QList.get(QList.size()-1).add(str);
		        	System.out.println("Would you like to add another optional answer");
		        	System.out.println("[1] Add another fake answer");
		        	System.out.println("[2] Add another Question");
		        	System.out.println("[3] Finish and Create Quiz");
		        	int z=100;
		        	try{
		        		z=Integer.parseInt(System.console().readLine());
		        	}
		        	catch(Exception ex){
		        	}
		        	switch(z){
		        		case 1:
		        			break;
		        		case 2:
		        			takinganswers=false;
		        			break;
		        		case 3:
		        			takinganswers=false;
		        			takingInput=false;
		        			break;
		        		default:
		        			System.out.println("Oops didnt catch that");
		        	}  	
		        }while(takinganswers);
		}
		return QList;
	}
	
	public int submitQuiz(Compute remoteQuizServer, ArrayList<ArrayList<String>> quiz) throws RemoteException{
        Integer id=null;
        
		for(ArrayList<String> q: quiz){
        	Iterator<String> it=q.iterator();
        	Object[] temp=q.subList(2, q.size()).toArray();
        	String[] falseQuestions=new String[temp.length];
	        	for(int i=0;i<temp.length;i++){
	        		falseQuestions[i]=(String) temp[i];
	        	}
	        if(id==null){
	        	id=remoteQuizServer.addFirstMultiChoiceQuestion(it.next(), it.next(), falseQuestions);
	        }
	        else{
	        	remoteQuizServer.addMultiChoiceQuestion(id, it.next(), it.next(), falseQuestions);
	        }
		}
		return id;
	}

}
