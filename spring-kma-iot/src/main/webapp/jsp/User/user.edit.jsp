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
							<th><b>Cập nhập người dùng</b></th>
							<th style="color: red">${message}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<form:form action="/acount-update" method="post"
							modelAttribute="info">
							<form:hidden path="username" />
							<tr class="active">
								<th>Số điện thoại người dùng</th>
								<td><b>${info.username}</b></td>
								<td></td>
							</tr>
							<tr>
								<th>Họ và tên</th>
								<td><form:input type="text" path="fullname" size="10"
										cssClass="form-control input-sm" cssStyle="border:none;" /></td>
								<td><form:errors path="fullname" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<th>Email</th>
								<td><form:input type="text" path="email" size="10"
										cssClass="form-control input-sm" cssStyle="border:none;"
										autofocus="autofocus" /></td>
								<td><form:errors path="email" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<th>Địa chỉ</th>
								<td><form:input type="text" path="address" size="10"
										cssClass="form-control input-sm" cssStyle="border:none;" /></td>
								<td><form:errors path="address" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<th>Mật khẩu cũ</th>
								<td><input type="password" name="oldPass" size="10"
									placeholder="Nhập mật khẩu cũ vào đây"
									class="form-control input-sm" style="border: none;" /></td>
								<td style="color: red">${errorpassword}</td>
							</tr>
							<tr>
								<th>Mật khẩu mới</th>
								<td><form:input type="password" path="password" size="10"
										placeholder="Nhập mật khẩu cũ nếu không thay đổi"
										cssClass="form-control input-sm" cssStyle="border:none;" /></td>
								<td><form:errors path="password" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<td><button style="padding: 0" type="submit"
										class="btn btn-link">Lưu thay đổi</button></td>
								<td><a href="/acount/details">Quay lại</a></td>
								<td></td>
							</tr>
						</form:form>
					</tbody>
				</table>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>