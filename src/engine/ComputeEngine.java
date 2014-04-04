package engine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import compute.Compute;
import compute.Task;

public class ComputeEngine extends UnicastRemoteObject implements Compute {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<Integer, String[]> Questions = new HashMap<Integer, String[]>();
	
	public ComputeEngine() throws RemoteException {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }
    
    public String[] addQuestion (String str, String str2)throws RemoteException{
    	System.out.println("added to db");
    	String[] strArray = {str, str2};
    	return Questions.put(001, strArray);
    }


}