import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class ServerLauncher {
	private void launch() {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			LocateRegistry.createRegistry(1099);
			QuizServer server = (QuizServer) new ServerImpl();
			String registryHost = "//localhost/";
			String serviceName = "quiz";
			Naming.rebind(registryHost + serviceName, server);
		}
		catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
		catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args){
		ServerLauncher app = new ServerLauncher();
		app.launch();
	}
}
