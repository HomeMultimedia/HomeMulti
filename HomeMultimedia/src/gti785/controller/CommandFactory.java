package gti785.controller;

import gti785.command.MediaCommand;
import gti785.command.MediaCommandGetList;
import gti785.command.MediaCommandPause;
import gti785.command.MediaCommandPlay;
import gti785.command.MediaCommandPlaylistAdd;
import gti785.command.MediaCommandPlaylistRemove;
import gti785.command.MediaCommandPoll;
import gti785.command.MediaCommandPrintPlaylist;
import gti785.command.MediaCommandRepeat;
import gti785.command.MediaCommandShuffle;
import gti785.command.MediaCommandVolume;
import gti785.model.MediaFolder;
import gti785.remote.ETSRemote;


/**
 * The commandFactory enables to get commands throughout the application.
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
	public MediaCommand getMediaCommandShuffle(ETSRemote remote, String arg) {
		return new MediaCommandShuffle(remote, arg);
	}
	public MediaCommand getMediaCommandPause(ETSRemote remote, String arg) {
		return new MediaCommandPause(remote, arg);
	}
	public MediaCommand getMediaCommandPrintPlaylist(ETSRemote remote, String arg) {
		return new MediaCommandPrintPlaylist(remote, arg);
	}
	public MediaCommand getMediaCommandGetList(MediaFolder folder, String arg) {
		return new MediaCommandGetList(folder, arg);
	}
	
}