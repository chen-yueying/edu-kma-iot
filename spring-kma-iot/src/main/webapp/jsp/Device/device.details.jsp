<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tiles:insertDefinition name="user">
	<tiles:putAttribute name="body">
		<div class="container-fluid body">
			<div class="table-responsive">
				<h4 style="text-align:center; color: red;">${message}</h4>
				<br/>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th><b>Quản lý thiết bị</b></th>
							<th><select class="select-device" name="type_code">
										<option value="">Tất cả</option>
									<c:forEach items="${classifies}" var="classify" varStatus="loop">
										<option value="${classify.type_code}">${classify.type_name}</option>
									</c:forEach>
							</select></th>
							<th></th>
							<th><a href="/device/add">Thêm</a></th>
						</tr>
					</thead>
					<tbody>
						<tr class="info">
							<th class="table-bordered">MAC ID</th>
							<th class="table-bordered">Loại</th>
							<th class="table-bordered">Vị trí</th>
							<th class="table-bordered"></th>
						</tr>
						<c:forEach items="${devices}" var="device" varStatus="loop">
							<tr>
								<td class="table-bordered">${device.mac_address}</td>
								<td class="table-bordered">${device.classify.type_name}</td>
								<td class="table-bordered">${device.location}</td>
								<td class="table-bordered"><a href="">chi tiết</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>