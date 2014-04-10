package quiz;


public class QuestionImpl implements Question{
	
	String Question;
	boolean answer;
	

	public QuestionImpl(String Question, boolean answer) {
		this.Question=Question;
		this.answer=answer;
	}

}
