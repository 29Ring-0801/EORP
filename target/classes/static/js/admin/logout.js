$(function (){
    $('#log-out').click(function (){
        //为了清空session
        $.ajax({
            url:'/logout',
            type:'post',
            async:false,
            cache:false,
            dataType:'json',
            success:function (data){
                if (data.success){
                    location.href = '/login';
                }
            },
            error:function (data,error){
                alert(error)
            }
        })
    })
})