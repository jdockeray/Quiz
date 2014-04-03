package quiz;
import java.io.Serializable;
import quiz.Database;
import compute.Task;


public class AddQuestion implements Task<String>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return addQuestiontoDb("whshs");
	}

	private String addQuestiontoDb(String Question) {
		// TODO Auto-generated method stub
		Database.QuizArray.add(Question);
		return "question added";
	}

}
