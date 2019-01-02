<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:insertAttribute name="title" ignore="true" />Fsmart Việt Nam</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/style.js"></script>	
<script type="text/javascript" src="/js/device.details.js"></script>	
<script type="text/javascript" src="/js/stomp.js"></script>
<script type="text/javascript" src="/js/sockjs-0.3.4.min.js"></script>		
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="body-content-menu">
		<div class="row">
			<div class="col-sm-3">
				<div class="row">
					<tiles:insertAttribute name="menu" />
				</div>
			</div>
			<div class="col-sm-6">
				<div class="row">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="row">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
		</div>
	</div>
	<div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>