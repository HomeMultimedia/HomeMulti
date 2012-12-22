package gti785.command;

import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandPlaylistRemove implements MediaCommand {
	private String commandType = "PlaylistRemove";
	private String response = "";
	private String arg = null;
	ETSRemote remote = null;
	PrintXML printer = null;
	
	public MediaCommandPlaylistRemove(ETSRemote remote, String arg){
		this.arg = arg;
		this.remote = remote;
		printer = new PrintXML();
	}
	
	public void execute() throws Exception {
		if( arg != null ){
			if(remote.playListRemove(Integer.parseInt(arg))){
				response = printer.printPlaylist(remote.getPlaylist());
			}
			else{
				throw new Exception("Play list remove: Song does not exist");
			}
			
		}else{
			throw new Exception("Play list remove: no option");
		}

	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}

}
