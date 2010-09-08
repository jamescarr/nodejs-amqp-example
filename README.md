# Simple AMQP Example
I put this together to give a quick example of using amqp to interact with a java based program. To run this example, first start up the node server under ./node
    node app.js

From there, run StockProcessor to listen for stock tickers. Navigate to http://localhost:3000/ and try GOOG or IBM. 

You can also run RandomPriceRunner, which will publish a random price every 500 milliseconds and will update in realtime in the view. 

Explore the code and have fun. :)

## Requirements
I use gradle to build the java classes... for the presentation I ran gradle eclipse and ran the main classes from eclipse. 

To gather the dependencies for the node portion of the app, just go to the node directory and type npm install . (you'll need to have npm from http://www.npmjs.org).

Oh yeah, you should probably have rabbitmq up and running but ideally this
should work with any amqp broker. :)


