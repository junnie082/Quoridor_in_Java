package moves;

import players.*;

import java.util.Scanner;

import board.*;
import partition.Wood;

public class Encounter { 
    
    Board board = new Board();

    public boolean checkPlayer(Player player, int row, int col) {
        
        char cmpPlayer = Board.mainBoard[row][col];
        if (cmpPlayer == '1' || cmpPlayer == '2') {
            board.checkBoundary(row);
            board.checkBoundary(col);
            return true; // true 면 있다. 
        }
        return false;  // false 면 없다.
    }

    public boolean checkWood(int row, int col) {
        Move move = new Move(); 
        Wood wood = new Wood(); 
        String direction = move.getDirection() != null ? move.getDirection() : wood.getWoodDirection(); 
        char boardVal = Board.mainBoard[row][col];
        
        if (boardVal == 'ㅡ' || boardVal == '|') {
            if (direction.equals("w")) System.out.println("위에 장애물이 있어 이동할 수 없습니다. 다시 입력하세요: ");
            else if (direction.equals("a")) System.out.println("왼쪽에 장애물이 있어 이동할 수 없습니다. 다시 입력하세요: ");
            else if (direction.equals("d")) System.out.println("오른쪽에 장애물이 있어 이동할 수 없습니다. 다시 입력하세요: ");
            else if (direction.equals("x")) System.out.println("아래쪽에 장애물이 있어 이동할 수 없습니다. 다시 입력하세요: ");
            return true;  // true 면 나무 판자가 이동 방향에 있다. 
        }
        return false;
    }

    public boolean playerOnTheUpDownSide(Player player, int currRow, int currCol) {
        // 만약에 왼쪽 오른쪽 모두에 나무 판자가 있다면 입력을 다시 받아야 함. 
        Scanner input = new Scanner(System.in); 
        boolean check = true; 

        while(check) {
            String leftOrRight = input.next();
            if (leftOrRight.equals("a")) {
                if (Board.mainBoard[currRow][currCol-1] == '|') { // 왼쪽으로 가려는데 나무 판자가 있을 때. 
                    System.out.println("해당 방향에 나무 판자가 있습니다. 다시 입력하세요."); 
                } else {
                    player.setRowPos(currRow);
                    player.setColPos(currCol - 2);
                    return true; 
                }
            } else if (leftOrRight.equals("d")) {
                if (Board.mainBoard[currRow][currCol+1] == '|') { // 오른쪽으로 가려는데 나무 판자가 있을 때. 
                    System.out.println("해당 방향에 나무 판자가 있습니다. 다시 입력하세요."); 
                } else {
                    player.setRowPos(currRow);
                    player.setColPos(currCol + 2);
                    return true; 
                }
            } else {
                System.out.println("다시 입력하세요. 왼쪽: a, 오른쪽: d");
            }
        }
        return false; 
    }

    public boolean playerOnTheLeftRightSide(Player player, int currRow, int currCol) {
        Scanner input = new Scanner(System.in); 
        boolean check = true; 

        while(check) {
            String UpOrDown = input.next();
            if (UpOrDown.equals("w")) {
                if (Board.mainBoard[currRow-1][currCol] == 'ㅡ') { // 왼쪽으로 가려는데 나무 판자가 있을 때. 
                    System.out.println("해당 방향에 나무 판자가 있습니다. 다시 입력하세요."); 
                } else {
                    player.setRowPos(currRow - 2);
                    player.setColPos(currCol);
                    return true; 
                }
            } else if (UpOrDown.equals("d")) {
                if (Board.mainBoard[currRow+1][currCol] == 'ㅡ') { // 오른쪽으로 가려는데 나무 판자가 있을 때. 
                    System.out.println("해당 방향에 나무 판자가 있습니다. 다시 입력하세요."); 
                } else {
                    player.setRowPos(currRow + 2);
                    player.setColPos(currCol);
                    return true; 
                }
            } else {
                System.out.println("다시 입력하세요. 위쪽: w, 아래쪽: x");
            }
        }
        return false; 
    }


    public boolean woodInBothSideLeftRight(int currRow, int currCol) {
        if (Board.mainBoard[currRow][currCol-1] == '|' && Board.mainBoard[currRow][currCol+1] == '|') return true;
        return false; 
    }

    public boolean woodInBothSideUpDown(int currRow, int currCol) {
        if (Board.mainBoard[currRow][currCol-1] == 'ㅡ' && Board.mainBoard[currRow][currCol+1] == 'ㅡ') return true;
        return false; 
    }

}
