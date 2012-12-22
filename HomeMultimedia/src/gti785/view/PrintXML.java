package gti785.view;

import gti785.model.Media;
import gti785.model.PlaylistItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import uk.co.caprica.vlcj.medialist.MediaListItem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * Class used to print objects in XML format
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
		xstream.alias("list", List.class);
		xstream.alias("list",  gti785.model.List.class);
		xstream.alias("playlist", MediaListItem.class);
		xstream.alias("playlist", PlaylistItem.class);
		xstream.alias("port", String.class);
	}
	
	/**
	 * Prints a list of media
	 * 
	 * @param files
	 * @param response
	 */
	public static String printMedia(List<Media> files) {
		/*try {				
			PrintWriter out = response.getWriter();
			out.write(xstream.toXML(files));
		} catch (IOException e) {
			System.out.println("Error while writing file list");
		}*/
		return xstream.toXML(files);
	}
	
	public static String printPlaylist(List<PlaylistItem> files) {
		/*try {				
			PrintWriter out = response.getWriter();
			out.write(xstream.toXML(files));
		} catch (IOException e) {
			System.out.println("Error while writing file list");
		}*/
		
		return xstream.toXML(files);
	}
	
	public void printPort(String port, HttpServletResponse response) {
		try {				
			PrintWriter out = response.getWriter();
			out.write(xstream.toXML(port));
		} catch (IOException e) {
			System.out.println("Error while writing port number");
		}
	}
	
	public static String printAfterPlay(String id) {
		/*PrintWriter out = response.getWriter();
		gti785.model.List l = new gti785.model.List(id);
		out.write(xstream.toXML(l));
		out.write(id);*/
		gti785.model.List l = new gti785.model.List(id);
		return xstream.toXML(l);
	}

	
}
