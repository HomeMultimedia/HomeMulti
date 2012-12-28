package controller;

import model.MediaFolder;
import param.Const;

import java.io.File;

/**
 * 
 * @author Cedric
 *
 */
public class MediaFolderFactory {
	private static MediaFolderFactory instance;
	
	private MediaFolderFactory(){}
	
	public static MediaFolderFactory getInstance(){
		if( instance == null)
			instance = new MediaFolderFactory();
			
		return instance;
	}
	
	
	public MediaFolder build(File file){
		if(file == null){
			file = new File(Const.dossier);
		}
		return new MediaFolder(file).build();
	}
}
