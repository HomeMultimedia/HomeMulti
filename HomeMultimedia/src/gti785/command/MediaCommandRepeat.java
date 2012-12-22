package gti785.command;

import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

public class MediaCommandRepeat implements MediaCommand {
	private String commandType = "PlaylistAdd";
	private String response = "";
	private String arg = null;
	ETSRemote remote = null;
	PrintXML printer = null;
	
	public MediaCommandRepeat(ETSRemote remote, String arg){
		this.arg = arg;
		this.remote = remote;
		printer = new PrintXML();
	}
	
	public void execute() throws Exception {
		if( arg != null){
			if(remote.repeat(arg))
				System.out.println("repeat mode: "+arg);
			else{
				throw new Exception("Repeat mode: repeat mode does not exist");
			}
		}
		else{
			throw new Exception("Repeat mode: no option");
		}

	}

	public String getCommadType() {
		return commandType;
	}

	public String getResponse() {
		return response;
	}

}
