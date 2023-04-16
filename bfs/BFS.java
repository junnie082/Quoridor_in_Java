package bfs;

import java.util.*;

import players.Player;
import board.*;
import moves.*;

public class BFS {

    private static int[][] visited = new int[18][18];

    private static void visitedToZero() {
        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= 17; visited[i][j++] = 0);
        }
    }

    public static void printVisited() {
        // for (int i = 1; i <= 17; i++) {
        //     for (int j = 1; j <= 17; j++) System.out.print(visited[i][j] + " "); 
        //     System.out.println(); 
        // }
        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= 17; j++) {
                if (visited[i][j] == 1) System.out.print("1 "); 
                else if (Board.mainBoard[i][j] == '|' || Board.mainBoard[i][j] == 'ㅡ') System.out.print("2 ");
                else System.out.print("0 ");
            }
            System.out.println(); 
        }
    }
    
    public boolean isThereAtLeastOneWay(Player player) {
        int currRow = player.getRowPos();
        int currCol = player.getColPos();
        BFS.visitedToZero();

        Queue <Pair> que = new LinkedList<Pair>();
        visited[currRow][currCol] = 1; 
        que.add(new Pair(currRow, currCol)); 

        boolean check; 
        
        while (!que.isEmpty()) {
            currRow = que.peek().x;
            currCol = que.peek().y; 
            
            check = checkArrival(player.getPlayerNum(), currRow, currCol);

            if (check) { // check 가 true 이면 상대편 진영에 도달할 수 있는 경로가 하나 이상있고,
                while (!que.isEmpty()) que.poll(); 
                return false;          // 따라서 isThereAtLeastOneWay 는 false를 반환.
            }

            // up 상
            if (currRow - 1 >= 1) {
                Move.direction = "w";
                check = this.bfsCheckWood(currRow - 1, currCol);  // true 면 이동방향에 나무 판자 있음.   ====> 와.... 하루종일 에러 난 것이 (currRow - 1, currRow) 로 하고 있어서... 
                if (!check && currRow - 2 >= 1) {
                    addPath(que, currRow - 2, currCol); 
                }
            }

            // right 우
            if (currCol + 1 <= 17) {
                Move.direction = "d";
                check = this.bfsCheckWood(currRow, currCol + 1); 
                if (!check && currCol + 2 <= 17) {
                    addPath(que, currRow, currCol + 2);
                }
            }

            // down 하
            if (currRow + 1 <= 17) {
                Move.direction = "x";
                check = this.bfsCheckWood(currRow + 1, currCol);
                if (!check && currRow + 2 <= 17) {
                    addPath(que, currRow + 2, currCol);
                }
            }

            // left 좌
            if (currCol - 1 >= 1) {
                Move.direction = "a";
                check = this.bfsCheckWood(currRow, currCol - 1);
                if (!check && currCol - 2 >= 1) {
                    addPath(que, currRow, currCol - 2);
                }
            } 
            //System.out.println();
            que.poll();  // poll 은 대기열이 비어 있다면 null 반환, remove 는 대기열이 비어 있으면 NoSuchElement 에러 발생. 
        }

        return true;  // 플레이어가 상대편 진영에 도달할 수 있는 가능성이 없음. 
    }

    private boolean bfsCheckWood(int row, int col) {
        char boardVal = Board.mainBoard[row][col];
        if (boardVal == 'ㅡ' || boardVal == '|' || visited[row][col] == 2) {
            visited[row][col] = 2;
            return true;  // true 면 나무 판자가 이동 방향에 있다. 
        }
        return false;
    }

    private boolean checkArrival(int playerNum, int currRow, int currCol) {
        if (playerNum == 1 && currRow == 17) return true;
        else if (playerNum == 2 && currRow == 1) return true;
        
        return false;
    }

    private void addPath(Queue<Pair> que, int currRow, int currCol) {
        
        if (visited[currRow][currCol] == 0 ) {
            visited[currRow][currCol] = 1;
            que.add(new Pair(currRow, currCol));  
        }
        
    }
    
}
