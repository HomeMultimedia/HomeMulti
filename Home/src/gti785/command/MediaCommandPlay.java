package gti785.command;

import gti785.remote.ETSRemote;

public class MediaCommandPlay implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Play Media";
	private String response = "";
	
	public MediaCommandPlay(ETSRemote remote, String arg){
		this.remote = remote;
	}
	
	public void execute() throws Exception{
		int idPlaylist = 0;
		if (arg != null) {
			idPlaylist = Integer.parseInt(arg);
			response = ""+idPlaylist;
		}
		remote.play(idPlaylist);
		
		if(remote.play(idPlaylist)){
			System.out.println("Song in play");
		}
		else{
			throw new Exception("Play : Error while trying to play song.");
		}
	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}


}
