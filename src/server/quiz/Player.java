package server.quiz;
import java.io.Serializable;
import java.util.List;

public interface Player extends Serializable{
	/**
	 * @param Quiz
	 * @return
	 */
	int playQuiz(List<Question> Quiz);
}

