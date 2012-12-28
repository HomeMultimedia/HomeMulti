package command;

import controller.Contexte;
import remote.ETSRemote;

public class MediaCommandPause implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Pause Media";
	private String response = "";
	
	public MediaCommandPause(String arg){
		this.remote = Contexte.getInstance().getRemote();
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
