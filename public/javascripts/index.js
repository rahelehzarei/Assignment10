(function(){

   var ws = new WebSocket($("body").data("ws-url"));

   ws.onmessage = function(event){
       var message = JSON.parse(event.data);
       $("time").append(message.time + "<br/>");
   };

})();