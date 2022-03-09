package TicTacToeGame;

import java.util.Scanner;

public class TicTacToeGame {
    //instances
    private char[][] Board=new char[3][3];
    private final char[] XO={'X','O'};
    
    //methods
    private void resetBoard(){
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                this.Board[i][j]='-';
    }
    private void showBoard(){
        System.out.println("^^^^^^^\n"
                         + "|"+this.Board[0][0]+"|"+this.Board[0][1]+"|"+this.Board[0][2]+"|\n"
                         + "|"+this.Board[1][0]+"|"+this.Board[1][1]+"|"+this.Board[1][2]+"|\n"
                         + "|"+this.Board[2][0]+"|"+this.Board[2][1]+"|"+this.Board[2][2]+"|\n"
                         + "vvvvvvv\n");
    }
    private int checkWinner(){
        for (int a = 0; a < 8; a++) {
            String line = null;
  
            switch (a) {
            case 0:
                line = ""+this.Board[0][0] + this.Board[0][1] + this.Board[0][2];
                break;
            case 1:
                line = ""+this.Board[1][0] + this.Board[1][1] + this.Board[1][2];
                break;
            case 2:
                line = ""+this.Board[2][0] + this.Board[2][1] + this.Board[2][2];
                break;
            case 3:
                line = ""+this.Board[0][0] + this.Board[1][0] + this.Board[2][0];
                break;
            case 4:
                line = ""+this.Board[0][1] + this.Board[1][1] + this.Board[2][1];
                break;
            case 5:
                line = ""+this.Board[0][2] + this.Board[1][2] + this.Board[2][2];
                break;
            case 6:
                line = ""+this.Board[0][0] + this.Board[1][1] + this.Board[2][2];
                break;
            case 7:
                line = ""+this.Board[0][2] + this.Board[1][1] + this.Board[2][0];
                break;
            }
            if (line.equals("XXX"))
                return 1;
            else{
                if (line.equals("OOO"))
                    return 2;
                else{
                    int count=0;
                    for (int i=0;i<3;i++)
                        for (int j=0;j<3;j++)
                            if (Board[i][j]!='-')
                                count++;
                     if (count==9)
                        return 3;
                }
            }
        }
        return 0;
    }
    public int findRow(int tile){
        if (tile%3==0){
            return (int)tile/3-1;
        }
        else{
            return (int)tile/3;
        }
    }
    public int findCollumn(int tile){
        if (tile%3==0){
            return (int)2;
        }
        else{
            return (int)tile%3-1;
        }
    }
    private char findTileInBoard(int tile){
        return this.Board[findRow(tile)][findCollumn(tile)];
    }
    
    private void playerMove(int playerNo){
        //this method lets a player choose his move and 
        Scanner console = new Scanner(System.in);
        //getting user input
        System.out.println("Player " + playerNo + " choose a tile by number 1->9!");
        int tile = console.nextInt();
        //verifying user input
        while (tile>9 || tile <1){
            System.out.println("Tile out of range!\n");
            System.out.println("Player " + playerNo + " choose a tile by number 1->9!");
            tile = console.nextInt();
        }
        while (findTileInBoard(tile)!='-'){
            if('-'!=findTileInBoard(tile)){
                System.out.println("Tile occupied try another!\n");
            }
            System.out.println("Player " + playerNo + " choose a tile by number 1->9!");
            tile = console.nextInt();
        }
        //putting input into board
        this.Board[findRow(tile)][findCollumn(tile)]=XO[playerNo-1];
        
    }
    private void printResults(){
        if (checkWinner()==1)
            System.out.println("Player 1 WINS!");
        else{   
            if(checkWinner()==2)
                System.out.println("Player 2 WINS!");
            else if(checkWinner()==3)
                    System.out.println("It's a draw!");
        }
    }
    public void play(){
        System.out.println("Player 1 is X and Player 2 is O.\nPress 'p' key to play or 'q' to quit!");
        Scanner console = new Scanner(System.in);
        String option =console.next();
        while(!"q".equals(option) && !"p".equals(option)){
                System.out.println("Please press 'p' to play or 'q' to quit");
                option =console.next();
        }
        if ("q".equals(option))
            System.out.println("Thank you for playing!");
        else {
            //reseting the board
            resetBoard();
            
            //choosing a random player to start
            int min=1;
            int max=2;
            int x = (int) ((Math.random() * (max - min)) + min);
           
            System.out.println("Player " + x + " goes first!");
            
            //finding the other player
            int y;
            if (x==1)
                y=2;
            else y=1;
            //starting game
            while(checkWinner()==0){
                playerMove(x);
                showBoard();
                if (checkWinner()==0){
                    playerMove(y);
                    showBoard();
                }
            }
            //printing the game result
            printResults();
            
            //asking user for another game
            System.out.println("Want to play again? Press 'p' to play again or 'q' to quit");
            option =console.next();
            while(!"q".equals(option) && !"p".equals(option)){
                System.out.println("Please press 'p' to play or 'q' to quit");
                option =console.next();
            }
            if ("q".equals(option))
                System.out.println("Thank you for playing!");
            else {
                play();
            }
        }
    }
}