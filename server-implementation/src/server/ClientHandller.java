package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import requiredEnum.Operator;
import requiredEnum.RequestType;
import utility.ClientServerException;
import utility.Defination;
import utility.Request;
import utility.Response;

/**
 * Class ClientHandller
 * @author Vinayak
 *
 */
public class ClientHandller implements Runnable
{
	/**
	 * Data Input Stream
	 */
	private ObjectInputStream _inStream ;
	/**
	 * Data Output Stream 
	 */
	private ObjectOutputStream _outStream;
	/**
	 * Client Socket
	 */
	private Socket _clientSocket = null;
	/**
	 * Server Socket
	 */
	private ServerSocket _serverSocket = null ;

	/**
	 * Parameterised Constructor.
	 * @param clientSocket - client socket.
	 * @param serverSocket - server socket.
	 */
	public ClientHandller(Socket clientSocket, ServerSocket serverSocket)
	{
		_serverSocket = serverSocket;
		_clientSocket = clientSocket;
	}

	/**
	 * run method of Thread Class.
	 */
	public void run()
	{
		try
		{
			startExecution();
		}
		catch(ClientServerException c)
		{
			System.err.println(c.getMessage());
		}
	}

	/**
	 * To start Execution.
	 * @throws ClientServerException - Own Exception.
	 */
	private void startExecution() throws ClientServerException
	{
		try
		{
			_inStream = new ObjectInputStream(_clientSocket.getInputStream());
			_outStream = new ObjectOutputStream(_clientSocket.getOutputStream());
			Request request = new Request();
			request = (Request) _inStream.readObject();
			executeRequest(request);
		}
		catch(ClassNotFoundException e)
		{
			throw new ClientServerException(e.getMessage());
		}
		catch(IOException e)
		{
			throw new ClientServerException(Defination.CLIENT_WRONG_ENTER);
		}
	}

	/**
	 * To execute request
	 * @param request - object of Request
	 */
	private void executeRequest(Request request)
	{
		if(request.getRequestType() == RequestType.Quit)
		{
			quitTypeReq();
		}
		if(request.getRequestType() == RequestType.AirthmaticOperation)
		{
			airthmaticTypeReq(request);
		}
	}

	/**
	 * To execute quit request
	 */
	private void quitTypeReq()
	{
		try
		{
			_serverSocket.close();
			_outStream.close();
			_clientSocket.close();
		}
		catch(IOException e)
		{
			throw new ClientServerException(Defination.CLIENT_WRONG_ENTER);
		}
	}

	/**
	 * To execute airthmatic request
	 * @param request - object of Request
	 */
	private void airthmaticTypeReq(Request request)
	{
		try
		{
			Response res = new Response();
			res = calculation(request);
			_outStream.writeObject(res);
			System.out.println(Defination.SEND_RES);
			_outStream.flush();
			System.out.println(Defination.CLIENT_MSG + _clientSocket.getInetAddress() + " "+ Defination.CLOSED);
			Thread.currentThread().interrupt();
		}
		catch(IOException e)
		{
			throw new ClientServerException(Defination.CLIENT_WRONG_ENTER);
		}
	}

	/**
	 * To calculate result of expression receive from client.
	 * @param req - object of Request class
	 */
	private Response calculation(Request req)
	{
		double result = 0.0;
		Response res = new Response();
		Operator param = req.getOperator();
		switch(param)
		{
			case ADD:
				result = req.getOperand1() + req.getOperand2();
				break;
			case SUB:
				result = req.getOperand1() - req.getOperand2();
				break;
			case MUL:
				result = req.getOperand1() * req.getOperand2();
				break;
			case DIV:
				result = req.getOperand1() / req.getOperand2();
				break;
		}
		res.setResult(result);
		return res;
	}
}