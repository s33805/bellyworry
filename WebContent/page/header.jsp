﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="decorative2">
    <div class="container">

       <div class="divPanel topArea notop nobottom">
            <div class="row-fluid">
                <div class="span12">

                    <div id="divLogo" class="pull-left">
                        <a href="index.jsp" id="divSiteTitle">Belly Worry</a><br />
                        <a href="index.jsp" id="divTagLine">Enjoy Your Life</a>
                    </div>

                    <div id="divMenuRight" class="pull-right" ">
<!-- 登入成功秀修改登出-->     <div class="navbar">
                        <button type="button" class="btn btn-navbar-highlight btn-large btn-primary" data-toggle="collapse" data-target=".nav-collapse">
                            功能選單 <span class="icon-chevron-down icon-white"></span>
                        </button>
                        <div class="nav-collapse collapse">
                        
              <!-- 登入登出 --><div style="position:absolute; top:20px; right:20px;">
              					<c:choose>
              						<c:when test="${empty user.id}">
              							<a href="${pageContext.request.contextPath}/login.jsp" id="dropdown-my">會員登入</a>
              						</c:when>
              						<c:otherwise>	
              							<span style="color:#FF0000;font-family: impact;">${user.nickname}&nbsp;您好&nbsp;</span><span>${suc.update}</span>&nbsp;<a href="${suc.out}">登出</a>
              						</c:otherwise>
              					</c:choose>
              				</div>                            
                            
                            <ul class="nav nav-pills ddmenu">
                                <c:if test="${ funcName != 'FOOD' }">
                                <li class="dropdown" >
                                </c:if>
                                <c:if test="${ funcName == 'FOOD' }"> 
					            <li class="dropdown active" >
					            </c:if>
                                 	<a href="javascript:void(0)" class="dropdown-toggle">健康食譜<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                       <li><a href="${pageContext.request.contextPath}/Menuservelt.controller">菜色&食譜</a></li>
<%--                                         <li><a href="${pageContext.request.contextPath}/logout.controller">登出</a></li> --%>
                                        <li><a href="${pageContext.request.contextPath}/Mealservelt.controller">套餐</a></li>
<!--                                         <li><a href="../documentation/index.html">說明文件</a></li> -->
<!--                                         <li class="dropdown"> -->
<!--                                         <a href="full.html" class="dropdown-toggle">分欄參考</a> -->
<!--                                         <ul class="dropdown-menu sub-menu"> -->
<!--                                         <li><a href="full.html">單欄版型</a></li> -->
<!--                                         <li><a href="2-column.html">兩欄版型</a></li> -->
<!--                                         <li><a href="3-column.html">三欄版型</a></li> -->
<!--                                         </ul> -->
<!--                                         </li> -->
                                    </ul>
                                </li>
                                <c:if test="${ funcName != 'CAL' }">
                                <li class="dropdown"><a href="${pageContext.request.contextPath}/foodCal.controller?pageNo=1">熱量計算</a></li>
                                </c:if>
                                <c:if test="${ funcName == 'CAL' }">
                                <li class="dropdown active"><a href="javascript:void(0)">熱量計算</a></li>
                                </c:if>
                                <c:if test="${ funcName != 'DIARY' }">
                                <li class="dropdown"><a href="diaryIndex.controller">健康管理</a></li>
                                </c:if>
                                <c:if test="${ funcName == 'DIARY' }"> 
                                <li class="dropdown active"><a href="javascript:void(0)">健康管理</a></li>
                                </c:if>
                                <c:if test="${ funcName != 'FUN' }">
                                <li class="dropdown" >
                                </c:if>
                                <c:if test="${ funcName == 'FUN' }"> 
					            <li class="dropdown active" >
					            </c:if>
                                    <a href="javascript:void(0)" class="dropdown-toggle">運動趣<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/YouBike.jsp">YouBike路線規劃</a></li>
                                        <li><a href="${pageContext.request.contextPath}/healthView.jsp">健康景點查詢</a></li>
                                    </ul>
                                </li>
	                            <c:if test="${ funcName != 'ACT' }">
                                <li class="dropdown"><a href="${pageContext.request.contextPath}/activity.controller?pageNo=1">活動情報</a></li>
                                </c:if>
                                <c:if test="${ funcName == 'ACT' }">
                                <li class="dropdown active"><a href="javascript:void(0)">活動情報</a></li>
                                </c:if>
                                <c:if test="${ funcName != 'RANK' }"> 
                                <li class="dropdown"><a href="${pageContext.request.contextPath}/hero.controller">英雄事蹟</a></li>
                                </c:if>
                                <c:if test="${ funcName == 'RANK' }"> 
                                <li class="dropdown active"><a href="javascript:void(0)">英雄事蹟</a></li>
                                </c:if>
                                <c:if test="${ funcName != 'EXAM' }"> 
                                <li class="dropdown"><a href="${pageContext.request.contextPath}/exam.controller?pageNo=1" >小學堂</a></li>
                                </c:if>
                                <c:if test="${ funcName == 'EXAM' }"> 
                                <li class="dropdown active"><a href="javascript:void(0)" >小學堂</a></li>
                                </c:if>                                 
                            </ul>
                        </div>
                    </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>

