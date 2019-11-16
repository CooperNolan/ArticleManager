$(function () {
    var reg = {
        password: /^[\w|\.]{8,18}$/,
        email: /^.*@.*$/,
        phone: /^[1-9][0-9]{7,10}$/
    };
    $("#userpass").on("blur", function () {
        if ($(this).val() == "") {
            $('#userpass-prompt').html("");
        } else if (!reg.password.test($(this).val())) {
            $('#userpass-prompt').html("密码格式错误");
        } else {
            $('#userpass-prompt').html("");
        }
    });

    $("#phone").on("blur", function () {
        if ($(this).val() == "") {
            $('#email-prompt').html("");
        } else if (!reg.phone.test($(this).val())) {
            $('#phone-prompt').html("电话号码格式错误");
        } else {
            $('#phone-prompt').html("");
        }
    });

    $("#email").on("blur", function () {
        if ($(this).val() == "") {
            $('#email-prompt').html("");
        } else if (!reg.email.test($(this).val())) {
            $('#email-prompt').html("邮箱格式错误");
        } else {
            $('#email-prompt').html("");
        }
    });

    $('#writeBack').click(function () {
        loadingShow();
        window.location.href = window.location.origin + "/Home";
        loadingHide();
    })

    $('#modify').click(function () {
        if ($("#userpass").val() != "" && !reg.password.test($("#userpass").val())) {
            $('#userpass-prompt').html("密码格式错误");
        } else if ($("#phone").val() != "" && !reg.phone.test($("#phone").val())) {
            $('#phone-prompt').html("电话号码格式错误");
        } else if ($('#email').val() != "" && !reg.email.test($('#email').val())) {
            $('#email-prompt').html("邮箱格式错误");
        } else {
            loadingShow();
            $.ajax({
                url: window.location.origin + "/User/modify",
                type: "POST",
                data: {
                    userId: $('#userId').val(),
                    userpass: $('#userpass').val(),
                    nickname: $('#nickname').val(),
                    birthday: $('#birthday').val(),
                    gender: $('#gender').val(),
                    phone: $('#phone').val(),
                    email: $('#email').val(),
                    address: $('#address').val(),
                    remark: $('#remark').val()
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        $('#user-name-label').html($('#nickname').val());
                        alert_success();
                    } else {
                        $('#prompt').html(data.msg);
                    }
                    loadingHide();
                }
            })
        }
    })
})