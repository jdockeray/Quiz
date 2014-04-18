package server.quiz;
import java.util.List;

public interface Player {
	void playQuiz(List<Question> Quiz);
	
	/**
	 * Takes a question and user input at run time, returning 1 or 0 depending
	 * on whether the answer is correct.
	 * @param q
	 * @return
	 */
	int playQuestion(Question q);
}

