/*
 * Bienvenue sur le application de navigateur multimedia 
 * Cot� javascript
 * 
 * @Author Thomas
 * @Status Developpment
 * (on peut appeler les fonctions directement dans la fonction avec app.someMethod)
 * 
**/

// on d�clare l'application
app = {};

// chansons disponibles dans l'ordi a partir du root
app.songs = [];

// chansons dans la playlist
app.playlist = new Playlist();

// function qui download les chansons � partir du root
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
		$(".content_3").mCustomScrollbar({
	    	scrollInertia:0
	    });
	});
};

// on affiche les chansons dans le champ pr�vu � cette effet
app.SongsView = function(){
	$("#song-browser").html('');
	this.songs.forEach(function(el){
		// TODO remplacer le content par un template
		//var content = "<li class='song' aria-songID='"+el.songID+"'><button class='btn btn-small addToPlaylist'><i class='icon-plus'></i></button>"+el.title+"</li>";
		var content = "<tr class='song' aria-songID='"+el.songID+"'><td><button class='btn btn-small addToPlaylist'><i class='icon-plus'></i></button>"+el.title+"</td></tr>";
				
		$("#song-browser").append(content);
		$(content).click(function(){console.log(this);});
	});
	$('.song').dblclick(addToPlaylist);
	$('.addToPlaylist').click(addToPlaylist);
};

// idem pour la playlist
app.PlaylistView = function(){
	$("#playlist").html('');
	app.playlist.forEach(function(elPlaylist){
		app.songs.some(function(elSongs){
			elPlaylist.title = elSongs.title;
			return elPlaylist.songID == elSongs.songID;
		});
		// TODO remplacer le content par un template
		//var content = "<li class='play'>"+elPlaylist.title+"</li>";
		var content = "<tr class='play' aria-SongPlayListID='"+elPlaylist.SongPlayListID+"'><td>"+elPlaylist.title+"</td></tr>"
		$("#playlist").append(content);
	});
	$('.play').click(playSong);
};

// Fonction qui permet d'ajouter la chanson dans du navigateur dans la playlist
var addToPlaylist = function(){
	var el;
	// On v�rifie que l'event vient du click sur le btn ou du dblclick
	//if($(this).is('li')){
	if($(this).is('tr')){
		el = $(this);
	}else{
		el = $(this).parent().parent();
	}
	var songid = el.attr('aria-songID');
	$.post('',{command:'playlistadd',option:songid},function(){
		app.playlist.push({songID:songid},function(){
			app.PlaylistView();
		});
	});
};

//jouer une chanson
var playSong = function(){
	el=$(this);
	var SongPlayListID = el.attr('aria-SongPlayListID');
	$.post('',{command:'play',option:SongPlayListID},function(data){
		var songID = app.playlist.stack[SongPlayListID].songID;
		var imgUrl = app.songs[songID].poster;
		img=document.createElement('img');
		img.src=imgUrl;
		$("#artwork-container").html(img);
	});
};

// Ce qu'on fait au lancement de la page
// C'est i�i qu'on appele les fonctions du coup
$(document).ready(function(){
	app.SongsDownload(app.SongsView);
	//app.PlaylistDownload(app.PlaylistView);
	
});