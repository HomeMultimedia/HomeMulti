package gti785.controller;

import gti785.command.MediaCommand;
import gti785.command.MediaCommandGetList;
import gti785.command.MediaCommandNext;
import gti785.command.MediaCommandOpenFolder;
import gti785.command.MediaCommandPause;
import gti785.command.MediaCommandPlay;
import gti785.command.MediaCommandPlaylistAdd;
import gti785.command.MediaCommandPlaylistRemove;
import gti785.command.MediaCommandPoll;
import gti785.command.MediaCommandPrevious;
import gti785.command.MediaCommandPrintPlaylist;
import gti785.command.MediaCommandRepeat;
import gti785.command.MediaCommandShuffle;
import gti785.command.MediaCommandStop;
import gti785.command.MediaCommandToBeginning;
import gti785.command.MediaCommandUpFile;
import gti785.command.MediaCommandVolume;


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
	
	
	public MediaCommand getMediaCommandPlay(String arg){
		return new MediaCommandPlay(arg);
	}
	public MediaCommand getMediaCommandPlaylistAdd(String arg){
		return new MediaCommandPlaylistAdd(arg);
	}
	public MediaCommand getMediaCommandPlaylistRemove(String arg){
		return new MediaCommandPlaylistRemove(arg);
	}
	public MediaCommand getMediaCommandPoll(String arg){
		return new MediaCommandPoll(arg);
	}
	public MediaCommand getMediaCommandVolume(String arg){
		return new MediaCommandVolume(arg);
	}
	public MediaCommand getMediaCommandRepeat(String arg){
		return new MediaCommandRepeat(arg);
	}
	public MediaCommand getMediaCommandShuffle(String arg) {
		return new MediaCommandShuffle(arg);
	}
	public MediaCommand getMediaCommandPause(String arg) {
		return new MediaCommandPause(arg);
	}
	public MediaCommand getMediaCommandPrintPlaylist(String arg) {
		return new MediaCommandPrintPlaylist(arg);
	}
	public MediaCommand getMediaCommandGetList(String arg) {
		return new MediaCommandGetList(arg);
	}

	public MediaCommand getMediaCommandOpenFolder(String file) {
		return new MediaCommandOpenFolder(file);
	}

	public MediaCommand getMediaCommandStop(String arg) {
		return new MediaCommandStop(arg);
	}

	public MediaCommand getMediaCommandNext(String arg) {
		return new MediaCommandNext(arg);
	}

	public MediaCommand getMediaCommandPrevious(String arg) {
		return new MediaCommandPrevious(arg);
	}

	public MediaCommand getMediaCommandToBeginning(String arg) {
		return new MediaCommandToBeginning(arg);
	}

	public MediaCommand getMediaCommandUpFile(String arg) {
		return new MediaCommandUpFile(arg);
	}
	
}
