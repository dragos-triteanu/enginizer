/**
 * Created by Dragos on 10/7/2015.
 */
$(document).ready(function() {
    $(document).delegate("#btnHide","click",function(e){
         $("#openChat").hide();
         $("#minimizeChat").show();
    });
    $(document).delegate("#btnShow","click",function(e){
        $("#openChat").show();
        $("#chatBody").scrollTop($("#chatBody")[0].scrollHeight);
        $("#minimizeChat").hide();
    });

    $('#chatBody').bind("DOMSubtreeModified",function(){
        $("#chatBody").scrollTop($("#chatBody")[0].scrollHeight);
    });
});
