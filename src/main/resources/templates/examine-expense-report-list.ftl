<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>审批报销单列表</title>
    <link rel="icon" href="/static/ico/favicon.ico" type="/image/ico">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/static/css/style.min.css" rel="stylesheet">
    <link href="/static/css/pagination.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <#include "slide.ftl" />
        <!--End 左侧导航-->

        <!--头部信息-->
        <#include "topbar.ftl" />
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clearfix">
                                <div class="pull-right m-10">
                                    <label class="lyear-switch switch-info">
                                        <input id="status-switch" type="checkbox">筛选状态
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <#--                                            <th>-->
                                            <#--                                                <label class="lyear-checkbox checkbox-primary">-->
                                            <#--                                                    <input type="checkbox" id="check-all"><span></span>-->
                                            <#--                                                </label>-->
                                            <#--                                            </th>-->
                                            <th>序号</th>
                                            <th>申请人</th>
                                            <th>报销原因</th>
                                            <th>创建时间</th>
<#--                                            <th>待处理人</th>-->
                                            <th>报销总金额</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="department-wrap">
                                        <#--<tr>
                                            <td>1</td>
                                            <td>第01章 天涯思君不可忘</td>
                                            <td>《倚天屠龙记》</td>
                                            <td><font class="text-success">正常</font></td>
                                            <td>
                                                <div class="btn-group">
                                                    <a class="btn btn-xs btn-default" href="#!" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>
                                                    <a class="btn btn-xs btn-default" href="#!" title="查看" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>
                                                    <a class="btn btn-xs btn-default" href="#!" title="修改状态" data-toggle="tooltip"><i class="mdi mdi-toggle-switch"></i></a>
                                                </div>
                                            </td>
                                        </tr>-->
                                        </tbody>
                                    </table>
                                </div>
                                <ul id="jq-page" class="m-style">
                                    <#--                                    <li class="disabled"><span>«</span></li>-->
                                    <#--                                    <li class="active"><span>1</span></li>-->
                                    <#--                                    <li><a href="#1">2</a></li>-->
                                    <#--                                    <li><a href="#1">3</a></li>-->
                                    <#--                                    <li><a href="#1">4</a></li>-->
                                    <#--                                    <li><a href="#1">5</a></li>-->
                                    <#--                                    <li><a href="#1">6</a></li>-->
                                    <#--                                    <li><a href="#1">7</a></li>-->
                                    <#--                                    <li><a href="#1">8</a></li>-->
                                    <#--                                    <li class="disabled"><span>...</span></li>-->
                                    <#--                                    <li><a href="#!">14452</a></li>-->
                                    <#--                                    <li><a href="#!">14453</a></li>-->
                                    <#--                                    <li><a href="#!">»</a></li>-->
                                </ul>

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
<script type="text/javascript" src="/static/js/main.min.js"></script>
<!--消息提示-->
<script src="/static/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/static/js/lightyear.js"></script>
<script type="text/javascript" src="/static/js/jquery.pagination.js"></script>
<script type="text/javascript" src="/static/js/admin/examine-expense-report-list.js"></script>
</body>
</html>