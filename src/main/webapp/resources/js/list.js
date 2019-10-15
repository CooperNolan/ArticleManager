$(function () {
    $('#search').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            searchArticle();
        }
    });
    $('.deleteIcon').hover(function () {
        $(this).removeClass("icon-shanchu1");
        $(this).addClass("icon-shanchusuoxuan");
    },function () {
        $(this).removeClass("icon-shanchusuoxuan");
        $(this).addClass("icon-shanchu1");
    });
    $('#Pagination input').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            if(/^\d+$/.test($('#Pagination input').val())){
                if($('#Pagination input').val() <= $('#Pagination input').attr('name') && $('#Pagination input').val() > 0) {
                    loadingShow();
                    window.location.href = window.location.origin + "/" + getPaginationURI() + "/" + $('#Pagination input').val();
                    loadingHide();
                }
            }
        }
    });
});

function iconOnClick(i) {
    loadingShow();
    window.location.href = window.location.origin + "/Article/ShowArticle/"+$(i).attr("name");
    loadingHide();
}

function searchArticle() {
    if ($('#search').val() != "") {
        var uriList = getURIList();
        if (uriList[0] != "Home" && uriList[1] != "Search") {
            var uri = "/Article/" + uriList[1] + "/Search/" + $('#search').val();
            loadingShow();
            window.location.href = window.location.origin + uri;
        } else {
            loadingShow();
            window.location.href = window.location.origin + "/Article/Search/"+$('#search').val();
        }
        loadingHide();
    }
}

function deleteIconOnClick(i) {
    loadingShow();
    $.ajax({
        url:window.location.origin + "/Article/delete",
        type: "POST",
        data:{
            articleId:$(i).attr("name")
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
    window.location.href = window.location.origin + "/"+ getPaginationURI() +"/" + $(i).attr("name");
    loadingHide();
}
function paginationRight(i) {
    loadingShow();
    window.location.href = window.location.origin + "/"+ getPaginationURI() +"/" + $(i).attr("name");
    loadingHide();
}

function getPaginationURI() {
    var uriList = getURIList();
    if (uriList[0] == "Home") {
        return uriList[0];
    }else{
        if (uriList[1] == "Search") {
            return uriList[0] + "/" + uriList[1] + "/" + uriList[2];
        } else {
            if (uriList[2] == "Search") {
                return uriList[0] + "/" + uriList[1] + "/" + uriList[2] + "/" + uriList[3];
            } else {
                return uriList[0] + "/" + uriList[1];
            }
        }
    }
}
function getURIList() {
    var uri = window.location.href.substring(window.location.origin.length + 1);
    var uriList = new Array();
    var index = -1;
    while (true){
        index = uri.indexOf("/");
        if(index != -1){
            uriList.push(uri.substring(0,index));
            uri = uri.substring(index+1);
        }else {
            uriList.push(uri);
            break;
        }
    }
    return uriList;
}