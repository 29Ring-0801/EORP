$(function (){

    //查看报销单url
    var queryExpenseReportUrl = '/expenseReport/queryExpenseReportById';

    var examine = '';

    //获取uri上的数据
    var expense_id = getQueryParam('expenseId');
    console.log(expense_id);
    var status = getQueryParam('status');
    console.log(status);

    var totalAmount = 0;
    //如果能从url获取到expense_id,说明用户正在查看或编辑报销单
    if (expense_id){
        //需要从后端获取对应编号的报销单信息,并填充到页面
        $.post(queryExpenseReportUrl,{expenseId:expense_id},function (data){
            console.log(data)
            if (data.success){
                let expenseReport = data.expenseReport;
                //报销原因
                $('#expense-cause').val(expenseReport.cause);
                //报销凭证
                let expenseVoucher = document.getElementById('expense-voucher');
                expenseVoucher.href = '../static/upload-voucher/'+expenseReport.expenseVoucher;
                //报销总金额
                $('#totalAmount').val(expenseReport.totalAmount);
                totalAmount = expenseReport.totalAmount;
                if (data.reportDetails.length!==0) {
                    //动态拼接
                    let html = '';
                    //前端遍历
                    data.reportDetails.map(function (item,index){
                        //查看
                        html += '<tr>\n' +
                        '        <td>\n' +
                        '            <a class="btn btn-default deleteItem disabled"  >×</a>\n' +
                        '        </td>\n' +
                        '        <td>\n' +
                        '           <input class="form-control item-select" value="'+item.item+'" disabled>\n' +
                        '        </td>\n' +
                        '        <td>\n' +
                        '            <input type="text" class="form-control item-description" value="'+item.comment+'" disabled/>\n' +
                        '        </td>\n' +
                        '        <td class="input-group">\n' +
                        '            <input type="text" class="form-control amount" value="'+item.amount+'" disabled/>\n' +
                        '            <span class="input-group-addon">元</span>\n' +
                        '        </td>\n' +
                        '    </tr>'
                    })
                    $('#tbodyId').html(html);
                }
            }

        })
    }

    //审批通过
    $("#examine-pass").click(function (){
        //审批通过标记
        examine = 'pass';
        $.post('/expenseReport/toggleExpenseReportStatus',{expenseId:expense_id,totalAmount:totalAmount,status:status,examine:examine},function (data){
            console.log(data)
            if (data.success){
                //成功后给出提示,并跳转
                lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center' ,'/expenseReport/toExamineList');
            }else{
                //不成功不跳转
                lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center','/error');
            }
        })
    })
    //审批打回
    $("#examine-repulse").click(function (){
        examine = 'repulse';
        $.post('/expenseReport/toggleExpenseReportStatus',{expenseId:expense_id,totalAmount:totalAmount,status:status,examine:examine},function (data){
            console.log(data)
            if (data.success){
                //成功后给出提示,并跳转
                lightyear.notify('操作成功~','success', 500, 'mdi mdi-emoticon-happy','top', 'center' ,'/expenseReport/toExamineList');
            }else{
                //不成功不跳转
                lightyear.notify(data.errMsg,'danger', 500, 'mdi mdi-emoticon-sad','top', 'center','/error');
            }
        })
    })

})