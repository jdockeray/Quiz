import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class ServerImpl extends UnicastRemoteObject implements QuizServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String CreateQuiz() throws RemoteException{
		// TODO Auto-generated method stub
		System.out.println("quiz created");
		return "quiz created";
	}
	
}
