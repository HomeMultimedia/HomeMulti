package gti785.command;

import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandPoll implements MediaCommand {
	private String commandType = "PlaylistAdd";
	private String response = "";
	private String arg = null;
	ETSRemote remote = null;
	PrintXML printer = null;
	
	public MediaCommandPoll(ETSRemote remote, String arg){
		this.arg = arg;
		this.remote = remote;
		printer = new PrintXML();
	}
	
	public void execute() throws Exception {
		int songPlayListID = remote.getCurrentSongPlaylistID();
		response = "current song: " + songPlayListID;

	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}

}
