function GetQueryValue(queryName) {
    const reg = new RegExp("(^|&)" + queryName + "=([^&]*)(&|$)", "i");
    const param = window.location.search.substr(1).match(reg);
    if (param != null) {
        return decodeURI(param[2]);
    } else {
        return null;
    }
}

function doRequest(url, param, success, method) {
    $.ajax(url, {
        method: method ? method : "POST",
        data: param,
        success: function (response) {
            if (success) {
                success(response);
            } else {
                if (response) {
                    alert(response.msg);
                }
            }
        },
        error: function () {
            window.location.href = "login.html";
        }
    });
}

function doGetRequest(url, param, success) {
    doRequest(url, param, success, "GET");
}