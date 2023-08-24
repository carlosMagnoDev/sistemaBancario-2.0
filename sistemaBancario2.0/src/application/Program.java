package application;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;

import connector.Database;
import entities.Account;
import util.CryptoUtils;
import util.IsValid;

public class Program {
	public static void main(String[] args) {
		
		AnsiConsole.systemInstall();
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		Database database = new Database(); // instancia -> banco de dados
		Account account = null; // instancia -> conta
		IsValid isValid = new IsValid(); 

		
		database.conectar();
		if (database.estaConectado()) {
			while (true) {
				try {
					System.out.println("bem-vindo ao Sistema Bancário!\n"
				+ "\nDigite o número correspondente à ação que deseja executar:");
					
				System.out.println("1. Acessar sua conta \n2. Abrir uma conta \n3. Encerrar sistema");
				
				String opc;
				int opc2;
				while (true) {
					System.out.print("\nDigite o número da opção desejada: ");	
					opc = input.nextLine();
					if (isValid.isOpc(opc)) {
						opc2 = Integer.parseInt(opc);
						break;
					}
				}
				
				if (opc2 == 1) {

					int cont = 0;
					while (cont != 3 ) {
						System.out.println("\nPara acessar sua conta, basta digitar!\n- Nome do titular \n- Sua senha");
					
						String holderLogin;
						while (true) {
							System.out.print("Titular:");
							holderLogin = input.nextLine();
						
							if (isValid.isHolder(holderLogin) == true) {
								break;
							}
						}
						
						String numberLogin;
						BigInteger criptLogin;
						while (true) {
							System.out.print("Senha:");
							numberLogin = input.nextLine();
							
							if (isValid.isNumber(numberLogin) == true) {
								break;
							}	
						}
						
						BigInteger cript;
						cript = CryptoUtils.encrypt(numberLogin);
						
						String numberDatabase = database.getNumberAccount(cript.toString());
						
						if (database.checkNumberAccount(holderLogin, cript.toString())) {

							System.out.println("\nDADOS DA CONTA:\n" + database.accountData(numberDatabase));
							while (true) {
								System.out.println("\nDigite o número correspondente à ação que deseja executar:"
										+ "\n1. Deposito \n2. Saque \n3. Sair");
								
								String opcAccount;
								int opcAccount2;
								while (true) {
									System.out.print("Digite o número da ação desejada: ");
									opcAccount = input.nextLine();
					
									if (isValid.isOpc(opcAccount)) {
										opcAccount2 = Integer.parseInt(opcAccount);
										break;
									}
								}
								
								if (opcAccount2 == 1) {
									
									String depositValue;
									while (true) {
										System.out.print("informe o valor que deseja depositar:");
										depositValue = input.nextLine();
										
										if (isValid.isDeposit(depositValue)) {
											database.depositDataBase(depositValue, numberDatabase);
											System.out.println("\nDADOS DA CONTA ATUALIZADOS!\n" + database.accountData(numberDatabase));
											break;
										}
									}
									
									
								} else if (opcAccount2 == 2) {
									String withdrawValue;
									String balanceValueDatabase;
									while (true) {
										System.out.print("informe o valor que deseja sacar:");
										withdrawValue = input.nextLine();
										
										if (isValid.isWithdraw(withdrawValue)) {
											
											balanceValueDatabase = database.getBalance(numberDatabase);  
											if (Double.parseDouble(withdrawValue) > Double.parseDouble(balanceValueDatabase)) {
												System.out.println("Error: o valor do saque é maior do que o deposito!");
												break;
											} else {
												database.withdrawDataBase(withdrawValue, numberDatabase);	
											}											
											System.out.println("\nDADOS DA CONTA ATUALIZADOS!\n" +  database.accountData(numberDatabase));
											break;
										}
									}
								} else if (opcAccount2 == 3) {
									System.out.println("Conta desconectada com sucesso!");
									System.exit(0);
									
								} else {
									System.out.println("Error: número inválido, tente novamente!");
								}
									
							}
							
						} else {
							cont += 1;
							System.out.println("Error: senha inválida (" + cont + "/3), tente novamente!");
							if (cont == 3) {
								System.out.println("Você excedeu o limite de tentativas incorretas!\n");
							}
							
						}
					}
					

				} else if (opc2 == 2) {	
					
					System.out.println("\nPara abrir uma conta, é necessário ter idade maior ou igual a 18 anos!");
					while (true) {
						LocalDate data_atual = LocalDate.now();
						
						String birthDate = null;
						while (true) {
							System.out.print("Digite sua data de nascimento: ");
							birthDate = input.nextLine();
							
							if (isValid.birthDateB(birthDate) == true) {
								break;
							}
							
						}
						
						if (isValid.birthDateB(birthDate) == true) {
								
							String dateIsValid = isValid.birthDate(birthDate);
							LocalDate data_titular = LocalDate.parse(dateIsValid);
								
							Long daysDifference = ChronoUnit.DAYS.between(data_titular, data_atual);
								
							if (daysDifference >=  6570) {
								System.out.println("\nDigite os dados solicitados a seguir para concluir o registro!\n");
								
								String number;
								BigInteger cripto;
								while (true) {
									System.out.println("Sua senha precisa precisa conter 6 digitos numericos");
									System.out.print("Digite sua senha:");
									number = input.nextLine();
									
									if (isValid.isNumber(number) == true) {
										cripto = CryptoUtils.encrypt(number);
										break;
									}	
								}
								
								String holder;
								while (true) {
									System.out.print("Digite seu nome completo:");
									holder = input.nextLine();
									
									if (isValid.isHolder(holder) == true) {
										break;
									}
								}
								
								String cpf;
								while (true) {
									System.out.print("Digite seu cpf:");
									cpf = input.nextLine();
									
									if (isValid.isCpf(cpf) == true) {
										break;
									}
								}
								
								String contac;
								while (true) {
									System.out.print("Digite o seu telefone para contato:");
									contac = input.nextLine();
									
									if (isValid.isContac(contac) == true) {
										break;
									}
								}
								
								String birthDate2 = isValid.birthDate(birthDate);
								
								String confirm;
								while (true) {
									System.out.print("\nDeseja fazer um deposito inicial (sim/não)? ");
									confirm = input.nextLine().toLowerCase();
									
									if (isValid.isConfirm(confirm)) {
										break;
									}
									
								}
								
								while (true) {
									if (confirm.equals("sim") || confirm.equals("s")) {
										
										String initialDeposit;
										while (true) {
											System.out.print("Digite o deposito inicial: ");
											initialDeposit = input.nextLine();
											if (isValid.isBalance(initialDeposit) == true) {
												double initialDeposit2 = Double.parseDouble(initialDeposit);
												
												account = new Account(cripto.toString(), holder, cpf, contac, birthDate2, initialDeposit2);							
												System.out.println("\nDADOS DA CONTA:" + account);
												
												database.accountDatabaseBalance(cripto.toString(), holder, cpf, contac, birthDate2, initialDeposit2);
												break;
											}
										}
										
									} else if (confirm.equals("nao") || confirm.equals("não") || confirm.equals("n")) {			
										account = new Account(cripto.toString(), holder, cpf, contac, birthDate2);
										System.out.println("\nDADOS DA CONTA:" + account);
										
										database.accountDatabase(cripto.toString(), holder, cpf, contac, birthDate2);
										break;
									} else {
										System.out.println("Error: resposta não identificada, tente novamente!");
									}
									
									break;
								}
								
							} else {
								System.out.println("Menor de idade");
								break;
							}	
						}
						break;
					}
					
				} else if (opc2 == 3) {
					database.desconectar();
					break;
					
				} else {
					System.out.println("Error: número digitado inválido, tente novamente!");
				}
				
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}	
			}
			
		} else {
			System.out.println("Error: falha no sistema!");
			database.desconectar();
		}
		AnsiConsole.systemUninstall();
	}
}
