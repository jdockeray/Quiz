package server.quiz;

import java.util.Random;

public class QuizUtilities {
	public static String sanitizeString(String str)throws IllegalArgumentException{
		if(str==null){
			throw new IllegalArgumentException();
		}
		return str;	
	}
	
	public static String[] sanitizeString(String[] strArray)throws IllegalArgumentException{
		for(String s:strArray){
			if(s==null){
				throw new IllegalArgumentException();
			}	
		}
		return strArray;	
	}
	
    // Implementing Fisher–Yates shuffle
    public static void shuffleArray(String[] ar)
    {
      Random rnd = new Random();
      for (int i = ar.length - 1; i > 0; i--)
      {
    	// Generates a random index, from a decreasing range of numbers
        int index = rnd.nextInt(i + 1);
        // Save the value
        String a = ar[index];
        // Swap it with position i
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
    
}
