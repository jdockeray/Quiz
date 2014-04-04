package quiz;


import java.rmi.Naming;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import compute.Compute;

public class SetUpClient {
	


    public static void main(String args[]) {
    	QuizBuilder quizBuilder=new QuizBuilder();
    	List<String[]> myQuiz=quizBuilder.questionsBuilder();
    	
    	
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {              
            Compute comp = (Compute) Naming.lookup("//127.0.0.1:1099/Compute"); 
            for(String[] i: myQuiz){
            	comp.addQuestion(i[0],i[1]);
            }
        } catch (Exception e) {
            System.err.println("Compute exception:");
            e.printStackTrace();
        }
    }  
}
