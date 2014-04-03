import java.io.Serializable;


public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String Question;
	boolean answer;

	public Question(String Q, boolean a) {
		Question = Q;
		answer = a;
	}
	public Question(String Q, int x) {
		answer=(x == 1);
		Question=Q;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public boolean isAnswer() {
		return answer;
	}
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
