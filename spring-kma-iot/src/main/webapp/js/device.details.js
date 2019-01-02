function listDevice() {
	var selected = document.getElementById('type_code').value;
	var path = "/device/" + selected;
	console.log(path);
	window.location.href = path;
}
