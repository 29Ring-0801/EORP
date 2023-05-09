$(function (){
    //新增员工url
    var addEmployeeUrl = '/employee/insertEmployee';
    //获取所有有效的部门列表
    var queryActiveDepartmentListUrl = '/department/queryActiveDepartmentList'
    //获取所有有效的职位列表
    var queryActivePositionListUrl = '/position/queryActivePositionList'
    //编辑员工信息URL
    var editEmployeeUrl = '/employee/editEmployeeById'
    //根据id获取员工信息
    var queryEmployeeUrl = '/employee/queryEmployeeById'

    //前端给前端传参
    var employee_id = getQueryParam('emId');
    var is_edit = getQueryParam('edit');

    if (employee_id){
        $.post(queryEmployeeUrl,{employeeId:employee_id},function (data){
            console.log(data)
            if (data.success) {
                let employee = data.data.employee;
                //渲染姓名
                $("#employee-name").val(employee.name);
                $("#employee-loginName").val(employee.loginName);
                // console.log(activeDepartmentList);
                // console.log(activePositionList);
                //渲染部门下拉列表
                var depHtml = '';
                var isSelected = '';
                data.data.activeDepartmentList.map(function (item, index) {
                    isSelected = (employee.depId === item.depId)?'selected':'';
                    depHtml += '<option data-value="'+item.depId+'"'+isSelected+'>'+item.name+'</option>'
                });
                $('#department-select').html(depHtml);
                //渲染职位下拉列表
                var positionHtml = '';
                data.data.activePositionList.map(function (item, index) {
                    isSelected = (employee.positionId === item.positionId)?'selected':'';
                    positionHtml += '<option data-value="'+item.positionId+'"'+isSelected+'>'+item.positionName+'</option>'
                });
                $('#position-select').html(positionHtml);
                //渲染员工在职状态
                if (employee.status === 1){
                    $('#status-on').attr('checked',true);
                    $('#status-off').removeAttr('checked');
                }else{
                    $('#status-off').attr('checked',true);
                    $('#status-on').removeAttr('checked');
                }

            }

        });
    } else {
        //获取部门下拉列表
        $.post(queryActiveDepartmentListUrl,function (data){
            console.log(data);
            if (data.success){
                let activeDepartmentList = data.data;
                let optionHtml = '<option data-value="">全部部门</option>';
                activeDepartmentList.map(function (item,index){
                    optionHtml+= '<option data-value="'+item.depId+'">'+item.name+'</option>'
                });
                $('#department-select').html(optionHtml)
            }
        })
        //获取职位下拉列表
        $.post(queryActivePositionListUrl,function (data){
            console.log(data);
            if (data.success){
                let activePositionList = data.data;
                let optionHtml = '<option data-value="">全部职位</option>';
                activePositionList.map(function (item,index){
                    optionHtml+= '<option data-value="'+item.positionId+'">'+item.positionName+'</option>'
                });
                $('#position-select').html(optionHtml);
            }
        });
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
        let employee = {};
        employee.name = $("#employee-name").val();
        employee.loginName = $("#login-name").val();
        employee.status = $('input[name="status"][checked]').val();
        employee.positionId = $("#position-select").find('option').not(
            function (){
                return !this.selected
            }
        ).data("value");
        employee.depId = $("#department-select").find('option').not(
            function (){
                return !this.selected
            }
        ).data("value");

        if (is_edit) {
            employee.emId = employee_id;
        }

        let formData = new FormData();
        formData.append("employeeStr",JSON.stringify(employee))
        $.ajax({
            url:is_edit?editEmployeeUrl:addEmployeeUrl,
            type:'post',
            data:formData,
            dataType:"json",
            contentType:false,
            processData:false,
            cache:false,
            success:function (data){
                console.log(data)
                if (data.success){
                    lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center','/employee/toList' );
                }else{
                    lightyear.notify('操作失败~','danger', 500, 'mdi mdi-emoticon-sad','top', 'center');
                }
            }
        })
    })

});