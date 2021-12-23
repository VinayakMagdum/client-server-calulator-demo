package utility;

import java.io.Serializable;
/**
 * Class Response
 * @author Vinayak
 *
 */
public class Response implements Serializable
{
	/**
	 * Serial version Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Result
	 */
	double result;
	/**
	 * To set result
	 * @param result - result of expression.
	 */
	public void setResult(double result)
	{
		this.result =result;
	}
	/**
	 * To get result of Expression.
	 * @return - result.
	 */
	public double getResult()
	{
		return result;
	}
}