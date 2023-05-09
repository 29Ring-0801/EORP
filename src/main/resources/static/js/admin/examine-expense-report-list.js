$(function (){

    //定义渲染列表时的条件
    var request_condition = {};

    //是否要初始化分页插件
    var flag = true;

    //改变筛选条件就回到第一页
    request_condition.current = 1;


    $('#status-switch').change(function (){
        console.log('状态筛选switch被触发了');
        //筛选状态,重新渲染列表之前,让分页插件初始化
        flag = true;
        request_condition.current = 1;
        if ($('#status-switch').is(':checked')){
            //只查询状态为1的报销单列表
            request_condition.status = 1;
            getList(request_condition);
        }else{
            //都查询
            request_condition.status = null;
            getList(request_condition)
        }
    })

    //调用方法,初始化部门列表
    getList(request_condition);

    //获取部门列表
    function getList(data){
        $.ajax({
            url:'/expenseReport/getExamineList',
            type:'post',
            async:false,
            cache:false,
            dataType:'json',
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify(data),
            success:function (data){
                if (data.success){
                    console.log("部门列表回调函数成功")
                    if (data.data.length===0){
                        lightyear.notify('啥也没搜到~','danger', 500, 'mdi mdi-emoticon-sad','top', 'center' );
                    }
                    // //避免分页插件反复初始化
                    // if (flag){
                    //     //初始化分页插件,将IPage对象传给分页插件
                    //     getPageInfo(data.data);
                    //     flag = false;
                    // }
                    //将后端返回的数组中data键对应的List集合传给方法渲染
                    handleList(data.data)
                }else{
                    //提醒
                    lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center' );
                }
            }
        });
    }
    //对列表集合进行渲染的方法
    function handleList(data){
        let i = 1;
        //动态拼接
        let html = '';
        //前端遍历
        data.map(function (item,index){
            html +=  ' <tr>'
                    +'    <td>'+(i++)+'</td>'
                    +'    <td>'+item.creator.name+'</td>'
                    +'    <td>'+item.cause+'</td>'
                    +'    <td>'+transformTimestamp(item.createTime)+'</td>'
                    // +'    <td>'+item.nextDealEm.name+'</td>'
                    +'    <td>'+item.totalAmount+'</td>'
                    +     departmentStatus(item.status)
                    +'    <td>'
                    +'        <div class="btn-group">'
                    +'            <a class="btn btn-xs btn-default" href="/expenseReport/goExamineExpenseReport?expenseId='+(item.expenseId)+'&status='+(item.status)+'" title="审批" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>'
                    // +             updateExpenseReportStatus(item.expenseId,item.status)
                    +'        </div>'
                    +'    </td>'
                    +'</tr>'
        })
        //渲染
        $('.department-wrap').html(html);
    }

    //时间格式转换
    function transformTimestamp(timestamp) {
        let a = new Date(timestamp).getTime();
        const date = new Date(a);
        const Y = date.getFullYear() + '-';
        const M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        const D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + '  ';
        const h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
        const m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
        // const s = date.getSeconds(); // 秒
        const dateString = Y + M + D + h + m;
        // console.log('dateString', dateString); // > dateString 2021-07-06 14:23
        return dateString;
    }

    //部门状态文字化处理
    function departmentStatus(status){
        if (status === 1){
            return '<td><font class="text-success">已处理</font></td>'
        }
        if (status === 2) {
            return '<td><font class="text-danger">已打回</font></td>'
        }
        return '<td><font class="text-warning">待处理</font></td>';
    }

    //修改状态的方法
    function updateExpenseReportStatus(expenseId,status){
        if (status === 1){
            return '<a class="btn btn-xs btn-default department-status-btn" href="#!" title="修改状态" data-id='+expenseId+' data-status='+status+' data-toggle="tooltip"><i class="mdi mdi-toggle-switch"></i></a>'
        }
        return '<a class="btn btn-xs btn-default department-status-btn" href="#!" title="修改状态" data-id='+expenseId+' data-status='+status+' data-toggle="tooltip"><i class="mdi mdi-toggle-switch-off"></i></a>'
    }

    //对.department-wrap列表下所有的a标签加点击事件,同时定位到class为department-status-btn的a标签
    $('.department-wrap').on('click','a',function (event){
        console.log(event.currentTarget.dataset)
        var target = $(event.currentTarget);
        //定位到是否是修改状态的a标签
        if (target.hasClass('department-status-btn')){
            console.log('定位到了修改状态的a标签')
            let expenseId = event.currentTarget.dataset.id;
            let status = event.currentTarget.dataset.status;
            //修改状态时
            if (request_condition.status === 1){
                flag = true;
                request_condition.current = 1;
            }
            $.ajax({
                url:'/expenseReport/toggleExpenseReportStatus',
                type:'post',
                cache:false,
                async:true,
                dataType:'json',
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify({
                    expenseId:expenseId,
                    status:status
                }),
                success:function (data){
                    console.log(data.success);
                    //重新渲染表格
                    getList(request_condition);
                    if (data.success){
                        lightyear.notify('状态修改成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center' );
                    }else{
                        lightyear.notify('状态修改失败!','danger', 500, 'mdi mdi-emoticon-sad','top', 'center' );
                    }
                }
            })
        }
    })

    //获取分页信息
    function getPageInfo(data){
        console.log('分页插件中的data'+data)
        //初始化分页插件
        $('#jq-page').pagination({
            pageCount:data.pages,
            coping:true,
            //回调函数:触发分页插件上的按钮事件
            callback:function (event){
                //首先获取到当前用户点击的页数,并作为参数传给getList
                console.log('用户当前点击页数:'+event.getCurrent())
                request_condition.current = event.getCurrent();
                getList(request_condition);
            }
        })
    }


})