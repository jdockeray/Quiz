package server.quiz;

public class QuestionImpl implements Question{
	
	String question;
	String[] multipleChoices;
	String answer;
	

	
	public QuestionImpl(String question, String answer, String... multipleChoices){
		this.question=question;
		this.answer=answer;
		this.multipleChoices=multipleChoices;
	}

	//Getters and Setters
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}



	public String[] getMultipleChoiceAnswers() {
		return multipleChoices;
	}

	public void setMultipleChoiceAnswers(String[] multipleChoiceAnswers) {
		this.multipleChoices = multipleChoiceAnswers;
	}


}
