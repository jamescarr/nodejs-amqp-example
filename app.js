require.paths.unshift(__dirname+"/lib/")
var app = require('appserver').createServer()
var io = require('socket.io')
var amqp = require('amqp')


app.get('/', function(req, res){
    res.render('index.jade', {
        locals: {
            title: 'RabbitMQ Demo'
        }
    });
});


var connection = amqp.createConnection({ host: 'localhost' });
 
var sys = require('sys')
connection.addListener('ready', function(){
  var exchange = connection.exchange('myExchange', {type:'direct', durable:true, autoDelete: false});
  var queue = connection.queue('for-node')
  queue.bind('java', 'testRoute')
  
  app.listen(3000, function(){
    console.log('listening for connections on port 3000')
    var socket = io.listen(app);
     
    socket.on('connection', function(client){
      client.on('message', function(msg){
        exchange.publish("forJava", msg);
      })
      client.on('disconnect', function(){
      })
      queue.subscribe( {ack:true}, function(message){
        client.send(message.data.toString())
        queue.shift()
      })
    })
    
  });
});

