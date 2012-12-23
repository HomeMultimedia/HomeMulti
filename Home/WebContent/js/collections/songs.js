define(['backbone','models/song'],function(Backbone,Song){
	var Songs=Backbone.Collection.extend({model:Song});
	
	return Songs;
});