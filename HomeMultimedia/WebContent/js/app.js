/*
 * Bienvenue sur le application de navigateur multimedia 
 * Coté javascript
 * 
 * @Author Thomas
 * @Status Developpment
 * (on peut appeler les fonctions directement dans la fonction avec app.someMethod)
 * 
**/

// on déclare l'application
app = {};

// chansons disponibles dans l'ordi a partir du root
app.songs = [];

// chansons dans la playlist
app.playlist = new Playlist();

// function qui download les chansons à partir du root
app.SongsDownload = function(handler){
	$.post('',{command:'getList'},function(data){
		var dataJSON = JSON.parse(data);
		dataJSON.list.song.forEach(function(el){
			app.songs.push(el);
		});
		if(typeof handler === 'function'){
			handler.apply(app);
		} else {console.log('handler in SongsDownload not a method !');};
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
		if(typeof handler === 'function'){
			handler.apply(app);
		} else {console.log('handler in SongsDownload not a method !');};
	});
};

// on affiche les chansons dans le champ prévu à cette effet
app.SongsView = function(){
	$("#playlist").html('');
	this.songs.forEach(function(el){
		// TODO remplacer le content par un template
		var content = "<li class='song' aria-songID='"+el.songID+"'><button class='btn btn-small addToPlaylist'><i class='icon-plus'></i></button>"+el.title+"</li>";
		$("#song-browser").append(content);
		$(content).click(function(){console.log(this);});
	});
	var addToPlaylist = function(e){
		// TODO envoyer la requete ajax au serveur pour l'ajouter.
		var el;
		// On vérifie que l'event vient du click sur le btn ou du dblclick
		if($(this).is('li')){
			el = $(this);
		}else{
			el = $(this).parent();
		}
		var songid = el.attr('aria-songID');
		console.log(songid);
		app.playlist.push({songID:songid},function(){
			app.PlaylistView();
		});
	};
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
		var content = "<li class='play'>"+elPlaylist.title+"</li>";
		$("#playlist").append(content);
	});
	$('.play').click(function(e){
		// TODO faire jouer la chanson
	});
};


$(document).ready(function(){
	app.SongsDownload(app.SongsView);
	app.PlaylistDownload(app.PlaylistView);
});