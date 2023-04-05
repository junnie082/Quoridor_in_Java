import players.*;

import board.*;

<<<<<<< HEAD
public class Main {  

    // Exception
    // 1. 나무 판자를 둘 때, 이미 그 곳에 나무 판자가 있을 때.   =====> 예외처리를 어느 정도 해주었음. 
    // 2. 나무 판자가 플레이어의 경로를 모두 막을 때. ===> BFS 알고리즘을 사용할 것. 
    // 3. 입력을 취소하고 싶을 때. 
    // 4. 나무 판자를 모두 사용하면 해당 플레이어는 더이상 나무 판자를 보드판에 둘 수 없음. 
    // 5. 값이 아닌 엔터를 입력으로 받았을 때의 예외를 처리해 주어야 함. 
    public static void main(String[] args) { 
=======
public class Main {
    public static void main(String[] args) {
>>>>>>> d0d6d37 ([FEAT] 2023.3.30. 쿼리도 프로젝트 시작. Main, Board, Wood, Move, Player 클래스 구현)

        Player p1 = new Player();
        p1.setPlayerNum(1);
        p1.setRowPos(1);
        p1.setColPos(9);

        Player p2 = new Player();
        p2.setPlayerNum(2);
        p2.setRowPos(17);
        p2.setColPos(9);

        Board board = new Board();
<<<<<<< HEAD
        board.initialize();
        board.printMainBoard();
        while (true) {
            p1.turn(p1, p2);
            board.printMainBoard(); 
            System.out.print("p1 의 좌표: " + p1.getRowPos() + " " + p1.getColPos());
            System.out.println("partition num: " + p1.getPartitionNum());
            if (p1.wins()) break;

            p2.turn(p1, p2); 
            board.printMainBoard(); 
            System.out.print("p2 의 좌표: " + p2.getRowPos() + " " + p2.getColPos());
            System.out.println("partition num: " +p2.getPartitionNum());
            if (p2.wins()) break;
=======

        while (p2.getRowPos() != 1 && p1.getRowPos() != 17) {
            p1.turn();
            board.printMainBoard();
            p2.turn(); 
            board.printMainBoard();
>>>>>>> d0d6d37 ([FEAT] 2023.3.30. 쿼리도 프로젝트 시작. Main, Board, Wood, Move, Player 클래스 구현)
        }
    }
}