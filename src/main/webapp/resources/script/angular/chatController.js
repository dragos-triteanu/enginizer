(function(angular) {
    angular.module("chatApp.controllers").controller("ChatCtrl", function($scope, ChatService) {
        $scope.messages = [];
        $scope.message = "";
        $scope.max = 140;
        $scope.userId = $("#userId").val();
        $scope.topicId = $("#topicId").val();


        init($scope,ChatService);

        $scope.addMessage = function() {
            ChatService.send({"content":$scope.message});
            $scope.message = "";
        };

        ChatService.receive().then(null, null, function(message) {
            $scope.messages.push(message);
        });

        $scope.fileChange = function(element) {
            var fd = new FormData();
            fd.append( 'uploadedFile', element.files[0]);
            fd.append('topicId',$scope.topicId);

            $.ajax({
                type: 'post',
                url: './api/service/file',
                data: fd,
                processData: false,
                contentType: false,
                xhrFields: {
                    // add listener to XMLHTTPRequest object directly for progress (jquery doesn't have this yet)
                    onprogress: function (progress) {
                        // calculate upload progress
                        var percentage = Math.floor(progress.total);
                        // log upload progress to console
                        console.log('progress', percentage);
                        if (percentage === 100) {
                            console.log('DONE!');
                        }
                    }
                }
            }).success(function(data){
                $("#fileUpload").val(null);
                var out = {};
                out.content = data.content;
                out.fileName = data.fileName;
                out.timestamp = data.timestamp;
                out.from  = data.from;
                out.location = data.location;
                out.topicId = $scope.topicId;
                ChatService.send(out);
            });
        };
    });
})(angular);


function init($scope,ChatService){
   ChatService.getMessages($scope);
}