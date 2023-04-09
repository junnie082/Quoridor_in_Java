import players.*;

import board.*;

public class Main {  

    // Exception
    // 1. 나무 판자를 둘 때, 이미 그 곳에 나무 판자가 있을 때.   =====> 예외처리를 어느 정도 해주었음. 
    // 2. 나무 판자가 플레이어의 경로를 모두 막을 때. ===> BFS 알고리즘을 사용할 것. 
    // 3. 입력을 취소하고 싶을 때. 
    // 4. 나무 판자를 모두 사용하면 해당 플레이어는 더이상 나무 판자를 보드판에 둘 수 없음. 
    // 5. 값이 아닌 엔터를 입력으로 받았을 때의 예외를 처리해 주어야 함. (col)  ===> 해결.
    // 6. 각 플레이어의 차례마다 본인의 시점으로 보드판 위치를 바꾸기. 상대 진영이 항상 위에 오도록.
    // 7. row col 입력 받을 때 입력 수가 range 를 넘을 때. (예) col 값으로 56을 입력 받았을 때.) 예외처리
    // 8. 나무 판자가 보드판의 두 칸을 잡아 먹는 것이 아니라, 한 칸만 잡아 먹을 때가 있는 것 같다. 
    // 9. 남은 나무 판자가 0일 때, '이동하기' 를 선택하면 무한루프에 갇혀버림. 
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