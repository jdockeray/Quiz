package server.quiz;

import java.util.Arrays;

public class QuestionImpl implements Question{
	
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
		String[] allAnswers=Arrays.copyOf(multipleChoices, multipleChoices.length);
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
