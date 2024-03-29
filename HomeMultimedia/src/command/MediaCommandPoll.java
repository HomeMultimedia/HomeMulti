package command;

import controller.Contexte;
import remote.ETSRemote;

public class MediaCommandPoll implements MediaCommand {
	private String commandType = "Poll";
	private String response = "";
	private String arg = null;
	private ETSRemote remote = null;
	
	public MediaCommandPoll(String arg){
		this.arg = arg;
		this.remote = Contexte.getInstance().getRemote();
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
	
	public String getArg() {
		return arg;
	}
}
