package gti785.command;

import gti785.controller.Contexte;
import gti785.model.MediaFolder;
import gti785.view.Folder;
import gti785.view.Folders;
import gti785.view.PrintXML;

import java.io.File;

/**
 * Gets the media list
 * 
 * @author Cedric
 *
 */
public class MediaCommandGetList implements MediaCommand {
	private MediaFolder folder = null;
	private String arg;
	private String commandType = "Get media list";
	private String response = "";
	
	public MediaCommandGetList(String arg){
		this.folder = Contexte.getInstance().getMediaFolder();
	}
	
	public void execute() throws Exception{
		response = "{\"media\":" + PrintXML.printMedia(folder.getFiles()) + ",";
		
		Folders fs = new Folders();
		for(File file:folder.getFolders()){
			Folder f = new Folder();
			f.name = file.getName();
			fs.folders.add(f);
		}
		response += "\"folders\":" + PrintXML.printFolders(fs) + "}";
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
