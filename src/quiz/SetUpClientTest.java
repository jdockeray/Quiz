package quiz;

import static org.junit.Assert.*;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import compute.Compute;

/**
 * @author James
 * 
 * The idea behind this Testing Class, is that it will the Remote Objects Methods from the client.
 *
 */
public class SetUpClientTest {
	Compute comp;
/**
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
**/
	@Before
	public void setUp() throws Exception {
		try {
			comp = (Compute) Naming.lookup("//127.0.0.1:1099/Compute"); 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@After
	public void tearDown() throws Exception {
		comp=null;
	}

	/**
	 * Creates two numbers and checks that they are no the same
	 * @throws RemoteException 
	 */
	@Test
	public void duplicateIdtest()  {
		Object x=null;
		Integer y=0;
		assertEquals("Uh oh they are not equal", x, x);
	}

}
