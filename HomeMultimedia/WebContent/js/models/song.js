define(['backbone'],function(Backbone){
	var Song = Backbone.Model.extend({
		defaults:{
			playlist: false
		},
		initialize: function(){
			
		}
	});
	
	return Song;
});