package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.*;

@Entity
@Table
public class Person {
	
	@Id
	private String id;
	private String name;
	private String surname;
	private String email;
	

//	private BankAccount bankAccount;

	public Person() {
		super();
		this.setId();
	}

	public Person(String name, String surname, String email, BankAccount bankAccount) {
		super();
		this.setId();
		this.name = name;
		this.surname = surname;
		this.email = email;
//		this.bankAccount = bankAccount;
	}


	public String getId() {
		return id;
	}
	
	public String setId() {
		return this.id = "u" + Utils.generateId();
	}

//	public BankAccount getBankAccount() {
//		return bankAccount;
//	}
//
//	public void setBankAccount(BankAccount bankAccount) {
//		this.bankAccount = bankAccount;
//	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + 
			//	", bankAccount="				+ bankAccount + 
				"]";
	}





}
