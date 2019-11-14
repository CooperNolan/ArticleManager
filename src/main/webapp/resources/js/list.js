$(function () {
    $('#search').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            searchArticle();
        }
    });
    $('.deleteIcon').hover(function () {
        $(this).removeClass("icon-shanchu1");
        $(this).addClass("icon-shanchusuoxuan");
    }, function () {
        $(this).removeClass("icon-shanchusuoxuan");
        $(this).addClass("icon-shanchu1");
    });
    $('#Pagination input').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            if (/^\d+$/.test($('#Pagination input').val())) {
                if ($('#Pagination input').val() <= $('#Pagination input').attr('name') && $('#Pagination input').val() > 0) {
                    loadingShow();
                    paginationAccess($('#Pagination input').val());
                    loadingHide();
                }
            }
        }
    });
    $('#category').on('change', function () {
        loadingShow();
        var uri = getURI() + "?category=" + $('#category').val();
        window.location.href = uri;
        loadingHide();
    });
});

function iconOnClick(i) {
    loadingShow();
    window.location.href = window.location.origin + "/Article/ShowArticle?articleId=" + $(i).attr("name");
    loadingHide();
}

function searchArticle() {
    loadingShow();
    var uri = getURI();
    if (uri.match("^.*Home$")) {
        uri = window.location.origin + "/Article/Search";
    }
    uri = uri + "?category=" + $('#category').val() + "&search=" + $('#search').val().trim();
    window.location.href = uri;
    loadingHide();
}

function deleteIconOnClick(i) {
    loadingShow();
    $.ajax({
        url: window.location.origin + "/Article/delete",
        type: "POST",
        data: {
            articleId: $(i).attr("name")
        },
        dataType: "JSON",
        success: function (data) {
            if (data.success) {
                $(i).parent().remove();
            } else {
                alert(data.msg);
            }
            loadingHide();
        }
    })
}

function paginationLeft(i) {
    loadingShow();
    paginationAccess($(i).attr("name"));
    loadingHide();
}

function paginationRight(i) {
    loadingShow();
    paginationAccess($(i).attr("name"));
    loadingHide();
}

function paginationAccess(page) {
    var uri = getURI();
    uri = uri + "?category=" + $('#category').val() + "&page=" + page;
    uri = uriAddSearch(uri);
    window.location.href = uri;
}

function uriAddSearch(uri) {
    if ($('#searchCache').val().trim() != "") {
        uri = uri + "&search=" + $('#searchCache').val().trim();
    }
    return uri;
}