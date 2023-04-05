package moves;

import java.util.Scanner;

import board.Board;
import players.*;

public class Move {  // 이 클래스는 사실 Player 와 Wood 둘 다 사용할 수 있어야 함. 

    public static String direction;
    Scanner input = new Scanner(System.in); 

    public String getDirection() {
        return this.direction;
    }

    public void selectDirection(Player player) {  // 코드 정리 한 번 해야함. 
        Move move = new Move();
        Board board = new Board();
        boolean check = true; 
        int currRow;
        int currCol; 
        while (check) {  // check가 false 면 해당 방향에 장애물이 없는 것, true 면 장애물이 있는 것. 
            System.out.println("상 w, 좌 a, 하 x, 우 d");
            this.direction = input.nextLine();
            currRow = player.getRowPos();
            currCol = player.getColPos();
            try {            
                if (direction.equals("w")) {
                    board.checkBoundary(currRow - 2);
                    check = move.moveToUp(player, currRow, currCol);
                } else if (direction.equals("a")) {
                    board.checkBoundary(currCol - 2);
                    check = move.moveToLeft(player, currRow, currCol); 
                } else if (direction.equals("x")) {
                    board.checkBoundary(currRow + 2);
                    check = move.moveToDown(player, currRow, currCol);
                } else if (direction.equals("d")) {
                    board.checkBoundary(currCol + 2);
                    check = move.moveToRight(player, currRow, currCol); 
                } else {
                    System.out.println("상 w, 좌 a, 하 x, 우 d 로만 입력해주세요.");
                    check = true; 
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("보드판의 바운더리를 넘습니다. 다시 입력하세요. ");
            }
        }
    }

    Encounter encounter = new Encounter();

    private boolean moveToUp(Player player, int currRow, int currCol) {
        Board board = new Board(); 
        boolean check = encounter.checkWood(currRow-1, currCol);
        if (check) return true; // 바로 위에 나무 판자가 있음.
        currRow -= 2;
        check = encounter.checkPlayer(player, currRow, currCol); // 나무 판자가 없다면 이번에는 해당 방향에 플레이어가 있나 확인. 
        if (check) { // 해당 방향에 플레이어 있으면, 건너 뛰어야 함. 
            currRow -= 1;
            board.checkBoundary(currRow-1); 
            check = encounter.checkWood(currRow-1, currCol);
            if (check) return true;
            currRow -= 1;
        }
        player.setRowPos(currRow);   
        return false; 
    }

    private boolean moveToLeft(Player player, int currRow, int currCol) {
        Board board = new Board(); 
        boolean check = encounter.checkWood(currRow, currCol-1);
        if (check) return true; 
        currCol -= 2;
        check = encounter.checkPlayer(player, currRow, currCol);  // 나무 판자가 없다면 이번에는 해당 방향에 플레이어가 있나 확인.
        if (check) {  // 해당 방향에 플레이어 있으면, 건너 뛰어야 함. 
            currCol -= 1;
            board.checkBoundary(currCol);
            check = encounter.checkWood(currRow, currCol); 
            if (check) return true;; 
            currCol -= 1;
        }
        player.setColPos(currCol);
        return false; 
    }

    private boolean moveToDown(Player player, int currRow, int currCol) {
        Board board = new Board(); 
        boolean check = encounter.checkWood(currRow+1, currCol);
        if (check) return true;  // 바로 주위에 나무 판자가 있음.
        currRow += 2;
        check = encounter.checkPlayer(player, currRow, currCol); // 나무 판자가 없다면 이번에는 해당 방향에 플레이어가 있나 확인.
        if (check) {  // 해당 방향에 플레이어 있으면, 건너 뛰어야 함. 
            currRow += 1;
            board.checkBoundary(currRow);
            check = encounter.checkWood(currRow, currCol);
            if (check) return true;
            currRow += 1; 
        }
        player.setRowPos(currRow);
        return false; 
    }

    private boolean moveToRight(Player player, int currRow, int currCol) {
        Board board = new Board();
        boolean check = encounter.checkWood(currRow, currCol+1);
        if (check) return true;  // 바로 주위에 나무 판자가 있음.
        currCol += 2;
        check = encounter.checkPlayer(player, currRow, currCol);
        if (check) {  // 해당 방향에 플레이어 있으면, 건너 뛰어야 함.
            currCol += 1;
            board.checkBoundary(currCol);
            check = encounter.checkWood(currRow, currCol);
            if (check) return true;
            currCol += 1;
        }   
        player.setColPos(currCol);
        return false; 
    }

    public void checkBoundary(int pos) {
        if (pos < 1 || pos > 17) 
            throw new IndexOutOfBoundsException();
    }
}
