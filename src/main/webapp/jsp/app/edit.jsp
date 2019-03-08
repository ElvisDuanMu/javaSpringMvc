
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>App管理系统</title>
    <link rel="stylesheet" href="${ctx}/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <jsp:include page="/jsp/common/header.jsp" />

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <form class="layui-form" action="${ctx}/app/add" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">软件名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="softwareName"   placeholder="请输入软件名称" autocomplete="off" class="layui-input" lay-verify="required" value="${app.softwareName}">
                    </div>
                    <label class="layui-form-label">APK名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="APKName"   placeholder="请输入APK名称" autocomplete="off" class="layui-input" lay-verify="required" value="${app.APKName}">
                    </div>
                    <label class="layui-form-label">支持ROM</label>
                    <div class="layui-input-inline">
                        <input type="text" name="supportROM"   placeholder="请输入ROM名称" autocomplete="off" class="layui-input" lay-verify="required" value="${app.supportROM}">
                    </div>
                    <label class="layui-form-label">界面语言</label>
                    <div class="layui-input-inline">
                        <input type="text" name="interfaceLanguage"   placeholder="请输入界面语言" autocomplete="off" class="layui-input" lay-verify="required" value="${app.interfaceLanguage}">
                    </div>
                    <label class="layui-form-label">软件大小</label>
                    <div class="layui-input-inline">
                        <input type="text" name="softwareSize"   placeholder="请输入软件大小" autocomplete="off" class="layui-input" lay-verify="required" value="${app.softwareSize}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <%--TODO App状态--%>
                    <label class="layui-form-label">app状态</label>
                    <div class="layui-input-inline">
                        <select name="appFlatform.valueId" >
                            <option value="">-请选择-</option>
                            <c:forEach items="${appStatus}" var="obj">
                                <option value="${obj.valueId}"  <c:if test="${app.appStatus.valueId eq obj.valueId}">
                                    selected
                                </c:if>>${obj.valueName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <%--TODO 所属平台--%>
                    <label class="layui-form-label">所属平台</label>
                    <div class="layui-input-inline">
                        <select name="appFlatform.valueId" >
                            <option value="">-请选择-</option>
                            <c:forEach items="${appFlatform}" var="obj">
                                <option value="${obj.valueId}"  <c:if test="${app.appFlatform.valueId eq obj.valueId}">
                                    selected
                                </c:if>>${obj.valueName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <%--TODO 一级分类--%>
                    <label class="layui-form-label">一级分类</label>
                    <div class="layui-input-inline" >
                        <select name="categoryLevel1.id" id="levelOne" lay-filter="levelOne" >
                            <option value="">-请选择-</option>
                            <c:forEach items="${levelOne}" var="obj">
                                <option value="${obj.id}"<c:if test="${obj.id eq app.categoryLevel1.id}">
                                    selected
                                </c:if>>${obj.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <%--TODO 二级分类--%>
                    <label class="layui-form-label">二级分类</label>
                    <div class="layui-input-inline">
                        <select name="categoryLevel2.id" id="levelTwo" lay-filter="levelTwo">
                            <option value="">-请选择-</option>
                        </select>
                    </div>
                    <%--TODO 三级分类--%>
                    <label class="layui-form-label">三级分类</label>
                    <div class="layui-input-inline">
                        <select name="categoryLevel3.id" id="levelThree" lay-filter="levelThree">
                            <option value="">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">应用简介</label>
                    <div class="layui-input-inline">
                        <input type="text" name="appInfo"   placeholder="请输入应用简介" autocomplete="off" class="layui-input" lay-verify="required" value="${app.appInfo}">
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">添加</button>
                    </div>
                </div>


            </form>

        </div>
    </div>
    <jsp:include page="/jsp/common/footer.jsp" />

</div>
<script src="${ctx}/static/plugins/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element','form','jquery'], function(){
        var element = layui.element;
        var form = layui.form;
        var $ = layui.jquery;

        //一级分类 start  ===================
        form.on('select(levelOne)',function () {
            //获取id
            var levelOneId = $('#levelOne').val();
            if(levelOneId == ''){
                //清空
                var html="<option value=\"\">-请选择-</option>";
                $('#levelTwo').html(html);
                $('#levelThree').html(html);
                form.render();
                return ;
            }
            else {
                $.ajax({
                    url:'${ctx}/category/queryLevelTwoByLevelOne/' + levelOneId,
                    type:'get',
                    success:function (data) {
                        //根据data修改数据，重新渲染即可
                        var html="<option value=\"\">-请选择-</option>";
                        var len = data.length;
                        var levelTwo = '${appInfoDTO.levelTwo}';
                        for(var i = 0; i <len;i++){
                            html += '<option value="' + data[i].id +'"';
                            if(data[i].id == levelTwo){
                                html+= ' selected';
                            }
                            html+='>' + data[i].categoryName +'</option>';
                        }
                        //选择level2更新
                        $('#levelTwo').html(html);
                        form.render();
                    }
                })
            }
        });
        //一级分类 end==========================


        //二级分类 start ===================
        form.on('select(levelTwo)',function () {
            //获取id
            var levelTwoId = $('#levelTwo').val();
            if(levelTwoId == ''){
                //清空
                var html="<option value=\"\">-请选择-</option>";
                $('#levelThree').html(html);
                form.render();
                return ;
            }
            else {
                $.ajax({
                    url:'${ctx}/category/queryLevelThreeByLevelTwo/' + levelTwoId,
                    type:'get',
                    success:function (data) {
                        //根据data修改数据，重新渲染即可
                        var html="<option value=\"\">-请选择-</option>";
                        var len = data.length;
                        for(var i = 0; i <len;i++){
                            html += '<option value="' + data[i].id +'"';
                            if(data[i].id == levelThree){
                                html+= ' selected';
                            }
                            html+='>' + data[i].categoryName +'</option>';
                        }
                        //选择level3更新
                        $('#levelThree').html(html);
                        form.render();
                    }
                })
            }
        })
        //二级分类 end ===================

        //分页事件 start ===================
        $('a[page]').click(function () {
            var pageNum = 1;
            var currPage = '${page.pageNum}';
            var totalPages = '${page.pages}';
            var v =$(this).attr('page');
            switch (v) {
                case "first":
                    pageNum = 1;
                    break;
                case "prev":
                    pageNum = parseInt(currPage) - 1;
                    if(pageNum < 1)
                        pageNum = 1;
                    break;
                case "next":
                    pageNum = parseInt(currPage) + 1;
                    if(pageNum > totalPages){
                        pageNum = totalPages;
                    }
                    break;
                case "last":
                    pageNum = totalPages;
                    break;
            }

            //更新隐藏域里面的值
            $('input[name=pageNum]').val(pageNum);
            //提交表单
            $('form').submit();
        })
        //分页事件 end ===================

        $(function () {
            // 1 取出一级分类的值
            var levelOne = $('#levelOne').val();
            if(levelOne !='' && levelOne !=null){
                //一级曾经选过
                var levelTwo = '${app.categoryLevel2.id}';
                if(levelTwo != null && levelTwo != undefined && levelTwo != ''){
                    //二级曾经选过
                    $.ajax({
                        url:'${ctx}/category/queryLevelTwoByLevelOne/' + levelOne,
                        //type:'get',
                        type:'post',
                        contentType: 'application/json',
                        success:function (data) {
                            //根据data修改数据，重新渲染即可
                            var html="<option value=\"\">-请选择-</option>";
                            var len = data.length;
                            //var levelTwo = '${app.categoryLevel2.id}';
                            for(var i = 0; i <len;i++){
                                html += '<option value="' + data[i].id +'"';
                                if(data[i].id == levelTwo){
                                    html+= ' selected';
                                }
                                html+='>' + data[i].categoryName +'</option>';
                            }
                            //选择level2更新
                            $('#levelTwo').html(html);
                            form.render();
                        }
                    })

                    var levelThree = '${app.categoryLevel3.id}';
                    //三级曾经选过
                    $.ajax({
                        url:'${ctx}/category/queryLevelThreeByLevelTwo/' + levelTwo,
                        type:'post',
                        contentType: 'application/json',
                        //type:'get',
                        success:function (data) {
                            //根据data修改数据，重新渲染即可
                            var html="<option value=\"\">-请选择-</option>";
                            var len = data.length;
                            for(var i = 0; i <len;i++){
                                html += '<option value="' + data[i].id +'"';
                                if(data[i].id == levelThree){
                                    html+= ' selected';
                                }
                                html+='>' + data[i].categoryName +'</option>';
                            }
                            //选择level3更新
                            $('#levelThree').html(html);
                            form.render();
                        }
                    })
                }
            }
        })
    });
</script>
</body>
</html>
