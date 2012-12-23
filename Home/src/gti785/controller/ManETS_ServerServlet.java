package gti785.controller;

import gti785.command.ExecuteCommand;
import gti785.command.MediaCommand;
import gti785.model.MediaFolder;
import gti785.param.Const;
import gti785.push.Push;
import gti785.remote.ETSRemote;
import gti785.view.PrintXML;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MultiServlet
 */

@WebServlet("/Man")
public class ManETS_ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ETSRemote remote;
	
	private MediaFolder mediaFolder;
	private PrintXML XMLprinter;
	private static Push server ; 
	private ExecuteCommand executer = null;
    /**
     * Default constructor. Instanciates objects artwork, mediaFolder and remote
     */
    public ManETS_ServerServlet() {
    	try {
    		server = new Push();
    		server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	mediaFolder = MediaFolderFactory.getInstance().build();
    	remote = new ETSRemote(mediaFolder, server);
    	XMLprinter = new PrintXML();
    	executer = new ExecuteCommand();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/index.html");
		dispatch.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean error = false;
		String errorMessage = null;
		MediaCommand toDo = null;
		
		String command = null;
		command = request.getParameter("command");
		System.out.println(command);
		
		//get all media list
		if(command != null && command.equals("getList")){
			XMLprinter.printMedia(mediaFolder.getFiles(), response);
		}
		
		//play song
		else if(command != null && command.equals("play")){
			String id = null;
			id = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlay(remote, id);
		}
		
		//pause song
		else if(command != null && command.equals("pause")){
			remote.pause();
			System.out.println("Song paused");
		}
		
		//stop song
		else if(command != null && command.equals("stop")){
			remote.stop();
			System.out.println("Song stopped");
		}
		
		//add song to playlist
		else if(command != null && command.equals("playlistadd")){
			String idSong = null;
			idSong = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistAdd(remote, idSong);
		}
		
		//remove song from playlist
		else if(command != null && command.equals("playlistremove")){
			String idPlaylist = null;
			idPlaylist = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistRemove(remote, idPlaylist);
		}
		
		//play next song
		else if(command!=null && command.equals("next")){
			remote.next();
		}
		
		//play previous song
		else if(command!=null && command.equals("previous")){
			remote.previous();
		}
		
		//toBeginning
		else if(command!=null && command.equals("toBeginning")){
			remote.toBeginning();
		}
		
		//shuffle play list
		else if(command != null && command.equals("shuffle")){
			remote.shuffle();
			//XMLprinter.printPlaylist(remote.getPlaylist(),response);
		}
		
		//repeat action
		else if(command != null && command.equals("repeat")){
			String mode = null;
			mode = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandRepeat(remote, mode);
		}
		
		//print play list songs
		else if(command != null && command.equals("getPlayList")){
			//XMLprinter.printPlaylist(remote.getPlaylist(),response);
		}
		
		//get information on current song
		else if(command != null && command.equals("poll")){
			toDo = CommandFactory.getInstance().getMediaCommandPoll(remote, null);
		}
		
		//set volume
		else if(command != null && command.equals("volume")){
			String volume = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandVolume(remote,volume);
		}
		
		//get information on current song
		else if(command != null && command.equals("setStream")){
			String mode = null;
			mode = request.getParameter("option");
			if( mode != null){
				if (mode.equals("0")) {
					remote.setStreamingMode(false);
					XMLprinter.printPort(Const.STREAMING_PORT,response);
					response.setStatus(HttpServletResponse.SC_OK);
				}else if (mode.equals("1")) {
					remote.setStreamingMode(true);
					XMLprinter.printPort(Const.STREAMING_PORT,response);
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
					response.getWriter().write("Streaming mode: no option");
				}
			} else {
				error = true;
				errorMessage = "Streaming mode: no option";
			}
		}

		//return not implemented
		else{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			response.getWriter().write("Method does not exist");
			System.out.println("Method does not exist");
		}
		
		if( toDo != null ){
			executer.execute(toDo, response);
		}
		
		//print errors
		if(error){
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			response.getWriter().write(errorMessage);
			System.out.println(errorMessage);
		}
	}

}
