package server.quiz;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PlayerImpl implements Player{
	
	
	
	@Override
	public void playQuiz(List<Question> Quiz) {
		for(Question q:Quiz){
		
		}
	}

	public int playQuestion(Question q){	
	    Scanner in = new Scanner(System.in);  
	    int user_input = 0;
		String[] answers=q.getAllAnswers();
		System.out.println(q.getQuestion());
		for(int i=0;i<answers.length; i++){
			System.out.println("["+i+"]"+answers[i]);
		}
		try{
			user_input=in.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("I am sorry but I didnt catch the question was..");
		}
		catch(NoSuchElementException e){	
			System.out.println("I am sorry but I didnt catch the question was");
		}
		if(answers[user_input]==q.getAnswer()){
			return 1;
		}
		else{
			return 0;
		}
	}
	public static void main(String[] args){
		Question q=new QuestionImpl("hey this is question", "yes", "no");
		Player p=new PlayerImpl();
		p.playQuestion(q);
	}
}
