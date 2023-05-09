$(function (){
    //新增报销单url
    var insertExpenseReport = '/expenseReport/insertExpenseReport'
    //查看报销单url
    var queryExpenseReportUrl = '/expenseReport/queryExpenseReportById';
    //修改报销单url
    var editExpenseReportUrl = '/expenseReport/editExpenseReportById';

    //获取uri上的数据
    var expense_id = getQueryParam('expenseId');
    console.log(expense_id);
    var is_edit = getQueryParam('edit');
    console.log(is_edit);

    //如果能从url获取到expense_id,说明用户正在查看或编辑报销单
    if (expense_id){
        //需要从后端获取对应编号的报销单信息,并填充到页面
        $.post(queryExpenseReportUrl,{expenseId:expense_id},function (data){
            console.log(data)
            if (data.success){
                let expenseReport = data.expenseReport;
                //报销原因
                $('#expense-cause').val(expenseReport.cause);
                //报销总金额
                $('#totalAmount').val(expenseReport.totalAmount);
                if (data.reportDetails.length!==0) {
                    //动态拼接
                    let html = '';
                    //前端遍历
                    data.reportDetails.map(function (item,index){
                        if (is_edit){
                            //修改
                            html += '<tr>\n' +
                                '        <td>\n' +
                                '            <a class="btn btn-default deleteItem"  >×</a>\n' +
                                '        </td>\n' +
                                '        <td>\n' +
                                '           <select class="form-control item-select">\n' +
                                                selectItem(item.item) +
                                '           </select>'+
                                '        </td>\n' +
                                '        <td>\n' +
                                '            <input type="text" class="form-control item-description" value="'+item.comment+'" />\n' +
                                '        </td>\n' +
                                '        <td class="input-group">\n' +
                                '            <input type="text" class="form-control amount" value="'+item.amount+'" />\n' +
                                '            <span class="input-group-addon">元</span>\n' +
                                '        </td>\n' +
                                '    </tr>'
                        }else{
                                //查看
                                html += '<tr>\n' +
                                '        <td>\n' +
                                '            <a class="btn btn-default deleteItem disabled"  >×</a>\n' +
                                '        </td>\n' +
                                '        <td>\n' +
                                '           <select class="form-control item-select" disabled>\n' +
                                                selectItem(item.item) +
                                '           </select>'+
                                '        </td>\n' +
                                '        <td>\n' +
                                '            <input type="text" class="form-control item-description" value="'+item.comment+'" disabled/>\n' +
                                '        </td>\n' +
                                '        <td class="input-group">\n' +
                                '            <input type="text" class="form-control amount" value="'+item.amount+'" disabled/>\n' +
                                '            <span class="input-group-addon">元</span>\n' +
                                '        </td>\n' +
                                '    </tr>'
                        }
                    })
                    $('#tbodyId').html(html);
                }
            }

        })
    }

    //对选择框进行渲染
    function selectItem(item){
            if (item === "洗脚") {
                return '    <option>请选择</option>\n' +
                    '       <option value="洗脚" selected>洗脚</option>\n' +
                    '       <option value="按摩">按摩</option>\n' +
                    '       <option value="唱歌">唱歌</option>\n' +
                    '       <option value="吃饭">吃饭</option>\n';
            }
            if (item === "按摩") {
                return '    <option>请选择</option>\n' +
                    '       <option value="洗脚" >洗脚</option>\n' +
                    '       <option value="按摩" selected>按摩</option>\n' +
                    '       <option value="唱歌" >唱歌</option>\n' +
                    '       <option value="吃饭">吃饭</option>\n';
            }if (item === "唱歌") {
                return '    <option>请选择</option>\n' +
                    '       <option value="洗脚" >洗脚</option>\n' +
                    '       <option value="按摩">按摩</option>\n' +
                    '       <option value="唱歌" selected>唱歌</option>\n' +
                    '       <option value="吃饭">吃饭</option>\n';
            }if (item === "吃饭") {
                return '    <option>请选择</option>\n' +
                    '       <option value="洗脚" >洗脚</option>\n' +
                    '       <option value="按摩">按摩</option>\n' +
                    '       <option value="唱歌">唱歌</option>\n' +
                    '       <option value="吃饭" selected>吃饭</option>\n';
            }
    }

    //添加一项报销项
    $("#addItem").click(function (){
        let trHtml = '<tr>\n' +
            '                                                <td>\n' +
            '                                                    <a class="btn btn-default deleteItem">×</a>\n' +
            '                                                </td>\n' +
            '                                                <td>\n' +
            '                                                    <select class="form-control item-select" name="item-select">\n' +
            '                                                        <option>请选择</option>\n' +
            '                                                        <option value="洗脚">洗脚</option>\n' +
            '                                                        <option value="按摩">按摩</option>\n' +
            '                                                        <option value="唱歌">唱歌</option>\n' +
            '                                                        <option value="吃饭">吃饭</option>\n' +
            '                                                    </select>\n' +
            '                                                </td>\n' +
            '                                                <td>\n' +
            '                                                    <input type="text" class="form-control item-description"/>\n' +
            '                                                </td>\n' +
            '                                                <td class="input-group">\n' +
            '                                                    <input type="text" class="form-control amount" />\n' +
            '                                                    <span class="input-group-addon">元</span>\n' +
            '                                                </td>\n' +
            '                                            </tr>'
        $("#tbodyId").append(trHtml);
    })

    //删除点击的报销列
    $(document).on("click",".deleteItem",function (){
        this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
        //删除一条重新计算总金额
        var totalAmount = 0;
        $(".amount").each(function (){
            amounts = Number($(this).val());
            totalAmount = totalAmount + amounts;
        })
        $("#totalAmount").val(totalAmount)
    })

    //输入框失去焦点时,计算总金额
    $("#tbodyId").on("blur",".amount",function (){
        var totalAmount = 0;
        $(".amount").each(function (){
            amounts = Number($(this).val());
            totalAmount = totalAmount + amounts;
        })
        $("#totalAmount").val(totalAmount)
    })

    //点击提交确认
    $("#submit").click(function (){
        //报销单信息(报销原因+报销总金额)
        let expenseReport = {};
        //报销原因
        expenseReport.cause = $("#expense-cause").val();
        //报销总金额
        expenseReport.totalAmount = $("#totalAmount").val();
        if (is_edit) {
            expenseReport.expenseId = expense_id;
        }
        //可能有多个报销项目
        let detailList = [];
        //遍历tr
        $("#tbodyId tr").each(function (index){
            //报销单细节
            let expenseReportDetail = {};
            //报销项目
            expenseReportDetail.item = $(this).find("option:selected").val();
            //报销说明
            expenseReportDetail.comment = $(this).find(".item-description").val();
            //报销金额
            expenseReportDetail.amount =  $(this).find(".amount").val();
            detailList.push(expenseReportDetail);
        });
        let expenseReportRequest = {};
        expenseReportRequest.expenseReport = expenseReport;
        expenseReportRequest.detailList = detailList;
        let formData = new FormData();
        formData.append("detailStr",JSON.stringify(expenseReportRequest));
        //报销凭证
        let expenseVoucher = $("#expense-voucher")[0].files[0];
        //封装到formdata中
        formData.append("expenseVoucher",expenseVoucher);
        console.log(formData)
        $.ajax({
            url:is_edit?editExpenseReportUrl:insertExpenseReport,
            type:"post",
            dataType:'json',
            cache:false,
            //默认的contentType是text/plain;jquery中post提交默认是application/x-www-form-urlencoded;
            contentType:false,
            //processData表示会不会序列化data里面的数据,默认true
            processData:false,
            data:formData,
            success:function (data){
                console.log(data)
                if (data.success){
                    //新增成功后给出提示,并跳转
                    lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center' ,'/expenseReport/toList');
                }else{
                    //不成功不跳转
                    lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center');
                }
            }
        })

    })

})