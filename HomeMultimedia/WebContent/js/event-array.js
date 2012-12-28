// Définition de l'objet playlist : permet de update les index des chansons de la playlist

// inspiré de :
// http://stackoverflow.com/questions/5306843/javascript-attach-event-listener-to-array-for-push-event
function Playlist(){
	this.stack = [];
	
	// On reimplémente les fonctions de Array
	// https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Array
	this.push = function(obj,handler){
		this.stack.push(obj);
		if(typeof handler === 'function'){
			handler.apply(this);
		}
	};
	
	// le splice (enlever des membres de l'array)
	this.splice = function(index,howMany,handler){
		this.stack.splice(index, howMany);
		if(typeof handler === 'function'){
			handler.apply(this);
		}
	};
}