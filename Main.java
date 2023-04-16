import players.*;

import board.*;

public class Main {  

    // Exception
    // 플레이어 끼리 만나서 해당 플레이어가 상대 플레이어를 건너 뛰어야할 때, 그곳에 이미 나무 판자가 있을 경우. 

    public static void main(String[] args) { 

        Player p1 = new Player();
        p1.setPlayerNum(1);
        p1.setRowPos(1);
        p1.setColPos(9);

        Player p2 = new Player();
        p2.setPlayerNum(2);
        p2.setRowPos(17);
        p2.setColPos(9);

        Board board = new Board();
        board.initialize();
        board.printMainBoard();
        while (true) {
            p1.turn(p1, p2);
            board.printMainBoard(); 
            if (p1.wins()) break;

            p2.turn(p1, p2); 
            board.printMainBoard(); 
            if (p2.wins()) break;
        }
    }
}