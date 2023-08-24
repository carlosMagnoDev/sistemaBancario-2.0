package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	
	public void conectar() {
		String servidor = "jdbc:mysql://localhost:3306/sistemaBancario?useSSL=false";
		String usuario = "root";
		String senha = "8585123445!";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
			
		} catch (Exception e) {
			System.out.println("Error " +e.getMessage());
		}
	}
	
	public boolean estaConectado() {
		if(this.connection != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void accountDatabase (String number, String holder, String cpf, String contac, String birthDate) {
		try {
			String query = "INSERT INTO account(holder, numberAccount, cpf, contac, birthDate) "
				+ "VALUES ('"+holder+"', '"+number+"', '"+cpf+"', '"+contac+"', '"+birthDate+"');";
			this.statement.executeUpdate(query);
			System.out.println("Conta aberta com sucesso!\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void accountDatabaseBalance (String number, String holder, String cpf, String contac, String birthDate, double balance) {
		try {
			String query = "INSERT INTO account(holder, numberAccount, cpf, contac, birthDate, balance) "
					+ "VALUES ('"+holder+"', '"+number+"', '"+cpf+"', '"+contac+"', '"+birthDate+"', '"+balance+"');";
			this.statement.executeUpdate(query);
			System.out.println("Conta aberta com sucesso!\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public String accountData(String numberAccount) {
		try {
			String query = "select holder, balance from account where numberAccount = "+numberAccount+";";
			this.resultset = this.statement.executeQuery(query);
			while(this.resultset.next()) {
			return "TITULAR: " + this.resultset.getString("holder") + "\nDEPOSITO: " + this.resultset.getString("balance");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return numberAccount;
	}
	
	public String getNumberAccount (String number) {
		try {
			String query = "select numberAccount from account where numberAccount = "+number+";";
			this.resultset = this.statement.executeQuery(query);
			
			if (this.resultset.next()) {
				return this.resultset.getString("numberAccount");
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return number;
	}
	
	public boolean checkNumberAccount (String holder, String number) {
		try {
			String query = "select numberAccount from account where holder = '"+holder+"' and numberAccount = "+number+";";
			this.resultset = this.statement.executeQuery(query);
			
			if (this.resultset.next()) {
				System.out.println("Conta conectada com sucesso!");
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
	
	public String getBalance (String number) {
		try {
			String query = "select balance from account where numberAccount = "+number+";";
			this.resultset = this.statement.executeQuery(query);
			
			if (this.resultset.next()) {
				return this.resultset.getString("balance");
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return number;
	}
	
	public void depositDataBase (String deposit, String numberAccount) {
		try {
			String query = "update account set balance = balance + "+deposit+" where numberAccount = "+numberAccount+";";
			this.statement.executeUpdate(query);
			System.out.println("deposito realizado com sucesso!");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void withdrawDataBase (String withdraw, String numberAccount) {
		try {
			String query = "update account set balance = balance - "+withdraw+" where numberAccount = "+numberAccount+";";
			this.statement.executeUpdate(query);
			System.out.println("saque realizado com sucesso!");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void desconectar() {
		try {
			System.out.println("Sistema encerrado!");
			this.connection.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
