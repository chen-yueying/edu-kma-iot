<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tiles:insertDefinition name="user">
	<tiles:putAttribute name="body">
			<div class="container-fluid body">
				<div class="table-responsive">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th><b>Thông tin người dùng</b></th>
								<th style="color: red">${message}</th>
							</tr>
						</thead>
						<tbody>
							<tr class="active">
								<th>Số điện thoại đăng nhập</th>
								<td><b>${user.username}</b></td>
							</tr>
							<tr>
								<th>Họ và tên</th>
								<td>${user.fullname}</td>
							</tr>
							<tr>
								<th>Email</th>
								<td>${user.email}</td>
							</tr>
							<tr>
								<th>Địa chỉ</th>
								<td>${user.address}</td>
							</tr>	
							<tr>
								<th>Mật khẩu</th>
								<td>*********</td>
							</tr>	
							<tr>
								<td><a href="/acount/edit">Chỉnh sửa</a></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
