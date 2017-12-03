package exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class CouponException.
 */
public class CouponException {
	
	/**
	 * Instantiates a new coupon exception.
	 *
	 * @param e the e
	 */
	public CouponException ( Exception e){
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType = ExceptionType.valueOf(exceptionClass);

		switch(exceptionType)
		{
		case ClassNotFoundException :
			System.out.println(e.getCause());
			System.out.println("coupon class does not exist");
			break;
		case SQLException :
			System.out.println(e.getCause());
			System.out.println("cannot connect to mysql");
			break;
		case InterruptedException :
			System.out.println(e.getCause());
			System.out.println("the thread has been interrupted - the system might be shutting down");
			break;
		case ParseException :
			System.out.println(e.getCause());
			System.out.println("the date has been entered in the wrong format");
			System.out.println("enter the date in the yy-mm-dd format");
			break;
		case DuplicateEntryException :
			System.out.println(e.getMessage());
			System.out.println("can't create coupon - another coupon with same title alreay exist!");
			break;
		case NullConnectionException :
			System.out.println(e.getMessage());
			System.out.println("your connection is null - the system might be shutting down!");
		default:
			break;
		}
	}

}
