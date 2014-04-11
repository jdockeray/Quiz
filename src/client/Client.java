package client;


import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import quiz.ClientSetUp;
import compute.Compute;

public class Client {
	
    public static void main(String args[]) {
    	
    //	if (System.getSecurityManager() == null) {
      //      System.setSecurityManager(new SecurityManager());
        //}
    	
        try {              
            Compute comp = (Compute) Naming.lookup("//127.0.0.1:1099/Compute"); 
            ClientSetUp quizBuilder=comp.setUpClientObject();
        	ArrayList<ArrayList<String>> myQuiz=quizBuilder.questionsBuilder();
        	quizBuilder.submitQuiz(comp, myQuiz);
           
            
        } catch (Exception e) {
            System.err.println("Compute exception:");
            e.printStackTrace();
        }
    }  
}
