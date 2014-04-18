package server.quiz;

public interface Question {

	/**
	 * Gets the Question
	 * 
	 * @return theQuestion
	 */
	String getQuestion();
	
	/**
	 * Sets the Question
	 * 
	 * @param question
	 */
	void setQuestion(String question);

	/**
	 * Returns multiple choice answer array, which the includes the multi choices
	 * and the answer 
	 * 
	 * @return
	 */
	String[] getAllAnswers();
	
	/**
	 * Returns  the multiple choices which does not
	 * include the answer
	 * @return
	 */
	String[] getMultipleChoiceAnswers();
	
	/**
	 * Sets the Question
	 * @param multipleChoiceAnswers
	 */
	void setMultipleChoiceAnswers(String[] multipleChoiceAnswers);

	/**
	 * Gets the answer
	 * @return
	 */
	String getAnswer();

}
