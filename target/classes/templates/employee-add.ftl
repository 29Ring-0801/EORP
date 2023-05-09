<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>新增员工</title>
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
                                    <div class="form-group col-md-12">
                                        <label for="employee-name">员工姓名</label>
                                        <input type="text" class="form-control" id="employee-name" name="employee-name" value="" placeholder="请输入员工姓名" />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="login-name">登录账户(手机号,默认密码123456)</label>
                                        <input type="text" class="form-control" id="login-name" name="login-name" value="" placeholder="请输入手机号" />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="department-select">所属部门</label>
                                        <select class="form-control" id="department-select" name="department-select">
                                            <option value="0">请选择</option>
                                            <option value="1">部门1</option>
                                            <option value="2">部门2</option>
                                            <option value="3">部门3</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="position-select">担任职务</label>
                                        <select class="form-control" id="position-select" name="position-select">
                                            <option value="0">请选择</option>
                                            <option value="1">职务1</option>
                                            <option value="2">职务2</option>
                                            <option value="3">职务3</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="status">状态</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input id="status-off" type="radio" name="status" value="0"><span>离职</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input id="status-on" type="radio" name="status" value="1" checked><span>在职</span>
                                            </label>
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
<script type="text/javascript" src="/static/js/admin/employee-operation.js"></script>
</body>
</html>