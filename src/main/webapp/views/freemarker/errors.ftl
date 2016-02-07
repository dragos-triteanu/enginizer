<!DOCTYPE html>
<html>
<head>
    <#import "*/components/errorBox.ftl" as errorBox/>
    <link rel="stylesheet" type="text/css" href="../../../resources/libs/bootstrap/dist/css/bootstrap.min.css" />
</head>
<body>

<div class="container" style="text-align: center;">
    <div class="row">
        <div class="col-md-12">
            <h2>ERROR!<h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
        <@errorBox.renderErrors errors />
        </div>
    </div>
    <div class="row">
        <p >Please use the back button to get out of this mess!</p>
    </div>
</div>
</body>
</html>