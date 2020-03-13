layui.use(['form'], function () {
    layui.form.on('submit(login)', function () {
        const hash = $.md5($("form input[type=password]").val() + "+");
        $("form input[name=password]").val(hash);
        doRequest("api/login", $(".login .layui-form").serialize(), function (response) {
            if (response.code === 200) {
                window.location.href = "index.html";
            } else {
                errorResponse(response.msg);
            }
        });
        return false;
    });
    function errorResponse(message) {
        $(".login .formMessageBox").show();
        $(".login .formMessageBox div").text(message);
        setTimeout(function () {
            $(".login .formMessageBox").fadeOut('fast', function () {
                $(this).hide();
            });
        }, 2500);
    }
});