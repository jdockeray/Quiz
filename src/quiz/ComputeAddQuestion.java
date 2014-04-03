package quiz;


import java.rmi.Naming;

import compute.Compute;

public class ComputeAddQuestion {

    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {              
            Compute comp = (Compute) Naming.lookup("//127.0.0.1:1099/Compute");  
            AddQuestion task = new AddQuestion();        
            String str = comp.executeTask(task);
            
            System.out.println(str);
        } catch (Exception e) {
            System.err.println("Compute exception:");
            e.printStackTrace();
        }
    }  
}
