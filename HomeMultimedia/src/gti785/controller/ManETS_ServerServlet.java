package gti785.controller;

import gti785.command.ExecuteCommand;
import gti785.command.MediaCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MultiServlet
 */
public class ManETS_ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static Push server ; 
	private ExecuteCommand executer = null;
	
    /**
     * Default constructor. Instanciates objects artwork, mediaFolder and remote
     */
    public ManETS_ServerServlet() {
    	/*try {
    		server = new Push();
    		server.start();
		} catch (Exception e) {
			e.printStackTrace();
		} */
    	
    	executer = new ExecuteCommand();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/*RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/index.html");
		dispatch.forward(request, response);*/
MediaCommand toDo = null;
		
		String command = null;
		command = request.getParameter("command");
		
		//get all media list
		if(command != null && command.equals("getList")){
			toDo = CommandFactory.getInstance().getMediaCommandGetList("");
		}
		
		//play song
		else if(command != null && command.equals("play")){
			String id = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlay(id);
		}
		
		//pause song
		else if(command != null && command.equals("pause")){
			toDo = CommandFactory.getInstance().getMediaCommandPause("");
		}
		
		//stop song
		else if(command != null && command.equals("stop")){
			toDo = CommandFactory.getInstance().getMediaCommandStop("");
			System.out.println("Song stopped");
		}
		
		//add song to playlist
		else if(command != null && command.equals("playlistadd")){
			String idSong = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistAdd(idSong);
		}
		
		//remove song from playlist
		else if(command != null && command.equals("playlistremove")){
			String idPlaylist = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistRemove(idPlaylist);
		}
		
		//play next song
		else if(command!=null && command.equals("next")){
			toDo = CommandFactory.getInstance().getMediaCommandNext("");
		}
		
		//play previous song
		else if(command!=null && command.equals("previous")){
			toDo = CommandFactory.getInstance().getMediaCommandPrevious("");
		}
		
		//toBeginning
		else if(command!=null && command.equals("toBeginning")){
			toDo = CommandFactory.getInstance().getMediaCommandToBeginning("");
		}
		
		//shuffle play list
		else if(command != null && command.equals("shuffle")){
			toDo = CommandFactory.getInstance().getMediaCommandShuffle("");
		}
		
		//repeat action
		else if(command != null && command.equals("repeat")){
			String mode = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandRepeat(mode);
		}
		
		//print play list songs
		else if(command != null && command.equals("getPlayList")){
			toDo = CommandFactory.getInstance().getMediaCommandPrintPlaylist("");
		}
		
		//get information on current song
		else if(command != null && command.equals("poll")){
			toDo = CommandFactory.getInstance().getMediaCommandPoll("");
		}
		
		//set volume
		else if(command != null && command.equals("volume")){
			String volume = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandVolume(volume);
		}
		
		//OpenFolder
		else if(command != null && command.equals("openFolder")){
			String file = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandOpenFolder(file);
		}
		
		//UpFile
		else if(command != null && command.equals("upFile")){
			toDo = CommandFactory.getInstance().getMediaCommandUpFile("");
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*MediaCommand toDo = null;
		
		String command = null;
		command = request.getParameter("command");
		//System.out.println(command);
		
		//get all media list
		if(command != null && command.equals("getList")){
			toDo = CommandFactory.getInstance().getMediaCommandGetList(contexte.getMediaFolder(), "");
		}
		
		//play song
		else if(command != null && command.equals("play")){
			String id = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlay(contexte.getRemote(), id);
		}
		
		//pause song
		else if(command != null && command.equals("pause")){
			toDo = CommandFactory.getInstance().getMediaCommandPause(contexte.getRemote(), "");
		}
		
		//stop song
		else if(command != null && command.equals("stop")){
			remote.stop();
			System.out.println("Song stopped");
		}
		
		//add song to playlist
		else if(command != null && command.equals("playlistadd")){
			String idSong = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistAdd(contexte.getRemote(), idSong);
		}
		
		//remove song from playlist
		else if(command != null && command.equals("playlistremove")){
			String idPlaylist = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandPlaylistRemove(contexte.getRemote(), idPlaylist);
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
			toDo = CommandFactory.getInstance().getMediaCommandShuffle(contexte.getRemote(), "");
		}
		
		//repeat action
		else if(command != null && command.equals("repeat")){
			String mode = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandRepeat(contexte.getRemote(), mode);
		}
		
		//print play list songs
		else if(command != null && command.equals("getPlayList")){
			toDo = CommandFactory.getInstance().getMediaCommandPrintPlaylist(contexte.getRemote(), "");
		}
		
		//get information on current song
		else if(command != null && command.equals("poll")){
			toDo = CommandFactory.getInstance().getMediaCommandPoll(contexte.getRemote(), "");
		}
		
		//set volume
		else if(command != null && command.equals("volume")){
			String volume = request.getParameter("option");
			toDo = CommandFactory.getInstance().getMediaCommandVolume(contexte.getRemote(),volume);
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
		
		try{
			if( toDo != null ){
				executer.execute(toDo, response);
			}
		}
		catch(Exception e){//print errors
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			response.getWriter().write(e.getMessage());
			System.out.println(e.getMessage());
		}*/
	}

}
