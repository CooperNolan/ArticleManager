$(function () {
    window.onload = function () {
        if (window.name == "hasLoad") {
            location.reload();
            window.name = "";
        } else {
            window.name = "hasLoad";
        }
    };
    var reg={
        username:/^[\w|\.]{6,12}$/,
        password:/^[\w|\.]{8,18}$/,
        VerificationCode:/^[A-Za-z0-9]{4}$/
    };

    $(".txtb input").on("focus", function () {
        $(this).addClass("focus");
    });

    $(".txtb input").on("blur", function () {
        if ($(this).val() == "")
            $(this).removeClass("focus");
    });
    $("#login-registered").click(function () {
        $("#login").hide();
        $("#registered").show();
        $("#login-username").val("");
        $("#login-password").val("");
        $('#login-prompt').html("&nbsp;");
        $("#login-username").blur();
        $("#login-password").blur();
    });
    $("#registered-login").click(function () {
        $("#registered").hide();
        $("#login").show();
        $("#registered-username").val("");
        $("#registered-password").val("");
        $("#VerificationCode").val("");
        $("#registered-prompt").html("&nbsp;");
        $("#registered-username").blur();
        $("#registered-password").blur();
        $("#VerificationCode").blur();
        $('#captcha_img').click();
        $('#registered-username-prompt').hide();
        $('#registered-password-prompt').hide();
    });
    $("#login-login").click(function () {
        var login_username = $("#login-username").val();
        var login_password = $("#login-password").val();
        if (login_username == "") {
            $('#login-prompt').text("用户名不能为空！");
        } else if (login_password == "") {
            $('#login-prompt').text("密码不能为空！");
        } else {
            $("#loading").show();
            $(".full-screen-coverage").show();
            $.ajax({
                url: window.location.origin + "/login",
                type: "POST",
                data: {
                    username: login_username,
                    password: login_password
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        window.location.href = window.location.origin + "/" + data.url;
                    } else {
                        $('#login-prompt').html(data.msg);
                        $("#loading").hide();
                        $("#full-screen-coverage").hide();
                    }
                    $("#loading").hide();
                    $(".full-screen-coverage").hide();
                }
            });
        }
    });
    $("#registered-registered").click(function () {
        var registered_username = $("#registered-username").val();
        var registered_password = $("#registered-password").val();
        var VerificationCode = $("#VerificationCode").val();
        if (!reg.username.test(registered_username)) {
            $('#registered-username-prompt').show();
        }else if(!reg.password.test(registered_password)){
            $('#registered-password-prompt').show();
        }else if(!reg.VerificationCode.test(VerificationCode)){
            $('#registered-prompt').html("验证码错误！");
            $('#captcha_img').click();
        }else {
            $("#loading").show();
            $(".full-screen-coverage").show();
            $.ajax({
                url: window.location.origin + "/registered",
                type: "POST",
                data: {
                    username: registered_username,
                    password: registered_password,
                    code: VerificationCode
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success) {
                        window.location.href = window.location.origin + "/" + data.url;
                    } else {
                        $('#captcha_img').click();
                        $('#registered-prompt').html(data.msg);
                        $("#loading").hide();
                        $("#full-screen-coverage").hide();
                    }
                    $("#loading").hide();
                    $(".full-screen-coverage").hide();
                }
            });
        }
    });

    var prompt;

    $("#registered-username").on("focus", function () {
        $('#registered-prompt').css("color","black");
        prompt = $('#registered-prompt').html();
        $('#registered-prompt').html("提示:6-12个数字、字母或下划线");
    });
    $("#registered-username").on("blur", function () {
        $('#registered-prompt').css("color", "red");
        $('#registered-prompt').html(prompt);
        if (!reg.username.test($("#registered-username").val())) {
            $('#registered-username-prompt').show();
        } else {
            $('#registered-username-prompt').hide();
        }
    });

    $('#registered-password').on("focus", function () {
        $('#registered-prompt').css("color","black");
        prompt = $('#registered-prompt').html();
        $('#registered-prompt').html("提示:8-18个数字、字母或下划线");
    });

    $("#registered-password").on("blur", function () {
        $('#registered-prompt').css("color", "red");
        $('#registered-prompt').html(prompt);
        if (!reg.password.test($('#registered-password').val())) {
            $('#registered-password-prompt').show();
        } else {
            $('#registered-password-prompt').hide();
        }
    });

    $('#login-username,#login-password').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            $("#login-login").click();
        }
    });
    $('#registered-username,#registered-password,#VerificationCode').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            $("#registered-registered").click();
        }
    });
});

function changeVerifyCode(img) {
    img.src = window.location.origin + "/Kaptcha?" + Math.floor(Math.random() * 100);
}