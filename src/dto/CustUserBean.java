package dto;

import java.io.Serializable;

public class CustUserBean implements Serializable {
	private String id;
	private String name;
	private String address;
	
	// Bean == Dto == VO
	public CustUserBean() {
		// TODO Auto-generated constructor stub
	}

	public CustUserBean(String id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CustUserBean [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
