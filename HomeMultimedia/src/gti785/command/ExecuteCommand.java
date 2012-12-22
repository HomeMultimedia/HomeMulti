package gti785.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
			//System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
}
