// Définition de l'objet playlist : permet de update les index des chansons de la playlist

// inspiré de :
// http://stackoverflow.com/questions/5306843/javascript-attach-event-listener-to-array-for-push-event
function Playlist(){
	this.stack = [];
	
	// On reimplémente les fonctions de Array
	// https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Array
	this.push = function(obj,handler){
		if(!obj.SongPlayListID){
			obj.SongPlayListID = this.stack.length;
		}
		this.stack.push(obj);
		if(typeof handler === 'function'){
			handler.apply(this);
		}
	};
	
	/* Pas d'utilisation pour l'instant
	this.pop = function(handler){
		if(typeof handler === 'function'){
			handler.apply(this);
		}
		return this.stack.pop();
	};
	*/
	
	// implémentation du forEach
	// http://stackoverflow.com/questions/10466436/javascript-foreach-implementation
	// on bind(this.stack) pour pouvoir reprendre le code en entier
	this.forEach = function(fun){
		var len = this.length;
		if(typeof fun != 'function'){
			throw new TypeError();
		}
		
		var thisp = arguments[1];
		for(var i =0; i<len;i++){
			if(i in this){
				fun.call(thisp,this[i],i,this);
			}
		}
	}.bind(this.stack);
}