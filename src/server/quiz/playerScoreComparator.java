package server.quiz;

import java.util.Comparator;

public class playerScoreComparator implements Comparator<playerScore> {
	@Override
		public int compare(playerScore x, playerScore y) {
		// TODO: Handle null x or y values
		if(x.getScore()>y.getScore()){
		return 1;
		}
		else if(x.getScore()<y.getScore()){
		return -1;
		}
		else{
		return 0;
		}
	}
}
