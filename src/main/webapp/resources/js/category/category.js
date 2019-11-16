$(function () {
    $('#search').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            searchCategory();
        }
    });
    $('.deleteIcon').hover(function () {
        $(this).removeClass("icon-shanchu1");
        $(this).addClass("icon-shanchusuoxuan");
    }, function () {
        $(this).removeClass("icon-shanchusuoxuan");
        $(this).addClass("icon-shanchu1");
    });
    $('.categoryButton').hover(function () {
        $(this).css("color", "#6bb2cd");
    }, function () {
        $(this).css("color", "");
    });
    $('#addCategory').on("click", function f() {
        if ($('#categoryName').val().trim() != "") {
            loadingShow();
            $.ajax({
                url: window.location.origin + "/Category/add",
                type: "POST",
                data: {
                    categoryName: $('#categoryName').val(),
                    categoryStatus: $('input[name="categoryStatus"]:checked').val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        location.reload(true);
                    } else {
                        alert(data.msg);
                    }
                    loadingHide();
                }
            });
        }
    })
});

function searchCategory() {
    loadingShow();
    var uri = getURI();
    uri = uri + "?search=" + $('#search').val().trim();
    window.location.href = uri;
    loadingHide();
}

function modifyOnClick(i) {
    loadingShow();
    $.ajax({
        url: window.location.origin + "/Category/modify",
        type: "POST",
        data: {
            categoryId: $(i).attr("name"),
            categoryName: $(i).parent().children('span').children('input[name="categoryName"]').val(),
            categoryStatus: $(i).parent().children('span').children('input[name^="categoryStatus"]:checked').val()
        },
        dataType: "JSON",
        success: function (data) {
            if (data.success) {
                alert_success();
            } else {
                alert(data.msg);
            }
            loadingHide();
        }
    })
}

function deleteOnClick(i) {
    loadingShow();
    $.ajax({
        url: window.location.origin + "/Category/delete",
        type: "POST",
        data: {
            categoryId: $(i).attr("name")
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