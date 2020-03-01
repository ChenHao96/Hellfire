$(function (){
    layui.use(['form','element','laytpl','jquery'], function(){
        const element = layui.element;
        const $ = layui.jquery;
        layui.laytpl($("#verificationFormTpl").html()).render(formData, function(html){$("#tplVerificationForm").append(html);});
        element.render('form','form_');
        $(".vButton").click(function(){
            $.get($(this).attr("clickUrl"));
            layer.msg('验证码发送中...');
        });
    });
});