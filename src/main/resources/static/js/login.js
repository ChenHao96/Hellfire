$(function () {
    if (hasError) {
        $(".login .formMessageBox").show();
        $(".login .formMessageBox div").text(errorMessage);
    }
    layui.use('form', function () {
        const form = layui.form;
        form.on('submit(login)', function (formData) {
            const hash = $.md5($("form input[type=password]").val() + "+");
            $("form input[name=password]").val(hash);
            return true;
        });
    });
    setTimeout(function () {
        $(".login .formMessageBox").fadeOut('fast', function () {
            $(this).remove();
        });
    }, 2500);
});