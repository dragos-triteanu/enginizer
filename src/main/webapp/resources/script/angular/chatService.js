(function(angular, SockJS, Stomp, _, undefined) {
    angular.module("chatApp.services").service("ChatService", function($q, $timeout,$http) {

        var service = {}, listener = $q.defer(), socket = {
            client: null,
            stomp: null
        },topicId = $("#topicId").val(),
          from =   $("#userId").val();

        service.RECONNECT_TIMEOUT = 30000;
        service.SOCKET_URL = "/chat";
        service.CHAT_TOPIC = "/topic/message/" + topicId;
        service.CHAT_BROKER = "/app/chat";

        service.receive = function() {
            return listener.promise;
        };

        service.send = function(message) {
            socket.stomp.send(service.CHAT_BROKER, {
                priority: 9
            }, JSON.stringify({
                topicId:topicId,
                from:from,
                content: message.content,
                timestamp: new Date(),
                location:message.location,
                fileName:message.fileName
            }));
        };

        var reconnect = function() {
            $timeout(function() {
                initialize();
            }, this.RECONNECT_TIMEOUT);
        };

        var getMessage = function(data) {
            var message = JSON.parse(data), out = {};
            out.content = message.content;
            out.timestamp = new Date(message.timestamp);
            out.from  = message.from;
            out.topicId = message.topicId;
            out.fileName = message.fileName;
            out.location = message.location;
            return out;
        };

        service.getMessages = function($scope){
           $http.get("./messenger/allMessages").then(function successCallback(response){

               for(var i=0; i< response.data.length; i++){
                   var date = new Date(response.data[i].timestamp);
                   response.data[i].timestamp = date;
               }

               $scope.messages = response.data;

           }, function errorCallback(response){
                console.log(response);
           });
        };

        var startListener = function() {
            socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
                listener.notify(getMessage(data.body));
            });
        };

        var initialize = function() {
            socket.client = new SockJS(service.SOCKET_URL);
            socket.stomp = Stomp.over(socket.client);
            socket.stomp.connect({}, startListener);
            socket.stomp.onclose = reconnect;
        };

        initialize();
        return service;
    });
})(angular, SockJS, Stomp, _);