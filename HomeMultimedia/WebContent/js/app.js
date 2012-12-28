/*
 * Bienvenue sur le application de navigateur multimedia 
 * Cote javascript
 * 
 * @Author Thomas & Cedric
 * @Status Developpment
 * (on peut appeler les fonctions directement dans la fonction avec app.someMethod)
 * 
**/

// on declare l'application
app = {};

// chansons disponibles dans l'ordi a partir du root
app.songs = [];

// chansons dans la playlist
app.playlist = new Playlist();

// function qui download les chansons à partir du root
app.SongsDownload = function(handler){
	$.post('',{command:'getList'},function(data){
		var dataJSON = JSON.parse(data);
		dataJSON.media.list.song.forEach(function(el){
			app.songs.push(el);
		});
		if(typeof(handler) == 'function'){
			handler.apply(app);
		}
		app.PlaylistDownload(app.PlaylistView);
		// TODO donner un nom compréhensible
		$(".content_2").mCustomScrollbar({
	    	scrollInertia:0
	    });
	});
};

// idem mais pour le download de la playlist
app.PlaylistDownload = function(handler){
	$.post('',{command:'getPlayList'},function(data){
		var dataJSON = JSON.parse(data);
		if(dataJSON.list){
			dataJSON.list.playlist.forEach(function(el){
				app.playlist.push(el);
			});
		}
		if(typeof(handler) == 'function'){
			handler.apply(app);
		}
		// TODO donner un nom compréhensible
		$(".content_3").mCustomScrollbar({
	    	scrollInertia:0
	    });
	});
};

// on affiche les chansons dans le champ prevu a cette effet
app.SongsView = function(){
	$("#song-browser tbody").html('');
	this.songs.forEach(function(el){
		// TODO remplacer le content par un template
		var content = "<tr aria-songID='"+el.songID+"'>" +
				"<td class='addToPlaylist'><button class='btn btn-small'><i class='icon-plus'></i></button></td>" +
				"<td class='song'>"+el.title+"</td></tr>";
				
		$("#song-browser tbody").append(content);
	});
	$('.song').dblclick(addToPlaylist);
	$('.addToPlaylist').click(addToPlaylist);
};

// idem pour la playlist
app.PlaylistView = function(){
	$("#playlist tbody").html('');
	app.playlist.stack.forEach(function(elPlaylist,indexPlaylist){
		app.songs.some(function(elSongs){
			elPlaylist.title = elSongs.title;
			return elPlaylist.songID == elSongs.songID;
		});
		// TODO remplacer le content par un template
		var playOrPauseButton;
		if(elPlaylist.status == 'play'){
			playOrPauseButton = "<td class='pause'><button class='btn btn-small'><i class='icon-pause'></i></button></td>";
		} else {
			playOrPauseButton = "<td class='play'><button class='btn btn-small'><i class='icon-play'></i></button></td>";
		}
		var content = "<tr aria-SongPlayListID='"+indexPlaylist+"'>" +
				"<td class='remove'><button class='btn btn-small'><i class='icon-remove'></i></button></td>" +
				"<td class='title'>"+elPlaylist.title+"</td>" +
				playOrPauseButton + "</tr>";
		$("#playlist tbody").append(content);
	});
	$('.pause').click(pauseSong);
	$('.play').click(playSong);
	// TODO gerer le double click en fonction du status de la chanson
	$('.play').dblclick(playSong);
	$('.remove').click(removeSongFromPlaylist);
};

// Fonction qui permet d'ajouter la chanson dans du navigateur dans la playlist
var addToPlaylist = function(){
	var el = $(this);
	var songid = el.parent().attr('aria-songID');
	$.post('',{command:'playlistadd',option:songid},function(){
		app.playlist.push({songID:songid},function(){
			app.PlaylistView();
		});
	});
};

//jouer une chanson
var playSong = function(){
	el=$(this);
	var SongPlayListID = el.parent().attr('aria-SongPlayListID');
	$.post('',{command:'play',option:SongPlayListID},function(data){
		// TODO Faire une vue de la chanson actuelle (avec album et cie)
		//On affiche la vue du poster
		var songID = app.playlist.stack[SongPlayListID].songID;
		var imgUrl = app.songs[songID].poster;
		img=document.createElement('img');
		img.src=imgUrl;
		$("#artwork-container").html(img);
		// On update le status de la chanson concernée et on refait la vue
		app.playlist.stack[SongPlayListID].status ='play';
		app.PlaylistView();
	});
};

//pauser une chanson
var pauseSong = function(){
	var el = $(this);
	var SongPlayListID = el.parent().attr('aria-SongPlayListID');
	$.post('',{command:'pause',option:SongPlayListID},function(data){
		app.playlist.stack[SongPlayListID].status = 'pause';
		app.PlaylistView();
	});
};

//enlever une chanson de la playlist
var removeSongFromPlaylist = function(){
	var el = $(this);
	var SongPlayListID = el.parent().attr('aria-SongPlayListID');
	$.post('',{command:'playlistremove',option:SongPlayListID},function(data){
		app.playlist.splice(SongPlayListID,1,function(){
			app.PlaylistView();
		});
	});
};

// Ce qu'on fait au lancement de la page
// C'est ici qu'on appele les fonctions du coup
$(document).ready(function(){
	app.SongsDownload(app.SongsView);
	//app.PlaylistDownload(app.PlaylistView);
	
});