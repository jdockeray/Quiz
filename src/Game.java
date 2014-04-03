import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Game implements QuizGame, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int id;
	
	private ArrayList<Question> QuestionsArray = new ArrayList<Question>();
	Iterator<Question> currentQuestion = QuestionsArray.iterator();

	
	public boolean AddNewQuestion(Question q){
		return QuestionsArray.add(q);
	}

	public Question getNextQuestion() {
		return currentQuestion.next();
	}

	public Integer getId() {
		return id;
	}

	

}
