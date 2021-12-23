package utility;
/**
 * Class ClientServerException.
 * @author Vinayak
 *
 */
public class ClientServerException extends RuntimeException
{
	/**
	 * Serial version Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Parameterised constructor.
	 * @param msg - Message
	 */
	public ClientServerException(String msg)
	{
		super(msg);
	}
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		return super.getMessage();
	}
	
}