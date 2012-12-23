define(['router/app-router', 'models/song' ,'collections/songs', 'views/songsView'], function(AppRouter,Song,Songs,SongsView) {
    new AppRouter();
    
    window.Song = Song;
    
    window.songs = new Songs();
    
    var songsView = new SongsView({collection:songs});
    
    $("#main").html(songsView.render().el);
    
});