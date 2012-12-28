package controller;

import command.MediaCommand;
import command.MediaCommandGetList;
import command.MediaCommandNext;
import command.MediaCommandOpenFolder;
import command.MediaCommandPause;
import command.MediaCommandPlay;
import command.MediaCommandPlaylistAdd;
import command.MediaCommandPlaylistRemove;
import command.MediaCommandPoll;
import command.MediaCommandPrevious;
import command.MediaCommandPrintPlaylist;
import command.MediaCommandRepeat;
import command.MediaCommandShuffle;
import command.MediaCommandStop;
import command.MediaCommandToBeginning;
import command.MediaCommandUpFile;
import command.MediaCommandVolume;


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
