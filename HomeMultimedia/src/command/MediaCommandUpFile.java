package command;

import controller.Contexte;
import model.MediaFolder;
import view.PrintXML;

public class MediaCommandUpFile implements MediaCommand {
	private MediaFolder folder = null;
	private String arg;
	private String commandType = "Pause Media";
	private String response = "";
	
	public MediaCommandUpFile(String arg){
		this.folder = Contexte.getInstance().getMediaFolder();
	}
	
	public void execute() throws Exception{
		MediaFolder f = folder.upFolder();
		if(f != null)
			response = PrintXML.printMedia(f.getFiles());
		else
			response = "folder does not exist";
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
