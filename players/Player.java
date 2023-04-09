package players;

import java.util.InputMismatchException;
import java.util.Scanner;

import board.*;
import moves.Move;
import partition.*;
import bfs.*;

public class Player {

    private int playerNum;
    private int rowPos; 
    private int colPos;
    private int numberOfPartitions = 10;

    Scanner input = new Scanner(System.in); 
    Board board = new Board();
    Move move = new Move(); 
    Wood wood = new Wood(); 

    public int getPartitionNum() {
        return this.numberOfPartitions;
    }

    public boolean wins() {
        if (playerNum == 1 && rowPos == 17) {
            System.out.println("************************");
            System.out.println("**  Player 1 WINS!!!  **");
            System.out.println("************************");
            return true;
        }
        else if (playerNum == 2 && rowPos == 1) {
            System.out.println("************************");
            System.out.println("**  Player 2 WINS!!!  **");
            System.out.println("************************");

            return true; 
        }
        return false; 
    }

    public void turn(Player p1, Player p2) {
        Player p = new Player(); 
        boolean check = true;

        printPlayerInfo();

        while (check) {
            System.out.println("Player" + playerNum + ", 말을 움직이려면 1, 판을 놓으려면 0을 입력하세요.");
            int action = inputAction();

            if (action == 1) { // 말을 움직이려 할 때 
                p.movePlayer(this);
                check = false;
            } else { // 판을 놓으려 할 때
                if (this.numberOfPartitions == 0) {
                    thereIsNoPartition();
                    continue;
                }
                wood.putWood(p1, p2);
                this.numberOfPartitions--;  // 나무 판자 개수 하나 줄어듦.
                check = false; 

                BFS.printVisited();
            }
        }
    }

    private void printPlayerInfo() {
        System.out.println("Player" + playerNum + " 의 차례");
        System.out.println("현재 위치는 (" + rowPos + ", " + colPos + ") 이며");
        System.out.println("남은 나무 판자의 개수는 " + numberOfPartitions + " 입니다. ");
    }

    private void thereIsNoPartition() {
        System.out.println("남은 나무 판자가 없습니다. 플레이어 " + playerNum + "의 남은 나무 판자의 개수: " + numberOfPartitions);
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
