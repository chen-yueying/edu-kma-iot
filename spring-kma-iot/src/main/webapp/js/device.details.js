function listDevice() {
	var selected = document.getElementById('type_code').value;
	var path = "/device/" + selected;
	console.log(path);
	window.location.href = path;
}

var stompClient = null;
function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected stomp client...");
}

function connect_cbnd() {
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	var mac = document.getElementById('cbnd-mac').value;
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/cbnd/'+ mac, function(sensor) {
			var sen = JSON.parse(sensor.body);
			document.getElementById('cbnd-temperature').innerHTML = sen.temperature_value;
			document.getElementById('cbnd-humidity').innerHTML = sen.humidity_value;
			document.getElementById('cbnd-status').innerHTML = sen.status_time;

		});
	});
}