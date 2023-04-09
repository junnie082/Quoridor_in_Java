package board;

import java.util.*;

import players.*; 
import partition.*;
import moves.Encounter;

public class Board {
    public static char[][] mainBoard = new char[18][18]; 
    
    Scanner input = new Scanner(System.in); 

    public void initialize() {
        for (int i = 0; i <= 17; i++) {
            for (int j = 0; j <= 17; j++) {
                if (i == 1 && j == 9) mainBoard[i][j] = '1';
                else if (i == 17 && j == 9) mainBoard[i][j] = '2'; 
                else if (i%2 == 0 && j%2 == 0) mainBoard[i][j] = ' ';
                else if (i%2 == 0 || j%2 == 0) mainBoard[i][j] = '*';
                else mainBoard[i][j] = '0';
            }
        }
    }

    int rowWood, colWood;
    String directionWood; 

    private void woodAlreadyInPlace() {
        System.out.println("해당 좌표에 이미 나무판자가 있습니다. 좌표와 방향을 다시 입력하세요."); 
    }

    public boolean putWoodOnBoard(boolean trueIfVertical) {
        Wood wood = new Wood(); 
        Board board = new Board(); 
        Encounter encounter = new Encounter(); 
        rowWood = wood.getRowWood();
        colWood = wood.getColWood();
        String directionWood = wood.getWoodDirection();

        boolean check = true;
     
        check = encounter.checkWood(rowWood, colWood); 
        if (check) {
            woodAlreadyInPlace();
            return true;
        }
        try {
            if (trueIfVertical) {
                // vertical
                if (directionWood.equals("w")) {
                    // 나무 판자가 해당 좌표로부터 위로 놓임.
                    board.putWoodUp(rowWood, colWood);
                } else if (directionWood.equals("x")) {
                    // 나무 판자가 해당 좌표로부터 아래로 놓임.
                    board.putWoodDown(rowWood, colWood);
                }
                mainBoard[rowWood][colWood] = '|';

            } else {
                // horizontal
                if (directionWood.equals("a")) {
                    // 나무 판자가 해당 좌표로부터 왼쪽으로 놓임.
                    board.putWoodLeft(rowWood, colWood);
                } else if (directionWood.equals("d")) {
                    // 나무 판자가 해당 좌표로부터 오른쪽으로 놓임.
                    board.putWoodRight(rowWood, colWood);
                }
                mainBoard[rowWood][colWood] = 'ㅡ';

            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("해당 좌표에 이미 장애물이 있습니다.");
            return true;  
        } 
        
        return false; 
    }

    private void putWoodUp(int rowWood, int colWood) {
        Encounter encounter = new Encounter();
        boolean check = encounter.checkWood(rowWood - 1, colWood); 
        check = check || encounter.checkWood(rowWood - 2, colWood); 
        if (check) {
            woodAlreadyInPlace();
            throw new IllegalArgumentException();
        }
        mainBoard[rowWood-2][colWood] = '|';
        mainBoard[rowWood-1][colWood] = '|';
    }

    private void putWoodDown(int rowWood, int colWood) {
        Encounter encounter = new Encounter();
        boolean check = encounter.checkWood(rowWood + 1, colWood);
        check = check || encounter.checkWood(rowWood + 2, colWood); 
        if (check) {
            woodAlreadyInPlace();
            throw new IllegalArgumentException();
        }
        mainBoard[rowWood+2][colWood] = '|';
        mainBoard[rowWood+1][colWood] = '|';
    }
    
    private void putWoodLeft(int rowWood, int colWood) {
        Encounter encounter = new Encounter();
        boolean check = encounter.checkWood(rowWood, colWood - 1);
        check = check || encounter.checkWood(rowWood, colWood - 2); 
        if (check) {
            woodAlreadyInPlace();
            throw new IllegalArgumentException();
        }
        mainBoard[rowWood][colWood-2] = 'ㅡ';
        mainBoard[rowWood][colWood-1] = 'ㅡ';
    }

    private void putWoodRight(int rowWood, int colWood) {
        Encounter encounter = new Encounter();
        boolean check = encounter.checkWood(rowWood, colWood + 1);
        check = check || encounter.checkWood(rowWood, colWood + 2); 
        if (check) {
            woodAlreadyInPlace();
            throw new IllegalArgumentException();
        }
        mainBoard[rowWood][colWood+2] = 'ㅡ';
        mainBoard[rowWood][colWood+1] = 'ㅡ';
    }

    public void setPos(Player player) {
        int row = player.getRowPos();
        int col = player.getColPos();
        System.out.println("row: " + row + "col: " + col); 
        int a = player.getPlayerNum();
        if (a == 1) {
            System.out.println("playerNum: " + '1');
            mainBoard[row][col] = '1';
        } else {
            System.out.println("playerNum: " + '2');
            mainBoard[row][col] = '2';
        }
    }

    public static void printMainBoard() {
        for (int i = 0; i <= 17; i++) {
            for (int j = 0; j <= 17; j++) {
                if (i == 0 && j % 2 == 0) System.out.print(Color.FONT_GREEN + (char)((j+1)/2 + '0') + " " + Color.RESET);
                else if (j == 0 && i % 2 == 0) System.out.print(Color.FONT_GREEN + (char)((i+1)/2 + '0') + " " + Color.RESET);
                else if (i == 0 && j % 2 == 1) System.out.print(Color.FONT_PURPLE + (char)((j-1)/2 + 'a') + " " + Color.RESET);
                else if (j == 0 && i % 2 == 1) System.out.print(Color.FONT_PURPLE + (char)((i-1)/2+ 'a') + " " + Color.RESET);
                else {
                    if (i % 2 == 0 && j % 2 == 0) System.out.print("  "); 
                    else if (mainBoard[i][j] == '1')
                        System.out.print(Color.FONT_RED + "1 " + Color.RESET);
                    else if (mainBoard[i][j] == '2')
                        System.out.print(Color.FONT_GREEN + "2 " + Color.RESET);
                    else if (mainBoard[i][j] == '*')
                        System.out.print(Color.FONT_CYAN + "* " + Color.RESET);
                    else if (mainBoard[i][j] == 'ㅡ') 
                        System.out.print(Color.FONT_YELLOW + "ㅡ" + Color.RESET);
                    else if (mainBoard[i][j] == '|')
                        System.out.print(Color.FONT_YELLOW + "| " + Color.RESET);
                    else System.out.print(mainBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void checkBoundary(int pos) {
        if (pos < 1 || pos > 17) {
            System.out.println("경계를 넘습니다. 다시 입력하세요.");
            throw new IndexOutOfBoundsException();
        }
    }
}
