package com.lfr.housing;

import java.time.LocalDate;
import java.util.HashMap;


import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import com.lfr.utils.Utils;


@Entity
@Table
public class Apartment {
	
	
	@Id
	String id;
	String personId;
	HashMap<LocalDate, LocalDate> openDates;
	int price;
	int area;
	int rooms;
	int bathrooms;
	String address;

	public Apartment() {
		super();
		this.setId();
		this.openDates = new HashMap<LocalDate, LocalDate>();
	}

	public Apartment(int price, int area, int rooms, int bathrooms,
			String address) {
		super();
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
		this.id = "a" + Utils.generateId();

	}

	public String getPersonId() {

		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public HashMap<LocalDate, LocalDate> getDates() {
		return openDates;
	}

	public void setDates(HashMap<LocalDate, LocalDate> dates) {
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

	public HashMap<LocalDate, LocalDate> getOpenDates() {
		return openDates;
	}

	public void setOpenDates(HashMap<LocalDate, LocalDate> openDates) {
		this.openDates = openDates;
	}

	@Override
	public String toString() {
		return "Apartment [id=" + id + ", personId=" + personId + 
				// ", openDates=" + openDates + 
				", price=" + price
				+ ", area=" + area + ", rooms=" + rooms + ", bathrooms=" + bathrooms + ", address=" + address + "]";
	}
}
