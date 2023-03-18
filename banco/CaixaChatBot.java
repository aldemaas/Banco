package banco;

import banco.entidades.*;
import banco.entidades.util.Data;

import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class CaixaChatBot {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        String nome;
        String cpf;

        Agencia.abrirCaixa();

        System.out.println("Contas existentes");
        Iterator it = Agencia.getContas();
        while (it.hasNext())
            System.out.println(it.next());

        System.out.println();

        System.out.println("1. para criar uma nova conta ");
        System.out.println();
        System.out.println("2. para localizar uma conta já existente através de seu número ");
        int acao = 0;
        double valor = 0;
        int numeroConta = 0;
        int identificaConta = sc.nextInt();
        sc.nextLine();


        Conta conta = null;


        if (identificaConta == 1) {

            System.out.println("Digite seu nome e cpf respectivamente para criar sua conta e ter acesso ao caixa.");
            nome = sc.nextLine();
            cpf = sc.nextLine();
            conta = new ContaSimples(new Pessoa(nome, cpf));
            Agencia.addConta(conta);
        }

        if (identificaConta == 2) {

            System.out.print("Digite o numero da sua conta: ");
            numeroConta = sc.nextInt();
            conta = Agencia.localizarConta(numeroConta);
        }

        while (acao != 6) {
            System.out.println(" 1. Depositar\n 2. Sacar\n 3. Extrato\n 4. Transferir\n 5. Saldo\n 6. Sair");


            acao = sc.nextInt();

            if (acao == 1) {
                System.out.println("Digite o valor do depósito");
                valor = sc.nextDouble();
                conta.depositar(valor);
            } else if (acao == 2) {
                System.out.println("Digite o valor do saque");
                valor = sc.nextDouble();
                conta.sacar(valor);
            } else if (acao == 3) {
                System.out.println("Digite a data inicial");
                System.out.print("Dia:");
                int diaInicial = sc.nextInt();
                System.out.print("Mês");
                int mesInicial = sc.nextInt() - 1;
                System.out.print("Ano:");
                int anoInicial = sc.nextInt();

                System.out.println("Digite a data final");
                System.out.print("Dia:");
                int diaFinal = sc.nextInt();
                System.out.print("Mês");
                int mesFinal = sc.nextInt() - 1;
                System.out.print("Ano:");
                int anoFinal = sc.nextInt();

                System.out.println(conta.criarExtrato(new Data(diaInicial, mesInicial, anoFinal), new Data(diaFinal, mesFinal, anoFinal)).formatar());

            } else if (acao == 4) {
                System.out.println();
                System.out.println("Digite o número da conta que deseja transferir");
                Conta destino = Agencia.localizarConta(sc.nextInt());
                System.out.println("Agora digite o valor da transferência");
                valor = sc.nextDouble();
                conta.transferir(destino, valor);

            } else if (acao == 5) {
                System.out.println("Seu saldo é: " + conta.getSaldo());

            } else if (acao == 6) {
                System.out.println("Seu saldo é: " + conta.getSaldo());
            }
        }
        Agencia.fecharCaixa();
    }
}

