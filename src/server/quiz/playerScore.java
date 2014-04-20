package server.quiz;

public class playerScore {
	Integer Score;
	String Name;
	public playerScore(Integer score, String name) {
		Score = score;
		Name = name;
	}
	public Integer getScore() {
		return Score;
	}
	public void setScore(Integer score) {
		Score = score;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
