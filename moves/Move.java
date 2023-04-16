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

    public boolean selectDirection(Player player) {  // 코드 정리 한 번 해야함. 
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
                    continue;
                }
                return check; // true 는 사용자로부터 입력을 다시 받아야 하는 상태. 
            } catch (IndexOutOfBoundsException e) {
                System.out.println("보드판의 바운더리를 넘습니다. 다시 입력하세요. ");
            }
        }
        return false; 
    }

    Encounter encounter = new Encounter();

    private boolean moveToUp(Player player, int currRow, int currCol) {
        Board board = new Board(); 
        boolean check = encounter.checkWood(currRow-1, currCol);
        if (check) return true; // 바로 위에 나무 판자가 있음.
        currRow -= 2;
        board.checkBoundary(currRow);
        check = encounter.checkPlayer(player, currRow, currCol); // 나무 판자가 없다면 이번에는 해당 방향에 플레이어가 있나 확인. 
        if (check) { // 해당 방향에 플레이어 있으면, 건너 뛰어야 함. 
            // 상대 플레이어의 위쪽에 나무 판자가 있으면 왼쪽으로 튀거나 오른쪽으로 튀어야 함. 
            if (currRow - 1  < 1|| Board.mainBoard[currRow - 1][currCol] == 'ㅡ') {
                
                //board.checkBoundary(currRow - 1);
                System.out.println("상대 플레이어의 위쪽에 나무 판자가 있거나 보드판의 바운더리를 넘습니다.");

                if (encounter.woodInBothSideLeftRight(currRow, currCol)) {
                    // 만약 플레이어 양쪽에 나무 판자가 있다면, 처음부터 다시 입력을 받아야 함. 
                    return true; 
                }   
                System.out.println("상대를 뛰어 넘어 왼쪽으로 이동하시겠습니까 (a), 오른쪽으로 이동하시겠습니까 (d)");
                check = encounter.playerOnTheUpDownSide(player, currRow, currCol);

                // check 가 true 면은 나무 판자를 적절한 위치에 둔 것에 성공.
                if (check) {
                    return false; 
                }
                return true; 
                
            }
            // 상대 플레이어의 왼쪽이나 오른쪽으로 튀어야 함. 
            currRow -= 2; 
        } 
        // 해당 방향에 플레이가 없다면, 
        player.setRowPos(currRow);   
        return false; 
    }

    private boolean moveToLeft(Player player, int currRow, int currCol) {
        Board board = new Board(); 
        boolean check = encounter.checkWood(currRow, currCol-1);
        if (check) return true; 
        currCol -= 2;
        board.checkBoundary(currCol);
        check = encounter.checkPlayer(player, currRow, currCol);  // 나무 판자가 없다면 이번에는 해당 방향에 플레이어가 있나 확인.
        if (check) {  // 해당 방향에 플레이어 있으면, 건너 뛰어야 함. 

            if (currCol - 1 < 1 || Board.mainBoard[currRow][currCol - 1] == '|') {
                //board.checkBoundary(currCol - 1);
                System.out.println("상대 플레이어의 왼쪽에 나무 판자가 있거나 보드판의 바운더리를 넘습니다.");

                if (encounter.woodInBothSideUpDown(currRow, currCol)) {
                    return true;
                }

                System.out.println("상대 플레이어의 왼쪽에 나무 판자가 있습니다. 상대를 뛰어 넘어 위쪽으로 이동하시겠습니까 (w), 아래쪽으로 이동하시겠습니까 (x)");
                check = encounter.playerOnTheLeftRightSide(player, currRow, currCol); 

                if (check) {
                    return false; 
                }
                return true;
            }

            currCol -= 2;
        }
        player.setColPos(currCol);
        return false; 
    }

    private boolean moveToDown(Player player, int currRow, int currCol) {
        Board board = new Board(); 
        boolean check = encounter.checkWood(currRow+1, currCol);
        if (check) return true;  // 바로 주위에 나무 판자가 있음.
        currRow += 2;
        board.checkBoundary(currRow);
        check = encounter.checkPlayer(player, currRow, currCol); // 나무 판자가 없다면 이번에는 해당 방향에 플레이어가 있나 확인.
        if (check) {  // 해당 방향에 플레이어 있으면, 건너 뛰어야 함. 

            if (currRow + 1 > 17 || Board.mainBoard[currRow + 1][currCol] == 'ㅡ') {

                //board.checkBoundary(currRow + 1);
                System.out.println("상대 플레이어의 아래쪽에 나무 판자가 있거나 보드판의 바운더리를 넘습니다.");

                if (encounter.woodInBothSideLeftRight(currRow, currCol)) {
                    return true;
                }

                System.out.println("상대를 뛰어 넘어 왼쪽으로 이동하시겠습니까 (a), 오른쪽으로 이동하시겠습니까 (d)");
                check = encounter.playerOnTheUpDownSide(player, currRow, currCol);
 
                if (check) {
                    return false; 
                }
                return true;
            }

            currRow += 2;
        }

        player.setRowPos(currRow);
        return false; 
    }

    private boolean moveToRight(Player player, int currRow, int currCol) {
        Board board = new Board();
        boolean check = encounter.checkWood(currRow, currCol+1);
        if (check) return true;  // 바로 주위에 나무 판자가 있음.
        currCol += 2;
        board.checkBoundary(currCol);
        check = encounter.checkPlayer(player, currRow, currCol);
        if (check) {  // 해당 방향에 플레이어 있으면, 건너 뛰어야 함.

            if (currCol + 1 > 17 || Board.mainBoard[currRow][currCol + 1] == '|') {

                //board.checkBoundary(currCol + 1);
                System.out.println("상대 플레이어의 오른쪽에 나무 판자가 있거나 보드판의 바운더리를 넘습니다.");

                if (encounter.woodInBothSideLeftRight(currRow, currCol)) {
                    return true;
                }
                System.out.println("상대 플레이어의 오른쪽에 나무 판자가 있습니다. 상대를 뛰어 넘어 위쪽으로 이동하시겠습니까 (w), 아래쪽으로 이동하시겠습니까 (x)");
                check = encounter.playerOnTheLeftRightSide(player, currRow, currCol);

                if (check) {
                    return false;
                }
                return true;
            }

            currCol += 2; 
        }   
        player.setColPos(currCol);
        return false; 
    }
}
