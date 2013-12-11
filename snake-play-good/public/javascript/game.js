var socket = new WebSocket("ws://localhost:9000/socket");
    socket.onmessage = function(msg) {
      refreshBoard();
    };
$(document).keypress(function (e) {
    var keycode = (e.keyCode ? e.keyCode : e.which);
    if(keycode == '100'){ socket.send("d");} else
    if (keycode == '97'){ socket.send("a");} else
    if (keycode == '115'){ socket.send("s");} else
    if (keycode == '119'){ socket.send("w");} else {socket.send("c");}
});

var refreshBoard = function() {
    $("#wrapper").load("/ #snakeContainer");

}