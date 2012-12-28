package command;

import controller.Contexte;
import remote.ETSRemote;

public class MediaCommandPrevious implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Previous Media";
	private String response = "";
	
	public MediaCommandPrevious(String arg){
		this.remote = Contexte.getInstance().getRemote();
	}
	
	public void execute() throws Exception{
		remote.previous();
		System.out.println("Song paused");
	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}

	public String getArg() {
		return arg;
	}
}
