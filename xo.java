import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class xo {

    static ArrayList<Integer> playerPostions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPostions = new ArrayList<Integer>();


    public static void main(String[] args) {
        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};
        
        Scanner scan = new Scanner(System.in);

        while (true) {
            
            System.out.println("Enter a placement number (1-9)");
            int playerPosition = scan.nextInt();

            while (playerPostions.contains(playerPosition) || cpuPostions.contains(playerPosition)) {
                System.out.println("position taken! , enter another position");
                playerPosition = scan.nextInt();
                
            }
    
            placingPiece(gameBoard, playerPosition, "player");
            
            String result = checkingWinner(); 
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            
            
            Random randad = new Random();
            
            int cpuPosition = randad.nextInt(9)+ 1;
            while (playerPostions.contains(cpuPosition) || cpuPostions.contains(cpuPosition)) {
                
                cpuPosition = randad.nextInt(9)+ 1;
                
            }
    
            placingPiece(gameBoard, cpuPosition, "CPU");
    
            printGameBoard(gameBoard);

            result = checkingWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);

    
            
        }



    }

    public static void printGameBoard (char [][] gameBoard){
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);   
            }
            System.out.println();   
        }
    }

    public static void placingPiece(char [][] gameBoard, int pos, String user){
        
        char symbol = ' ';

        if (user.equals("player")){
            symbol = 'X';
            playerPostions.add(pos); 

        } else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPostions.add(pos);
            
        }

        switch(pos) {
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
    public static String checkingWinner(){

        List topRow = Arrays.asList(1 , 2, 3);
        List midRow = Arrays.asList(4 , 5, 6);
        List bottomRow = Arrays.asList(7 , 8, 9);
        
        List topColumn = Arrays.asList(1 , 4, 7);
        List midColumn = Arrays.asList(2 , 5, 8);
        List bottomColumn = Arrays.asList(3 ,6 , 9);
        
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(bottomRow);
        winningConditions.add(topColumn);
        winningConditions.add(midColumn);
        winningConditions.add(bottomColumn);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        

            
            for(List l: winningConditions) {
                if(playerPostions.containsAll(l)) {
                     return "Congratulations you won!";
                  } else if(cpuPostions.containsAll(l)) {
                     return "CPU wins! Sorry :(";
                 } else if(playerPostions.size() + cpuPostions.size() == 9) {
                     return "CAT!";
             }

            
        }
        
        

        return "";
    }
}