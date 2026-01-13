import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Insira seu nome");
        String nome = input.nextLine().trim();

        String sobrenome = "Lopes";

        System.out.println("Insira sua senha");
        int senha = input.nextInt();

        Double saldo = 1575.90;

        String mensagem = String.format("Olá %s %s, seu saldo é de R$%.2f", nome, sobrenome, saldo);

        boolean login = false;
        if (nome != null && senha > 0) {
            login = true;
            System.out.println("Usuário logado com sucesso!");
            System.out.println(mensagem);
        } else {
            System.out.println("Nome ou senha inválidos!");
        }

        if (login) {
            System.out.println("Deseja sacar ou depositar?");
            String operacao = input.next();
            if (operacao.equals("sacar")) {
                System.out.println("Digite o valor que deseja sacar:");
                Double valorSaque = input.nextDouble();
                if (valorSaque <= saldo) {
                    saldo -= valorSaque;
                    System.out.println("Seu saldo atual é R$" + saldo);
                } else {
                    System.out.println("Saldo insuficiente para saque.");
                }
            } else if (operacao.equals("depositar")) {
                System.out.println("Digite o valor que deseja depositar:");
                Double valorDeposito = input.nextDouble();
                saldo += valorDeposito;
                System.out.println("Seu saldo atual é R$" + saldo);
            }
            if (operacao != null) {
                System.out.println("Operação finalizada. Obrigado por usar nosso sistema!");
            }
        }
        input.close();

    }
}
