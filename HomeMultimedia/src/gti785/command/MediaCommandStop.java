package gti785.command;

import gti785.controller.Contexte;
import gti785.remote.ETSRemote;

public class MediaCommandStop implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Stop Media";
	private String response = "";
	
	public MediaCommandStop(String arg){
		this.remote = Contexte.getInstance().getRemote();
	}
	
	public void execute() throws Exception{
		remote.stop();
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
