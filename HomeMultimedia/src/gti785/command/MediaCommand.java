package gti785.command;

public interface MediaCommand {
	
	void execute() throws Exception;
	
	String getCommadType();
	
	String getResponse();
}
