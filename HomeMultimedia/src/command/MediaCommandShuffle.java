package command;

import controller.Contexte;
import remote.ETSRemote;
import view.PrintXML;

/**
 * Shuffles the playlist.
 * 
 * @author Cedric
 *
 */
public class MediaCommandShuffle implements MediaCommand {
	private ETSRemote remote = null;
	private String arg;
	private String commandType = "Shuffle";
	private String response = "";
	
	public MediaCommandShuffle(String arg){
		this.remote = Contexte.getInstance().getRemote();
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

	public String getArg() {
		return arg;
	}
}
