package com.lfr.rental;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="apartment")
public class Apartment {

	@Id
	String id;
	String personId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ElementCollection
	List<LocalDate> openDates;
	@Column(name="price")
	int price;
	@Column(name="area")
	int area;
	@Column(name="rooms")
	int rooms;
	@Column(name="bathrooms")
	int bathrooms;
	String address;

	private static int counter;

	public static int getNumOfInstances() {
		return counter;
	}

	public Apartment() {
		super();
		//counter++;
		this.setId();
		this.openDates = new ArrayList<LocalDate>();
		
	}

	public Apartment(int price, int area, int rooms, int bathrooms, String address) {
		super();
		counter++;
		this.setId();
		this.price = price;
		this.area = area;
		this.rooms = rooms;
		this.bathrooms = bathrooms;
		this.address = address;
		
	}

	public String getId() {
		return id;
	}

	public void setId() {
		String part = "";
		if (Apartment.getNumOfInstances() < 10) {
			part = "0";
		}
		this.id = "a" + part + Apartment.getNumOfInstances();

	}

	public String getPersonId() {

		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public List<LocalDate> getDates() {
		return openDates;
	}

	public void setDates(List<LocalDate> dates) {
		this.openDates = dates;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public List<LocalDate> getOpenDates() {
		return openDates;
	}

	public void setOpenDates(List<LocalDate> openDates) {
		this.openDates = openDates;
	}

	@Override
	public String toString() {
		return "Apartment [id=" + id + ", personId=" + personId +
		// ", openDates=" + openDates +
				", price=" + price + ", area=" + area + ", rooms=" + rooms + ", bathrooms=" + bathrooms + ", address="
				+ address + "]";
	}
}
