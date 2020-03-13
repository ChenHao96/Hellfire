layui.use(['flow', 'layer'], function () {
    const layer = layui.layer;
    const index = layer.load();
    doGetRequest("api/getInfo", {}, function (response) {
        console.log(response);
        if (response.code === 200) {
            loginUser = response.data;
            $.getScript("res/js/index.js", function () {
                layer.close(index);
            });
        } else if (response.code === 100) {
            window.location.href = "verification.html?codeLength="
                + response.data.codeLength
                + "&codeLifeTimeStr="
                + encodeURI(response.data.codeLifeTimeStr);
        } else {
            layer.msg(response.msg);
        }
    });
});