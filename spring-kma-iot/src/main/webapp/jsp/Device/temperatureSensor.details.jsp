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
								<th><b>Thông tin thiết bị</b></th>
								<th style="color: red">${message}</th>
							</tr>
						</thead>
						<tbody  >
							<script>connect_cbnd();</script>
							<tr class="active">
								<th>MAC ID:</th>
								<td id="cbnd-mac" ><b>${sensor.mac_address}</b></td>
							</tr>
							<tr>
								<th>Tên:</th>
								<td>${sensor.name}</td>
							</tr>
							<tr>
								<th>Vị trí:</th>
								<td>${sensor.location}</td>
							</tr>
							<tr>
								<th>Nhiệt độ:</th>
								<td id="cbnd-temperature">${sensor.temperature_value}</td>
							</tr>	
							<tr>
								<th >Độ ẩm:</th>
								<td id="cbnd-humidity">${sensor.humidity_value}</td>
							</tr>
							<tr>
								<th>Thời gian cập nhập:</th>
								<td id="cbnd-status">${sensor.status_time}</td>
							</tr>		
							<tr>
								<td><a href="/device/details">Quay lại</a></td>
								<td><a href="/device/${sensor.type_code}/delete/${sensor.mac_address}">Xóa</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
