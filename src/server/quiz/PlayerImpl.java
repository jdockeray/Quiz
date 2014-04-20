package server.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PlayerImpl implements Player, Serializable{
	
	
	private static final long serialVersionUID = 1L;

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
			return playQuestion(q);
		}
		if(answers[user_input]==q.getAnswer()){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public int playQuiz(List<Question> Quiz) {
		int result=0;
		for(Question q:Quiz){
			result += playQuestion(q);
		}
		return result;
	}
	
	public static void main(String[] args){
		Question q=new QuestionImpl("hey this is question", "yes", "no");
		Question q2=new QuestionImpl("hey this is question2", "yes", "no");
		List<Question> quiz = new ArrayList<Question>();
		quiz.add(q);
		quiz.add(q2);

		Player p=new PlayerImpl();
		int score=p.playQuiz(quiz);
		System.out.println("your score is "+score);
	}
}
