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

<#--                                <div class="edit-avatar">-->
<#--                                    <img src="images/users/avatar.jpg" alt="..." class="img-avatar">-->
<#--                                    <div class="avatar-divider"></div>-->
<#--                                    <div class="edit-avatar-content">-->
<#--                                        <button class="btn btn-default">修改头像</button>-->
<#--                                        <p class="m-0">选择一张你喜欢的图片，裁剪后会自动生成264x264大小，上传图片大小不能超过2M。</p>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                                <hr>-->
                                <div class="site-form">
                                    <div class="form-group">
                                        <label for="username">真实姓名</label>
                                        <input type="text" class="form-control" name="username" id="username" disabled="disabled" />
                                    </div>
                                    <div class="form-group">
                                        <label for="nickname">登录名(手机号)</label>
                                        <input type="text" class="form-control" name="nickname" id="nickname" placeholder="输入您的昵称" >
                                    </div>
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="请输入正确的邮箱地址" value="3331653644@qq.com">
                                        <small id="emailHelp" class="form-text text-muted">请保证您填写的邮箱地址是正确的。</small>
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">简介</label>
                                        <textarea class="form-control" name="remark" id="remark" rows="3"></textarea>
                                    </div>
                                    <button id="submit" class="btn btn-primary">保存</button>
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
<script type="text/javascript" src="/static/js/admin/personal-info-operation.js"></script>

</body>
</html>