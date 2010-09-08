var amqp = require('amqp')
var sys = require('sys')

console.log("starting up commandline amqp subscriber / producer")

var connection = amqp.createConnection({ host: 'localhost' });

connection.addListener('ready', function(){
  var queue = connection.queue('commandline-queue')
  // create the exchange if it doesnt exist
  var exchange = connection.exchange('some-exchange')
  queue.bind("some-exchange", "key.a.b");

  queue.subscribe( {ack:true}, function(message){
    console.log("received message")
    console.log(sys.inspect(message))
    console.log("===========================")
    exchange.publish("key.b.a", reverse(message.data.toString()));
    queue.shift()
  });

});

function reverse(str){
  return str.split('').reduce(function(a,b){ return b+a}, "")
}

