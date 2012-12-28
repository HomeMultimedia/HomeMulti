package command;

import controller.Contexte;
import model.MediaFolder;
import view.PrintXML;

import java.io.File;

/**
 * Gets the media list
 * 
 * @author Cedric
 *
 */
public class MediaCommandOpenFolder implements MediaCommand {
	private MediaFolder _folder = null;
	private String arg;
	private String commandType = "Open folder";
	private String response = "";
	
	public MediaCommandOpenFolder(String arg){
		this._folder = Contexte.getInstance().getMediaFolder();
		this.arg = arg;
	}
	
	public void execute() throws Exception{
		String racine = _folder.getRacine();
		String path = racine + "/" + arg;
		
		File file = new File(path);
		MediaFolder folder = _folder.OpenFolder(file);
		
		if(folder == null){//for windows
			file = new File(racine + "\\" + arg);
		}
		
		if(folder != null){
			Contexte.getInstance().setMediaFolder(folder);
			response = PrintXML.printMedia(folder.getFiles());
			//response += PrintXML.printFolders(folders);
		}
		else {
			response = "Folder does not exist";
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
