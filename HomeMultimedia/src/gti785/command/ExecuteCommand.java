package gti785.command;

import gti785.view.PrintXML;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ExecuteCommand {
	private List<MediaCommand> history = null;
	private PrintXML XMLprinter = null;
	
	public ExecuteCommand(){
		history = new ArrayList<MediaCommand>();
		XMLprinter = new PrintXML();
	}
	
	public void execute(MediaCommand command, HttpServletResponse response){
		try{
			history.add(command);
			command.execute();
			//XMLprinter.printAfterPlay(command.getResponse(),response);
			response.getWriter().write(command.getResponse());
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			//print history
		}
	}
}
