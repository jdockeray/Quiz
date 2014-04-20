package tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.engine.ComputeEngine;
import server.quiz.Question;
import server.quiz.QuestionImpl;
import compute.Compute;

public class ComputeEngineTest {
	
	Compute c;
	@Before
	public void setUp() throws Exception {
		c = new ComputeEngine();
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void addMultiChoiceQuestionTestAddedToQuizArray() {
		
		String[] fakeAnswers={"one", "two", "three"};
		String answer="answer";
		String question="this is the question?";
		Map<Integer, List<Question>> quizArray = null;

		int id=123;
		try {
			c.addMultiChoiceQuestion(id, question, answer, fakeAnswers);
			quizArray = c.getQuizArray();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		List<Question> aQuiz=quizArray.get(id);
		Iterator<Question> it=aQuiz.iterator();
		Question returnedQuestion=it.next();
		assertTrue(returnedQuestion.getQuestion().equals(question));
		assertTrue(returnedQuestion.getMultipleChoiceAnswers().equals(fakeAnswers));
		assertTrue(returnedQuestion.getAnswer().equals(answer));	
	}
	
	public void addTwoMultiChoiceQuestionTestAddedToQuizArray() {
		
		String[] fakeAnswers={"one", "two", "three"};
		String answer="answer";
		String question="this is the question?";
		Map<Integer, List<Question>> quizArray = null;

		Integer id=null;
		try {
			id=c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);
			c.addMultiChoiceQuestion(id, question, answer, fakeAnswers);
			quizArray = c.getQuizArray();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		List<Question> aQuiz=quizArray.get(id);
		Iterator<Question> it=aQuiz.iterator();
		Question returnedQuestion=it.next();
		assertTrue(returnedQuestion.getQuestion().equals(question));
		assertTrue(returnedQuestion.getMultipleChoiceAnswers().equals(fakeAnswers));
		assertTrue(returnedQuestion.getAnswer().equals(answer));	
		returnedQuestion=it.next();
		assertTrue(returnedQuestion.getQuestion().equals(question));
		assertTrue(returnedQuestion.getMultipleChoiceAnswers().equals(fakeAnswers));
		assertTrue(returnedQuestion.getAnswer().equals(answer));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addMultipleChoiceQuestionTestNullMultipleChoice() throws RemoteException{
		String[] fakeAnswers={null, "two", "three"};
		String answer="";
		String question="this is the question?";
		c.addMultiChoiceQuestion(2, question, answer, fakeAnswers);

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addMultipleChoiceQuestionTestNullAnswer() throws RemoteException{
		String[] fakeAnswers={"one", "two", "three"};
		String answer=null;
		String question="this is the question?";
		c.addMultiChoiceQuestion(2, question, answer, fakeAnswers);

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addMultipleChoiceQuestionTestNullQuestion() throws RemoteException{
		String[] fakeAnswers={"one", "two", "three"};
		String answer="sjjs";
		String question=null;
		c.addMultiChoiceQuestion(2, question, answer, fakeAnswers);

	}
	
	@Test 
	public void addMultipleChoiceQuestionAddTwoQuestionAndCheckTheyAreAdded() throws RemoteException{
		String[] fakeAnswers={"one", "two", "three"};
		String answer="sjjs";
		String question="mdmd";
		c.addMultiChoiceQuestion(2, question, answer, fakeAnswers);
		
		String[] fakeAnswers2={"dede", "dede", "dededede"};
		String answer2="sdede";
		String question2="lldld";
		c.addMultiChoiceQuestion(2, question2, answer2, fakeAnswers2);
		
		Iterator<Question> q=c.getQuiz(2).iterator();
		assertEquals(answer, q.next().getAnswer());
		assertEquals(answer2, q.next().getAnswer());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addMultipleChoiceQuestionEmptyMultiChoice() throws RemoteException{
		String[] fakeAnswers={};
		String answer="sjjs";
		String question="mdmd";
		c.addMultiChoiceQuestion(2, question, answer, fakeAnswers);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void addFirstMultipleChoiceQuestionTestNullMultipleChoice() throws RemoteException{
		String[] fakeAnswers={null, "two", "three"};
		String answer="";
		String question="this is the question?";
		c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addFirstMultipleChoiceQuestionTestNullAnswer() throws RemoteException{
		String[] fakeAnswers={"one", "two", "three"};
		String answer=null;
		String question="this is the question?";
		c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addFirstMultipleChoiceQuestionTestNullQuestion() throws RemoteException{
		String[] fakeAnswers={"one", "two", "three"};
		String answer="sjjs";
		String question=null;
		c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);
	}
	

	
	@Test (expected = IllegalArgumentException.class)
	public void addFirstMultipleChoiceQuestionEmptyMultiChoice() throws RemoteException{
		String[] fakeAnswers={};
		String answer="sjjs";
		String question="mdmd";
		c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);
	}
	
	//Get ID
	@Test
	public void duplicateIdtest()   {
		try {
			int x=c.getId();
			int y=c.getId();
			assertFalse(x==y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get Quiz
	@Test
	public void getQuizTestInput() {
		String[] fakeAnswers={"one", "two", "three"};
		String answer="answer";
		String question="this is the question?";
		Integer id=null;
		List<Question> returnedQuiz =null;
		Iterator<Question> it=null;
		Question returnedQuestion=null;

		try {
			id=c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);
			returnedQuiz = c.getQuizArray().get(id);
			it=returnedQuiz.iterator();
			returnedQuestion=it.next();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		assertTrue(returnedQuestion.getMultipleChoiceAnswers().equals(fakeAnswers));

	}
	
	@Test
	public void getQuizTestReturnType() {
		String[] fakeAnswers={"one", "two", "three"};
		String answer="answer";
		String question="this is the question?";
		Integer id=null;
		List<Question> returnedQuiz =null;
		try {
			id=c.addFirstMultiChoiceQuestion(question, answer, fakeAnswers);
			returnedQuiz = c.getQuiz(id);
		} catch (RemoteException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		assertTrue(returnedQuiz instanceof List<?>);
		assertTrue(returnedQuiz != null);
	}
	
	@Test
	public void testQuizScoresBasic(){
		try {
			c.addScore(102, 20,"a");
			c.addScore(102, 21,"b");
			assertEquals(c.getWinner(102), "b");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetQuizIdBasic(){
		try {
			c.addQuizName("standard", 101);
			assertTrue(c.getQuizId("standard")==101);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
