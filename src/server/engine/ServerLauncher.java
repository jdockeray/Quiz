package server.engine;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import compute.Compute;




public class ServerLauncher {
	public static void main(String[] args){
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
	
		//Try to open the registry if that fails assume that it is already running
			try {
				LocateRegistry.createRegistry(1099);
			} catch (RemoteException e) {
				System.out.println("Start Registry failed as Registry is probably already running");
			}
			try {
			    Compute engine = new ComputeEngine();
				String registryHost = "//localhost/";
				String serviceName = "Compute";
				Naming.rebind(registryHost + serviceName, engine);
				System.out.println("Server Started");
				engine.builddb();
					System.out.println("Database constructed");
				
			}
			catch (MalformedURLException ex) {
				ex.printStackTrace();
			}
			catch (RemoteException ex) {
				ex.printStackTrace();
			}
		}
}