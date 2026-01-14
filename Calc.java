import java.util.Scanner;

public class Calc {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Escolha a operação que deseja: +(somar), -(subtrair), *(multiplicar) ou /(dividir)");
        String operation = input.nextLine();

        System.out.println("Insira um número: ");
        double num1 = input.nextDouble();

        System.out.println("Insira outro número: ");
        double num2 = input.nextDouble();



        if(operation.equals("+")){
            double soma = num1 + num2;
            System.out.println("O resultado da soma é: " + soma);
        } else if (operation.equals("-")){
            double subtracao = num1 - num2;
        System.out.println("O resultado da subtração é: " + subtracao);
        } else if (operation.equals("*")){
            double multiplicacao = num1 * num2;
            System.out.println("O resultado da mutiplicação é: " + multiplicacao);
        } else if (operation.equals("/")){
            double divisao = num1 / num2;
            System.out.println("O resultado da divisão é: " + divisao);
        } else {
            System.out.println("Operação inválida!");
        }
        



        input.close();
    }
}
