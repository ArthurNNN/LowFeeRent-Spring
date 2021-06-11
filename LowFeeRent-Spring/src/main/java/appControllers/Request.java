package appControllers;

import java.time.LocalDate;
//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.Utils;


@Entity
@Table
public class Request {
	
	@Id
	String id;
	String personId;
	LocalDate checkin;
	LocalDate checkout;
	int priceMax;
	Integer areaMin;
	Integer roomsMin;
	Integer bathroomsMin;

	public Request() {
		super();
		this.setId();
	}

	public Request(
			 LocalDate checkin, LocalDate checkout, 
			int priceMax, Integer areaMin, Integer roomsMin,
			Integer bathroomsMin) {
		super();
		this.setId();
		this.checkin = checkin;
		this.checkout = checkout;
		this.priceMax = priceMax;
		this.areaMin = areaMin;
		this.roomsMin = roomsMin;
		this.bathroomsMin = bathroomsMin;

	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = "r" + Utils.generateId();
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public int getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}

	public Integer getAreaMin() {
		return areaMin;
	}

	public void setAreaMin(Integer areaMin) {
		this.areaMin = areaMin;
	}

	public Integer getRoomsMin() {
		return roomsMin;
	}

	public void setRoomsMin(Integer roomsMin) {
		this.roomsMin = roomsMin;
	}

	public Integer getBathroomsMin() {
		return bathroomsMin;
	}

	public void setBathroomsMin(Integer bathroomsMin) {
		this.bathroomsMin = bathroomsMin;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", personId=" + personId 
				// + ", checkin=" + checkin + ", checkout=" + checkout
				+ ", priceMax=" + priceMax + ", areaMin=" + areaMin + ", roomsMin=" + roomsMin + ", bathroomsMin="
				+ bathroomsMin + "]";
	}

}
