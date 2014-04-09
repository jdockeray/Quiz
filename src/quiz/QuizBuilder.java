package quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuizBuilder {


	
	List<String[]> questionsBuilder(){
		List<String[]> QList = new ArrayList<String[]>();
	    Scanner in = new Scanner(System.in);  
	
		boolean takinginput=true;
		while(takinginput){
	        try{
	        	System.out.println("please enter a question to be added to the server db");
	            String question=in.nextLine();
	        	int x = Integer.parseInt(in.nextLine());
	        	if(!(x==1||x==0)){
	        		throw new IllegalArgumentException();
	        	}
	        	String[] z = {question,  String.valueOf(x)};
	        	QList.add(z);
	        }
	        catch(IllegalArgumentException ex){
	        	System.out.println("Sorry invalid argument qustion not saved");
	        }
	        System.out.println("add another question");
	        System.out.println("[1] Add another question"); 
	        System.out.println("[2] Exit and create quiz"); 
	        boolean takinginput2=true;
	        while(takinginput2){
	        	int y= Integer.parseInt(in.nextLine());
	        	if(y==1){
	        		takinginput2=false;
	        	}
	        	else if(y==2){
	        		takinginput=false;
	        		takinginput2=false;
	        	}
	        	else{
	        		System.out.println("sorry didnt catch that, please try again");
	        	}
	        }
		}
		return QList;
	};

}
