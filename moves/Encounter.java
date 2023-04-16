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

    // public void playerOnTheUpSide() {
    //     Scanner input = new Scanner(System.in); 
    //     System.out.println("상대 플레이어의 위쪽에 나무 판자가 있습니다. 상대를 뛰어 넘어 왼쪽으로 이동하시겠습니까 (a), 오른쪽으로 이동하시겠습니까 (d)");
    //     String leftOrRight = input.next();

    //     if (leftOrRight.eqauls("a")); 
    // }

}
