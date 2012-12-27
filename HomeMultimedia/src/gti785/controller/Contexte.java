package gti785.controller;

import gti785.model.MediaFolder;
import gti785.remote.ETSRemote;

public class Contexte {
	private ETSRemote remote = null;
	private MediaFolder mediaFolder = null;
	private static Contexte instance = null;
	
	private Contexte(){
		mediaFolder = MediaFolderFactory.getInstance().build(null);
    	remote = new ETSRemote(mediaFolder, null);
	}
	
	public static Contexte getInstance(){
		if(instance == null){
			instance = new Contexte();
	    	return instance;
		}
		else
			return instance;
	}
	/**
	 * GETTERS and SETTERS
	 * @return
	 */
	
	public ETSRemote getRemote() {
		return remote;
	}
	public void setRemote(ETSRemote remote) {
		this.remote = remote;
	}
	public MediaFolder getMediaFolder() {
		return mediaFolder;
	}
	public void setMediaFolder(MediaFolder mediaFolder) {
		this.mediaFolder = mediaFolder;
	}
	
	
}
