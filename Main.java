import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {//change class from mainconnectfour to main

    public static void main(String[] args){
        new Main(); // changed4
    }

    private BufferedReader input; 
    private char[][] board; 

    public Main(){ //main instead connect4
        board = new char[6][7]; 
        input = new BufferedReader(new InputStreamReader(System.in));
        playGame();
    }

    private void playGame(){
        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
        System.out.println("");
        printBoard();
        boolean win = false;
        while(!win){
            // player 1
            if(isGameFinished())
                break;
            boolean moveCompleted;
            do {
                int move = getUserInput();
                moveCompleted = placeCounter('r', move);
            }while (!moveCompleted);
            win = checkIfPlayerWin('r');
            printBoard();
            if(win)
                break;
            //player 2
            if(isGameFinished())
                break;
            do {
                int move = getUserInput();
                moveCompleted = placeCounter('y', move);
            }while (!moveCompleted);
            win = checkIfPlayerWin('y');
            printBoard();
        }
        if(win)
            System.out.println("You Have Won!!!");
        else
            System.out.println("Draw!");
    }

    private boolean isGameFinished(){
        for(int i=0; i<board[0].length; i++){
            if(board[0][i]!='r' && board[0][i]!='y')
                return false;
        }
        return true;
    }

    private boolean checkIfPlayerWin(char player){
        // check horizontal
        for(int i=0; i<board.length; i++){
            int count = 0;
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == player){
                    count = count + 1;
                    if(count == 4){
                        return true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        // check vertical
        for(int i=0; i<board[0].length; i++){
            int count = 0; //int 
            for(int j=0; j<board.length; j++){
                if(board[j][i] == player){ 
                    count = count + 1;
                    if(count == 4){ 
                        return true;
                    }
                }
                else{
                    count = 0;
                }
            }
        }
        return false;
    }

    private int getUserInput(){
        int move=-1;
        do {
            try{
                String userInput = null;
                userInput = input.readLine();
                move = Integer.parseInt(userInput);
            }
            catch(Exception e){

            }
            if(move<1 || move>7){
                System.out.println("Enter a valid integer input in the range: [1-7]");
            }
        }while (move<1 || move>7);
        return move-1;
    }

    private void printBoard(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == 'r'){
                    System.out.print("| r ");
                }
                else if(board[i][j] == 'y'){
                    System.out.print("| y ");
                }
                else{
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  1   2   3   4   5   6   7");
    }
	
    private boolean placeCounter(char player, int position){
        for(int i=board.length-1; i>=0; i--){
            if(board[i][position] != 'y' && board[i][position] != 'r'){
                board[i][position] = player;
                return true;
            }
        }
        System.out.println("Column is full, select another column!");
        return false;
    }
}
