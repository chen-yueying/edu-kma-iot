<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<div class="container-fluid menu">
		<form method="get" class="fs-1" action="">
			<div class="input-group">
				<input type="text" class="form-control fs-2"
					placeholder="Tìm kiếm ...">
				<div class="input-group-btn">
					<button class="btn btn-warning fs-3" type="submit">
						<i class="fa fa-search fs-4"></i>
					</button>
				</div>
			</div>
		</form>
	<div class="menu1">
			<ul class="list-group">
				<li class="list-group-item"><a href="">Cửa hàng</a></li>
				<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
					<li class="list-group-item"><a href="/acount/details">Người
							dùng</a></li>
					<li class="list-group-item"><a href="/device/details">Quản
							lý thiết bị</a><span class="badge"></span></li>
				</sec:authorize>
			</ul>
	</div>
</div>