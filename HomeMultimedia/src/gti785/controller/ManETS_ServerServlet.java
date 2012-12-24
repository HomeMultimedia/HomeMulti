package gti785.controller;

import gti785.command.ExecuteCommand;
import gti785.command.MediaCommand;
import gti785.model.MediaFolder;
import gti785.push.Push;
import gti785.remote.ETSRemote;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MultiServlet
 */
public class ManETS_ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ETSRemote remote;
	
	private MediaFolder mediaFolder;
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
		MediaCommand toDo = null;
		
		String command = null;
		command = request.getParameter("command");
		//System.out.println(command);
		
		//get all media list
		if(command != null && command.equals("getList")){
			toDo = CommandFactory.getInstance().getMediaCommandGetList(mediaFolder, "");
		}
		
		//play song
		else if(command != null && command.equals("play")){
			String id = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlay(remote, id);
		}
		
		//pause song
		else if(command != null && command.equals("pause")){
			toDo = CommandFactory.getInstance().getMediaCommandPause(remote, "");
		}
		
		//stop song
		else if(command != null && command.equals("stop")){
			remote.stop();
			System.out.println("Song stopped");
		}
		
		//add song to playlist
		else if(command != null && command.equals("playlistadd")){
			String idSong = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistAdd(remote, idSong);
		}
		
		//remove song from playlist
		else if(command != null && command.equals("playlistremove")){
			String idPlaylist = request.getParameter("option");
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
			toDo = CommandFactory.getInstance().getMediaCommandShuffle(remote, "");
		}
		
		//repeat action
		else if(command != null && command.equals("repeat")){
			String mode = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandRepeat(remote, mode);
		}
		
		//print play list songs
		else if(command != null && command.equals("getPlayList")){
			toDo = CommandFactory.getInstance().getMediaCommandPrintPlaylist(remote, "");
		}
		
		//get information on current song
		else if(command != null && command.equals("poll")){
			toDo = CommandFactory.getInstance().getMediaCommandPoll(remote, "");
		}
		
		//set volume
		else if(command != null && command.equals("volume")){
			String volume = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandVolume(remote,volume);
		}
		
		//get information on current song
		/*else if(command != null && command.equals("setStream")){
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
		}*/

		//return not implemented
		else{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			response.getWriter().write("Method does not exist");
			System.out.println("Method does not exist");
		}
		
		try{
			if( toDo != null ){
				executer.execute(toDo, response);
			}
		}
		catch(Exception e){//print errors
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			response.getWriter().write(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

}
