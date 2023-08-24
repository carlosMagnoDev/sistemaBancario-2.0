package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class IsValid{

	String [] error = {"campo vazio", "apenas letras", "apenas números", "caracteres especiais não são permitidos"};
	
	public String birthDate(String birthDate) throws ParseException {
		if (!birthDate.isEmpty()) {
			
			String validarData = "^(0[1-9]|1\\d|2\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
            String validarSomenteNumeros = "\\d+";
            String validarSomenteLetras = "[a-zA-Z]+";

            if (birthDate.matches(validarData)) {
            	
                SimpleDateFormat formatoDataOriginal = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoDataSql = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatoDataOriginal.parse(birthDate);
                birthDate = formatoDataSql.format(date);
                return birthDate;
                
            } else if (birthDate.matches(validarSomenteNumeros)) {
                System.out.println("Erro: a data de nascimento deve conter apenas números, tente novamente!");
            } else if (birthDate.matches(validarSomenteLetras)) {
                System.out.println("Erro: a data de nascimento deve conter apenas números, tente novamente!");
            } else {
                System.out.println("Erro: formato de data inválida, tente novamente! (dd/mm/aaaa)");
            }
		} else {
			System.out.printf("Erro: %s, tente novamente!\n", error[0]);
		}
		return null;
	}
	
	public boolean birthDateB(String birthDate) throws ParseException {
		if (!birthDate.isEmpty()) {
			
			String validarData = "^(0[1-9]|1\\d|2\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
			String validarSomenteNumeros = "\\d+";
			String validarSomenteLetras = "[a-zA-Z]+";
			
			if (birthDate.matches(validarData)) {
				
				SimpleDateFormat formatoDataOriginal = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatoDataSql = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatoDataOriginal.parse(birthDate);
				birthDate = formatoDataSql.format(date);
				return true;
				
			} else if (birthDate.matches(validarSomenteNumeros)) {
				System.out.println("Erro: a data de nascimento deve conter apenas números, tente novamente!");
			} else if (birthDate.matches(validarSomenteLetras)) {
				System.out.println("Erro: a data de nascimento deve conter apenas números, tente novamente!");
			} else {
				System.out.println("Erro: formato de data inválida, tente novamente! (dd/mm/aaaa)");
			}
		} else {
			System.out.printf("Erro: %s, tente novamente!\n", error[0]);
		}
		return false;
	}
	
	public boolean isConfirm (String confirm) {
		while (true) {
			if (!confirm.isEmpty()) {
				try {
					Integer.parseInt(confirm);
					System.out.println("Error: valor digitado deve ser (sim/não), tente novamente!");
					return false;
				} catch (Exception e) {
					if (confirm.equals("sim") || confirm.equals("s") || confirm.equals("nao") || confirm.equals("não") || confirm.equals("n")) {
						return true;						
					} else {
						System.out.println("Error: valor digitado deve ser (sim/não), tente novamente!");
						return false;
					}
				}
			} else {
				System.out.printf("Erro: senha vazia, tente novamente!\n");
				return false;
			}
		}
	}
	
	public boolean isOpc (String opc) {
		while (true) {
			if (!opc.isEmpty()) {
				try {
					Integer.parseInt(opc);
					return true;
				} catch (Exception e) {
					System.out.println("Error: apenas números, tente novamente!");
					return false;
				}
			} else {
				System.out.printf("Erro: senha vazia, tente novamente!\n");
				return false;
			}
		}
	}
	
	public boolean isNumber (String number) {
		while (true) {
			if (!number.isEmpty()) {
				if (number.length() == 6) {
					try {
						Integer.parseInt(number);
						return true;
					} catch (Exception e) {
						System.out.println("Error: apenas números!");
						return false;
					}
					
				} else {
					System.out.println("Error: a senha precisa conter 6 digitos!");
					return false;
				}
			} else {
				System.out.printf("Erro: senha vazia, tente novamente!\n");
				return false;
			}
		}
	}
	
	public boolean isHolder (String holder) {
		while (true) {
			if (!holder.isEmpty()) {
				try {
					Integer.parseInt(holder);
					System.out.println("Error: apenas letras");
					return false;
				} catch (Exception e) {
					if (holder.matches("[A-Za-z ]+")) {
						return true;
					} else {
						System.out.println("Error: caracteres especiais, tente novamente!");
						return false;
					}
				}
			} else {
				System.out.printf("Erro: senha vazia, tente novamente!\n");
				return false;
			}
		}
	}
	
	public boolean isContac (String contact) {
		List<String> dddsValidos = Arrays.asList(
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28",
				"31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47",
				"48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68",
				"69", "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87",
				"88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99");
		
		while (true) {
			if (!contact.isEmpty()) {
				String dds = contact.substring(0, 2);
				
				if (dddsValidos.contains(dds)) {
					try {
						Long.parseLong(contact);
						if (contact.matches("[0-9]{11}")) {
							return true;
						} else if (contact.length() < 11){
							System.out.print("Error: quantidade de números insuficientes, tente novamente!\n");
							return false;
						} else if (contact.length() > 11) {
							System.out.println("Error: quantidade de números excede, tente novamente");
							return false;
						}
					} catch (Exception e) {
						System.out.printf("Error: %s, tente novamente!\n", error[2]);
						return false;
					}
					
				} else {
					System.out.println("Error: DDD inválido, tente novamente!");
					return false;
				}
				
			} else {
				System.out.printf("Error: %s, tente novamente!\n", error[0]);
				return false;
			}
		}	
	}
	
	
	public boolean isBalance (String balance) {
		while (true) {
			if (!balance.isEmpty()) {
				try {
					Double.parseDouble(balance);
					return true;
				} catch (Exception e) {
					System.out.println("Error: valor digitado não é um número!");
					return false;
				}
			} else {
				System.out.println("Erro: caractere vazio, tente novamente!\n");
				return false;
			}
		}
	}
	
	public boolean isDeposit (String deposit) {
		while (true) {
			if (!deposit.isEmpty()) {
				try {
					Double.parseDouble(deposit);
					return true;
				} catch (Exception e) {
					System.out.println("Error: valor digitado não é um número!");
					return false;
				}
			} else {
				System.out.println("Erro: caractere vazio, tente novamente!");
				return false;
			}	
		}
	}
	
	public boolean isWithdraw (String withdraw) {
		while (true) {
			if (!withdraw.isEmpty()) {
				try {
					Double.parseDouble(withdraw);
					return true;
				} catch (Exception e) {
					System.out.println("Error: valor digitado não é um número!");
					return false;
				}
			} else {
				System.out.println("Erro: caractere vazio, tente novamente!");
				return false;
			}	
		}
	}

	
    public static boolean isCpf(String CPF) {
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {

            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }

        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }
	
}
