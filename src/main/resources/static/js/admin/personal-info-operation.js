$(function (){

    $.post('/employee/queryEmployeeById',function (data){
        console.log(data)
        if (data.success) {
            let employee = data.data.employee;
            //渲染姓名
            $("#username").val(employee.name);
            $("#nickname").val(employee.loginName);
        }
    });

    $("#submit").click(function (){
        let employee = {};
        employee.name = $("#username").val();
        employee.loginName = $("#nickname").val();
        let formData = new FormData();
        formData.append("employeeStr",JSON.stringify(employee))
        $.ajax({
            url:'/employee/editEmployeeById',
            type:'post',
            data:formData,
            dataType:"json",
            contentType:false,
            processData:false,
            cache:false,
            success:function (data){
                console.log(data)
                if (data.success){
                    lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center', );
                }else{
                    lightyear.notify('操作失败~','danger', 500, 'mdi mdi-emoticon-sad','top', 'center');
                }
            }
        })
    })

});