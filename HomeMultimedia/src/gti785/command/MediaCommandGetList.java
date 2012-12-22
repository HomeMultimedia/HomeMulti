package gti785.command;

import gti785.model.MediaFolder;
import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandGetList implements MediaCommand {
	private MediaFolder folder = null;
	private String arg;
	private String commandType = "Get media list";
	private String response = "";
	
	public MediaCommandGetList(MediaFolder folder, String arg){
		this.folder = folder;
	}
	
	public void execute() throws Exception{
		response = PrintXML.printMedia(folder.getFiles());
	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}


}
