package gti785.command;

import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandPlaylistAdd implements MediaCommand {
	private String commandType = "PlaylistAdd";
	private String response = "";
	private String arg = null;
	private ETSRemote remote = null;
	
	public MediaCommandPlaylistAdd(ETSRemote remote, String arg){
		this.arg = arg;
		this.remote = remote;
	}
	
	public void execute() throws Exception {
		if( arg != null ){
			if(remote.playListAdd(Integer.parseInt(arg))){
				response = PrintXML.printPlaylist(remote.getPlaylist());
			}
			else{
				throw new Exception("Play list add: Song does not exist");
			}
		}
		else{
			throw new Exception("Play list add: no option");
		}

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