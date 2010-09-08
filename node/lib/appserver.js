var express = require('express'),
    connect = require('connect');

exports.createServer = function(){
  var app = express.createServer();

  app.configure(function(){
      app.set('views', __dirname + '/../views');
      app.use(connect.bodyDecoder());
      app.use(connect.methodOverride());
      app.use(connect.compiler({ src: __dirname + '/../public', enable: ['less'] }));
      app.use(app.router);
      app.use(connect.staticProvider(__dirname + '/../public'));
  });

  app.configure('development', function(){
      app.use(connect.errorHandler({ dumpExceptions: true, showStack: true })); 
  });

  app.configure('production', function(){
     app.use(connect.errorHandler()); 
  });

  return app;
}

