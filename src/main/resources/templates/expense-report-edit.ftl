<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>修改报销单</title>
    <link rel="icon" href="/static/ico/favicon.ico" type="image/ico">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/materialdesignicons.min.css" rel="stylesheet">
    <!--标签插件-->
    <#--    <link rel="stylesheet" href="/js/jquery-tags-input/jquery.tagsinput.min.css">-->
    <link href="/static/css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <#include "slide.ftl"/>
        <!--End 左侧导航-->

        <!--头部信息-->
        <#include "topbar.ftl"/>
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="divider text-uppercase">报销原因</div>
                                    <div class="form-group col-md-12">
                                        <input type="text" class="form-control" id="expense-cause" name="expense-cause" value="" placeholder="请填写报销原因" />
                                    </div>
                                    <div class="divider text-uppercase">报销凭证</div>
                                    <div class="form-group col-md-12">
                                        <input type="file" id="expense-voucher" name="expense-voucher">
                                    </div>
                                    <div class="divider text-uppercase">报销明细</div>
                                    <div class="col-md-12">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>选择</th>
                                                <th>费用项目</th>
                                                <th>费用说明</th>
                                                <th>报销金额</th>
                                            </tr>
                                            </thead>
                                            <tbody id="tbodyId">
                                            <tr>
                                                <td>
                                                    <a class="btn btn-default deleteItem">×</a>
                                                </td>
                                                <td>
                                                    <select class="form-control item-select">
                                                        <option>请选择</option>
                                                        <option value="洗脚">洗脚</option>
                                                        <option value="按摩">按摩</option>
                                                        <option value="唱歌">唱歌</option>
                                                        <option value="吃饭">吃饭</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control item-description"/>
                                                </td>
                                                <td class="input-group">
                                                    <input type="text" class="form-control amount" />
                                                    <span class="input-group-addon">元</span>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-3">
                                        <label>总金额</label>
                                        <div class="input-group m-b-10">
                                            <a class="btn btn-default input-group-addon" id="addItem">+</a>
                                            <input type="text" class="form-control" id="totalAmount" disabled>
                                            <span class="input-group-addon">元</span>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button id="submit" class="btn btn-primary">确 定</button>
                                        <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="/static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/perfect-scrollbar.min.js"></script>
<!--标签插件-->
<#--<script src="/js/jquery-tags-input/jquery.tagsinput.min.js"></script>-->
<!--消息提示-->
<script src="/static/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/static/js/lightyear.js"></script>
<script type="text/javascript" src="/static/js/main.min.js"></script>
<script type="text/javascript" src="/static/js/admin/common.js"></script>
<script type="text/javascript" src="/static/js/admin/expense-report-operation.js"></script>
</body>
</html>