package edu.kma.iot;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import edu.kma.iot.dao.model.Device;
import edu.kma.iot.dao.model.SensorTemperature;
import edu.kma.iot.restful.controller.DeviceRestController;
import edu.kma.iot.restful.controller.SensorTemperatureRest;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpringKmaIotApplicationTests {
	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = builder.build();
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	@Test
	public void contextLoads() {
		String address = "http://localhost:8080/rest/device/cbnd/save";
//		ResponseEntity<List> entity = restTemplate.getForEntity(address, List.class);
		//List devices = (List<Device>) entity.getBody();
		
		SensorTemperature sensor = new SensorTemperature();
		sensor.setMac_address("afff-gre-5434-feee-hkods");
		sensor.setHumidity_value(50);
		sensor.setTemperature_value(40);
		sensor.setLocation("nhà vệ sinh");
		sensor.setType_code("cbnd");
		sensor.setName("cảm biến 1");
		sensor.setOwner("0398749499");
		ResponseEntity<String> insertEntity = restTemplate.postForEntity(address, sensor, String.class);
	}

}
