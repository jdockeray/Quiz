package server.quiz;

import java.io.Serializable;
import java.util.Arrays;

public class QuestionImpl implements Question, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2637834762125565887L;
	String question;
	String[] multipleChoices=null;
	String answer;
	
	public QuestionImpl(String question, String answer, String... multipleChoices){
		this.question=question;
		this.answer=answer;
		this.multipleChoices=multipleChoices;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public String[] getAllAnswers() {
		String[] allAnswers=Arrays.copyOf(multipleChoices, multipleChoices.length+1);
    	allAnswers[allAnswers.length-1]=answer;
    	QuizUtilities.shuffleArray(allAnswers);
		return allAnswers;
	}

	public void setMultipleChoiceAnswers(String[] multipleChoiceAnswers) {
		this.multipleChoices = multipleChoiceAnswers;
	}

	public String[] getMultipleChoiceAnswers() {
		return this.multipleChoices;
	}


}
