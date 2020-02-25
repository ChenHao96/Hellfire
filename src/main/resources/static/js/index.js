layui.use('element', function () {
    var element = layui.element;
    element.on('nav(navTab)', function (data) {
        var selectedTab = data[0].attributes["taggle"];
        $(".menuUlTab").hide();
        if (selectedTab) {
            $("#" + selectedTab.value).show();
        }
    });
    $(".layui-tab-title").on('contextmenu', 'li', function (event) {
        var tab_left = $(this).position().left;
        var tab_width = $(this).width();
        var left = $(this).position().top;
        var this_index = $(this).attr('lay-id');
        $('#tab_right').css({'left': tab_left + tab_width / 2}).show().attr('lay-id', this_index);
        $('#tab_show').show();
        return false;
    });
    var firstMenuButton = $(".layui-header .layui-layout-left li:first");
    if (firstMenuButton.length > 0) {
        $("#" + firstMenuButton.addClass("layui-this").children().attr("taggle")).show();
    }
});

function resizeXFrame() {
    var clientHeight = document.documentElement.clientHeight;
    var headerHeight = $(".layui-layout-body .layui-header").css("height");
    headerHeight = headerHeight.replace("px", "");
    var footerHeight = $(".layui-layout-body .layui-footer").css("height");
    footerHeight = footerHeight.replace("px", "");
    var bodyHeight = clientHeight - headerHeight - footerHeight;
    var tabFrameHeight = $(".layui-body .tabFrame").css("height");
    tabFrameHeight = tabFrameHeight.replace("px", "");
    $(".layui-layout-body .layui-body .x-iframe").css("height", (bodyHeight - tabFrameHeight) + "px");
    $(".layui-layout-body .layui-body").css("height", bodyHeight + 3 + "px");
}

function openUrl2Tab(url) {
    console.log(url);
}

$(function () {
    resizeXFrame();
    $('#tab_right,.layui-layout-body').on('click', function (event) {
        $('#tab_right').hide();
        $('#tab_show').hide();
    });
    $.getJSON(index2PageUrl, function (data) {
        $(".layui-menu-button").click(function () {
            var idAttr = $(this).attr("id");
            if (idAttr != null) {
                var openUrl = data[idAttr];
                if (openUrl != null) {
                    openUrl2Tab(openUrl);
                }
            }
        });
    });
});

$(window).resize(resizeXFrame);