import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    public static void main(String[] args) {

        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},  //gameBoard[0][] X
                               {'-', '+', '-', '+', '-'},  //gameBoard[1][]
                               {' ', '|', ' ', '|', ' '},  //gameBoard[2][] X
                               {'-', '+', '-', '+', '-'},  //gameBoard[3][]
                               {' ', '|', ' ', '|', ' '}}; //gameBoard[4][] X

        System.out.println("\n" + "TIC TAC TOE GAME" +
                           "\n -> Insert number similarly like a phone keypad to place X" +
                           "\n -> CPU will respond every move with 'O'" + "\n");
        printGameBoard(gameBoard);

        while (true){
            Scanner scan = new Scanner(System.in);
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            System.out.print("\nInsert number (1-9) to place X: ");
            int playerPos = scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position occupied. Insert another number.");
                playerPos = scan.nextInt();
            }

            System.out.println();
            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() > 0){
                printGameBoard(gameBoard);
                System.out.println("\n" + result);
                break;
            }


            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0){
                //printGameBoard(gameBoard);
                System.out.println("\n" + result);
                break;
            }
        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row: gameBoard){
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char [][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")){
            symbol = '0';
            cpuPositions.add(pos);
        }

        switch (pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning){
            if (playerPositions.containsAll(l)){
                return "PLAYER WINS!";
            }else if(cpuPositions.containsAll(l)){
                return "CPU WINS!";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "PLAYER AND CPU TIE!";
            }
        }

        return "";
    }
}
