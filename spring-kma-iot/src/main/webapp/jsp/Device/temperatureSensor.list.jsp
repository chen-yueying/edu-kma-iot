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
							<th><select class="select-device" name="type_code" id="type_code" onchange="listDevice()">
									<c:forEach items="${classifies}" var="classify" varStatus="loop">
										<option value="${classify.type_code}">${classify.type_name}</option>
									</c:forEach>
									<option value="details">Tất cả</option>
							</select></th>
							<script type="text/javascript">
									function listDevice(){
												var selected = document.getElementById('type_code').value;
												var path = "/device/" + selected;
												console.log(path);
												window.location.href = path;
										}
							</script>
							<th></th>
							<th></th>
							<th><a href="/device/add">Thêm</a></th>
						</tr>
					</thead>
					<tbody>
						<tr class="active">
							<th class="table-bordered">Tên</th>
							<th class="table-bordered">Vị trí</th>
							<th class="table-bordered">Nhiệt độ - độ ẩm</th>
							<th class="table-bordered">Cập nhập</th>
							<th class="table-bordered"></th>
						</tr>
						<c:forEach items="${sensors}" var="sensor" varStatus="loop">
							<tr>
								<td class="table-bordered">${sensor.name}</td>
								<td class="table-bordered">${sensor.location}</td>
								<td class="table-bordered">${sensor.temperature_value} -  ${sensor.moisture_value}</td>
								<td class="table-bordered">${sensor.status_time}</td>
								<td class="table-bordered"><a href="/device/${sensor.classify.type_code}/get-${sensor.mac_address}">chi tiết</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>