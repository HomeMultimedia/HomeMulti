package gti785.command;

import gti785.remote.ETSRemote;

public class MediaCommandVolume implements MediaCommand {
	private String commandType = "Change Volume";
	private String response = "";
	private String arg = null;
	private ETSRemote remote = null;
	
	public MediaCommandVolume(ETSRemote remote, String arg){
		this.arg = arg;
		this.remote = remote;
	}
	
	public void execute() throws Exception {
		if( arg != null){
			remote.changeVolume(Integer.parseInt(arg));
			System.out.println("volume: "+arg);
		}
		else{
			throw new Exception("Volume: no option");
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
