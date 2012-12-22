package gti785.command;

import gti785.remote.ETSRemote;


/**
 * 
 * @author Cedric
 *
 */
public class CommandFactory {
	private static CommandFactory instance;
	
	private CommandFactory(){}
	
	public static CommandFactory getInstance(){
		if( instance == null)
			instance = new CommandFactory();
			
		return instance;
	}
	
	
	public MediaCommand getMediaCommandPlay(ETSRemote remote, String arg){
		return new MediaCommandPlay(remote, arg);
	}
	public MediaCommand getMediaCommandPlaylistAdd(ETSRemote remote, String arg){
		return new MediaCommandPlaylistAdd(remote, arg);
	}
	public MediaCommand getMediaCommandPlaylistRemove(ETSRemote remote, String arg){
		return new MediaCommandPlaylistRemove(remote, arg);
	}
	public MediaCommand getMediaCommandPoll(ETSRemote remote, String arg){
		return new MediaCommandPoll(remote, arg);
	}
	public MediaCommand getMediaCommandVolume(ETSRemote remote, String arg){
		return new MediaCommandVolume(remote, arg);
	}
	public MediaCommand getMediaCommandRepeat(ETSRemote remote, String arg){
		return new MediaCommandRepeat(remote, arg);
	}
	
}
