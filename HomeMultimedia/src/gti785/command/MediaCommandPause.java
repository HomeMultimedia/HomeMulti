package gti785.command;

import gti785.remote.ETSRemote;

public class MediaCommandPause implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Pause Media";
	private String response = "";
	
	public MediaCommandPause(ETSRemote remote, String arg){
		this.remote = remote;
	}
	
	public void execute() throws Exception{
		remote.pause();
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
