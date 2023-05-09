<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>老李OA系统</title>
    <link rel="icon" href="/static/ico/favicon.ico" type="/image/ico">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/static/css/style.min.css" rel="stylesheet">
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
                            <div class="card-body">

                                <div class="site-form">
                                    <div class="form-group">
                                        <label for="old-password">旧密码</label>
                                        <input type="password" class="form-control" name="oldpwd" id="old-password" placeholder="输入账号的原登录密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="new-password">新密码</label>
                                        <input type="password" class="form-control" name="newpwd" id="new-password" placeholder="输入新的密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="confirm-password">确认新密码</label>
                                        <input type="password" class="form-control" name="confirmpwd" id="confirm-password" placeholder="请输入正确的邮箱地址">
                                    </div>
                                    <button id="submit" class="btn btn-primary">修改密码</button>
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
<script src="/static/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/static/js/lightyear.js"></script>
<script type="text/javascript" src="/static/js/main.min.js"></script>
<#--<script type="text/javascript" src="/js/admin/logout.js"></script>-->
<!--图表插件-->
<script type="text/javascript" src="/static/js/Chart.js"></script>
<script type="text/javascript" src="/static/js/admin/edit-password-operation.js"></script>

</body>
</html>