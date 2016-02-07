<#macro renderChatWindow userRole>
    <div class="chatContainer" ng-controller="ChatCtrl">
        <section id="openChat" class="module col-md-5 col-md-offset-7" style="display: none">
            <header class="top-bar">
                <div class="left">
                    <h1>Chat</h1>
                </div>
                <div class="right">
                    <button id="btnHide" type="button" class="btn-chat btn-xs">
                        <span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
                    </button>
                </div>
                <div class="right">
                    <div class="fileUploadContainer">
                        <label for="fileUpload">
                            <span class="glyphicon glyphicon-upload"></span>
                        </label>
                        <input id="fileUpload" ng-model="uploadedFile" onchange="angular.element(this).scope().fileChange(this)" type="file" class="fileUpload" name="uploadedFile"/>
                    </div>
                </div>
            </header>
                <div id="chatBody">
                    <p ng-repeat="message in messages | orderBy:'time':reverse" class="message">
                        <time class="chatTime">{{message.timestamp | date:'HH:mm'}}</time>
                        {{message.from == userId ? 'me:' : 'C' + message.from + ':'}}
                        <span ng-if="message.location != null">
                           <a href="{{message.location}}"><span ng-class="{self: message.self}">{{message.fileName}}</span></a>
                        </span>
                        <span ng-if="message.location == null">
                            <span>{{message.content}}</span>
                        </span>
                    </p>
                </div>
            <div id="chatFooter">
                <form ng-submit="addMessage()" name="messageForm">
                    <div class="input-group">
                        <input type="text" placeholder="Introduceti mesaj..." class="form-control no-border-radius" ng-model="message" />
                            <span class="input-group-btn">
                                <button class="btn btn-default no-border-radius" type="submit" type="button">
                                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                                </button>
                            </span>
                    </div>
                </form>
            </div>
        </section>
        <section id="minimizeChat" class="module col-md-5 col-md-offset-7 display-none" style="display: block">
            <header class="top-bar minimize-header">
                <div class="left">
                    <h1>Chat</h1>
                </div>
                <div class="right">
                    <button id="btnShow" type="button" class="btn-chat btn-xs ">
                        <span class="glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                    </button>
                </div>
            </header>
        </section>
    </div>
</#macro>