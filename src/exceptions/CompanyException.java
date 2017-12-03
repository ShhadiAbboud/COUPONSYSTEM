package exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyException.
 */
public class CompanyException {
	
	/**
	 * Instantiates a new company exception.
	 *
	 * @param e the e
	 */
	public CompanyException (Exception e){
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType = ExceptionType.valueOf(exceptionClass);

		switch(exceptionType)
		{
		case ClassNotFoundException :
			System.out.println(e.getCause());
			System.out.println("company class does not exist");
			break;
		case SQLException :
			System.out.println(e.getCause());
			System.out.println("cannot connect to mysql");
			break;
		case InterruptedException :
			System.out.println(e.getCause());
			System.out.println("the thread has been interrupted - the system might be shutting down");
			break;
		case DuplicateEntryException :
			System.out.println(e.getMessage());
			System.out.println("can't create company, databease already contains this company's name");
			break;
		case WrongDataInputException :
			System.out.println(e.getMessage());
			System.out.println("either the company name or the password is wrong - can't login!");
			break;
		case NullConnectionException :
			System.out.println(e.getMessage());
			System.out.println("your connection is null - the system might be shutting down!");
		default:
			break;
		}

	}

}
