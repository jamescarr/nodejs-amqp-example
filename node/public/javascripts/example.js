$(function(){
  var socket = new io.Socket('localhost')
  socket.connect();
  
  $('#send').click(function(){
    socket.send(getMessageToSend())
  })
  
  socket.on('message', function(data){
    $('#console').text("[RECEIVED] " + data)
  })
  
  
})


function getMessageToSend(){
  var messageBox = $('input[name="message"]');
  var text = messageBox.val()
  messageBox.val('')
  return text
}
