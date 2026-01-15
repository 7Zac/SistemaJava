import java.util.Scanner;

public class Media {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        double[] notas = new double[2];
        double soma = 0.0;

        for (int i = 0; i < notas.length; i++) {
            System.out.print("Digite a nota " + (i + 1) + ": ");
            notas[i] = input.nextDouble();
            soma += notas[i];
        }

        double media = soma / notas.length;
        System.out.println("A média das notas é: " + media);

        input.close();
    }
}
