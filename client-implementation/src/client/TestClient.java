package client;

import utility.Defination;

/**
 * Class TestClient
 * @author Vinayak
 *
 */
public class TestClient
{
	/**
	 * Statrting point of Program.
	 * @param args - command line arguments
	 */
	public static void main(String[] args)
	{
		String ipAddress = null;
		int portNum = 0;
		if(args.length == 2)
		{
			ipAddress = args[0];
			portNum = Integer.parseInt(args[1]);
			ClientCreator client = new ClientCreator(ipAddress, portNum);
			client.startExecution();
		}
		else
		{
			System.out.println(Defination.CONN_FAIL);
		}
	}
}