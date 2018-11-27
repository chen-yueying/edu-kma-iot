package edu.kma.iot.dao.model;

public class Device {
	private String mac_address;
	private String name;
	private String owner;
	private String status_time;

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus_time() {
		return status_time;
	}

	public void setStatus_time(String status_time) {
		this.status_time = status_time;
	}

}
