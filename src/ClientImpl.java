import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientImpl implements SetUpClient {
	
	public ClientImpl(QuizServer s) {
		super();
	}
				
	public static void main(String[] args){
		try{
			Remote service = Naming.lookup("//127.0.0.1:1099/quiz");
			QuizServer quizService = (QuizServer) service;
			System .out.println(quizService.CreateQuiz());
			}
			catch(MalformedURLException ex){
				ex.printStackTrace();
			}
			catch(NotBoundException ex){
				ex.printStackTrace();
			}
			catch(RemoteException ex){
				ex.printStackTrace();
			}
	}
}
