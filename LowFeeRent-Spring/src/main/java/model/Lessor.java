package model;

public class Lessor extends Person {

	String apartmentId;

	public Lessor() {
		super();
		this.setId();
	}

	public Lessor(String apartmentId) {
		super();
		this.setId();
		this.apartmentId = apartmentId;
	}


	public String getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}

	@Override
	public String toString() {
		return "Lessor [apartmentId=" + apartmentId + "]" + super.toString();
	}

}
