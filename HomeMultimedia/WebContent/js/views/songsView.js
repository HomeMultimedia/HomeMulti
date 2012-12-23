define(['backbone','resthub','views/songView','hbs!template/songs'],function(Backbone,Resthub,SongView,SongsTemplate){
	var SongsView = Resthub.View.extend({
		template:SongsTemplate,
		events:{
			'click #chargement':'create'
		},
		
		initialize:function(){
			this.collection.on('add',this.add, this);
		},
		
		render:function(){
			SongsView.__super__.render.apply(this);
			this.collection.forEach(this.add,this);
			return this;
		},
		
		add:function(song){
			var songView = new SongView({root: this.$('.song-list'),model: song});
			songView.render();
		},
		
		create:function(){
			$.post("",{command:'getList'},function(data){
				var donnee = JSON.parse(data);
				this.collection.add(donnee.list.song);
    		}.bind(this));
		}
		
	});
	
	return SongsView;
	
});