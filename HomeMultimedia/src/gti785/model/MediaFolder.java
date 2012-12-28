package gti785.model;


import gti785.controller.MediaFolderFactory;
import gti785.param.Const;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/**
 * Le MediaFolder permet de g�rer les m�dias du dossier multim�dia.
 * 
 * @author Cedric
 *
 */
public class MediaFolder{
	private List<Media> files = null;
	private File _folder;
	private ArtworkFolder artwork;
	private List<File> folders;
	private String racine = "";
	/**
	 * Constructeur, remplit la liste files avec tous les m�dias du dossier
	 * multim�dia.
	 * 
	 * @param folder path to the folder which will contain medias
	 * @param artwork Object responsible for artwork
	 */
	public MediaFolder(File folder){
		_folder = folder;
		/*int ind = folder.getAbsolutePath().lastIndexOf("/");
		racine = folder.getAbsolutePath().substring(0, ind);*/
		racine = folder.getAbsolutePath();
		this.artwork = new ArtworkFolder(new File(Const.dossierImage));
		this.files = new ArrayList<Media>();
		folders = new ArrayList<File>();
	}
	
	/**
	 * Checks if extension is .mp3 or .m4a
	 * @param filename path to check
	 * @return true if .mp3 or .m4a
	 */
	private boolean checkMP3(String filename) { //D�placer vers utils?
		int pos = filename.lastIndexOf(".");
		String extension = filename.substring(pos);
		return extension.equals(".mp3") || extension.equals(".m4a");
	}
	
	/**
	 * OpenFolder returns an instance of a contained MediaFolder
	 * 
	 * @param file
	 * @return
	 */
	public MediaFolder OpenFolder(File file){
		if(folders.contains(file)){
			int index = folders.indexOf(file);
			return MediaFolderFactory.getInstance().build(folders.get(index));
		}
		else
			return null;
	}
	
	public MediaFolder upFolder(){
		int index = _folder.getAbsolutePath().lastIndexOf("/");
		String path = _folder.getAbsolutePath().substring(0, index);
		File file = new File(path);
		if(file.exists())
			return MediaFolderFactory.getInstance().build(file);
		else
			return null;
	}
	/**
	 * GETTERS and SETTERS
	 * 
	 * @return
	 */
	public List<Media> getFiles() {
		return files;
	}
	
	public String getRacine() {
		return racine;
	}
	
	public List<File> getFolders(){
		return folders;
	}
	

	/**
	 * build is called on creation of the mediaFolder. Retrieves song information 
	 * of the media folder and creates a media list and associated artwork.
	 * 
	 * @return the MediaFolder instance
	 */
	public MediaFolder build() {
		try {
			int i = 0;
			for(File file: _folder.listFiles()){//cr�er une fonction
				AudioFile f;
				String filename = file.toString();
				if(file.isDirectory()){
					folders.add(file);
				}
				else if( checkMP3(filename)){//if mp3 (or m4a)
					
					//jaudiotagger fonctions
					f = AudioFileIO.read(new File(filename));
					Tag tag = f.getTag();
					
					//retrive song information
					String album = tag.getFirst(FieldKey.ALBUM);
					String poster = Const.ARTWORK_URL+album+".png";
					int length = f.getAudioHeader().getTrackLength();
					String mrl = file.toString();
					String title = file.getName();

					if(!artwork.imageExist(album) && tag.getFirstArtwork() != null){
						
						BufferedImage img = (BufferedImage)tag.getFirstArtwork().getImage(); //error if no artwork
						//if( img != null)
							artwork.saveToFolder(img, album);//g�rer if album = null
					}
					else if(tag.getFirstArtwork() == null){//mettre image par def
						poster = Const.ARTWORK_URL+"default.png";
					}
					
					//create new Media
					Media media = new Media(i, title, album, length, mrl, poster);
					
					//add to list of media
					files.add(media);
					i++;
				}
			}
			
			//System.out.println("test:" + files.get(0).getTitle());
			return this;
		
		} catch (CannotReadException e) {
			System.out.println("Impossible de lire le fichier");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur dans la lecture du fichier");
			//e.printStackTrace();
		} catch (TagException e) {
			System.out.println("Erreur dans le tag du fichier");
			//e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			System.out.println("Erreur: fichier en lecture seule.");
			//e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			System.out.println("Frame audio invalide.");
			//e.printStackTrace();
		}
		
		return null;
	}

}
