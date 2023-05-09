<!--左侧导航-->
<aside class="lyear-layout-sidebar">

    <!-- logo -->
    <div id="logo" class="sidebar-header">
        <a href="/main"><img src="/static/images/logo-sidebar.png" title="LightYear" alt="LightYear" /></a>
    </div>
    <div class="lyear-layout-sidebar-scroll">

        <nav class="sidebar-main">
            <ul class="nav nav-drawer">
                <li class="nav-item active"> <a href="/main"><i class="mdi mdi-home"></i> 后台首页</a> </li>
                <li class="nav-item">
                    <a href="/expenseReport/toList"><i class="mdi mdi-email-variant"></i> 报销单</a>
                </li>
                <li class="nav-item">
                    <a href="/expenseReport/toAddExpenseReport"><i class="mdi mdi-grease-pencil"></i> 填写报销单</a>
                </li>
                <#if userInfo.positionId != 3>
                    <li class="nav-item">
                        <a href="/expenseReport/toExamineList"><i class="mdi mdi-onenote"></i> 审批报销单</a>
                    </li>
                </#if>
                <#if userInfo.positionId = 1>
                    <li class="nav-item">
                        <a href="/department/toList"><i class="mdi mdi-houzz"></i> 部门管理</a>
                    </li>
                    <li class="nav-item">
                        <a href="/position/toList"><i class="mdi mdi-tie"></i> 职位管理</a>
                    </li>
                    <li class="nav-item">
                        <a href="/employee/toList"><i class="mdi mdi-human"></i> 员工管理</a>
                    </li>
                </#if>
            </ul>
        </nav>

        <div class="sidebar-footer">
            <p class="copyright">Copyright &copy; 2019. <a target="_blank" href="http://lyear.itshubao.com">IT书包</a> All rights reserved.</p>
        </div>
    </div>

</aside>
<!--End 左侧导航-->