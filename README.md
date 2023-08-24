# Sistema Bancário com Login Criptografado

Este é um projeto de um sistema bancário em Java que utiliza criptografia para o login dos usuários e armazena os dados em um banco de dados MySQL. O projeto permite que os usuários acessem suas contas, realizem operações como depósitos e saques, e abram novas contas.

## Funcionalidades

1. **Acesso à Conta:**
   - Os usuários podem fazer login em suas contas usando o nome do titular e a senha criptografada.
   - Após o login bem-sucedido, os usuários podem realizar operações como depósitos e saques.

2. **Abertura de Conta:**
   - Os usuários podem abrir novas contas, desde que tenham idade igual ou superior a 18 anos.
   - Os dados do titular, como nome, CPF, telefone e data de nascimento, são coletados.
   - Uma senha é definida e criptografada antes de ser armazenada no banco de dados.
   - Os usuários têm a opção de fazer um depósito inicial ou não.

## Tecnologias Utilizadas

- Linguagem: Java
- Biblioteca de Criptografia: CryptoUtils
- Banco de Dados: MySQL
- Biblioteca de Interface de Linha de Comando: Scanner
- Biblioteca para Formatação no Terminal: JansiConsole

## Configuração do Banco de Dados

1. **Crie um banco de dados chamado `sistemaBancario`:**
CREATE DATABASE sistemaBancario;

2. **Ultilize o banco de dados criado:**
USE sistemaBancario;

3. **Crie a tabela account para armazenar as informações das contas:**
CREATE TABLE account (
    holder VARCHAR(100) NOT NULL,
    numberAccount VARCHAR(100) PRIMARY KEY,
    cpf CHAR(11) NOT NULL UNIQUE,
    contac CHAR(11) NOT NULL UNIQUE,
    birthDate DATE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0
);

## Como Executar o Projeto
1. Clone este repositório para a sua máquina.
2. Configure o ambiente Java em sua máquina, se ainda não estiver configurado.
3. Certifique-se de ter um servidor MySQL em execução e atualize as informações de conexão no arquivo Database.java.
4. Importe a biblioteca jansi para formatação no terminal.
5. Compile e execute o arquivo Program.java para iniciar o sistema bancário.
