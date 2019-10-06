$(function () {
    $('#toHome').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/Home";
        loadingHide();
    });
    $('#userMsg').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/User/ModifyUser";
        loadingHide();
    });
    $('#myArticle').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/Article/" + $('#user-name-label').attr('name');
        loadingHide();
    });
    $('#writeArticle').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/Article/WriteArticle";
        loadingHide();
    });
    $('#logout').click(function () {
        loadingShow();
        $.ajax({
            url:window.location.origin + "/logout",
            type:"post",
            dataType:"JSON",
            success: function (data) {
                if (data.success) {
                    window.location.href = window.location.origin + "/" + data.url;
                } else {
                    window.location.href = window.location.origin + "/Login";
                }
                loadingHide();
            }
        })
    });
});

function loadingShow() {
    $("#loading").show();
    $(".full-screen-coverage").show();
}
function loadingHide() {
    $("#loading").hide();
    $("#full-screen-coverage").hide();
}
