package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.quiz.Question;
import server.quiz.QuestionImpl;

public class QuestionTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getallAnswerstestlength() {
		String[] multipleChoices={"no"};
		Question q=new QuestionImpl("hey", "yes", multipleChoices);
		String[] allAnsw=q.getAllAnswers();
		assertTrue(allAnsw.length==2);
	}



}
