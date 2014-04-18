package client;


import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import compute.Compute;

import server.quiz.ClientSetUp;

public class Client {
	
    public static void main(String args[]) {
    	
    //	if (System.getSecurityManager() == null) {
      //      System.setSecurityManager(new SecurityManager());
        //}
    	
        try {   
        	
        	//SET UP
        	//connect to remote
            Compute comp = (Compute) Naming.lookup("//127.0.0.1:1099/Compute"); 
            //get the set up methods
            ClientSetUp quizBuilder=comp.setUpClientObject();
            //build the quiz
        	ArrayList<ArrayList<String>> myQuiz=quizBuilder.questionsBuilder();
        	//submit quiz
        	quizBuilder.submitQuiz(comp, myQuiz);
           
            
        } catch (Exception e) {
            System.err.println("Compute exception:");
            e.printStackTrace();
        }
    }  
}
