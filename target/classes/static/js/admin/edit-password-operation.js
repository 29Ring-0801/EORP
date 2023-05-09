$(function (){

    // //旧密码框失去焦点事件
    // $("old-password").blur(function (){
    //
    //     $("#submit").removeAttr("disabled");
    // });
    //
    //
    // //新密码框获得焦点事件
    // $("#new-password").focus(function (){
    //     console.log("新密码框获得焦点事件触发了")
    //     var oldpwd = $("old-password").val();
    //     if (!oldpwd) {
    //         $("#submit").attr("disabled","disabled");
    //     }
    // });
    //
    // //确认密码框失去焦点事件
    // $("#confirm-password").blur(function (){
    //     var oldpwd = $("old-password").val();
    //
    //     if (oldpwd) {
    //         $("#submit").removeAttr("disabled");
    //     }
    //
    // });

    $("#submit").click(function (){
        let requestDTO = {};
        requestDTO.oldpwd = $("#old-password").val();
        requestDTO.newpwd = $("#new-password").val();
        requestDTO.confirmpwd = $("#confirm-password").val();
        // let formData = new FormData();
        // formData.append("requestDTO",JSON.stringify(requestDTO))
        $.ajax({
            url:'/employee/modifyPwd',
            type:'post',
            data:requestDTO,
            // dataType:"json",
            // contentType:false,
            // processData:false,
            cache:false,
            success:function (data){
                console.log(data)
                if (data.success){
                    lightyear.notify(data.errMsg,'success', 500, 'mdi mdi-emoticon-happy','top', 'center','/toEditPwd' );
                }else{
                    lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center');
                }
            }
        })
    })

});