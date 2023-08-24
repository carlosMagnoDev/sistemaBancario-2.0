package entities;

import connector.Database;

public class Account{
	
	Database database = new Database();
	
	private String number; // atributo senha
	private String holder; // atributo titular da conta
	private String cpf; // atributo cpf do titular
	private String birthDate; // atributo data de nascimento do titular
	private String contac; // atributo contato
	private double balance; // atributo deposito
	
	// Construtores --------------------------------------------------------------------------
	public Account (String number, String holder, String cpf, String contac, String birthDate) {
		this.number = number;
		this.holder = holder;
		this.cpf = cpf;
		this.contac = contac;
		this.birthDate = birthDate;
	}
	
	public Account (String number, String holder, String cpf, String contac, String brithDate, double balance) {
		this.number = number;
		this.holder = holder;
		this.cpf = cpf;
		this.contac = contac;
		this.birthDate = brithDate;
		this.balance = balance;
	}
	// ---------------------------------------------------------------------------------------

	// ENCAPSULAMENTO - getts AND setss ------------------------------------------------------
	public String getHolder () {
		return holder;
	}
	
	public void setHolder (String holder) {
		this.holder = holder;
	}
	
	public String getNumber () {
		return number;
	}

	public String getCpf() {
		return cpf;
	}

	public String getContac() {
		return contac;
	}

	public void setContac(String contac) {
		this.contac = contac;
	}

	public String getBrithDate() {
		return birthDate;
	}

	public void setBrithDate(String brithDate) {
		this.birthDate = brithDate;
	}

	public double getBalance() {
		return balance;
	}
	// ---------------------------------------------------------------------------------------
	
	// metodos -------------------------------------------------------------------------------
	
	public void deposit (double deposit) {
		this.balance += deposit;
	}
	
	public void withdraw (double withdraw) {
		this.balance = (this.balance - withdraw) - 5.0;
	}
	
	public String toString () {
		return "\nTitular: " + holder + "\nSaldo: R$ " + String.format("%.2f", balance);
	}
	
	// ---------------------------------------------------------------------------------------
	
	// ---------------------------------------------------------------------------------------
	
}
