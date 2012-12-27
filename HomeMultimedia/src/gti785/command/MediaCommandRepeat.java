package gti785.command;

import gti785.controller.Contexte;
import gti785.remote.ETSRemote;

public class MediaCommandRepeat implements MediaCommand {
	private String commandType = "Repeat mode";
	private String response = "";
	private String arg = null;
	private ETSRemote remote = null;
	
	public MediaCommandRepeat(String arg){
		this.arg = arg;
		this.remote = Contexte.getInstance().getRemote();
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
	
	public String getArg() {
		return arg;
	}
}
