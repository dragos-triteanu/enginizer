<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/chat-window.ftl" as chatRenderer />
<head>
<#include "*/includes.ftl">
    <link type="text/css" rel="stylesheet" href="./resources/libs/bootstrap-star-rating/css/star-rating.min.css"/>
    <link type="text/css" rel="stylesheet" href="./resources/css/chat.css"/>
</head>
<body ng-app="chatApp">
    <input id="userRole" type="hidden" value="${userRole}"/>
    <input id="userId" type="hidden" value="${currentUser.userId}"/>
    <input id="topicId" type="hidden" value="1" />
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="messenger"/>
    <div class="myOrderPage">
        <div class="container">
              <h1>Messenger</h1>
        </div>

        <@chatRenderer.renderChatWindow userRole />

    </div>
    <script src="resources/libs/sockjs/sockjs.min.js" type="text/javascript"></script>
    <script src="resources/libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    <script src="resources/libs/angular/angular.min.js"></script>
    <script src="resources/libs/lodash/dist/lodash.min.js"></script>
    <script src="resources/libs/bootstrap-star-rating/js/star-rating.min.js"></script>
    <script src="resources/script/angular/app.js" type="text/javascript"></script>
    <script src="resources/script/angular/chatController.js" type="text/javascript"></script>
    <script src="resources/script/angular/chatService.js" type="text/javascript"></script>
    <script src="resources/script/chat.js"></script>
</body>