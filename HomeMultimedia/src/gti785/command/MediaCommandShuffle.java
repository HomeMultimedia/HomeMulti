package gti785.command;

import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandShuffle implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Shuffle";
	private String response = "";
	
	public MediaCommandShuffle(ETSRemote remote, String arg){
		this.remote = remote;
	}
	
	public void execute() throws Exception{
		remote.shuffle();
		response = PrintXML.printPlaylist(remote.getPlaylist());
	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}


}
