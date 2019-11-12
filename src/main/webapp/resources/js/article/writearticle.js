$(function () {
    $("#topic").on("blur", function () {
        onblurTopic();
    });
    $('#Submit').click(function () {
        if (onblurTopic()) {
            loadingShow();
            $.ajax({
                url: window.location.origin + "/Article/AddArticle",
                type: "post",
                data: {
                    articleTopic: $('#topic').val(),
                    categoryId: $('#category').val(),
                    articleContent: $('#content').val()
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        window.location.href = window.location.origin + "/" + data.url;
                    } else {
                        $('#prompt').html(data.msg);
                        loadingHide();
                    }
                }
            })
        }
    });
    $('#Modify').click(function () {
        if (onblurTopic()) {
            loadingShow();
            $.ajax({
                url: window.location.origin + "/Article/modifyArticle",
                type: "POST",
                data: {
                    articleId: window.location.href.substring(window.location.href.lastIndexOf("/") + 1),
                    articleTopic: $('#topic').val(),
                    categoryId: $('#category').val(),
                    articleContent: $('#content').val()
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        window.location.href = window.location.origin + "/" + data.url;
                    } else {
                        $('#prompt').html(data.msg);
                        loadingHide();
                    }
                }
            })
        }
    })
});

function onblurTopic() {
    if ($('#topic').val() == "") {
        $('#prompt').html("文章题目不能为空！");
        return false;
    } else {
        $('#prompt').html("&nbsp;");
        return true;
    }
}