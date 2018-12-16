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
							<th><b>Thêm thiết bị</b></th>
							<th style="color: red">${message}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<form:form action="/device/save" method="post"
							modelAttribute="device-info">
							<tr>
								<th>MAC ID</th>
								<td><form:input type="text" path="mac_address" size="10"
										cssClass="form-control input-sm"
										cssStyle="border: 1px solid#ead8d8;"
										placeholder="Nhập địa chỉ mac của thiết bị" autofocus="autofocus" /></td>
								<td><form:errors path="mac_address" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<th>Tên</th>
								<td><form:input type="text" path="name" size="10"
										cssClass="form-control input-sm"
										cssStyle="border:1px solid#ead8d8;"
										placeholder="Nhập tên thiết bị ứng với chức năng" /></td>
								<td><form:errors path="name" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<th>Vị trí</th>
								<td><form:input type="text" path="location" size="10"
										cssClass="form-control input-sm"
										cssStyle="border:1px solid#ead8d8;"
										placeholder="Nhập vị trí đặt thiết bị" /></td>
								<td><form:errors path="location" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<th>Loại thiết bị</th>
								<td><form:select path="type_code" cssClass="select-device">
										<form:options  items="${classifies}"/>
									</form:select></td>
									<td><form:errors path="type_code" cssStyle="color:red" /></td>
							</tr>
							<tr>
								<td><button type="submit"
										class="btn-device">Lưu thiết bị</button></td>
								<td><a href="/device/details">Quay lại</a></td>
								<td></td>
							</tr>
						</form:form>
					</tbody>
				</table>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>