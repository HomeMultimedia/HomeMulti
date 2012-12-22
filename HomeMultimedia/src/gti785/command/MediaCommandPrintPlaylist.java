package gti785.command;

import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandPrintPlaylist implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Print playlist";
	private String response = "";
	
	public MediaCommandPrintPlaylist(ETSRemote remote, String arg){
		this.remote = remote;
	}
	
	public void execute() throws Exception{
		response = PrintXML.printPlaylist(remote.getPlaylist());
	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}


}
