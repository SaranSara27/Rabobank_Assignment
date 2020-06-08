package com.rabobank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Saranya
 *
 */
public class Record {
	
	@JsonProperty("Reference")
	private int transactionRef;
	@JsonProperty("AccountNumber")
	private String accountNumber;
	@JsonProperty("Start Balance")
	private double startBalance;
	@JsonProperty("Mutation")
	private double mutation;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("End Balance")
	private double endBalance;

	public Record() {
	}

	public Record(int transactionRef, String accountNumber, double startBalance, double mutation, String description,
			double endBalance) {
		super();
		this.transactionRef = transactionRef;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
	}

	public int getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(int transactionRef) {
		this.transactionRef = transactionRef;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	public double getMutation() {
		return mutation;
	}

	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

}
