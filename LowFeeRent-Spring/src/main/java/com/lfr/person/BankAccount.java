package com.lfr.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankAccount {
	
    @Id
	String iban;
	double amount;
	
	
	
	public BankAccount() {
		super();
	}

	public BankAccount(String iban, double amount) {
		super();
		this.iban = iban;
		this.amount = amount;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BankAccount [iban=" + iban + ", amount=" + amount + "]";
	}



}
