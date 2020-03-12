function resizeXFrame() {
    const clientHeight = document.documentElement.clientHeight;
    var headerHeight = $(".layui-layout-body .layui-header").css("height");
    headerHeight = headerHeight.replace("px", "");
    var footerHeight = $(".layui-layout-body .layui-footer").css("height");
    footerHeight = footerHeight.replace("px", "");
    const bodyHeight = clientHeight - headerHeight - footerHeight;
    var tabFrameHeight = $(".layui-body .tabFrame").css("height");
    tabFrameHeight = tabFrameHeight.replace("px", "");
    $(".layui-layout-body .layui-body .x-iframe").css("height", (bodyHeight - tabFrameHeight) + "px");
    $(".layui-layout-body .layui-body").css("height", bodyHeight + 3 + "px");
}
layui.use(['element','laytpl','jquery'], function () {
    const element = layui.element;
    const $ = layui.jquery;
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
    layui.laytpl($("#topMenuTpl").html()).render(loginUser, function(html){$(".tplTopMenu").html(html);});
    layui.laytpl($("#leftMenuTpl").html()).render(loginUser, function(html){$(".tplLeftMenu").html(html);});
    element.render('nav');
    const firstMenuButton = $(".layui-header .layui-layout-left li:first");
    if (firstMenuButton.length > 0) {
        $("#" + firstMenuButton.addClass("layui-this").children().attr("taggle")).show();
    }
    $.getJSON(index2PageUrl, function (data) {
        $(".layui-menu-button").click(function () {
            const idAttr = $(this).attr("id");
            const title = $(this).text();
            if (idAttr != null) {
                const openUrl = data[idAttr];
                if (openUrl != null) {
                    layuiTabAdd(idAttr, title, openUrl);
                }
            }
        });
    });
    $(".logout-button").click(function(){
        $.getJSON("",{},function(data){
            if(data.code === 200){
                window.location.href="login.html";
            }
        });
    });
    function layuiTabAdd(id, title, url) {
        const opened = $(".layui-tab-title li[lay-id=home]").nextAll();
        if (opened != null && opened.length > 0) {
            for (var i = 0; i < opened.length; i++) {
                const layId = $(opened[i]).attr('lay-id');
                if (id === layId) {
                    element.tabChange('xbs_tab', id);
                    return;
                }
            }
        }
        element.tabAdd('xbs_tab', {
            id: id, title: title,
            content: '<iframe tab-id="' + id + '" src="' + url + '" scrolling="auto" class="x-iframe"></iframe>'
        });
        resizeXFrame();
        element.tabChange('xbs_tab', id);
    }
});
$(function () {
    $(".loginUserNickName").text(loginUser.nickName);
    $('#tab_right,.layui-layout-body').on('click', function(){$('#tab_right').hide();$('#tab_show').hide();});
    $('#tab_right dd').click(function () {
        const data_type = $(this).attr("data-type");
        $(".tabFrame li.home").find("i.layui-tab-close").remove();
        const lay_id = $(this).parents('#tab_right').attr('lay-id');
        if (data_type === "all") {
            $('.layui-tab-title li').find('.layui-tab-close').click();
        } else if (data_type === "other") {
            $('.layui-tab-title li[lay-id!=' + lay_id + ']').find('.layui-tab-close').click();
        } else if (data_type === "right") {
            $('.layui-tab-title li[lay-id=' + lay_id + ']').nextAll().find('.layui-tab-close').click();
        }
    });
});
$(window).resize(resizeXFrame);