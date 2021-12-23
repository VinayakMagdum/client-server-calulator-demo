package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import requiredEnum.Operator;
import requiredEnum.RequestType;
import utility.ClientServerException;
import utility.Defination;
import utility.Request;
import utility.Response;
import utility.Utility;
/**
 * Class ClientCreator
 * @author Vinayak
 *
 */
public class ClientCreator
{
	/**
	 * IP address
	 */
	private String _ipAddress ;
	/**
	 * Port number
	 */
	private int _portNum ;
	/**
	 * Object of Request
	 */
	private Request _request ;
	/**
	 * Default constructor of ClientCreator
	 */
	ClientCreator(String ipAddress, int portNum)
	{
		_ipAddress = ipAddress;
		_portNum = portNum;
		_request = new Request();
	}

	/**
	 * To start execution.
	 */
	public void startExecution()
	{
		try
		{
			createClientSocket();
		}
		catch(ClientServerException c)
		{
			System.err.println(c.getMessage());
		}
		catch(IOException i)
		{
			throw new ClientServerException(i.getMessage());
		}

		
	}
	/**
	 * To create client socket
	 * @throws IOException 
	 * @throws ClientServerException 
	 */
	public void createClientSocket() throws ClientServerException, IOException
	{
		Socket socket = null;
		try
		{
			System.out.println(Defination.SERVER_CONN);
			socket = new Socket(_ipAddress, _portNum);
			System.out.println(socket.isConnected());
			System.out.println(Defination.CLIENT_RUN + socket.getInetAddress());
			System.out.println(Defination.SERVER_RUN + _ipAddress);
			setRequestType();
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			outStream.writeObject(_request);
			outStream.flush();
			Response res = new Response();
			try
			{
				ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
				res = (Response) inStream.readObject();
				System.out.println(Defination.RESULT + res.getResult());
				System.out.println(Defination.CLIENT_MSG + " " + Defination.CLOSED);
			}
			catch(ClassNotFoundException e)
			{
				throw new ClientServerException(Defination.CLIENT_MSG + " " + Defination.CLOSED);
			}
		}
		catch(UnknownHostException e){e.printStackTrace();}
		catch(IOException e){throw new ClientServerException(Defination.SERVER_MSG+" "+ Defination.CLOSED);}
		finally
		{
			try
			{
				if(socket.isConnected())
				{
					socket.close();
				}
			}
			catch(NullPointerException n)
			{
				throw new ClientServerException(Defination.SERVER_NOT_READY);
			}
		}
	}

	/**
	 * To set operand from user
	 */
	private void setOperands()
	{
		double operand1 ;
		double operand2 ;
		System.out.println(Defination.OPER_1);
		operand1 = Utility.getDoubleInput();
		_request.setOperand1(operand1);
		System.out.println(Defination.OPER_2);
		operand2 = Utility.getDoubleInput();
		_request.setOperand2(operand2);
	}

	/**
	 * To set Request type.
	 */
	private void setRequestType()
	{
		int choice;
		RequestType reqType = null;
		System.out.println(Defination.AIRTHMATIC + "\n" +Defination.QUIT);
		System.out.println(Defination.ENTER_CHOICE_MSG);
		choice = Utility.getIntegerInput();
		switch(choice)
		{
			case 1:
				reqType = RequestType.AirthmaticOperation;
				_request.setRequestType(reqType);
				setAirthmaticReqType();
				setOperands();
				break;
			case 2:
				reqType = RequestType.Quit;
				_request.setRequestType(reqType);
				_request.setOperator(null);
				break;
			default:
				System.err.println(Defination.WRONG_INPUT_MSG);
				setRequestType();
		}
		
	}

	/**
	 * To set data for Airthmatic request type.
	 */
	private void setAirthmaticReqType()
	{
		Operator operator=null;
		String choice;
		System.out.println(Defination.OPERATORS_MSG);
		System.out.println(Defination.ENTER_CHOICE_MSG);
		choice = Utility.getStringInput();
		switch(choice)
		{
			case "+":
				operator = Operator.ADD;
				_request.setOperator(operator);
				break;
			case "-":
				operator = Operator.SUB;
				_request.setOperator(operator);
				break;
			case "*":
				operator = Operator.MUL;
				_request.setOperator(operator);
				break;
			case "/":
				operator = Operator.DIV;
				_request.setOperator(operator);
				break;
			default:
				System.err.println(Defination.WRONG_INPUT_MSG);
				setAirthmaticReqType();
		}
	}
}