package utility;

import java.util.Scanner;
/**
 * Class Utility
 * @author Vinayak
 */
public class Utility
{
	/**
	 * Scanner object
	 */
	static private Scanner _scan = new Scanner(System.in);
	/**
	 * To get integer input.
	 * @return - Integer value.
	 */
	public static int getIntegerInput()
	{
		int input =0 ;
		try
		{
			String strInput = _scan.nextLine();
			input = Integer.parseInt(strInput);
			if(input < 0)
			{
				System.err.println(Defination.WRONG_INPUT_MSG);
				input = getIntegerInput();
			}
		}
		catch(Exception e)
		{
			System.err.println(Defination.WRONG_INPUT_MSG);
			input = getIntegerInput();
		}
		return input;
	}
	/**
	 * To get double input.
	 * @return - Double value.
	 */
	public static double getDoubleInput()
	{
		double input =0 ;
		try
		{
			String strInput = _scan.nextLine();
			input = Double.parseDouble(strInput);
			if(input < 0)
				
			{
				System.err.println(Defination.WRONG_INPUT_MSG);
				input = getDoubleInput();
			}
		}
		catch(Exception e)
		{
			System.err.println(Defination.WRONG_INPUT_MSG);
			input = getDoubleInput();
		}
		return input;	
	}
	/**
	 * To get String as input.
	 * @return - String
	 */
	public static String getStringInput()
	{
		String input = null;
		try
		{
			input =_scan.nextLine();
			if(!input.equals("+") && !input.equals("-") && !input.equals("*") && !input.equals("/"))
			{
				System.err.println(Defination.WRONG_INPUT_MSG);
				input = getStringInput();
			}
		}
		catch(Exception E)
		{
			System.err.println(Defination.WRONG_INPUT_MSG);
			input=getStringInput();
		}
		return input;
	}
}