package gti785.view;

import gti785.model.Media;
import gti785.model.PlaylistItem;

import java.io.File;
import java.util.List;

import uk.co.caprica.vlcj.medialist.MediaListItem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * Class used to create JSON for divers objects or lists.
 * 
 * @author Cedric
 *
 */
public class PrintXML {
private static final XStream xstream = new XStream(new JettisonMappedXmlDriver());
	
	// Configuration de XStream
	static {
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("song", Media.class);
		//xstream.alias("test", List.class);
		xstream.alias("list",  gti785.model.List.class);
		xstream.alias("playlist", MediaListItem.class);
		xstream.alias("playlist", PlaylistItem.class);
		xstream.alias("port", String.class);
		xstream.alias("name", File.class);
		
		xstream.alias("folder",Folder.class);
		xstream.alias("folderList",Folders.class);
	}
	
	/**
	 * Prints a list of media
	 * 
	 * @param files
	 * @param response
	 */
	public static String printMedia(List<Media> files) {
		return xstream.toXML(files);
	}
	
	/**
	 * prints the playlist.
	 * 
	 * @param files
	 * @return 
	 */
	public static String printPlaylist(List<PlaylistItem> files) {
		return xstream.toXML(files);
	}
	
	/**
	 * prints folders.
	 * 
	 * @param files
	 * @return 
	 */
	public static String printFolders(Folders fs) {
		return xstream.toXML(fs);
	}

	/**
	 * Print method when play is executed
	 * @param id
	 * @return
	 */
	public static String printAfterPlay(String id) {
		gti785.model.List l = new gti785.model.List(id);
		return xstream.toXML(l);
	}
	
	/*public void printPort(String port, HttpServletResponse response) {
		try {				
			PrintWriter out = response.getWriter();
			out.write(xstream.toXML(port));
		} catch (IOException e) {
			System.out.println("Error while writing port number");
		}
	}*/
}
