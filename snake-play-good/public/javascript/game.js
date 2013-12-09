$(function() {
    var sURL = unescape(window.location.pathname);
    var socket = new WebSocket("ws://localhost:9000/socket");
    socket.onmessage = function(msg) {
        window.location.href = sURL;
    };
    setTimeout(function() {socket.send("ceva");},3350);
});
