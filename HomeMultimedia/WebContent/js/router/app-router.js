define(['backbone', 'backbone-queryparams'], function(Backbone) {

    var AppRouter = Backbone.Router.extend({
        
        initialize: function() {
            Backbone.history.start({ pushState: true, root: "/" });
        },
        
        routes: {
            '': 'main'
        },
        
        main: function() {
            console.debug("Main route activated");
        }
    });
    
    return AppRouter;

});
