import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n; // oyun alaninin boyutu

        System.out.println("Oyun alaninin boyutunu giriniz");
        System.out.print("Minimum 3, Maksimum 7: ");
        n = sc.nextInt();

        if (n < 3 || n > 7){
            System.out.println("Oyun alani buyuklugu minimum 3, maksimum 7 olmalidir");
        }
        else{
            Game game = new Game(n);
            game.start();
        }
        sc.close();
    }
}





