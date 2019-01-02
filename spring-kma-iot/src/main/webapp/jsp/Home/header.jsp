<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar" style="background-color: #828282;"></span> <span
					class="icon-bar" style="background-color: #828282;"></span> <span
					class="icon-bar" style="background-color: #828282;"></span>
			</button>
			<a class="navbar-brand" style="color: #6633CC !important;" href="/">FSMART</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-left nav-left">
				<li><a href="/">HOME</a></li>
				<li><a href="/">GIỚI THIỆU</a></li>
				<li><a href="/">HƯỚNG DẪN</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!hasAnyRole('ROLE_USER','ROLE_ADMIN')">
					<li><a href="/login">Đăng nhập</a></li>
					<li><a href="/register">Đăng ký</a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
					<li><a href="/acount/details"><i class="fa fa-user-o"
							style="font-size: 17px;"></i>
							${pageContext.request.userPrincipal.name}</a></li>
					<li><a
						href="javascript:document.getElementById('logout').submit();"><i
							class="fa fa-sign-out" style="font-size: 17px"></i> Đăng xuất</a></li>
				</sec:authorize>
				<li>
					<form action="/logout" id="logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}">
					</form>
				</li>
			</ul>
		</div>
	</div>
</nav>
