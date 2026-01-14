import java.util.Scanner;

public class While {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Insira um número inicial: ");
        int init = input.nextInt();

        System.out.println("Insira um número final: ");
        int end = input.nextInt();

        System.out.println("Insira um número que deseja parar: ");
        int stop = input.nextInt();

        while (init <= end) {
            System.out.println("Número: " + init);

            init++;
            if (init == stop) {
                System.out.println("Chegou no numero " + stop + ", hora de parar!");
                break;
            }
        }
        input.close();
    }
}
