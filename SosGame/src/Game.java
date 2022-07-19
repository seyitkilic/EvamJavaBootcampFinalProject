import java.util.Random;
import java.util.Scanner;


public class Game {
    /*
     * @param n: oyun n satir ve n sutunlu bir panel uzerinde gerceklesecek
     * panel: oyunun oynanacagi alan
     * playersScore & computersScore: oyuncularin kazandigi skorlar
     * playersTurn & computersTurn: oyuncularin hamle sayilari
     */
    private int n;
    private String[][] panel;
    private int playersScore;
    private int computersScore;
    private int playersTurn;
    private int computersTurn;
    Scanner sc = new Scanner(System.in);
    

    public Game(int n) {
        /*
         * Constructor Method
         */
        this.n = n;
        this.panel = new String[n][n];
        this.playersScore = 0;
        this.computersScore = 0;
        this.playersTurn = 1;
        this.computersTurn = 1;
    }

    private void fill() {
        /*
         * Oyun panelini tamamen tire işareti "-" ile dolduran method
         */

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                panel[i][j] = "-";
            }
        }
    }

    public void print(String[][] matris) {
        /*
         * Parametre olarak verilen matrisi ekrana basan method
         */

        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                System.out.print(matris[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void controller(String[][] matris, int row, int column, String letter, boolean isUser) {
        /*
         * Girilen S veya O degerinin sonucunda,
         * bu değeri giren oyuncunun puan alip alamayacagini hesaplayan method
         */
        int point = 0;

        // ArrayIndexOutOfBoundsException isimli Exception i almamak icin gerekli kontroller
        boolean isRow, isColumn;

        isRow = (row > 0 && row < n-1) ? true : false;
        isColumn = (column > 0 && column < n-1) ? true : false;

        // SOS olma durumlarinin kontrolleri
        if (letter.equals("o")) { // eger girilen ifade O ise
            if (isColumn && matris[row][column + 1].equals("s") && matris[row][column - 1].equals("s"))
                point++;
            if (isRow && matris[row + 1][column].equals("s") && matris[row - 1][column].equals("s"))
                point++;
            if (isRow && isColumn && matris[row + 1][column - 1].equals("s") && matris[row - 1][column + 1].equals("s"))
                point++;
            if (isRow && isColumn && matris[row - 1][column - 1].equals("s") && matris[row + 1][column + 1].equals("s"))
                point++;
        }

        else if (letter.equals("s")) {
            if ((column < n-2) && matris[row][column + 1].equals("o") && matris[row][column + 2].equals("s"))
                point++;
            if ((column > 1) && matris[row][column - 1].equals("o") && matris[row][column - 2].equals("s"))
                point++;
            if ((row < n-2) && matris[row + 1][column].equals("o") && matris[row + 2][column].equals("s"))
                point++;
            if ((row > 1) && matris[row - 1][column].equals("o") && matris[row - 2][column].equals("s"))
                point++;
            if ((row > 1 && column > 1) && matris[row - 1][column - 1].equals("o") && matris[row - 2][column - 2].equals("s"))
                point++;
            if ((row > 1 && column < n-2) && matris[row - 1][column + 1].equals("o") && matris[row - 2][column + 2].equals("s"))
                point++;
            if ((row < n-2 && column < n-2) && matris[row + 1][column + 1].equals("o") && matris[row + 2][column + 2].equals("s"))
                point++;
            if ((row < n-2 && column > 1) && matris[row + 1][column - 1].equals("o") && matris[row + 2][column - 2].equals("s"))
                point++;
        }
        
        if (isUser && point > 0) {
            this.playersScore += point; // Son durumda oluşan SOS ler kullanici hanesine yazildi
            this.playersTurn++; // kullanici SOS yaptigi icin 1 hamle sayisi kazandi
        } else if (!isUser && point > 0) {
            this.computersScore += point;
            this.computersTurn++;
        }
    }

    private String[][] randomOS(String[][] matris, int row, int column, boolean isUser) {
        /*
         * Verilen satir ve sutuna rastgele olarak O veya S harfini yazan metod
         */
        Random rand = new Random();
        int selected = rand.nextInt(2); 
        String[] os = { "o", "s" };
        matris[row][column] = os[selected]; 

        controller(matris, row, column, os[selected], isUser);

        return matris;
    }

    private String[][] computerPlay(String[][] matris) {
        /*
         * Hamle sirasi bilgisayardayken, rastgele olarak bir satir ve sutun numarasi
         * secer. Daha sonra eger secilen satir ve sutun bossa gerekli islem 
         * (randomOS()) yapilir, degilse tekrar yeni satir ve sutun numarasi secilir
         */
        Random rand = new Random();
        boolean isTrue = true;

        while (isTrue) {
            // Rastgele olarak satir ve sutun sayisinin belirlenmesi
            int row = rand.nextInt(matris.length);
            int column = rand.nextInt(matris[1].length);

            if (matris[row][column].equals("-")) {
                matris = randomOS(matris, row, column, false);
                isTrue = false;
            }
        }

        return matris;
    }

    private String[][] userPlay(String[][] matris) {
        /*
         * Hamle sirasi kullanicidayken, bir satir ve sutun numarasi yazar.
         * Daha sonra eger bu satir ve sutun uygunsa gerekli islem (randomOS()) yapilir
         */
        boolean isTrue = true;

        while (isTrue) {
            // Kullanicinin satir ve sutun sayilarini belirlemesi
            System.out.print("Sectiginiz Satir Numarasi: ");
            int row = sc.nextInt();
            System.out.print("Sectiginiz Sutun Numarasi: ");
            int column = sc.nextInt();

            // Kullanicinin girdigi satir ve sutun sayisinin basit bir kontrolu
            if ((row < 0 || column < 0) || (row >= n || column >= n)){
                System.out.println("Hata! Sectiginiz satir/sutun hatali.");
                continue;
            }

            else if (matris[row][column].equals("-")) {
                matris = randomOS(matris, row, column, true);
                isTrue = false;
            }

            // Kullanicinin girdigi satir ve sutun sayisi sonucunda islem basarili
            // bir sekilde yapilmadiysa, ekrana tekrar deneyiniz basmak icin
            if (isTrue){
                System.out.println("Tekrar Deneyiniz!!");
            }
        }
        

        return matris;
    }

    private boolean isFinish(String[][] matris) {
        /*
         * Oyunun bitip bitmediginin kontrolunu yapan metod.
         * Eger panelde S veya O olarak isaretlenmemis bir alan kaldiysa
         * oyun devam eder, kalmadiysa oyun biter
         */
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                if (matris[i][j] == "-")
                    return false; 
            }
        }
        return true;
    }

    private void result(int playerScore, int computerScore) {
        /*
         * Skorlari yazdiran metot
         */
        System.out.println("Bilgisayarin elde ettigi toplam puan: " + computerScore);
        System.out.println("Kullanicinin elde ettigi toplam puan: " + playerScore);
        System.out.println("--------------------");
        
    }

    public void start() {
        /*
         * Tasarlanan oyunun oynandigi metot
         */
        Random rand = new Random();

        fill(); 
        print(panel);
        System.out.println("--------------------");

        // Oyuna kimin baslayacaginin secildigi yer
        int whoStarts = rand.nextInt(2);
        if (whoStarts == 0)
            System.out.println("Oyuna Kullanici Baslayacak");
        else
            System.out.println("Oyuna Bilgisayar Baslayacak");


        while(!isFinish(panel)){ // her turda oyunun bitip bitmediginin kontrolu
            if (whoStarts == 0){ // eger oyuna kullanici basladiysa
                while(playersTurn > 0 && !isFinish(panel)){ 
                    this.panel = userPlay(panel); // kullanici hamlesini yapti
                    print(panel); // panelin son hali ekrana basildi
                    System.out.println("--------------------");
                    System.out.println("Kullanici hamlesini yapti");
                    System.out.println("--------------------");
                    result(playersScore, computersScore);
                    playersTurn --; // kullanici 1 hamle yaptigi icin kalan hamle sayisi 1 azalir
                }


                if (isFinish(panel))
                    break;
                
                while(computersTurn > 0 && !isFinish(panel)){
                    this.panel = computerPlay(panel); // sira bilgisayarda, bilgisayar hamlesini yapti
                    print(panel); // panelin son hali ekrana basildi
                    System.out.println("--------------------");
                    System.out.println("Bilgisayar hamlesini yapti");
                    System.out.println("--------------------");
                    result(playersScore, computersScore);
                    computersTurn --;
                }
            }

            else{ // eger oyuna bilgisayar basladiysa
                while(computersTurn > 0 && !isFinish(panel)){
                    this.panel = computerPlay(panel); // bilgisayar hamlesini yapti
                    print(panel);
                    System.out.println("--------------------");
                    System.out.println("Bilgisayar hamlesini yapti");
                    System.out.println("--------------------");
                    result(playersScore, computersScore);
                    computersTurn --;
                }

                if (isFinish(panel))
                    break;
                
                while(playersTurn > 0 && !isFinish(panel)){
                    this.panel = userPlay(panel); // kullanici hamlesini yapti
                    print(panel);
                    System.out.println("--------------------");
                    System.out.println("Kullanici hamlesini yapti");
                    System.out.println("--------------------");
                    result(playersScore, computersScore);
                    playersTurn --;
                }
            }

            playersTurn = 1; // karsilikli yapilan hamlelerin sonucunda oyun basa doner ve 
            computersTurn = 1; // yine herkesin 1 er hamlesi olur
        }
        // Son olarak sonuclar
        if (playersScore > computersScore)
            System.out.println("Tebrikler Kazandiniz!!");
        else if (computersScore > playersScore)
            System.out.println("Uzgunum Kaybettiniz");
        else{
            System.out.println("Berabere!!!");
        }
        System.out.println("--------------------");

    }
}