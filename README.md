# Simple AMQP Example
I put this together ti give a quick example of using amqp to send messages. To
run this example, startup console.js and app.js and navigate to
http://localhost:3000. Text entered in the input will be sent to the server via
websocket and published on an exchange with the routing key key.a.b, which
console.js will pick up, dump to the console, and reply by reversing the string
and publishing it on the same exchange with the routing key key.b.a, which
app.js will pick up and display to the user via websocket. 

Oh yeah, you should probably have rabbitmq up and running but ideally this
should work with any amqp broker. :)


