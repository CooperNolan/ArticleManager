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
    $('#categoryManager').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/Category/manager";
        loadingHide();
    });
    $('#logout').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/User/logout";
        loadingHide();
    });
    //textarea支持tab缩进
    $("textarea").on(
        'keydown',
        function (e) {
            if (e.keyCode == 9) {
                e.preventDefault();
                var indent = '    ';
                var start = this.selectionStart;
                var end = this.selectionEnd;
                var selected = window.getSelection().toString();
                selected = indent + selected.replace(/\n/g, '\n' + indent);
                this.value = this.value.substring(0, start) + selected
                    + this.value.substring(end);
                this.setSelectionRange(start + indent.length, start
                    + selected.length);
            }
        })
});

function loadingShow() {
    $("#loading").show();
    $(".full-screen-coverage").show();
}

function loadingHide() {
    $("#loading").hide();
    $("#full-screen-coverage").hide();
}

function getURI() {
    var uri = window.location.href.substring(window.location.origin.length + 1);
    var i = uri.indexOf("?");
    if (i != -1) {
        uri = uri.substring(0, i);
    }
    return window.location.origin + "/" + uri;
}