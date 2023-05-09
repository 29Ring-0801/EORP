$(function (){

    //新增部门url
    var addDepartmentUrl = '/department/insertDepartment';
    //查看部门详情url
    var queryDepartmentUrl = '/department/queryDepartmentById';
    //修改部门信息url
    var editDepartmentUrl = '/department/editDepartmentById';

    var dep_id = getQueryParam('depId');
    console.log(dep_id);
    var is_edit = getQueryParam('edit');
    console.log(is_edit);

    //如果能从url获取到dep_id,说明用户正在查看或编辑部门信息
    if (dep_id){
        //需要从后端获取对应编号的部门信息,并填充到页面
        $.post(queryDepartmentUrl,{departmentId:dep_id},function (data){
            console.log(data)
            if (data.success){
                let department = data.department;
                $('#dep-name').val(department.name);
                $('#dep-address').val(department.address);
                if (department.status === 1){
                    $('#status-on').attr('checked',true);
                    $('#status-off').removeAttr('checked');
                }else{
                    $('#status-off').attr('checked',true);
                    $('#status-on').removeAttr('checked');
                }
            }
        })
    }


    $('#status-on').click(function (){
        console.log('启用按钮被点击了~')
        $('#status-on').attr('checked',true);
        $('#status-off').removeAttr('checked');
    })

    $('#status-off').click(function (){
        console.log('禁用按钮被点击了~')
        $('#status-off').attr('checked',true);
        $('#status-on').removeAttr('checked');
    })

    $("#submit").click(function (){
        //定义一个JS的对象
        let department = {};
        //将名称,地址,状态封装到对象里面
        department.name = $('#dep-name').val();
        department.address = $('#dep-address').val();
        department.status = $('input[name="status"][checked]').val();
        //如果是修改页面的提交,就要给id号
        if (is_edit){
            department.depId = dep_id;
        }
        //创建FormData对象
        let formData = new FormData();
        //通过formData实现键值对的数据提交,将对象转换成JSON字符串,后端再通过jackson反序列化转换为对象
        formData.append("departmentStr",JSON.stringify(department));
        $.ajax({
            //三目运算符
            url:is_edit ? editDepartmentUrl : addDepartmentUrl,
            type:'post',
            dataType:'json',
            cache:false,
            //默认的contentType是text/plain;jquery中post提交默认是application/x-www-form-urlencoded;
            contentType:false,
            //processData表示会不会序列化data里面的数据,默认true
            processData:false,
            // data:{name:name, address:address,status:status},
            data:formData,
            success:function (data){
                console.log(data)
                if (data.success){
                    //新增成功后给出提示,并跳转
                    lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center' ,'/department/toList');
                }else{
                    //不成功不跳转
                    lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center');
                }
            }
        })

    })


})