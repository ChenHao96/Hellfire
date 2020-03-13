layui.use(['form', 'element', 'laytpl', 'layer'], function () {
    const layer = layui.layer;
    const codeLength = GetQueryValue("codeLength");
    const codeLifeTimeStr = GetQueryValue("codeLifeTimeStr");
    formData.codeLength = codeLength != null ? codeLength : formData.codeLength;
    formData.codeLifeTimeStr = codeLifeTimeStr != null ? codeLifeTimeStr : formData.codeLifeTimeStr;
    layui.laytpl($("#verificationFormTpl").html()).render(formData, function (html) {
        $("#tplVerificationForm").append(html);
    });
    layui.element.render('form', 'form_');
    $(".vButton").click(function () {
        doRequest("api/getInfo" + $(this).attr("clickUrl"), {}, function (response) {
            layui.layer.msg("验证码发送中...");
        }, "GET");
    });
    layui.form.on('submit(login)', function () {
        doGetRequest("api/getInfo", $(".login .layui-form").serialize(), function (response) {
            if (response.code === 200) {
                window.location.href = "index.html";
            } else {
                layer.msg(response.msg);
            }
        });
        return false;
    });
});

