package server;

/**
 * Class TestServer
 * @author Vinayak
 *
 */
public class TestServer
{
	/**
	 * Starting point of program.
	 * @param args - Command Line arguments.
	 */
	public static void main(String[] args)
	{
		ServerCreator server = new ServerCreator();
		server.startExecution();
	}
}