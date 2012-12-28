package command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * Executes the given command and stores all commands in a history lists. This enables to 
 * playback all commands for debug purposes.
 * 
 * @author Cedric
 *
 */
public class ExecuteCommand {
	private List<MediaCommand> history = null;
	
	public ExecuteCommand(){
		history = new ArrayList<MediaCommand>();
	}
	
	public void execute(MediaCommand command, HttpServletResponse response) throws Exception{
		try{
			history.add(command);
			command.execute();
			response.getWriter().write(command.getResponse());
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
