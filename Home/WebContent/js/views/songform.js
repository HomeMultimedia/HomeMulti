define(['backbone','resthub','hbs!template/songform'],function(Backbone,Resthub,songFormTemplate){
	var SongFormView = Resthub.View.extend({
		template:songFormTemplate,
		tagName:'form',
		events:{
			'click .cancel':'cancel',
			'click .save':'submit'
		},
		cancel: function(){
			this.model.trigger('change');
		},
		submit:function(){
			this.model.set({
				title: $('.title').val(),
				album: $('.album').val(),
			});
		}
	});
	
	return SongFormView;
});