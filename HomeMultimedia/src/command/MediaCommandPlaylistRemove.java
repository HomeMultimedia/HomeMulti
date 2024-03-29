package command;

import controller.Contexte;
import remote.ETSRemote;
import view.PrintXML;

public class MediaCommandPlaylistRemove implements MediaCommand {
	private String commandType = "PlaylistRemove";
	private String response = "";
	private String arg = null;
	private ETSRemote remote = null;
	
	public MediaCommandPlaylistRemove(String arg){
		this.arg = arg;
		this.remote = Contexte.getInstance().getRemote();
	}
	
	public void execute() throws Exception {
		if( arg != null ){
			if(remote.playListRemove(Integer.parseInt(arg))){
				response = PrintXML.printPlaylist(remote.getPlaylist());
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

	public String getArg() {
		return arg;
	}
}
