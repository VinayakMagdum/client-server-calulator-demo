package utility;

import java.io.Serializable;
import requiredEnum.*;
/**
 * Class Request.
 */
public class Request implements Serializable
{
	/**
	 * serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * First Operand.
	 */
	double _operand1;
	/**
	 * Second Operand.
	 */
	double _operand2;
	/**
	 * Reference of Operator enum.
	 */
	Operator _operator;
	/**
	 * Reference of RequestType enum
	 */
	RequestType _reqType;
	/**
	 * To set First Operand 
	 * @param operand1 - First Operand.
	 */
	public void setOperand1(double operand1)
	{
		this._operand1 = operand1;
	}
	/**
	 * To get First Operand.
	 * @return - First Operand value.
	 */
	public double getOperand1()
	{
		return _operand1;
	}
	/**
	 * To set Second Operand 
	 * @param operand2 - Second Operand.
	 */
	public void setOperand2(double operand2)
	{
		this._operand2 = operand2;
	}
	/**
	 * To get Second Operand.
	 * @return - Second Operand value.
	 */
	public double getOperand2()
	{
		return _operand2;
	}
	/**
	 * To set Airthmatic Operator.
	 * @param operator - Airthmatic Operator.
	 */
	public void setOperator(Operator operator)
	{
		this._operator = operator ;
	}
	/**
	 * To get Airthmatic Operator.
	 * @return - Airthmatic Operator.
	 */
	public Operator getOperator()
	{
		return _operator;
	}
	/**
	 * To set type of request.
	 * @param reqParam - type of request.
	 */
	public void setRequestType(RequestType reqParam)
	{
		this._reqType = reqParam;
	}
	/**
	 * To get type of request.
	 * @return - type of request.
	 */
	public RequestType getRequestType()
	{
		return _reqType;
	}
}