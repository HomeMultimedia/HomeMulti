package command;

/**
 * Basic command interface. Used to implement all the media related commands.
 * The commands are created by the commandFactory.
 * 
 * @author Cedric
 *
 */

public interface MediaCommand {
	
	/**
	 * Method that will be executed. Throws Exception containing a message with the 
	 * reason.
	 * 
	 * @throws Exception
	 */
	void execute() throws Exception;
	
	/**
	 * Used for history debug purposes.
	 * @return
	 */
	String getCommadType();
	
	/**
	 * The response generated by the execute method. Will be fetched and send to the client.
	 * 
	 * @return
	 */
	String getResponse();
	
	String getArg();
}