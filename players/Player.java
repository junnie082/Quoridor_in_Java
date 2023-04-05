package players;

<<<<<<< HEAD
import java.util.InputMismatchException;
import java.util.Scanner;

import board.*;
import moves.Move;
import partition.*;
=======
import java.util.Scanner;

import board.*;
import players.*;
>>>>>>> d0d6d37 ([FEAT] 2023.3.30. 쿼리도 프로젝트 시작. Main, Board, Wood, Move, Player 클래스 구현)

public class Player {

    private int playerNum;
    private int rowPos; 
    private int colPos;
<<<<<<< HEAD
    private int numberOfPartitions = 10;
=======
    private static int numberOfPartitions = 10;
>>>>>>> d0d6d37 ([FEAT] 2023.3.30. 쿼리도 프로젝트 시작. Main, Board, Wood, Move, Player 클래스 구현)

    Scanner input = new Scanner(System.in); 
    Board board = new Board();
    Move move = new Move(); 
<<<<<<< HEAD
    Wood wood = new Wood(); 

    public int getPartitionNum() {
        return this.numberOfPartitions;
    }

    public boolean wins() {
        if (playerNum == 1 && rowPos == 17) {
            System.out.println("Player 1 WINS!!!");
            return true;
        }
        else if (playerNum == 2 && rowPos == 1) {
            System.out.println("Player 2 WINS!!!");
            return true; 
        }
        return false; 
    }

    public void turn(Player p1, Player p2) {
        Player p = new Player(); 
        System.out.println("Player" + playerNum + ", 말을 움직이려면 1, 판을 놓으려면 0을 입력하세요.");
        int action = inputAction();

        if (action == 1) { // 말을 움직이려 할 때 
            p.movePlayer(this);
        } else { // 판을 놓으려 할 때
            wood.putWood(p1, p2);
            this.numberOfPartitions--;  // 나무 판자 개수 하나 줄어듦. 
        }
    }

    private void movePlayer(Player player) {
        Board.mainBoard[player.rowPos][player.colPos] = '0';
        move.selectDirection(player);
        board.setPos(player);
    }

    private int inputAction() {
        int action; 
        while (true) {
            try {
                InputMismatchException e = new InputMismatchException();
                action = input.nextInt(); 
                if (action != 0 && action != 1) throw e;
                break;
            } catch (InputMismatchException e) {
                System.out.println("오직 1이나 0만으로 다시 입력하세요: ");
                input.nextLine(); 
            }
        }
        return action; 
=======

    public void turn() {
        System.out.println("Player" + playerNum + ", 말을 움직이려면 1, 판을 놓으려면 0을 입력하세요.");
        int action = input.nextInt(); 

        if (action == 1) {
            board.getMainBoard()[this.rowPos][this.colPos] = 0;
            move.selectDirection(this);
            board.setPos(this);
        } else {
            
        }
        
>>>>>>> d0d6d37 ([FEAT] 2023.3.30. 쿼리도 프로젝트 시작. Main, Board, Wood, Move, Player 클래스 구현)
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum; 
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public int getRowPos() {
        return this.rowPos;
    }
    public int getColPos() {
        return this.colPos;
    }
}
