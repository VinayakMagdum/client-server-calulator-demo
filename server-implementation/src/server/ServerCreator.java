package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import utility.ClientServerException;
import utility.Defination;

/**
 * Class ServerCreator
 * @author Vinayak
 *
 */
public class ServerCreator
{
	/**
	 * Server Socket.
	 */
	private ServerSocket _serverSocket ;
	/**
	 * Client Socket
	 */
	private Socket _clientSocket ;
	/**
	 * To start execution.
	 */

	public void startExecution()
	{
		try
		{
			createServer();
		}
		catch(ClientServerException c)
		{
			System.err.println(c.getMessage());
		}
	}

	/**
	 * To create Server.
	 * @throws ClientServerException 
	 */
	public void createServer() throws ClientServerException 
	{
		final int portNumber = 4444;
		try
		{
			_serverSocket = new ServerSocket(portNumber);
			System.out.println(Defination.SERVER_CREATED);
			System.out.println(Defination.SERVER_IS_WAITING);
			while(!_serverSocket.isClosed())
			{
				clientHandller();
			}
		}
		catch(IOException e)
		{
			throw new ClientServerException(Defination.SERVER_MSG+" "+ Defination.CLOSED);
		}
	}

	/**
	 * To handle client.
	 */
	private void clientHandller()
	{
		try
		{
			_clientSocket = _serverSocket.accept();
			System.out.println(Defination.REQ_COME_MSG + _clientSocket.getInetAddress());
			ClientHandller client = new ClientHandller(_clientSocket, _serverSocket);
			Thread t = new Thread(client);
			t.start();
		}
		catch(IOException e)
		{
			throw new ClientServerException(Defination.SERVER_MSG+" "+ Defination.CLOSED);
		}
	}
}