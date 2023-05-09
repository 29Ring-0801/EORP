$(function (){
    //登录验证的url
    const loginUrl = '/checkLogin';
    //统计失败次数
    var loginCount = 0;

    $('#i18n').click(function (){
        let language = $('#i18n').text();
        $.post(
            "/i18n",
            {language:language},
            function (data){
                location.reload();
            }
        )

    });

    $('#submit').click(function (){
        //获取用户输入的用户名
        let username = $('#username').val();
        //获取用户输入的密码
        let password = $('#password').val();
        //获取用户输入的验证码
        let in_captcha = $('#in_captcha').val();
        //是否需要验证验证码的标记
        let needVerify = false;
        //如果三次失败就提示输入验证码
        if (loginCount>=3){
            if (!in_captcha){//输入验证码为空;前端!null=true,提醒输入,并且方法结束
                lightyear.notify('请输入验证码...','warning', 3000, 'mdi mdi-exclamation','top', 'center' );
                return;
            }else{//修改是否验证验证码的标记,方法继续执行
                needVerify = true;
            }
        }

        //将用户输入的数据传给后台做验证
        $.ajax({
            //请求路径
            url:loginUrl,
            //type:请求类型
            type:'post',
            //是否同步
            async:false,
            //缓存
            cache:false,
            //后端返回给前端的数据类型
            dataType:'json',
            //发送的数据的类型
            contentType:'application/json;charset=utf-8',
            //发送的数据,JSON.stringify将json对象转换成json字符串
            data:JSON.stringify({
                username:username,
                password:password,
                needVerify:needVerify,
                inCaptcha:in_captcha
            }),
            //回调函数
            success:function (data){
                if (data.success){
                    //success为true登录成功
                    lightyear.notify('登录成功,'+data.username+' 欢迎回来...','success', 500, 'mdi mdi-emoticon-happy','top', 'center' ,'/main');
                }else{
                    //false登录失败
                    lightyear.notify(data.errMsg+'','danger', 3000, 'mdi mdi-emoticon-sad','top', 'center' );
                    loginCount++;
                    //如果失败次数大于3就显示验证码
                    if (loginCount>=3){
                        $('#verifyPart').show();
                        $('#captcha').click();
                    }
                }
            }
        })
    })
})

function changeVerifyCode(img){
    img.src = "/Kaptcha?"+Math.floor(Math.random()*100)
}