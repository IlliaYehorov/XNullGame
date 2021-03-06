import java.util.Arrays;
import java.util.Scanner;

public class XNullGame {
    public static Scanner s = new Scanner(System.in);
    public static char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    public static int whoturn = 0;
    public static boolean winner;
    static TurnBoards[] boards = new TurnBoards[9];

    public static void printBoard(int whoturnnow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(boards[whoturnnow].boardd[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void userTurn(String user, char l){
        Scanner s = new Scanner(System.in);
        System.out.println(user + ", your turn. Give row:");
        while (!s.hasNextInt()) { System.out.println("That not a number!"); s.next(); }
        System.out.println("Give column:");
        int a = s.nextInt();
        while (!s.hasNextInt()) { System.out.println("That not a number!"); s.next(); }
        int b = s.nextInt();
        if (a<=3 && a>=1 && b<=3 && b>=1 && board[a - 1][b - 1] == '-') {
            board[a - 1][b - 1] = l;
            whoturn++;
         boards[whoturn] = new TurnBoards(board, whoturn);
            boards[1].PrintTurnBoard(1);


        } else {
            System.out.println("You cheater!");
            System.out.println("I will give you another chance this time.");
            userTurn(user, l);
        }
    }

    public static void whoWinner(char l){
        winner = (board[0][0]==l && board[0][1]==l && board[0][2]==l ||
            board[1][0]==l && board[1][1]==l && board[1][2]==l ||
            board[2][0]==l && board[2][1]==l && board[2][2]==l ||
            board[0][0]==l && board[1][0]==l && board[2][0]==l ||
            board[0][1]==l && board[1][1]==l && board[2][1]==l ||
            board[0][2]==l && board[1][2]==l && board[2][2]==l ||
            board[0][0]==l && board[1][1]==l && board[2][2]==l ||
            board[0][2]==l && board[1][1]==l && board[2][0]==l);
    }

    public static void wantSeeAllTurns(){


        System.out.println("See all turns:");
        for (int c = 0; c<=whoturn; c++) {
            boards[c].PrintTurnBoard(c);
     //       printBoard(c);
        }

    }

    public static void main(String[] args) {
        boards[0] = new TurnBoards(board, 0);
        User userX = new User("X-man", 'X');
        User userO = new User("Null-man", 'O');

        printBoard(whoturn);

        for (int c = 0; c >= 0; c++) {
            if (whoturn % 2 == 0) {
                userTurn(userX.getName(), userX.ch);
                printBoard(whoturn);
                whoWinner(userX.ch);
                if(winner){
                    userX.plusWinscount();
                    System.out.println(userX);
                    wantSeeAllTurns();
                    break;}
            } else {
                userTurn(userO.getName(), userO.ch);
                printBoard(whoturn);
                whoWinner(userO.ch);
                if(winner){
                    userO.plusWinscount();
                    System.out.println(userO);
                    wantSeeAllTurns();
                    break;}
            }

        }
    }

    static class User {
        private String name;
        private char ch;
        private int winscount;

    public User(String name, char ch) {
        this.name = name;
        this.ch = ch;
        winscount = 0;
        System.out.println("Name for " + ch + "-gamer, please:");
        setName(s.nextLine());
    }

    public void setName(String name){this.name = name;}

    public String getName(){return name;}

    public void plusWinscount(){this.winscount++;}

    public String toString() {
        return name + " - is a winner! His wins count is " + winscount;
    }

}

    static class TurnBoards {
        public char[][] boardd;
        public int turn;

        public TurnBoards(char[][] boardd, int turn) {
            this.boardd = Arrays.copyOf(boardd,boardd.length);
            this.turn = turn;

        }
        public void PrintTurnBoard(int whoturnnow){
            {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(boards[whoturnnow].boardd[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        }

    }

}


