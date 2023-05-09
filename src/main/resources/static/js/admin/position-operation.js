$(function (){

    //新增职位url
    var addPositionUrl = '/position/insertPosition';
    //查看职位详情url
    var queryPositionUrl = '/position/queryPositionById';
    //修改职位信息url
    var editPositionUrl = '/position/editPositionById';

    var position_id = getQueryParam('positionId');
    console.log(position_id);
    var is_edit = getQueryParam('edit');
    console.log(is_edit);

    //如果能从url获取到position_id,说明用户正在查看或编辑职位信息
    if (position_id){
        //需要从后端获取对应编号的职位信息,并填充到页面
        $.post(queryPositionUrl,{positionId:position_id},function (data){
            console.log(data)
            if (data.success){
                let position = data.position;
                $('#position-name').val(position.positionName);
                // $('#dep-address').val(position.address);
                if (position.status === 1){
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
        let position = {};
        //将名称,地址,状态封装到对象里面
        position.positionName = $('#position-name').val();
        // position.address = $('#dep-address').val();
        position.status = $('input[name="status"][checked]').val();
        //如果是修改页面的提交,就要给id号
        if (is_edit){
            position.positionId = position_id;
        }
        //创建FormData对象
        let formData = new FormData();
        //通过formData实现键值对的数据提交,将对象转换成JSON字符串,后端再通过jackson反序列化转换为对象
        formData.append("positionStr",JSON.stringify(position));
        $.ajax({
            //三目运算符
            url:is_edit ? editPositionUrl : addPositionUrl,
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
                    lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center' ,'/position/toList');
                }else{
                    //不成功不跳转
                    lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center');
                }
            }
        })

    })


})