package engine;

public class BooleanQuestion implements Question{
	
	String Question;
	boolean answer;
	

	public BooleanQuestion(String Question, boolean answer) {
		this.Question=Question;
		this.answer=answer;
	}

}
