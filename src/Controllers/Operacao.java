package Controllers;

import Banco_Digital.*;


import java.util.Scanner;

public class Operacao {
    private static Banco banco = new Banco("Meu Banco Digital");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    efetuarOperacao(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

    private static void exibirMenu() {
        System.out.println("\n------------------------");
        System.out.println("Banco Digital - " + banco.getNome());
        System.out.println("------------------------");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Efetuar Operação");
        System.out.println("3. Sair");
        System.out.print("Digite a opção desejada: ");
    }

    private static void cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n------------------------");
        System.out.println("Cadastro de Cliente");
        System.out.println("------------------------");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, idade, cpf);

        int tipoConta;
        do {
            System.out.println("\nEscolha o tipo de conta:");
            System.out.println("1. Conta Corrente");
            System.out.println("2. Conta Poupança");
            System.out.print("Digite a opção desejada: ");
            tipoConta = scanner.nextInt();
        } while (tipoConta < 1 || tipoConta > 2);

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else {
            conta = new ContaPoupanca(cliente);
        }

        cliente.setConta(conta);
        banco.getContaList().add(conta);
        System.out.println("\nCliente cadastrado com sucesso!");
    }

    private static void efetuarOperacao(Scanner scanner) {
        if (banco.getContaList().isEmpty()) {
            System.out.println("\nNão há clientes cadastrados. Cadastre um cliente antes de efetuar operações.");
            return;
        }

        System.out.print("\nDigite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Conta conta = buscarContaPorCpf(cpf);
        if (conta == null) {
            System.out.println("\nCliente não encontrado.");
            return;
        }

        int opcaoOperacao;
        do {
            exibirMenuOperacao();
            opcaoOperacao = scanner.nextInt();

            switch (opcaoOperacao) {
                case 1:
                    sacar(conta, scanner);
                    break;
                case 2:
                    depositar(conta, scanner);
                    break;
                case 3:
                    transferir(conta, scanner);
                    break;
                case 4:
                    System.out.println("\nVoltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoOperacao != 4);
    }

    private static Conta buscarContaPorCpf(String cpf) {
        for (Conta conta : banco.getContaList()) {
            if (conta.getCliente().getCpf().equals(cpf)) {
                return conta;
            }
        }
        return null;
    }

    private static void exibirMenuOperacao() {
        System.out.println("\n------------------------");
        System.out.println("Operações");
        System.out.println("------------------------");
        System.out.println("1. Sacar");
        System.out.println("2. Depositar");
        System.out.println("3. Transferir");
        System.out.println("4. Voltar ao menu principal");
        System.out.print("Digite a opção desejada: ");
    }

    private static void sacar(Conta conta, Scanner scanner) {
        System.out.print("Digite o valor para sacar: ");
        double valor = scanner.nextDouble();
        conta.sacar(valor);
        System.out.println("Saque realizado com sucesso!");
    }

    private static void depositar(Conta conta, Scanner scanner) {
        System.out.print("Digite o valor para depositar: ");
        double valor = scanner.nextDouble();
        conta.depositar(valor);
    }

    private static void transferir(Conta conta, Scanner scanner) {
        System.out.print("Digite o valor para transferir: ");
        double valor = scanner.nextDouble();
        System.out.print("Digite o CPF do destinatário: ");
        scanner.nextLine();
        String cpfDestino = scanner.nextLine();
        Conta contaDestino = buscarContaPorCpf(cpfDestino);
        if (contaDestino != null) {
            conta.trasnferir(valor, contaDestino);
        } else {
            System.out.println("Conta destinatária não encontrada.");
        }
    }
}
