$(function () {
    $('#showBack').click(function () {
        loadingShow();
        window.location.href = document.referrer;
        loadingHide();
    })
    $('#modifyArticle').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/Article/ModifyArticle/" + $(this).attr("name");
        loadingHide();
    })
    $('#deleteArticle').click(function () {
        loadingShow();
        $.ajax({
            url:window.location.origin + "/Article/delete",
            type: "POST",
            data:{
                articleId:window.location.href.substring(window.location.href.lastIndexOf("/")+1)
            },
            dataType: "JSON",
            success: function (data) {
                if (data.success) {
                    window.location.href = document.referrer;
                } else {
                    alert(data.msg);
                }
                loadingHide();
            }
        })
    })
})
function searchByAuthorId(search) {
    loadingShow();
    window.location.href = window.location.origin + "/Article/" + $(search).attr("name");
    loadingHide();
}
function submit_reply_button(reply) {
    if($('#ReplyToArticle').val() != "") {
        loadingShow();
        $.ajax({
            url: window.location.origin + "/Reply/AddReply",
            type: "POST",
            data: {
                replyGrade: 0,
                articleId: $('#articleId').attr("name"),
                replyContent: $('#ReplyToArticle').val(),
                forUsersId: $('#user-name-label').attr("name")
            },
            dataType: "JSON",
            success: function (data) {
                if (data.success) {
                    $('#replyDiv').append(
                        '<div class="replyToArticle" name="'+ data.replyId + '">\n' +
                        '   <div class="show-div">'+ $('#user-name-label').text()+':'+$('#ReplyToArticle').val()+'</div>\n' +
                        '   <div name="replyToReplyShow">\n' +
                        '   </div>\n' +
                        '   <div class="replyToReplyInput" name="'+ $('#user-name-label').attr("name") + '">\n' +
                        '       <input type="text" name="ReplyToReply" placeholder="不超过100字" maxlength="100">\n' +
                        '       <i class="iconfont icon-huiche" onclick="submit_reply_i(this)"></i>\n' +
                        '   </div>\n' +
                        '</div>');
                    $('#ReplyToArticle').val("");
                } else {
                    alert(data.msg);
                }
                loadingHide();
            }
        })
    }
}
function submit_reply_i(reply) {
    if($(reply).parent().children('input').val() != "") {
        loadingShow();
        $.ajax({
            url: window.location.origin + "/Reply/AddReply",
            type: "POST",
            data: {
                replyGrade: $(reply).parent().parent().attr("name"),
                articleId: $('#articleId').attr("name"),
                replyContent: $(reply).parent().children('input').val(),
                forUsersId: $(reply).parent().attr("name")
            },
            dataType: "JSON",
            success: function (data) {
                if (data.success) {
                    $(reply).parent().parent().children('div[name="replyToReplyShow"]').append(
                        '<div class="replyToReply">\n' +
                        '                            <div class="show-div">' + $('#user-name-label').text() + ':' + $(reply).parent().children('input').val() + '</div>\n' +
                        '                        </div>');
                    $(reply).parent().children('input').val("");
                } else {
                    alert(data.msg);
                }
                loadingHide();
            }
        })
    }
}
function inputReplyToReply(ReplyToReply) {
    if (event.keyCode == "13") {
        $(ReplyToReply).parent().children('i').click();
    }
}