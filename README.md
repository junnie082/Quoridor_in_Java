# Quoridor_in_Java

## Why?

I love the strategy & logic game, Quoridor.   
As a student studying programming, I decided to develop console-based logic Quoridor game in Java for fun. 

## Rules

[Gigamic](https://en.gigamic.com/modern-classics/107-quoridor.html)  
[Wikipedia](https://en.wikipedia.org/wiki/Quoridor)

1. There are two players on the board.
2. The board is 9 x 9.
3. You can put planks on the board to block the opponent or to make a path for your own.
4. Each player has 10 planks.
5. Player wins if reaches the other side of the board.
6. There should be at least one way for each player to reach the goal.
7. If two players encounter at some point, one player can jump over the other one.

## Game 

1. Player 1 and Player 2 are on the board.


<img width="550" alt="스크린샷 2024-06-18 오전 1 40 21" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/a928e288-b2bf-4e02-b71a-50b98515c991">

------------

2. You can choose whether to move your pawn or to put a plank. (input 0 to put a plank, 1 to move)

<img width="550" alt="스크린샷 2024-06-18 오전 1 41 01" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/cb9a8623-f495-4a22-adea-a6584c3db56a">

------------

3. If you are to put a plank on the board, you first need to insert the information about the plank.
   - coordinate of the plank
   - the direction of it (whether if it's horizontally or vertically put in on the board)
   - It automatically knows if the direction of the plank
   - If you're inserting wrong information, you have to put the information of the plank again. 

<img width="550" alt="스크린샷 2024-06-18 오전 1 41 37" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/2a78d1f5-a467-4f85-a665-04738bc45c7a">

<img width="550" alt="스크린샷 2024-06-18 오전 1 41 57" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/87f5e6f6-18b8-43fc-a12e-7114a13cbd85">

-----------

4. If you are trying to move your pawn to the direction where the plank already exists, you have to take your turn again (You can't move your pawn to where the plank already exists).


<img width="550" alt="스크린샷 2024-06-18 오전 1 43 21" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/c5f70830-32be-4502-a927-f58fb53bde2f">

<img width="550" alt="스크린샷 2024-06-18 오전 1 44 03" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/9883f095-d3c1-4ae3-9f49-1bfc1ddd14ce">

-----------

5. If you encounter the opponent, you can jump over it.

<img width="550" alt="스크린샷 2024-06-18 오전 1 44 58" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/9c9b230d-5098-4b20-a04d-6e144b419380">

<img width="550" alt="스크린샷 2024-06-18 오전 1 45 19" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/47aade54-881d-4643-8f7e-122ede91c361">


------------

6. If the player reaches the other side of the board, he wins.

<img width="550" alt="스크린샷 2024-06-18 오전 1 45 49" src="https://github.com/junnie082/Quoridor_in_Java/assets/88719152/b8d80c18-a41b-4fbc-a5cf-fdd5dc5d9eab">



## Algorithms

One player should have at least one way to get to the goal. 
To check this out every time each player tries to put a plank on the board, I used BFS (Breadth First Search) algorithm and checked whether there is a path or not. 

```Java
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

```

## Development Info

Period: 2023.03. a week. 

Developer: Hyojeong Jun. 



