var socket = new WebSocket("ws://localhost:9000/socket");
$(function() {
    socket.onmessage = function(msg) {
        alert("da")
    };

    setTimeout(function() {socket.send("ceva");},3350);
});
$(document).keypress(function (e) {
    var keycode = (e.keyCode ? e.keyCode : e.which);
    if(keycode == '100'){ alert("d")}

});