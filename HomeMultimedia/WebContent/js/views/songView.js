define(['backbone','resthub','hbs!template/song','views/songform'],function(Backbone,Resthub,songTemplate,SongFormView){
	var SongView = Resthub.View.extend({
		
		template:songTemplate,
		tagName:'li',
		className:'song',
		strategy:'append',
		
		events:{
			click:'toggleDetails',
			dblclick:'edit'
		},
		
		initialize: function(){
			this.model.on('change',this.render,this);
		},
		
		render:function(){
			this.$el.fadeOut(function(){
				SongView.__super__.render.apply(this);
				this.$el.fadeIn();
			}.bind(this));
			return this;
		},
		
		toggleDetails:function(){
			this.$('p').slideToggle();
		},
		
		edit:function(){
			var songFormView = new SongFormView({root:this.$el,model:this.model});
			songFormView.render();
		}
		
	});
	
	return SongView;
});