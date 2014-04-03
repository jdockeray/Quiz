


public class Builder implements QuizBuilder {
	private static final long serialVersionUID = 1L;

	QuizGame game;
	
	public Builder(){
		game = new Game();
	};
	

	
	public Question booleanQuestionBuilder() throws IllegalArgumentException{
		System.out.println("Please insert a valid true false question");
		String str = System.console().readLine();
		System.out.println("and either a 1 or 0 to indicate valid output");
		int i = Integer.parseInt(System.console().readLine());
		if(!(i==0||i==1)){
			throw new IllegalArgumentException();
		}
		return new Question(str, i);
	};
	

	public QuizGame buildGame() {
		boolean flag = true;
		while(flag){
			try {
				game.AddNewQuestion(booleanQuestionBuilder());
			}
			catch(IllegalArgumentException ex){
				System.out.println("Illegal argument \nQuestion not added");
				
			}
			System.out.println("add another question, press 1 to indicate yes");
			if(!System.console().readLine().equals("1")){
				flag=false;
			}
		}
		return game;
	}

	
	
}
